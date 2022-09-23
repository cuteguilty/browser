package com.example.newbrowser;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText inputUrl;
    WebView webView;
    ImageButton sendButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputUrl = (EditText) findViewById(R.id.autoCompleteTextView);
        webView = (WebView) findViewById(R.id.webView);
        sendButton = (ImageButton) findViewById(R.id.sendButton);

        backButton = (ImageButton) findViewById(R.id.backButton);


        webView.setWebViewClient(new NewServis());

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress==100)
                    progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
        WebSettings webset = webView.getSettings();
        webset.setJavaScriptEnabled(true);

        webView.loadUrl("");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = inputUrl.getText().toString();

                if (!url.startsWith("https://www.google.com/search?q=")) {
                    url = "https://www.google.com/search?q=" + url;
                }
                webView.loadUrl(url);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView.getWindowToken(), 0);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack())
                    webView.goBack();
            }
        });
    }
}