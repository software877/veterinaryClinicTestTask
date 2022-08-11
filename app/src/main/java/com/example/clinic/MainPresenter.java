package com.example.clinic;

import com.example.clinic.base.BasePresenter;
import com.example.clinic.models.ConfigModel;
import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public class MainPresenter extends BasePresenter<MainView> {

    public void getAllPets() {
        new Requests().getAllPets(new Requests.PetsResponse() {
            @Override
            public void onSuccess(ArrayList<PetsModel> pets) {
                if (view != null) {
                    view.petsObtained(pets);
                }
            }

            @Override
            public void onError() {
                if (view != null) {
                    view.showError();
                }
            }
        });
    }

    void getConfig() {
        new Requests().getConfig(new Requests.ConfigResponse() {
            @Override
            public void onSuccess(ConfigModel configModel) {
                if (view != null) {
                    view.configObtained(configModel);
                }
            }

            @Override
            public void onError() {
                if (view != null) {
                    view.showError();
                }
            }
        });
    }

}
