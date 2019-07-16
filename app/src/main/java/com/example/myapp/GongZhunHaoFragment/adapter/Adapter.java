package com.example.myapp.GongZhunHaoFragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private ArrayList<GongZhunHao.DataBean>list=new ArrayList<>();
    private View inflate;

    public Adapter(Context context) {
        this.context = context;
    }
    public void updataData(ArrayList<GongZhunHao.DataBean> list){
        if(list!=null){
            this.list=list;
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_date, null);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView data;
        private Button name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            data = itemView.findViewById(R.id.tv_date);
        }
    }
}
