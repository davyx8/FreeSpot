package com.example.shaihoff.freespot;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class screen_2 extends Activity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.screen_2, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_2);
        WebView myWebView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        myWebView.getSettings().setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);


        myWebView.loadUrl("http://stone.md.huji.ac.il/huji/mobile/index.php?lab=2");






    Runnable runny = new Runnable() {

        private String url;

        public Runnable setURL(String url) {
            this.url = url;
            return this;
        }

        @Override
        public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            Log.d("shay","in here");
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            if(e.getLocalizedMessage() != null) {
                Log.d("readJSONFeed", e.getLocalizedMessage());
            }
            LinearLayout rl = (LinearLayout) findViewById(R.id.root_view);
            rl.postDelayed( this, 5000);
        }


// Add the request to the RequestQueue.
        }
    }.setURL("http://stone.md.huji.ac.il/huji/mobile/index.php?lab=1");

        LinearLayout rl2 = (LinearLayout) findViewById(R.id.root_view);
        rl2.post(runny);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(1200);
        val = val * 100d;
        return val.intValue();
    }



}
