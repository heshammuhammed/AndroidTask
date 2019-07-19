package com.example.androidtask_itwolf.MainScreen.JavascriptInterface;

import android.content.Context;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

    Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToastMessage() {
        Toast.makeText(context, "Hello World", Toast.LENGTH_LONG).show();
    }

}
