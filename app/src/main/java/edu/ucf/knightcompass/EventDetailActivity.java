package edu.ucf.knightcompass;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetailActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_event_detail);
        int index=getIntent().getIntExtra("event",0);
        Event e=Event.ALL[index>=0 && index<Event.ALL.length ? index : 0];
        set(R.id.title,e.title); set(R.id.date,e.date); set(R.id.time,e.time); set(R.id.building,e.building);
        set(R.id.room,e.room); set(R.id.organizer,e.organizer); set(R.id.contact,e.contact); set(R.id.about,e.about);
        set(R.id.badges,e.virtual?"FREE     •     GUESTS WELCOME     •     VIRTUAL":"FREE     •     GUESTS WELCOME     •     IN-PERSON");
        findViewById(R.id.back).setOnClickListener(v -> finish());
        findViewById(R.id.register).setOnClickListener(v -> message("Register / Join Online","Registration is a demo preview for this checkpoint."));
        findViewById(R.id.map).setOnClickListener(v -> message("Map and Parking",e.virtual?"This event takes place online.":e.building+" · "+e.room+". Campus maps and parking will be added later."));
        findViewById(R.id.reviews).setOnClickListener(v -> message("Ratings and Reviews","Event ratings and reviews will be available in a future version."));
    }
    private void set(int id,String value) { ((TextView)findViewById(id)).setText(value); }
    private void message(String title,String message) { new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("OK",null).show(); }
}
