package com.example.personalassistant.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.personalassistant.R;
import com.example.personalassistant.adapter.SearchAdapter;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private int flag;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText searchEdit = findViewById(R.id.search_et);
        ImageView searchIc = findViewById(R.id.search_imv);
        listView = findViewById(R.id.search_list);
        searchIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchEdit.toString() == null || searchEdit.toString().isEmpty()) {
                    Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                searchAndShow(searchEdit.toString());
            }
        });
        listView.setAdapter(new SearchAdapter(this, R.layout.child_item, ));
    }


    private void searchAndShow(String toString) {
        List<Task> searchList = new ArrayList<>();
        for (TaskList ts :
                ) {

        }
    }
//    private void choiceDialog() {
//        final String[] searchChoices = {"按类型排序", "按名称排序"};
//        flag = 0;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this, 0)
//                .setTitle("选择搜索内容")
//                .setSingleChoiceItems(searchChoices, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        flag = which;
//                    }
//                })
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        Toast.makeText(SearchActivity.this, "您选择了" + searchChoices[flag], Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        builder.create().show();
//    }
}
