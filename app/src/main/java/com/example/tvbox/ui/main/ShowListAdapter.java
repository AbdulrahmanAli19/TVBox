package com.example.tvbox.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvbox.R;
import com.example.tvbox.pojo.modules.ShowModule;

import java.util.ArrayList;

class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.ShowHolder> {
    private static final String TAG = "ShowListAdapter";
    private ArrayList<ShowModule> data = new ArrayList<>();
    @NonNull
    @Override
    public ShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowHolder holder, int position) {
        holder.tvShowName.setText(data.get(position).getName());
        holder.tvShowTitle.setText(data.get(position).getTitle());
        holder.tvShowTime.setText(data.get(position).getTime());
        final int val = position ;
        holder.btnGetNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+data.get(val).getName());
            }
        });
    }

    public void setArrayList (ArrayList<ShowModule> showModules){
        this.data = showModules ;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ShowHolder extends RecyclerView.ViewHolder {
        TextView tvShowName, tvShowTitle, tvShowTime ;
        Button btnGetNotification ;
        public ShowHolder(@NonNull View itemView) {
            super(itemView);
            btnGetNotification = itemView.findViewById(R.id.btn_get_notification);
            tvShowName = itemView.findViewById(R.id.tv_show_name);
            tvShowTime = itemView.findViewById(R.id.tv_show_time);
            tvShowTitle = itemView.findViewById(R.id.tv_show_title);
        }
    }

}
