package edu.ucf.knightcompass;

import android.content.Context;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ArrayList;
public class Repository {
    private static Repository instance;

    static Repository get(Context context) {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    // testing data. delete once db is made
    private final Map<String, User> users = new HashMap<>();
    private final Set<String> saved = new HashSet<>();
    private static final String TEST_HOST = "host@ucf.edu";
    private static final String TEST_HOST_EVENT = "e0";

    static boolean isUcfEmail(String email) {
        if (email = null) {
            return false;
        }
        String e = email.toLowerCase(Locale.ROOT);

        int at = e.indexOf('@');
        if (at <= 0 || at != e.lastIndexOf('@')) {
            return false;
        }
        return e.substring(at + 1).equals("ucf.edu");

    }

    User login(String email) {
        if (!isUcfEmail(email)) {
            throw new IllegalArgumentException("Please use your @ucf.edu email.");
        }

        String e=email.trim().toLowerCase(Locale.ROOT);
        User existing = users.get(e);
        if (existing != null) {
            return new User(e, existing.firstName, existing.lastName, false);
        }

        User created = new User(e,null,null,true);
        users.put(e,created);
        return created;
    }

    void addUser(String email, String firstName, String lastName) {
        String e=email.trim().toLowerCase(Locale.ROOT);
        users.put(e, new User(e, firstName, lastName, false));
    }

    // Events
    List<Event> getEvents(boolean weekend) {
        List<Event> result = new ArrayList<>();
        for (int i = weekend ? 3:0; i < (weekend?6:3); i++) {
            result.add(Event.ALL[i]);
        };
        return result;
    }

    Event getEvent(String eventId) {
        for (Event e : Event.ALL) if(e.id.equals(eventId)) return e;
        return null;
    }

    void save(String email, String eventId) {
        saved.add(email+"|"+eventId);
    }
    void unsave(String email, String eventId) {
        saved.remove(email+"|"+eventId);
    }
    boolean isSaved(String email, String eventId) {
        return saved.contains(email+"|"+eventId);
    }

    List<Event> getSavedEvents(String email) {
        List<Event> result = new ArrayList<>();
        for (Event e : Event.ALL) {
            if (isSaved(email, e.id)) {
                result.add(e);
            }
        }
        return result;
    }
}