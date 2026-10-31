package edu.ucf.knightcompass;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import android.view.View;
import java.util.Locale;

public class MainActivity extends Activity {
    private boolean weekend;
    private EditText search;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_home);
        weekend=state!=null && state.getBoolean("weekend");
        search=findViewById(R.id.search);
        if(state!=null) search.setText(state.getString("query", ""));
        findViewById(R.id.login).setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        findViewById(R.id.today).setOnClickListener(v -> { weekend=false; render(); });
        findViewById(R.id.weekend).setOnClickListener(v -> { weekend=true; render(); });
        search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s,int start,int count,int after) {}
            public void onTextChanged(CharSequence s,int start,int before,int count) { render(); }
            public void afterTextChanged(Editable e) {}
        });
        render();
    }
    @Override protected void onSaveInstanceState(Bundle out) {
        out.putBoolean("weekend",weekend); out.putString("query",search.getText().toString()); super.onSaveInstanceState(out);
    }
    private void render() {
        findViewById(R.id.today).setBackgroundResource(weekend ? R.drawable.tab_off : R.drawable.button_orange);
        findViewById(R.id.weekend).setBackgroundResource(weekend ? R.drawable.button_orange : R.drawable.tab_off);
        LinearLayout list=findViewById(R.id.events);
        list.removeAllViews();
        String query=search.getText().toString().trim().toLowerCase(Locale.ROOT);
        for(int i=weekend?3:0; i<(weekend?6:3); i++) {
            Event event=Event.ALL[i];
            if(!(event.title+" "+event.building+" "+event.organizer).toLowerCase(Locale.ROOT).contains(query)) continue;
            View card=getLayoutInflater().inflate(R.layout.item_event,list,false);
            ((TextView)card.findViewById(R.id.title)).setText(event.title);
            ((TextView)card.findViewById(R.id.date)).setText("▦  "+event.date+" · "+event.time);
            ((TextView)card.findViewById(R.id.location)).setText("⌖  "+event.building+"  ·  "+event.organizer);
            ((TextView)card.findViewById(R.id.badge)).setText(event.virtual?"FREE · VIRTUAL":"FREE");
            final int index=i;
            card.setOnClickListener(v -> startActivity(new Intent(this,EventDetailActivity.class).putExtra("event",index)));
            list.addView(card);
        }
        if(list.getChildCount()==0) { TextView empty=new TextView(this); empty.setText("No events found. Try another search."); empty.setPadding(12,24,12,24); list.addView(empty); }
    }
}
