package com.example.personalassistant.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalassistant.Constant;
import com.example.personalassistant.R;
import com.example.personalassistant.activity.MoveActivity;
import com.example.personalassistant.activity.TaskActivity;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskAdapter extends BaseExpandableListAdapter {

    private Repo repo = Repo.getInstance();

    private List<TaskList> manifest;

    private Context context;

    private int flag;

    public TaskAdapter(Context context) {
        manifest = repo.getManifest();
        this.context = context;
    }

    public List<TaskList> getManifest() {
        return manifest;
    }

    public void setManifest(List<TaskList> manifest) {
        this.manifest = manifest;
    }

    @Override
    public int getGroupCount() {
        return manifest.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return manifest.get(groupPosition).getTaskList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return manifest.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return manifest.get(groupPosition).getTaskList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() { //id会随之发生改变
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.deleteImv = convertView.findViewById(R.id.delete_imv);
            groupViewHolder.nameTv = convertView.findViewById(R.id.list_name);
            groupViewHolder.sortImv = convertView.findViewById(R.id.sort_imv);
            groupViewHolder.typeTv = convertView.findViewById(R.id.list_type);
            groupViewHolder.addImv = convertView.findViewById(R.id.add_imv);
            groupViewHolder.itemSelf = convertView.findViewById(R.id.list_item_self);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.nameTv.setText(manifest.get(groupPosition).getName());
        groupViewHolder.typeTv.setText(manifest.get(groupPosition).getType());
        groupViewHolder.deleteImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manifest.remove(groupPosition);
                notifyDataSetChanged();
            }
        });
        groupViewHolder.sortImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogChoice(groupPosition);

            }
        });
        groupViewHolder.addImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskActivity.class);
                dialogTaskType(groupPosition, intent);
            }
        });
//        groupViewHolder.itemSelf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isExpanded) {
//
//                }
//            }
//        });
        return convertView;
    }

    private void dialogTaskType(final int groupPosition, final Intent intent) {
        final String[] typeChoices = {Constant.SHORT_TASK, Constant.CYCLE_TASK, Constant.LONG_TASK};
        flag = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, 0)
                .setTitle("选择新建任务类型")
                .setSingleChoiceItems(typeChoices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        flag = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "您选择了" + typeChoices[flag], Toast.LENGTH_SHORT).show();
                        intent.putExtra(Constant.TASK_TYPE, typeChoices[flag]);
                        intent.putExtra(Constant.GROUP_INDEX, groupPosition);
                        context.startActivity(intent);
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

    private void dialogChoice(final int groupPosition) {
        final String[] sortChoices = {"按截止日期排序", "按标题排序", "按内容排序"};
        flag = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, 0)
                .setTitle("选择排序方式")
                .setSingleChoiceItems(sortChoices, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        flag = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "您选择了" + sortChoices[flag], Toast.LENGTH_SHORT).show();
                        sortList(groupPosition);
                        TaskAdapter.this.notifyDataSetChanged();
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

    private void sortList(int groupPosition) {
        Comparator<Task> comparator;    //自定义排序
        switch (flag) {
            case 0:
                comparator = new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        if (o1.getTime().compareTo(o2.getTime()) > 0) {
                            return -1;
                        } else return 1;
                    }
                };
                break;
            case 1:
                comparator = new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        if (o1.getTitle().compareTo(o2.getTitle()) > 0) {
                            return 1;
                        } else return -1;
                    }
                };
                break;
            default:
                comparator = new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        if (o1.getContent().compareTo(o2.getContent()) > 0) {
                            return 1;
                        } else return -1;
                    }
                };
                break;

        }
        Collections.sort(manifest.get(groupPosition).getTaskList(), comparator);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
        childViewHolder = new ChildViewHolder();
        childViewHolder.copyImv = convertView.findViewById(R.id.copy_imv);
        childViewHolder.deleteImv = convertView.findViewById(R.id.delete_imv);
        childViewHolder.moveImv = convertView.findViewById(R.id.move_imv);
        childViewHolder.itemSelf = convertView.findViewById(R.id.item_self);
        childViewHolder.timeTv = convertView.findViewById(R.id.time_tv);
        childViewHolder.titleTv = convertView.findViewById(R.id.title_tv);
        childViewHolder.typeTv = convertView.findViewById(R.id.type_tv);
        childViewHolder.radioButton = convertView.findViewById(R.id.is_finished);
        convertView.setTag(convertView);
//        } else {
//            childViewHolder = (ChildViewHolder) convertView.getTag();
//        }
        final Task task = manifest.get(groupPosition).getTask(childPosition);
        childViewHolder.typeTv.setText(task.getType());
        childViewHolder.radioButton.setChecked(task.isFinish());
        childViewHolder.radioButton.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setFinish(isChecked);
            }
        });
        childViewHolder.titleTv.setText(task.getTitle());
        childViewHolder.timeTv.setText(task.getTime());
        childViewHolder.itemSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskActivity.class);
                // TODO: 2019/11/13 maybe there are some problem
                intent.putExtra(Constant.GROUP_INDEX, groupPosition);
                intent.putExtra(Constant.CHILD_INDEX, childPosition);
                context.startActivity(intent);
            }
        });
        childViewHolder.moveImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.MOVE_TASK);
                intent.putExtra(Constant.GROUP_INDEX, groupPosition);
                intent.putExtra(Constant.CHILD_INDEX, childPosition);
                context.startActivity(intent);
            }
        });
        childViewHolder.copyImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.COPY_TASK);
                intent.putExtra(Constant.GROUP_INDEX, groupPosition);
                intent.putExtra(Constant.CHILD_INDEX, childPosition);
                context.startActivity(intent);
            }
        });
        childViewHolder.deleteImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manifest.get(groupPosition).getTaskList().remove(childPosition);
                // TODO: 2019/11/13 does notify ok?
                TaskAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;    //不太确定
    }

    static class GroupViewHolder {
        TextView nameTv;

        TextView typeTv;

        ImageView deleteImv;

        ImageView sortImv;

        ImageView addImv;

        RelativeLayout itemSelf;

    }

    static class ChildViewHolder {
        TextView typeTv;

        TextView titleTv;

        TextView timeTv;

        ImageView deleteImv;

        ImageView moveImv;

        ImageView copyImv;

        RelativeLayout itemSelf;

        RadioButton radioButton;
    }
}
