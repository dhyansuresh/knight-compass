package edu.ucf.knightcompass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class LoginActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state); setContentView(R.layout.activity_login);
        findViewById(R.id.back).setOnClickListener(v -> finish());
        findViewById(R.id.submit).setOnClickListener(v -> {
            Toast.makeText(this,"Demo login — welcome, Knight!",Toast.LENGTH_SHORT).show(); finish();
        });
    }
}
