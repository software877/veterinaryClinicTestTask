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
                    view.initHoursText(configModel);
                }
                setChatButtonVisibility(configModel);
                setCallButtonVisibility(configModel);
                setButtonsLayoutVisibility(configModel);
            }

            @Override
            public void onError() {
                if (view != null) {
                    view.showError();
                }
            }
        });
    }

    void setChatButtonVisibility(ConfigModel configModel) {
        if (hideChatButton(configModel)) {
            if (view != null) {
                view.hideChatButton();
            }
        }
    }

    public boolean hideButtonsLayout(ConfigModel configModel) {
        return !configModel.getChatEnabled() && !configModel.getCallEnabled();
    }

    void setButtonsLayoutVisibility(ConfigModel configModel) {
        if (hideButtonsLayout(configModel)) {
            if (view != null) {
                view.hideButtonsLayout();
            }
        }
    }

    public boolean hideChatButton(ConfigModel configModel) {
        return !configModel.getChatEnabled();
    }

    void setCallButtonVisibility(ConfigModel configModel) {
        if (!hideCallButton(configModel)) {
            if (view != null) {
                view.hideCallButton();
            }
        }
    }

    public boolean hideCallButton(ConfigModel configModel) {
        return !configModel.getCallEnabled();
    }

}
