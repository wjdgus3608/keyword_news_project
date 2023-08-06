package com.example.keyword_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keyword_project.ApiCallClient;
import com.example.keyword_project.GlobalData;
import com.example.keyword_project.MainActivity;
import com.example.keyword_project.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMainKeywordAdapter extends RecyclerView.Adapter<NewMainKeywordAdapter.ViewHolder> {
    private List<String> data;

    public NewMainKeywordAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_keyword_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = data.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_main_keyword_text_view);
            deleteButton = itemView.findViewById(R.id.delete_button);

            // 삭제 버튼 클릭 이벤트 처리
            deleteButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("keyword",GlobalData.clickedKeyword);
                    map.put("removeContainKeyword",data.get(position));
                    ApiCallClient.callUpdateSetting(GlobalData.mainContext,4, map);

                    removeItem(position);
                }
            });
        }

        public void onBind(String item) {
            textView.setText(item);
        }
    }

    public void addItem(String item) {
        data.add(item);
        notifyDataSetChanged();
    }

    // 데이터 리스트에서 아이템 제거하는 메서드
    private void removeItem(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyItemRemoved(position);
            // 마지막 아이템이 삭제된 경우에도 RecyclerView를 업데이트하기 위해 추가
            if (data.size() == 1) {
                notifyItemChanged(0);
            }
        }
    }
    public void allDelete(){
        while (!data.isEmpty()) {
            data.remove(0);
        }
    }
}