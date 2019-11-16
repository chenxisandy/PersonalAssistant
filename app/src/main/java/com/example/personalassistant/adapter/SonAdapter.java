package com.example.personalassistant.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.personalassistant.R;
import com.example.personalassistant.bean.SonTask;

import java.util.List;


public class SonAdapter extends ArrayAdapter<SonTask> {

    public SonAdapter(@NonNull Context context, int resource, @NonNull List<SonTask> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SonTask sonTask =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.son_item, parent, false);
        TextView timeTv = view.findViewById(R.id.son_time);
        TextView titleTv = view.findViewById(R.id.son_title);
        timeTv.setText(sonTask.getTime());
        titleTv.setText(sonTask.getTitle());
        return view;
    }
}
