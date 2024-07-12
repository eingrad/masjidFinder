package com.example.levonovo.peta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class masjid extends AppCompatActivity {

    List<tmptdftr> tmptdftrList ;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masjid);



        tmptdftrList = new ArrayList<>();

        tmptdftrList.add(new tmptdftr("Masjid Diraja Tengku Ampuan Jemaah","Lot Masjid, Jalan Bazar U8/98, Seksyen U8",
                "Bukit Jelutong",R.drawable.ampuan));
        tmptdftrList.add(new tmptdftr("Masjid Sultan Salahuddin Abdul Aziz","St., Sekysen 14",
                "Shah Alam",R.drawable.sultan));
        tmptdftrList.add(new tmptdftr("Masjid Seksyen 7","Jalan Kristal 7/70, Seksyen 7",
                "Shah Alam",R.drawable.seksyen));
        tmptdftrList.add(new tmptdftr("Masjid Setia Alam"," 2, Jalan Setia Prima T U13/T",
                "Setia Alam",R.drawable.alam));
        tmptdftrList.add(new tmptdftr("Al Munawwarah Mosque","Jalan Kemensah 27/53, Seksyen 27",
                "Shah Alam",R.drawable.munawwarah));
        tmptdftrList.add(new tmptdftr("Masjid Al-Ikhlas","Jalan Lompat Pagar 13/37, Seksyen 13",
                "Shah Alam",R.drawable.ikhlas));

        RecyclerView op = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,tmptdftrList);
        op.setLayoutManager(new GridLayoutManager(this,3));
        op.setAdapter(myAdapter);

    }
}