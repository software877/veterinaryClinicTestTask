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

    public void initCallButton(ConfigModel configModel) {
        if (!configModel.getCallEnabled()) {
            callButton.setVisibility(View.GONE);
        }
    }

    public void initChatButton(ConfigModel configModel) {
        if (!configModel.getChatEnabled()) {
            chatButton.setVisibility(View.GONE);
        }
    }

    public void initButtonsLayout(ConfigModel configModel) {
        if (!configModel.getChatEnabled() && !configModel.getCallEnabled()) {
            buttonsLayout.setVisibility(View.GONE);
        }
    }

    void showDialog() {
        dialog.show();
    }

    @Override
    public void showError() {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "The server error occurred", Toast.LENGTH_LONG).show());
    }

    @Override
    public void configObtained(ConfigModel configModel) {
        runOnUiThread(() -> {
            initChatButton(configModel);
            initCallButton(configModel);
            initButtonsLayout(configModel);
            initHoursText(configModel);
            dialog = new CustomDialog(MainActivity.this, hoursText.getText().toString());
        });
    }

    @Override
    public void petsObtained(ArrayList<PetsModel> pets) {
        petsAdapter.setPets(pets);
    }

    public void initHoursText(ConfigModel configModel) {
        hoursText.setText(configModel.getWorkHours());
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