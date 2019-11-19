package com.example.personalassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.activity.MoveActivity;
import com.example.personalassistant.activity.SearchActivity;
import com.example.personalassistant.activity.TaskActivity;
import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.model.Repo;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<Task> {

    private Context context;

    public SearchAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Task task =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.child_item, parent, false);
        TextView timeTv = view.findViewById(R.id.time_tv);
        TextView titleTv = view.findViewById(R.id.title_tv);
        TextView typeTv = view.findViewById(R.id.type_tv);
        RadioButton isFinished = view.findViewById(R.id.is_finished);
        isFinished.setChecked(task.isFinish());
        isFinished.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setFinish(isChecked);
            }
        });
        timeTv.setText(task.getTime());
        titleTv.setText(task.getTitle());
        typeTv.setText(task.getType());
        ImageView copyImv = view.findViewById(R.id.copy_imv);
        ImageView deleteImv = view.findViewById(R.id.delete_imv);
        ImageView moveImv = view.findViewById(R.id.move_imv);
        RelativeLayout itemSelf = view.findViewById(R.id.item_self);
        itemSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskActivity.class);
                // TODO: 2019/11/13 maybe there are some problem
                intent.putExtra(Constant.GROUP_INDEX, Repo.getInstance().getParentIndex(task));
                intent.putExtra(Constant.CHILD_INDEX, Repo.getInstance().getChildIndex(task));
                context.startActivity(intent);
            }
        });
        moveImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.MOVE_TASK);
                context.startActivity(intent);
            }
        });
        copyImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.COPY_TASK);
                context.startActivity(intent);
            }
        });
        deleteImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repo.getInstance().getParent(task).getTaskList().remove(task);
                // TODO: 2019/11/13 does notify ok?
                SearchAdapter.this.notifyDataSetChanged();
            }
        });
        return view;
    }
}
