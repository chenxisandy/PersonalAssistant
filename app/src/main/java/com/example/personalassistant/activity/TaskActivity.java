package com.example.personalassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;

public class TaskActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    private EditText titleEdit;

    private EditText contentEdit;

    private EditText timeEdit;

    private RadioGroup cycleInterval;

    private RadioButton everyWeek;

    private RadioButton everyMonth;

    private RadioButton everyYear;

    private RadioButton defineOthers;

    private EditText defineEdit;

    private Button addSontask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Intent intent = getIntent();
        int groupPosition = intent.getIntExtra(Constant.GROUP_INDEX, -1);
        int childPosition = intent.getIntExtra(Constant.CHILD_INDEX, -1);
        initView();
    }

    private void initView() {
        RadioButton shortTaskButton = findViewById(R.id.short_task);
        RadioButton longTaskButton = findViewById(R.id.long_task);
        RadioButton cycleTaskButton = findViewById(R.id.cycle_task);
    }
}
