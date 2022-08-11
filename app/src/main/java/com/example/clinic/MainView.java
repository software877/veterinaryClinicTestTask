package com.example.clinic;

import com.example.clinic.base.IView;
import com.example.clinic.models.ConfigModel;
import com.example.clinic.models.PetsModel;

import java.util.ArrayList;

public interface MainView extends IView {
    void showError();
    void configObtained(ConfigModel configModel);
    void petsObtained(ArrayList<PetsModel> pets);
}
