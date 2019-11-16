package com.example.personalassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.bean.CycleTask;
import com.example.personalassistant.bean.LongTask;
import com.example.personalassistant.bean.ShortTask;
import com.example.personalassistant.bean.SonTask;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener{

    //private RadioGroup radioGroup;
    private Repo repo;

    private Task task;

    private EditText titleEdit;

    private EditText contentEdit;

    private EditText timeEdit;

    private RadioGroup cycleInterval;

    private RadioButton everyWeek;

    private RadioButton everyMonth;

    private RadioButton everyYear;

    private RadioButton defineOthers;

    private EditText defineEdit;

    private Button addSonTask;

    private ListView sonListView;

    private EditText repeatTimes;

    private List<SonTask> sonTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        repo = Repo.getInstance();
        Intent intent = getIntent();
        int groupPosition = intent.getIntExtra(Constant.GROUP_INDEX, -1);
        if (groupPosition == -1) finish();
        int childPosition = intent.getIntExtra(Constant.CHILD_INDEX, -1);
        if (childPosition == -1) {
            childPosition = Repo.getInstance().getManifest().get(groupPosition).getTaskList().size();
            String type = intent.getStringExtra(Constant.TASK_TYPE);
            switch (type) {
                case Constant.SHORT_TASK:
                    task = new ShortTask();
                    break;
                case Constant.CYCLE_TASK:
                    task = new CycleTask();
                    break;
                case Constant.LONG_TASK:
                    task = new LongTask();
                    break;
                    default:
                        Log.e("TaskActivity:", "onCreate: intent.getTypeExtra has problem：type error");
                        break;
            }
            task.setTaskList(Repo.getInstance().getManifest().get(groupPosition));
        }
        initView();
    }

    private void initView() {
//        RadioButton shortTaskButton = findViewById(R.id.short_task);
//        RadioButton longTaskButton = findViewById(R.id.long_task);
//        RadioButton cycleTaskButton = findViewById(R.id.cycle_task);
//        radioGroup = findViewById(R.id.group_radio);
        titleEdit = findViewById(R.id.title_edit);
        contentEdit = findViewById(R.id.content_edit);
        timeEdit = findViewById(R.id.time_edit);
        cycleInterval = findViewById(R.id.cycle_interval);
        everyWeek = findViewById(R.id.every_week);
        everyMonth = findViewById(R.id.every_month);
        everyYear = findViewById(R.id.every_year);
        defineOthers = findViewById(R.id.define_yourself);
        defineEdit = findViewById(R.id.define_edit);
        addSonTask = findViewById(R.id.add_son_task);
        sonListView  = findViewById(R.id.son_task_list);
        repeatTimes = findViewById(R.id.repeat_times);
        ImageView backIc = findViewById(R.id.back_imv);
        ImageView finishIc = findViewById(R.id.finish_imv);
        backIc.setOnClickListener(this);
        finishIc.setOnClickListener(this);
        switch (task.getType()) {
            case Constant.LONG_TASK:
                addSonTask.setVisibility(View.VISIBLE);
                sonListView.setVisibility(View.VISIBLE);
                sonTaskList = repo.getSonListByFather((LongTask)task);
                if (sonTaskList == null) sonTaskList = new ArrayList<>();
                break;
            case Constant.CYCLE_TASK:
                repeatTimes.setVisibility(View.VISIBLE);
                cycleInterval.setVisibility(View.VISIBLE);
                cycleInterval.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (everyWeek.isChecked()) {
                            ((CycleTask)task).setInterval(7);
                            defineEdit.setVisibility(View.GONE);
                        } else if (everyMonth.isChecked()) {
                            defineEdit.setVisibility(View.GONE);
                            ((CycleTask)task).setInterval(30);
                        } else if (everyYear.isChecked()) {
                            defineEdit.setVisibility(View.GONE);
                            ((CycleTask)task).setInterval(365);
                        } else if (defineOthers.isChecked()) {
                            defineEdit.setVisibility(View.VISIBLE);
                        }
                    }
                });
                //defineEdit.setVisibility(View.VISIBLE);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_imv:
                finish();
                break;
            case R.id.finish_imv:
                saveTask();
        }
    }

    private void saveTask() {
        task.setTitle(titleEdit.toString());
        task.setContent(contentEdit.toString());
        task.setType(contentEdit.toString());
        task.setTime(timeEdit.toString());
        if (task.getType().equals(Constant.LONG_TASK)) {
            for (SonTask sontask :
                    sonTaskList) {
                sontask.setFather((LongTask)task);
                repo.addSonTask(sontask);
            }
        } else if (task.getType().equals(Constant.CYCLE_TASK)) {
            if (everyWeek.isChecked()) {
                ((CycleTask)task).setInterval(7);
            } else if (everyMonth.isChecked()) {
                ((CycleTask)task).setInterval(30);
            } else if (everyYear.isChecked()) {
                ((CycleTask)task).setInterval(365);
            } else if (defineOthers.isChecked()) {
                ((CycleTask)task).setInterval(Integer.getInteger(defineEdit.toString()));
            }
            ((CycleTask)task).setRepeatTimes(Integer.getInteger(repeatTimes.toString()));
            // TODO: 2019/11/16  see the situation
        }
    }
}
