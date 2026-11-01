package edu.ucf.knightcompass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetailActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_event_detail);
        int requested=getIntent().getIntExtra("event",0);
        int index=requested>=0 && requested<Event.ALL.length ? requested : 0;
        Event e=Event.ALL[index];
        set(R.id.title,e.title); set(R.id.date,e.date); set(R.id.time,e.time); set(R.id.building,e.building);
        set(R.id.room,e.room); set(R.id.organizer,e.organizer); set(R.id.contact,e.contact); set(R.id.about,e.about);
        set(R.id.badges,e.virtual?"FREE     •     GUESTS WELCOME     •     VIRTUAL":"FREE     •     GUESTS WELCOME     •     IN-PERSON");
        findViewById(R.id.back).setOnClickListener(v -> finish());
        findViewById(R.id.register).setOnClickListener(v -> message("Register / Join Online","Registration is a demo preview for this checkpoint."));
        findViewById(R.id.map).setOnClickListener(v -> { if(e.virtual) message("Map and Parking","This event takes place online."); else startActivity(new Intent(this,MapActivity.class).putExtra("event",index)); });
        findViewById(R.id.reviews).setOnClickListener(v -> message("Ratings and Reviews","Event ratings and reviews will be available in a future version."));
    }
    private void set(int id,String value) { ((TextView)findViewById(id)).setText(value); }
    private void message(String title,String message) { new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("OK",null).show(); }
}
