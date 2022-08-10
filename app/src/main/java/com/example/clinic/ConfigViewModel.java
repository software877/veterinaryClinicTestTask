package com.example.clinic;

import androidx.lifecycle.MutableLiveData;

public class ConfigViewModel {
    MutableLiveData<ConfigState> state = new MutableLiveData<>();
    void getConfig() {
        new Requests().getConfig(configModel -> state.postValue(new ConfigState.Success(configModel)));
    }

}
