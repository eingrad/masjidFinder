package com.example.levonovo.peta;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class daftar extends AppCompatActivity {
    private TextView tvnama,tvjalan,tvkota;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("Daftar Masjid");
        setContentView(R.layout.activity_daftar);

        tvnama = (TextView) findViewById(R.id.txnama);
        tvjalan= (TextView) findViewById(R.id.txtjln);
        tvkota = (TextView) findViewById(R.id.txtkota);
        gambar = (ImageView) findViewById(R.id.gbr);

        Intent intent = getIntent();
        String Nama = intent.getExtras().getString("Nama");
        String Jalan= intent.getExtras().getString("Jalan");
        String Kota= intent.getExtras().getString("Kota");
        int image = intent.getExtras().getInt("Logo");

        tvnama.setText(Nama);
        tvjalan.setText(Jalan);
        tvkota.setText(Kota);
        gambar.setImageResource(image);
    }

}
