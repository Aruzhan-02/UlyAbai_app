package com.example.ulyabai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private List<HomeModel> HomeList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView title, date;

        public MyViewHolder(View view){
            super(view);

            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.tvTitle);
            date = view.findViewById(R.id.tvDate);
        }
    }

    public HomeAdapter(Context context, List<HomeModel> HomeList){
        this.context = context;
        this.HomeList = HomeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){
        HomeModel item =HomeList.get(position);
        Glide.with(context.getApplicationContext())
                .load(item.getImage())
                .placeholder(R.drawable.ic_image)
                .into(holder.image);

        holder.title.setText(item.getTitle());
        holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount(){
        return HomeList.size();
    }
}
