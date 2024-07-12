package com.example.levonovo.peta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final Context mContext ;
    private final List<tmptdftr> mData ;


    public RecyclerViewAdapter(Context mContext, List<tmptdftr> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.piew,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tv_title.setText(mData.get(position).getNama());
        holder.img_thumbnail.setImageResource(mData.get(position).getLogo());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,daftar.class);

                // passing data to the book activity
                intent.putExtra("Nama",mData.get(position).getNama());
                intent.putExtra("Jalan",mData.get(position).getJalan());
                intent.putExtra("Kota",mData.get(position).getKota());
                intent.putExtra("Foto",mData.get(position).getLogo());
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView img_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}