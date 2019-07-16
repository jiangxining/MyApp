package com.example.myapp.GongZhunHaoFragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilder;
import com.example.myapp.R;

import java.util.ArrayList;

public class GongZhunHaoAdapter extends RecyclerView.Adapter {
    private final Context context;
    private ArrayList<GongZhunHaoChilder.DataBean.DatasBean> dataBean_chliders=new ArrayList<>();



    public GongZhunHaoAdapter(Context context) {
        this.context = context;
    }
    public void updataData(ArrayList<GongZhunHaoChilder.DataBean.DatasBean> dataBean_chliders){
        if(dataBean_chliders!=null){
            this.dataBean_chliders=dataBean_chliders;
            notifyDataSetChanged();
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.gongzhunhao_item, null);
        return new project_childer(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final GongZhunHaoChilder.DataBean.DatasBean datasBean2 = dataBean_chliders.get(position);
        project_childer viewHolder1 = (project_childer) viewHolder;

        viewHolder1.title.setText(dataBean_chliders.get(position).getAuthor());
        viewHolder1.author_title.setText(dataBean_chliders.get(position).getTitle());
        viewHolder1.niceDate.setText(dataBean_chliders.get(position).getNiceDate());
        viewHolder1.superChapterName.setText(dataBean_chliders.get(position).getSuperChapterName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick!=null){
                    onItemClick.onClick(datasBean2);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBean_chliders.size();
    }



    public class project_childer extends RecyclerView.ViewHolder {


        private final TextView niceDate;
        private final TextView superChapterName;
        private final TextView title;
        private final TextView author_title;
        private final CheckBox childer_cahbox;

        public project_childer(@NonNull View itemView) {
            super(itemView);

            niceDate = itemView.findViewById(R.id.niceDate);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            title = itemView.findViewById(R.id.title);
            author_title = itemView.findViewById(R.id.author_title);
            childer_cahbox = itemView.findViewById(R.id.project_childer_chbox);

        }
    }
      public interface OnItemClick{
            void onClick(GongZhunHaoChilder.DataBean.DatasBean gongZhunHaoChilder);
        }
        private OnItemClick onItemClick;

        public void setOnItemClick(OnItemClick onItemClick) {
            this.onItemClick = onItemClick;
        }


}
