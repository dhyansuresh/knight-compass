package edu.ucf.knightcompass;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Review {
    final String author, date, text, reply;
    final int rating;
    Review(String author, String date, int rating, String text, String reply) {
        this.author=author; this.date=date; this.rating=rating; this.text=text; this.reply=reply;
    }
    // Sample data held in memory for this demo; written reviews last until the app process ends.
    private static final Map<Integer,List<Review>> BY_EVENT=new HashMap<>();
    private static final Set<Integer> REVIEWED=new HashSet<>();
    static {
        List<Review> symposium=new ArrayList<>();
        symposium.add(new Review("Priya M.", "Apr 12, 2024", 5, "Absolutely loved it. The poster session was so well organized, and I got great feedback on my research. Will definitely attend again next year.", "Thank you Priya! We're so glad you had a great experience. See you next year!"));
        symposium.add(new Review("Carlos T.", "Apr 13, 2024", 4, "Really informative panels. The keynote speaker was excellent. Only complaint is that parking was a bit confusing — clearer signs would help.", null));
        symposium.add(new Review("Aisha W.", "Apr 14, 2024", 4, "Good event overall. Refreshments ran out early though. Great way to meet other researchers across departments.", "We hear you, Aisha! We'll plan for more refreshments next time."));
        BY_EVENT.put(0,symposium);
    }
    static List<Review> forEvent(int event) {
        List<Review> reviews=BY_EVENT.get(event);
        if(reviews==null) { reviews=new ArrayList<>(); BY_EVENT.put(event,reviews); }
        return reviews;
    }
    // The design shows 12 reviews for the symposium but only three cards.
    static int count(int event) { return forEvent(event).size()+(event==0?9:0); }
    static boolean alreadyReviewed(int event) { return REVIEWED.contains(event); }
    static void add(int event,Review review) { forEvent(event).add(0,review); REVIEWED.add(event); }
    static CharSequence stars(int rating,int emptyColor) {
        SpannableString stars=new SpannableString("★★★★★".substring(0,rating)+"☆☆☆☆☆".substring(rating));
        stars.setSpan(new ForegroundColorSpan(Color.parseColor("#F9A719")),0,rating,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stars.setSpan(new ForegroundColorSpan(emptyColor),rating,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stars;
    }
}
