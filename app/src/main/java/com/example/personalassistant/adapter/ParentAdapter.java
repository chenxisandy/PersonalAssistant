package com.example.personalassistant.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.util.List;

public class ParentAdapter extends ArrayAdapter<TaskList> {

    private String type;

    private Task task;

    private Context context;

    public ParentAdapter(@NonNull Context context, int resource, @NonNull List<TaskList> objects, String type, Task task) {
        super(context, resource, objects);
        this.type = type;
        this.task = task;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        TaskList taskList = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);
        final TextView typeTv = view.findViewById(R.id.parent_type);
        TextView titleTv = view.findViewById(R.id.parent_title);
        typeTv.setText(taskList.getType());
        titleTv.setText(taskList.getName());
        RelativeLayout parentSelf = view.findViewById(R.id.parent_self);
        parentSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals(Constant.COPY_TASK)) {
                    Repo.getInstance().getManifest().get(position).addTask(task);
                    Toast.makeText(getContext(), "复制成功", Toast.LENGTH_SHORT).show();
                    ((Activity)getContext()).finish();
                }
                else if (type.equals(Constant.MOVE_TASK)) {
                    Repo.getInstance().getManifest().get(position).addTask(task);
                    Repo.getInstance().getParent(task).getTaskList().remove(task);
                    Toast.makeText(getContext(), "剪切成功", Toast.LENGTH_SHORT).show();
                    ((Activity)getContext()).finish();
                }
            }
        });
        return view;
    }
}

