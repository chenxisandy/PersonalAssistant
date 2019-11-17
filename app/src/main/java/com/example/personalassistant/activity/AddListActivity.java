package com.example.personalassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.personalassistant.R;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.util.ArrayList;

public class AddListActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nameEdit;

    private EditText typeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        ImageView backIc = findViewById(R.id.back_imv);
        ImageView finishIc = findViewById(R.id.finish_imv);
        nameEdit = findViewById(R.id.name_edit);
        typeEdit = findViewById(R.id.type_edit);
        backIc.setOnClickListener(this);
        finishIc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_imv:
                finish();
                break;
            case R.id.finish_imv:
                Repo.getInstance().addManifest(new TaskList(nameEdit.getText().toString(), typeEdit.getText().toString(), new ArrayList<Task>()));
                finish();
                break;
        }
    }
}
