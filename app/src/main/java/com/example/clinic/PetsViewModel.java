package com.example.clinic;

import androidx.lifecycle.MutableLiveData;

import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public class PetsViewModel {
    MutableLiveData<PetsState> state = new MutableLiveData<>();
    public void getAllPets() {

        new Requests().getAllPets(new Requests.PetsResponse() {
            @Override
            public void onSuccess(ArrayList<PetsModel> pets) {
                state.postValue(new PetsState.Success(pets));
            }

            @Override
            public void onError() {
                state.postValue(new PetsState.Error());
            }
        });
    }
}
