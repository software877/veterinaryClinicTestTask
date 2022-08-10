package com.example.clinic;

import androidx.lifecycle.MutableLiveData;
public class PetsViewModel {
    MutableLiveData<PetsState> state = new MutableLiveData<>();
    public void getAllPets() {
        new Requests().getAllPets(pets -> state.postValue(new PetsState.Success(pets)));
    }
}
