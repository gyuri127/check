package com.example.check;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder>  {

    private Context mContext;
    private ArrayList<Items> items = new ArrayList<>();
    private int position;

    public CheckAdapter(Context mContext) {
        this.mContext = mContext;
        this.items = items;
    }




    public class CheckViewHolder extends RecyclerView.ViewHolder{

        //xml의 위젯 사용하기 위해
        ImageView drink_image;
        TextView drink_name;


        public CheckViewHolder(@NonNull View itemView) {
            super(itemView);

            drink_image= itemView.findViewById(R.id.drink_image);
            drink_name= itemView.findViewById(R.id.drink_name);


        }

        public void setItem(Items item){
            drink_image.setImageResource(item.resId);
            drink_name.setText(item.name);
        }


    }


    @NonNull
    @Override
    public CheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //viewHolder에 itemView를 넣어서 생성하고 생성된 ViewHolder가 리턴되도록 합니다.
        View itemView=inflater.inflate(R.layout.check_recycler , parent, false);
        return new CheckViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull CheckViewHolder holder, final int position) {

        Items item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Items getPosition(int position){
        return items.get(position);
    }


    public void addItem(Items item){
        items.add(item);
    }

}
