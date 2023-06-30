package com.example.keyword_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keyword_project.R;
import com.example.keyword_project.item.NewsItem;

import java.util.List;

public class NewsKeywordAdapter extends RecyclerView.Adapter<NewsKeywordAdapter.ViewHolder>{
        private List<String> dataList;
        private OnItemClickListener itemClickListener;
        public interface OnItemClickListener{
            void onItemClick(View v, int pos);
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.itemClickListener = listener;
        }
        public NewsKeywordAdapter(List<String> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword_cardview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String keyword = dataList.get(position);
            holder.bind(keyword);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView newsKeywordView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(v -> {
                    if(itemClickListener!=null){
                        int position = getAdapterPosition();
                        itemClickListener.onItemClick(v,position);
                    }
                });
                newsKeywordView = itemView.findViewById(R.id.item_keyword_text_view);
            }

            public void bind(String keyword) {
                newsKeywordView.setText(keyword);
            }
        }


}
