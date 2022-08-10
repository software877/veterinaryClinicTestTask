package com.example.clinic;

import androidx.annotation.NonNull;

import com.example.clinic.models.ConfigModel;
import com.example.clinic.models.PetsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Requests {

    private final String baseUrl = "https://software877.github.io";
    private final String configUrl = baseUrl + "/api/config.json";
    private final String petsUrl = baseUrl + "/api/pets.json";

    interface PetsResponse {
        void onSuccess(ArrayList<PetsModel> pets);
        void onError();
    }

    interface ConfigResponse {
        void onSuccess(ConfigModel configModel);
        void onError();
    }


    public void getAllPets(PetsResponse callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(petsUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body = response.body().string();
                ArrayList<PetsModel> pets = new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(body);
                    JSONArray petsArray = responseObject.getJSONArray("pets");
                    for (int i = 0; i < petsArray.length() - 1; i++) {
                        JSONObject pet = petsArray.getJSONObject(i);
                        String imageUrl = pet.getString("image_url");
                        String title = pet.getString("title");
                        String contentUrl = pet.getString("content_url");
                        String dateAdded = pet.getString("date_added");
                        pets.add(new PetsModel(imageUrl, title, contentUrl, dateAdded));
                    }
                    callback.onSuccess(pets);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void getConfig(ConfigResponse callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(configUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONObject responseObject = new JSONObject(body);
                    JSONObject configObject = responseObject.getJSONObject("settings");
                    Boolean isChatEnabled = configObject.getBoolean("isChatEnabled");
                    Boolean isCallEnabled = configObject.getBoolean("isChatEnabled");
                    String workHours = configObject.getString("workHours");
                    callback.onSuccess(new ConfigModel(isChatEnabled, isCallEnabled, workHours));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
