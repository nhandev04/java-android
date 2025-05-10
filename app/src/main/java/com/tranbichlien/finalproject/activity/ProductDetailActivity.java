package com.tranbichlien.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    private static final String PHONE_NUMBER = "tel:0123456789";
    private static final String FACEBOOK_APP_URL = "fb://page/your_page_id";
    private static final String FACEBOOK_WEB_URL = "https://www.facebook.com";
    private static final String DEFAULT_MAP_COORDINATES = "10.7769,106.7009";
    private static final String GOOGLE_MAPS_PACKAGE = "com.google.android.apps.maps";

    public static final String EXTRA_PRODUCT_NAME = "product_name";
    public static final String EXTRA_PRODUCT_BRAND = "product_brand";
    public static final String EXTRA_PRODUCT_PRICE = "product_price";
    public static final String EXTRA_PRODUCT_RATING = "product_rating";
    public static final String EXTRA_PRODUCT_IMAGE = "product_image";
    public static final String EXTRA_PRODUCT_IMAGE_URL = "product_image_url";

    private ImageView productImage;
    private TextView productName, productBrand, productPrice;
    private RatingBar productRating;
    private Button addToCartButton;
    private ImageButton callButton, facebookButton, mapButton, shareButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        initViews();
        processIntentData(getIntent());
        setupClickListeners();
    }

    private void processIntentData(Intent intent) {
        if (intent == null) return;

        String name = intent.getStringExtra(EXTRA_PRODUCT_NAME);
        String brand = intent.getStringExtra(EXTRA_PRODUCT_BRAND);
        String price = intent.getStringExtra(EXTRA_PRODUCT_PRICE);
        float rating = intent.getFloatExtra(EXTRA_PRODUCT_RATING, 0.0f);
        String imageUrl = intent.getStringExtra(EXTRA_PRODUCT_IMAGE_URL);
        int imageResId = intent.getIntExtra(EXTRA_PRODUCT_IMAGE, R.drawable.img);

        updateUIWithProductData(name, brand, price, rating, imageUrl, imageResId);
    }

    private void updateUIWithProductData(String name, String brand, String price,
                                       float rating, String imageUrl, int imageResId) {
        productName.setText(name);
        productBrand.setText(brand);
        productPrice.setText(price);
        productRating.setRating(rating);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            com.bumptech.glide.Glide.with(this)
                .load(imageUrl)
                .into(productImage);
        } else {
            productImage.setImageResource(imageResId);
        }
    }

    private void setupClickListeners() {
        backButton.setOnClickListener(v -> finish());
        addToCartButton.setOnClickListener(v -> finish());
        callButton.setOnClickListener(v -> handleCallButton());
        facebookButton.setOnClickListener(v -> handleFacebookButton());
        mapButton.setOnClickListener(v -> handleMapButton());
        shareButton.setOnClickListener(v -> handleShareButton());
    }

    private void handleCallButton() {
        try {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(PHONE_NUMBER)));
        } catch (Exception e) {
            showToast("Could not open dialer");
        }
    }

    private void handleFacebookButton() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_APP_URL)));
        } catch (Exception e) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_WEB_URL)));
            } catch (Exception ex) {
                showToast("Could not open Facebook");
            }
        }
    }

    private void handleMapButton() {
        try {
            String mapUrl = "geo:" + DEFAULT_MAP_COORDINATES + "?q=Shop+Location";
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
            mapIntent.setPackage(GOOGLE_MAPS_PACKAGE);

            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                String mapWebUrl = "https://maps.google.com/?q=" + DEFAULT_MAP_COORDINATES;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapWebUrl)));
            }
        } catch (Exception e) {
            showToast("Could not open maps");
        }
    }

    private void handleShareButton() {
        ProductSharer sharer = new ProductSharer(productName.getText().toString(),
                                               productBrand.getText().toString(),
                                               productPrice.getText().toString());
        try {
            startActivity(sharer.createShareIntent());
        } catch (Exception e) {
            showToast("Could not share product");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productBrand = findViewById(R.id.product_detail_brand);
        productPrice = findViewById(R.id.product_detail_price);
        productRating = findViewById(R.id.product_detail_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        backButton = findViewById(R.id.back_button);
        callButton = findViewById(R.id.call_button);
        facebookButton = findViewById(R.id.facebook_button);
        mapButton = findViewById(R.id.map_button);
        shareButton = findViewById(R.id.share_button);
    }

    public static Intent newIntent(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_NAME, product.getName());
        intent.putExtra(EXTRA_PRODUCT_BRAND, product.getBrand());
        intent.putExtra(EXTRA_PRODUCT_PRICE, product.getPrice());
        intent.putExtra(EXTRA_PRODUCT_RATING, product.getRating());

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            intent.putExtra(EXTRA_PRODUCT_IMAGE_URL, product.getImageUrl());
        } else {
            intent.putExtra(EXTRA_PRODUCT_IMAGE, product.getImageResource());
        }
        return intent;
    }

    private static class ProductSharer {
        private final String name;
        private final String brand;
        private final String price;

        ProductSharer(String name, String brand, String price) {
            this.name = name;
            this.brand = brand;
            this.price = price;
        }

        Intent createShareIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, createShareMessage());
            return Intent.createChooser(shareIntent, "Share Product via");
        }

        private String createShareMessage() {
            return String.format("Check out this product: %s by %s for %s%n%nShared from My Shopping App",
                    name, brand, price);
        }
    }
}