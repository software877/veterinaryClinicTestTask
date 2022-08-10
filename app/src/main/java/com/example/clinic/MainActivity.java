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

public class MainActivity extends AppCompatActivity {

    PetsViewModel petsViewModel = new PetsViewModel();
    ConfigViewModel configViewModel = new ConfigViewModel();
    private RecyclerView petsRecycler;
    private Button chatButton;
    private Button callButton;
    private TextView hoursText;
    private PetsRecyclerViewAdapter petsAdapter;
    private CustomDialog dialog;
    private CustomWebViewScreen customWebViewLayout;
    private LinearLayout buttonsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petsRecycler = findViewById(R.id.petsRecycler);
        chatButton = findViewById(R.id.chatButton);
        callButton = findViewById(R.id.callButton);
        hoursText = findViewById(R.id.hoursText);
        customWebViewLayout = findViewById(R.id.customWebViewLayout);
        buttonsLayout = findViewById(R.id.buttonsLayout);

        callButton.setOnClickListener(view -> showDialog());

        chatButton.setOnClickListener(view -> showDialog());

        petsViewModel.state.observe(this, petsState -> petsState.handle(new HandleResult() {
            @Override
            public void onSuccess(ArrayList<PetsModel> pets) {
                petsAdapter.setPets(pets);
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(), "The server error occurred", Toast.LENGTH_LONG).show();

            }
        }));

        configViewModel.state.observe(this, configState -> configState.handle(new HandleConfigResult() {
            @Override
            public void success(ConfigModel configModel) {
                initChatButton(configModel);
                initCallButton(configModel);
                initButtonsLayout(configModel);
                initHoursText(configModel);
                dialog = new CustomDialog(MainActivity.this, hoursText.getText().toString());
            }

            @Override
            public void error() {
                Toast.makeText(getApplicationContext(), "The server error occurred", Toast.LENGTH_LONG).show();
            }
        }));

        initPetsRecycler();
        petsViewModel.getAllPets();
        configViewModel.getConfig();
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

    public void initHoursText(ConfigModel configModel) {
        hoursText.setText(configModel.getWorkHours());
    }

    public void initPetsRecycler() {
        petsAdapter = new PetsRecyclerViewAdapter();
        petsRecycler.setLayoutManager(new LinearLayoutManager(this));
        petsRecycler.setAdapter(petsAdapter);
        petsAdapter.listener = contentUrl -> customWebViewLayout.initView(contentUrl);
    }
}