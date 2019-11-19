package com.example.personalassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.adapter.SonAdapter;
import com.example.personalassistant.bean.LongTask;
import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.util.ArrayList;

public class AddSonActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEdit;

    private EditText timeEdit;

    private Button addButton;

    private boolean isSonTask = false;

    private int groupPosition;

    private int childPosition;

    private int sonIndex;

    private SonAdapter adapter;

    private ListView listView;

    private SonTask sonTask;

    private Repo repo = Repo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_son);
        ImageView backIc = findViewById(R.id.back_imv);
        ImageView finishIc = findViewById(R.id.finish_imv);
        nameEdit = findViewById(R.id.son_name_edit);
        timeEdit = findViewById(R.id.son_time_edit);
        addButton = findViewById(R.id.add_son_task);
        backIc.setOnClickListener(this);
        finishIc.setOnClickListener(this);
        addButton.setOnClickListener(this);
        Intent intent = getIntent();
        sonIndex = intent.getIntExtra(Constant.SON_INDEX, -1);
        groupPosition = intent.getIntExtra(Constant.GROUP_INDEX, -1);
        childPosition = intent.getIntExtra(Constant.CHILD_INDEX, -1);
        listView = findViewById(R.id.son_task_list);
        sonTask = new SonTask();
        if (groupPosition != -1 && childPosition != -1) {
            isSonTask = false;
            sonTask.setFather((LongTask)Repo.getInstance().getManifest().get(groupPosition).getTaskList().get(childPosition));
        } else {
            sonTask.setFather(Repo.getInstance().getSonList().get(groupPosition));
        }
        repo.addSonTask(sonTask);
        adapter = new SonAdapter(this, R.layout.son_item, new ArrayList<SonTask>());
        listView.setAdapter(adapter);
    }

    private void refresh() {
        sonTask.setTime(timeEdit.getText().toString());
        sonTask.setTitle(nameEdit.getText().toString());
        if (isSonTask) {
            adapter.setObjects(Repo.getInstance().getSonListByFather((LongTask) Repo.getInstance().getManifest().get(groupPosition).getTaskList().get(childPosition))
            );
        } else {
            adapter.setObjects(Repo.getInstance().getSonListByFather(Repo.getInstance().getSonList().get(sonIndex)));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_imv:
                repo.getSonList().remove(sonTask);
                finish();
                break;
            case R.id.finish_imv:
                sonTask.setTime(timeEdit.getText().toString());
                sonTask.setTitle(nameEdit.getText().toString());
                finish();
                break;
            case R.id.add_son_task:
                Intent intentSon = new Intent(this, AddSonActivity.class);
                intentSon.putExtra(Constant.SON_INDEX, Repo.getInstance().getSonList().size());
                intentSon.putExtra(Constant.GROUP_INDEX, Repo.getInstance().getSonList().indexOf(sonTask));
                startActivity(intentSon);
                break;
        }
    }
}
