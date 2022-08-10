package com.example.clinic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

public class CustomWebViewScreen extends LinearLayout {

    private CustomWebViewScreen customWebViewScreen;

    public CustomWebViewScreen(Context context) {
        super(context);
    }

    public CustomWebViewScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(String contentUrl) {
        customWebViewScreen = this;
        customWebViewScreen.setVisibility(View.VISIBLE);
        View view = View.inflate(getContext(), R.layout.custom_webview_layout, this);
        ImageView closeButton = view.findViewById(R.id.closeButton);
        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(contentUrl);
        closeButton.setOnClickListener(view1 -> customWebViewScreen.setVisibility(View.GONE));
    }

}
