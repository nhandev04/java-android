package com.tavanhoaisung.example16;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileEmail;
    private ImageView profileImage, searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout file fragment_profile.xml
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ánh xạ các view từ layout
        profileName = view.findViewById(R.id.profileName_profileFrag);
        profileEmail = view.findViewById(R.id.profileEmail_profileFrag);
        profileImage = view.findViewById(R.id.profileImage_profileFrag);
        searchButton = view.findViewById(R.id.searchIv_ProfileFrag);

        // Thiết lập dữ liệu mẫu cho profile
        profileName.setText("Tạ Văn Hoài Sung");
        profileEmail.setText("Hoaisung113@gmail.com");

        // Thiết lập sự kiện click cho các nút nếu cần
        searchButton.setOnClickListener(v -> {
            // Xử lý sự kiện khi nhấn nút tìm kiếm
        });
    }
}