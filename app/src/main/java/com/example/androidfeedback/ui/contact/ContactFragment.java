package com.example.androidfeedback.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.gallery.GalleryViewModel;

public class ContactFragment extends Fragment {
    private TextView txtEmail,txtAddress, txtPhone;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();

        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        txtEmail = root.findViewById(R.id.txtContactEmail);
        txtAddress = root.findViewById(R.id.txtContactAddress);
        txtPhone = root.findViewById(R.id.txtContactPhone);




        txtEmail.setText("vuhung1908@gmail.com");
        txtAddress.setText("Thủ Đức Tp Hồ Chí Minh");
        txtPhone.setText("+8487654321");
        return root;
    }
}
