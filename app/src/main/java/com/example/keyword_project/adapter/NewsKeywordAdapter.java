package com.example.keyword_project.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keyword_project.R;
import com.example.keyword_project.item.NewsItem;
import com.example.keyword_project.item.NewsKeyword;

import java.util.List;

public class NewsKeywordAdapter extends RecyclerView.Adapter<NewsKeywordAdapter.ViewHolder>{
        private List<NewsKeyword> dataList;
        private OnItemClickListener itemClickListener;
        public interface OnItemClickListener{
            void onItemClick(View v, int pos);
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.itemClickListener = listener;
        }
        public NewsKeywordAdapter(List<NewsKeyword> dataList) {
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
            ColorStateList blueBackGround = ColorStateList.valueOf(Color.parseColor("#DEE9FF"));
            ColorStateList whiteBackGround = ColorStateList.valueOf(Color.parseColor("#F5F5F5"));
            int grayText = Color.parseColor("#B6B6B6");
            int blueText = Color.parseColor("#3A7DFF");

            NewsKeyword newsKeyword = dataList.get(position);
            holder.bind(newsKeyword.getKeyword());

            holder.itemView.setBackgroundTintList(newsKeyword.isClicked() ? blueBackGround : whiteBackGround);
            TextView tx = holder.itemView.findViewById(R.id.item_keyword_text_view);
            tx.setTextColor(newsKeyword.isClicked() ? blueText : grayText);

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
