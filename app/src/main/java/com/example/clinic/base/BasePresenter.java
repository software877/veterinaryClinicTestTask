package com.example.clinic.base;

import androidx.annotation.Nullable;

public abstract class BasePresenter<IView> {

    protected @Nullable IView view = null;

    public void bind(IView view) {
        this.view = view;
    }

    public void unbind() {
        this.view = null;
    }

}
