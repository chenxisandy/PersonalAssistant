package com.example.personalassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.adapter.ParentAdapter;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.model.Repo;

public class MoveActivity extends AppCompatActivity {

    private String type;

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        Intent intent = getIntent();
        type = intent.getStringExtra(Constant.MOVE_OR_COPY);
        int parentIndex = intent.getIntExtra(Constant.GROUP_INDEX, -1);
        int childIndex = intent.getIntExtra(Constant.CHILD_INDEX, -1);
        task = Repo.getInstance().getManifest().get(parentIndex).getTaskList().get(childIndex);
        ListView listView = findViewById(R.id.parent_list);
        listView.setAdapter(new ParentAdapter(this, R.layout.task_list_item, Repo.getInstance().getManifest(), type, task));
    }
}
