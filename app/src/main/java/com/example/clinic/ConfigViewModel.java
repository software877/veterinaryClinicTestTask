package com.example.clinic;

import androidx.lifecycle.MutableLiveData;

import com.example.clinic.models.ConfigModel;

public class ConfigViewModel {
    MutableLiveData<ConfigState> state = new MutableLiveData<>();
    void getConfig() {
        new Requests().getConfig(new Requests.ConfigResponse() {
            @Override
            public void onSuccess(ConfigModel configModel) {
                state.postValue(new ConfigState.Success(configModel));
            }

            @Override
            public void onError() {
                state.postValue(new ConfigState.Error());
            }
        });
    }

}
