package com.tranbichlien.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productBrand, productPrice;
    private RatingBar productRating;
    private Button addToCartButton;
    private ImageButton callButton, facebookButton, mapButton, shareButton;
    private ImageView backButton;

    // Constants for intent extras
    public static final String EXTRA_PRODUCT_NAME = "product_name";
    public static final String EXTRA_PRODUCT_BRAND = "product_brand";
    public static final String EXTRA_PRODUCT_PRICE = "product_price";
    public static final String EXTRA_PRODUCT_RATING = "product_rating";
    public static final String EXTRA_PRODUCT_IMAGE = "product_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Initialize views
        initViews();

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra(EXTRA_PRODUCT_NAME);
            String brand = intent.getStringExtra(EXTRA_PRODUCT_BRAND);
            String price = intent.getStringExtra(EXTRA_PRODUCT_PRICE);
            float rating = intent.getFloatExtra(EXTRA_PRODUCT_RATING, 0.0f);
            int imageResId = intent.getIntExtra(EXTRA_PRODUCT_IMAGE, R.drawable.img); // Default image

            // Set data to views
            productName.setText(name);
            productBrand.setText(brand);
            productPrice.setText(price);
            productRating.setRating(rating);
            productImage.setImageResource(imageResId);
        }

        // Set click listeners
        backButton.setOnClickListener(v -> {
            finish();
        });

        addToCartButton.setOnClickListener(v -> {
            // Add to cart functionality would go here
            // For now, just finish the activity
            finish();
        });

        // Set click listener for Call button
        callButton.setOnClickListener(v -> {
            try {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:0123456789"));
                startActivity(dialIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Could not open dialer", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Facebook button
        facebookButton.setOnClickListener(v -> {
            try {
                // Try to open Facebook app
                String facebookUrl = "fb://page/your_page_id"; // Replace with actual page ID if available
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            } catch (Exception e) {
                // If Facebook app is not installed, open in browser
                try {
                    String facebookWebUrl = "https://www.facebook.com"; // Replace with actual page URL if available
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookWebUrl));
                    startActivity(webIntent);
                } catch (Exception ex) {
                    Toast.makeText(this, "Could not open Facebook", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for Map button
        mapButton.setOnClickListener(v -> {
            try {
                // Default coordinates for demonstration (can be replaced with actual shop
                // location)
                String mapUrl = "geo:10.7769,106.7009?q=Shop+Location"; // Example coordinates for Ho Chi Minh City
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                mapIntent.setPackage("com.google.android.apps.maps"); // Specify Google Maps app
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // If Google Maps is not installed, open in browser
                    String mapWebUrl = "https://maps.google.com/?q=10.7769,106.7009"; // Example coordinates
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapWebUrl));
                    startActivity(webIntent);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Could not open maps", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Share button
        shareButton.setOnClickListener(v -> {
            try {
                // Create share intent
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                // Get product details to share
                String name = productName.getText().toString();
                String brand = productBrand.getText().toString();
                String price = productPrice.getText().toString();

                // Create share message
                String shareMessage = "Check out this product: " + name +
                        " by " + brand +
                        " for " + price +
                        "\n\nShared from My Shopping App";

                // Add text to share
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                // Create chooser dialog
                Intent chooser = Intent.createChooser(shareIntent, "Share Product via");

                // Start the activity
                startActivity(chooser);
            } catch (Exception e) {
                Toast.makeText(this, "Could not share product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productBrand = findViewById(R.id.product_detail_brand);
        productPrice = findViewById(R.id.product_detail_price);
        productRating = findViewById(R.id.product_detail_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        backButton = findViewById(R.id.back_button);

        // Initialize tool buttons
        callButton = findViewById(R.id.call_button);
        facebookButton = findViewById(R.id.facebook_button);
        mapButton = findViewById(R.id.map_button);
        shareButton = findViewById(R.id.share_button);
    }

    // Static method to create intent for this activity
    public static Intent newIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_NAME, product.getName());
        intent.putExtra(EXTRA_PRODUCT_BRAND, product.getBrand());
        intent.putExtra(EXTRA_PRODUCT_PRICE, product.getPrice());
        intent.putExtra(EXTRA_PRODUCT_RATING, product.getRating());
        intent.putExtra(EXTRA_PRODUCT_IMAGE, product.getImageResId());
        return intent;
    }
}
