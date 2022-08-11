package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinic.models.ConfigModel;
import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView petsRecycler;
    private Button chatButton;
    private Button callButton;
    private TextView hoursText;
    private PetsRecyclerViewAdapter petsAdapter;
    private CustomDialog dialog;
    private CustomWebViewScreen customWebViewLayout;
    private LinearLayout buttonsLayout;
    private final MainPresenter mainPresenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter.bind(this);

        petsRecycler = findViewById(R.id.petsRecycler);
        chatButton = findViewById(R.id.chatButton);
        callButton = findViewById(R.id.callButton);
        hoursText = findViewById(R.id.hoursText);
        customWebViewLayout = findViewById(R.id.customWebViewLayout);
        buttonsLayout = findViewById(R.id.buttonsLayout);

        callButton.setOnClickListener(view -> showDialog());
        chatButton.setOnClickListener(view -> showDialog());

        mainPresenter.getAllPets();
        mainPresenter.getConfig();

        initPetsRecycler();
    }

    void showDialog() {
        dialog.show();
    }

    @Override
    public void showError() {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "The server error occurred", Toast.LENGTH_LONG).show());
    }

    @Override
    public void petsObtained(ArrayList<PetsModel> pets) {
        runOnUiThread(() -> petsAdapter.setPets(pets));
    }

    @Override
    public void hideChatButton() {
        runOnUiThread(() -> chatButton.setVisibility(View.GONE));
    }

    @Override
    public void hideCallButton() {
        runOnUiThread(() -> callButton.setVisibility(View.GONE));
    }

    @Override
    public void hideButtonsLayout() {
        runOnUiThread(() -> buttonsLayout.setVisibility(View.GONE));
    }

    @Override
    public void initHoursText(ConfigModel configModel) {
        runOnUiThread(() -> {
            hoursText.setText(configModel.getWorkHours());
            dialog = new CustomDialog(MainActivity.this, configModel.getWorkHours());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.unbind();
    }

    public void initPetsRecycler() {
        petsAdapter = new PetsRecyclerViewAdapter();
        petsRecycler.setLayoutManager(new LinearLayoutManager(this));
        petsRecycler.setAdapter(petsAdapter);
        petsAdapter.listener = contentUrl -> customWebViewLayout.initView(contentUrl);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}