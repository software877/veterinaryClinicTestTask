package com.example.clinic;

import com.example.clinic.models.ConfigModel;

public abstract class ConfigState {

    abstract void handle(HandleConfigResult handleResult);

    static class Success extends ConfigState {
        ConfigModel configModel;
        public Success(ConfigModel configModel) {
            this.configModel = configModel;
        }
        @Override
        void handle(HandleConfigResult handleResult) {
            handleResult.success(configModel);

        }
    }

    static class Error extends ConfigState {
        @Override
        void handle(HandleConfigResult handleResult) {
            handleResult.error();
        }
    }
}

interface HandleConfigResult {
    void success(ConfigModel configModel);
    void error();
}


