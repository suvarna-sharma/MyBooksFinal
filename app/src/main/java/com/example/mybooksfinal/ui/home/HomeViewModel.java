package com.example.mybooksfinal.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("WELCOME TO THE MY BOOKS APP\"");
    }

    public LiveData<String> getText() {
        return mText;
    }
}