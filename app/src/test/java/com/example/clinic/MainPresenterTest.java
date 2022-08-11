package com.example.clinic;

import static org.junit.Assert.assertEquals;

import com.example.clinic.models.ConfigModel;

import org.junit.Test;

public class MainPresenterTest {

    @Test
    public void buttonsLayoutHiddenWhenBothButtonsDisabled() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(false, false, "");
        Boolean result = mainPresenter.hideButtonsLayout(configModel);
        assertEquals(result, true);
    }

    @Test
    public void buttonsLayoutVisibleWhenBothButtonsEnabled() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(true, true, "");
        Boolean result = mainPresenter.hideButtonsLayout(configModel);
        assertEquals(result, false);
    }

    @Test
    public void buttonsLayoutVisibleWhenChatEnabled() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(true, false, "");
        Boolean result = mainPresenter.hideButtonsLayout(configModel);
        assertEquals(result, false);
    }

    @Test
    public void buttonsLayoutVisibleWhenCallEnabled() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(false, true, "");
        Boolean result = mainPresenter.hideButtonsLayout(configModel);
        assertEquals(result, false);
    }

    @Test
    public void callButtonHidden() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(true, false, "");
        Boolean result = mainPresenter.hideCallButton(configModel);
        assertEquals(result, true);
    }

    @Test
    public void callButtonVisible() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(true, true, "");
        Boolean result = mainPresenter.hideCallButton(configModel);
        assertEquals(result, false);
    }

    @Test
    public void chatButtonHidden() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(false, true, "");
        Boolean result = mainPresenter.hideChatButton(configModel);
        assertEquals(result, true);
    }

    @Test
    public void chatButtonVisible() {
        MainPresenter mainPresenter = new MainPresenter();
        ConfigModel configModel = new ConfigModel(true, true, "");
        Boolean result = mainPresenter.hideChatButton(configModel);
        assertEquals(result, false);
    }

}