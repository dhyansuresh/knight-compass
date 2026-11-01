package edu.ucf.knightcompass;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReviewsActivity extends Activity {
    private int event;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_reviews);
        event=getIntent().getIntExtra("event",0);
        findViewById(R.id.back).setOnClickListener(v -> finish());
        findViewById(R.id.write).setOnClickListener(v -> write());
        render();
    }
    private void render() {
        List<Review> reviews=Review.forEvent(event);
        int total=0;
        for(Review review : reviews) total+=review.rating;
        double average=reviews.isEmpty()?0:(double)total/reviews.size();
        int count=Review.count(event);
        ((TextView)findViewById(R.id.average)).setText(reviews.isEmpty()?"–":String.format(Locale.US,"%.1f",average));
        ((TextView)findViewById(R.id.stars)).setText(Review.stars((int)Math.round(average),Color.WHITE));
        ((TextView)findViewById(R.id.count)).setText(count==0?"No reviews yet":count==1?"1 review":count+" reviews");
        LinearLayout list=findViewById(R.id.reviews);
        list.removeAllViews();
        for(Review review : reviews) {
            View card=getLayoutInflater().inflate(R.layout.item_review,list,false);
            ((TextView)card.findViewById(R.id.author)).setText(review.author);
            ((TextView)card.findViewById(R.id.date)).setText(review.date);
            ((TextView)card.findViewById(R.id.stars)).setText(Review.stars(review.rating,Color.parseColor("#B8BDC8")));
            ((TextView)card.findViewById(R.id.text)).setText(review.text);
            if(review.reply==null) card.findViewById(R.id.reply_box).setVisibility(View.GONE);
            else ((TextView)card.findViewById(R.id.reply)).setText(review.reply);
            card.findViewById(R.id.report).setOnClickListener(v -> report(review));
            list.addView(card);
        }
        if(reviews.isEmpty()) { TextView empty=new TextView(this); empty.setText("Be the first to review this event."); empty.setPadding(12,24,12,24); list.addView(empty); }
    }
    private void report(Review review) {
        new AlertDialog.Builder(this).setTitle("Report this review?").setMessage("Report the review by "+review.author+" to the organizer.")
            .setPositiveButton("Report",(d,w) -> Toast.makeText(this,"Thanks. This review has been reported.",Toast.LENGTH_SHORT).show())
            .setNegativeButton("Cancel",null).show();
    }
    private void write() {
        if(Review.alreadyReviewed(event)) { Toast.makeText(this,"You've already reviewed this event.",Toast.LENGTH_SHORT).show(); return; }
        View form=getLayoutInflater().inflate(R.layout.dialog_review,null);
        AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Write a Review").setView(form).setPositiveButton("Submit",null).setNegativeButton("Cancel",null).show();
        // Set here so a failed check keeps the form open.
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            int rating=(int)((RatingBar)form.findViewById(R.id.rating)).getRating();
            String comment=((EditText)form.findViewById(R.id.comment)).getText().toString().trim();
            if(!((CheckBox)form.findViewById(R.id.attended)).isChecked()) { Toast.makeText(this,"Mark the event as attended before reviewing.",Toast.LENGTH_SHORT).show(); return; }
            if(rating==0 || comment.isEmpty()) { Toast.makeText(this,"Add a star rating and a comment.",Toast.LENGTH_SHORT).show(); return; }
            Review.add(event,new Review("You",new SimpleDateFormat("MMM d, yyyy",Locale.US).format(new Date()),rating,comment,null));
            dialog.dismiss(); render();
        });
    }
}
