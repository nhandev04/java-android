package com.tranbichlien.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FavoriteFragment favoriteFragment;
    BottomNavigationView bottomNavigationView;

    private static final String CHANNEL_ID = "mock_purchase_channel";
    private static final long NOTIFICATION_INTERVAL = 60000; // 60 seconds
    private static final int PERMISSION_REQUEST_CODE = 123;

    private Handler notificationHandler;
    private ArrayList<Product> sampleProducts;
    private Random random;
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create notification channel
        createNotificationChannel();

        // Initialize random generator
        random = new Random();

        // Initialize sample products
        initSampleProducts();

        // Check and request notification permission for Android 13+
        checkNotificationPermission();

        // Setup notification handler
        setupNotificationHandler();

        bottomNavigationView = findViewById(R.id.bottomNavMenu);

        // Mặc định load HomeFragment
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.homeMenu) {
                selectedFragment = new HomeFragment();
            }

            if (itemId == R.id.shopMenu) {
                selectedFragment = new ShopFragment();
            }

            if (itemId == R.id.profileMenu) {
                selectedFragment = new ProfileFragment();
            }
            if (itemId == R.id.bagMenu) {
                selectedFragment = new BagFragment();
            }

            if (itemId == R.id.favMenu) {
                selectedFragment = new FavoriteFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    // Phương thức để load fragment
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Creates a notification channel for Android 8.0 and higher
     */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ (Android 8.0) because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Purchase Notifications";
            String description = "Channel for mock purchase notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Initializes a list of sample products for notifications
     */
    private void initSampleProducts() {
        sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("iPhone 14", "Apple", "$999", 5.0f, "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        sampleProducts.add(new Product("iPhone 13 ProMax", "Apple", "$1999", 5.0f, "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        sampleProducts.add(new Product("Galaxy S22", "Samsung", "$796", 4.0f, "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        sampleProducts.add(new Product("Galaxy S20", "Samsung", "$1572", 4.0f, "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        sampleProducts.add(new Product("iPhone 13", "Apple", "$799", 4.7f, "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
    }

    /**
     * Sets up a handler to trigger notifications at regular intervals
     */
    private void setupNotificationHandler() {
        notificationHandler = new Handler(Looper.getMainLooper());

        // Start the periodic notification task
        notificationHandler.post(new Runnable() {
            @Override
            public void run() {
                showRandomPurchaseNotification();
                // Schedule the next notification
                notificationHandler.postDelayed(this, NOTIFICATION_INTERVAL);
            }
        });
    }

    /**
     * Shows a notification with a random product purchase message
     */
    private void showRandomPurchaseNotification() {
        if (sampleProducts == null || sampleProducts.isEmpty()) {
            return;
        }

        // Get a random product
        Product randomProduct = sampleProducts.get(random.nextInt(sampleProducts.size()));

        // Create notification message
        String notificationMessage = "You have a new voucher about " +
                randomProduct.getName() + " from " + randomProduct.getBrand() +
                " worth " + randomProduct.getPrice() + "!";

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.img) // Using a placeholder icon
                .setContentTitle("New Voucher!")
                .setContentText(notificationMessage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Show the notification with a unique ID
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        try {
            // Increment notification ID for each notification
            notificationId++;
            notificationManager.notify(notificationId, builder.build());
        } catch (SecurityException e) {
            // Handle the case where notification permission is not granted
            e.printStackTrace();
        }
    }

    /**
     * Checks if the app has notification permission and requests it if needed
     */
    private void checkNotificationPermission() {
        // For Android 13 and higher, we need to request the POST_NOTIFICATIONS
        // permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Request the permission
                requestPermissions(new String[] { Manifest.permission.POST_NOTIFICATIONS }, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Check if the permission was granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks to prevent memory leaks
        if (notificationHandler != null) {
            notificationHandler.removeCallbacksAndMessages(null);
        }
    }
}
