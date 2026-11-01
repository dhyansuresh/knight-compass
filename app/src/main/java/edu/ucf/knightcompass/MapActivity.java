package edu.ucf.knightcompass;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_map);
        Event e=Event.ALL[getIntent().getIntExtra("event",0)];
        ((TextView)findViewById(R.id.place)).setText(e.building+" · "+e.room);
        ((TextView)findViewById(R.id.address)).setText(e.address());
        ((TextView)findViewById(R.id.parking)).setText(e.parking());
        String place=Uri.encode(e.mapQuery());
        findViewById(R.id.back).setOnClickListener(v -> finish());
        findViewById(R.id.open_maps).setOnClickListener(v -> open("https://www.google.com/maps/search/?api=1&query="+place));
        findViewById(R.id.directions).setOnClickListener(v -> open("https://www.google.com/maps/dir/?api=1&destination="+place));
        WebView map=findViewById(R.id.map);
        map.getSettings().setJavaScriptEnabled(true);
        map.setWebViewClient(new WebViewClient() {
            @Override public void onReceivedError(WebView view,WebResourceRequest request,WebResourceError error) {
                if(request.isForMainFrame()) { view.setVisibility(View.GONE); findViewById(R.id.map_error).setVisibility(View.VISIBLE); }
            }
        });
        map.loadDataWithBaseURL("https://www.google.com","<html><body style=\"margin:0\"><iframe style=\"border:0;width:100%;height:100%;position:absolute\" src=\"https://maps.google.com/maps?output=embed&z=16&q="+place+"\"></iframe></body></html>","text/html","UTF-8",null);
    }
    private void open(String url) {
        try { startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url))); }
        catch(ActivityNotFoundException missing) { Toast.makeText(this,"No app available to open maps.",Toast.LENGTH_SHORT).show(); }
    }
}
