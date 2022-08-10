package com.example.clinic;

import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public abstract class PetsState {

    abstract void handle(HandleResult handleResult);

    static class Success extends PetsState{
        ArrayList<PetsModel> pets;
        Success(ArrayList<PetsModel> pets) {
            this.pets = pets;
        }
        @Override
        void handle(HandleResult handleResult) {
            handleResult.onSuccess(pets);
        }
    };
    static class Error extends PetsState {
        @Override
        void handle(HandleResult handleResult) {
            handleResult.onError();
        }
    };
}

interface HandleResult {
    void onSuccess(ArrayList<PetsModel> pets);
    void onError();
}