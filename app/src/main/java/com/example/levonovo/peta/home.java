package com.example.levonovo.peta;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("Menu");
        setContentView(R.layout.activity_home);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); // Sign out from Firebase Authentication

        // Redirect to Login activity
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear back stack
        startActivity(intent);
        finish(); // Finish current activity
    }

    public void clickpta(View view) {

        Intent peta = new Intent(this, peta.class);
        startActivity(peta);

    }
    public void clickdaftar(View view) {

        Intent daftar = new Intent(this,masjid.class);
        startActivity(daftar);
    }


    public void clickabout(View view) {

        Intent about = new Intent(this, About.class);
        startActivity(about);

    }
    public void clickhelp(View view) {

        Intent help = new Intent(this, help.class);
        startActivity(help);

    }
}