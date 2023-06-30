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

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder>{
        private List<NewsItem> dataList;

        public NewsItemAdapter(List<NewsItem> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_cardview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            NewsItem item = dataList.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView newsImageView;
            private TextView newsTimeView;
            private TextView newsTitleView;
            private TextView newsTextView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsImageView = itemView.findViewById(R.id.item_card_image_view);
                newsTimeView = itemView.findViewById(R.id.item_card_time_view);
                newsTitleView = itemView.findViewById(R.id.item_card_news_title);
                newsTextView = itemView.findViewById(R.id.item_card_news_text);
            }

            public void bind(NewsItem item) {
                newsImageView.setImageResource(item.getImage());
                newsTimeView.setText(item.getTime());
                newsTitleView.setText(item.getTitle());
                newsTextView.setText(item.getText());
            }
        }


}
