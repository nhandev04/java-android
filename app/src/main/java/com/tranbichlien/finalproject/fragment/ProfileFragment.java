package com.tranbichlien.finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.tranbichlien.finalproject.R;

import com.tranbichlien.finalproject.activity.AddressActivity;
import com.tranbichlien.finalproject.activity.CouponsActivity;
import com.tranbichlien.finalproject.activity.OrdersActivity;
import com.tranbichlien.finalproject.activity.PaymentMethodActivity;
import com.tranbichlien.finalproject.activity.ProfileConfigActivity;
import com.tranbichlien.finalproject.activity.SettingsActivity;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileEmail;
    private ImageView profileImage, searchButton;
    private View profileInfoSection;
    private CardView ordersCard, addressCard, paymentCard, couponsCard, settingsCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate layout file fragment_profile.xml
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        initViews(view);

        // Set sample data for profile
        profileName.setText("Trần Bích Liên");
        profileEmail.setText("bichlienne@gmail.com");

        // Set click listeners for all sections
        setupClickListeners();
    }

    private void initViews(View view) {
        // Profile info section
        profileName = view.findViewById(R.id.profileName_profileFrag);
        profileEmail = view.findViewById(R.id.profileEmail_profileFrag);
        profileImage = view.findViewById(R.id.profileImage_profileFrag);
        searchButton = view.findViewById(R.id.searchIv_ProfileFrag);
        profileInfoSection = view.findViewById(R.id.linearLayout3);

        // Feature cards
        // Note: The orders card doesn't have an ID in the layout, so we'll find its
        // parent and use child indexing
        View ordersCardView = ((ViewGroup) view.findViewById(R.id.linearLayout4)).getChildAt(0);
        ordersCard = (CardView) ordersCardView;

        addressCard = view.findViewById(R.id.shippingAddressCard_ProfilePage);

        // Payment card doesn't have an ID, using child indexing
        View paymentCardView = ((ViewGroup) view.findViewById(R.id.linearLayout4)).getChildAt(2);
        paymentCard = (CardView) paymentCardView;

        // Coupons card doesn't have an ID, using child indexing
        View couponsCardView = ((ViewGroup) view.findViewById(R.id.linearLayout4)).getChildAt(3);
        couponsCard = (CardView) couponsCardView;

        settingsCard = view.findViewById(R.id.settingCd_profileFrag);
    }

    private void setupClickListeners() {
        // Search button click listener
        searchButton.setOnClickListener(v -> {
            // Show toast message for search functionality
            showToast("Search functionality");
        });

        // Profile info section click listener
        profileInfoSection.setOnClickListener(v -> {
            // Navigate to profile configuration screen
            Intent intent = new Intent(getActivity(), ProfileConfigActivity.class);
            startActivity(intent);
        });

        // Orders card click listener
        ordersCard.setOnClickListener(v -> {
            // Navigate to orders screen
            Intent intent = new Intent(getActivity(), OrdersActivity.class);
            startActivity(intent);
        });

        // Address card click listener
        addressCard.setOnClickListener(v -> {
            // Navigate to address screen
            Intent intent = new Intent(getActivity(), AddressActivity.class);
            startActivity(intent);
        });

        // Payment card click listener
        paymentCard.setOnClickListener(v -> {
            // Navigate to payment method screen
            Intent intent = new Intent(getActivity(), PaymentMethodActivity.class);
            startActivity(intent);
        });

        // Coupons card click listener
        couponsCard.setOnClickListener(v -> {
            // Navigate to coupons screen
            Intent intent = new Intent(getActivity(), CouponsActivity.class);
            startActivity(intent);
        });

        // Settings card click listener
        settingsCard.setOnClickListener(v -> {
            // Navigate to settings screen
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Helper method to show toast messages
     * 
     * @param message The message to display
     */
    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
