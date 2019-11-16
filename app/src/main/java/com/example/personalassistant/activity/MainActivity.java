package com.example.personalassistant.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.adapter.TaskAdapter;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ExpandableListView expandableListView;

    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        expandableListView = findViewById(R.id.expandable_list);
        FloatingActionButton SearchFab = findViewById(R.id.search_fab);
        FloatingActionButton AddListFab = findViewById(R.id.add_list_fab);
        FloatingActionButton SortFab = findViewById(R.id.sort_list_fab);
        expandableListView.setAdapter(new TaskAdapter(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_fab:
                Intent intent1 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.add_list_fab:
                Intent intent2 = new Intent(MainActivity.this, AddListActivity.class);
                startActivity(intent2);
                break;
            case R.id.sort_list_fab:
                dialogChoice();

        }
    }



    private void dialogChoice() {
        final String[] sortChoices = {"按类型排序", "按名称排序"};
        flag = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(this, 0)
                .setTitle("选择排序方式")
                .setSingleChoiceItems(sortChoices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setFlag(which);
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "您选择了" + sortChoices[getFlag()], Toast.LENGTH_SHORT).show();
                        sortList();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void sortList() {
        Comparator<TaskList> comparator;    //自定义排序
        switch (flag) {
            case 0:
                comparator = new Comparator<TaskList>() {
                    @Override
                    public int compare(TaskList o1, TaskList o2) {
                        if (o1.getType().compareTo(o2.getType()) > 0) {
                            return 1;
                        }
                        else return -1;
                    }
                };
                break;
            default:
                comparator = new Comparator<TaskList>() {
                    @Override
                    public int compare(TaskList o1, TaskList o2) {
                        if (o1.getName().compareTo(o2.getName()) > 0) {
                            return 1;
                        }
                        else return -1;
                    }
                };
                break;

        }
        Collections.sort(Repo.getInstance().getManifest(), comparator);
    }

    private void setFlag(int which) {
        flag = which;
    }

    private int getFlag() {
        return flag;
    }

}
