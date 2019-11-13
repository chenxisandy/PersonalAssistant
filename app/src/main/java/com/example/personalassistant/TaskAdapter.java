package com.example.personalassistant;

import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContentRating;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.personalassistant.activity.MainActivity;
import com.example.personalassistant.activity.MoveActivity;
import com.example.personalassistant.activity.TaskActivity;
import com.example.personalassistant.bean.Task;
import com.example.personalassistant.bean.TaskList;
import com.example.personalassistant.model.Repo;

import java.security.acl.Group;
import java.util.List;

import javax.crypto.spec.IvParameterSpec;

public class TaskAdapter extends BaseExpandableListAdapter {

    private Repo repo = Repo.getInstance();

    private List<TaskList> manifest;

    private Context context;

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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.deleteImv = convertView.findViewById(R.id.delete_imv);
            groupViewHolder.nameTv = convertView.findViewById(R.id.list_name);
            groupViewHolder.sortImv = convertView.findViewById(R.id.sort_imv);
            groupViewHolder.typeTv = convertView.findViewById(R.id.list_type);
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
            }
        });
        groupViewHolder.sortImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019/11/13 sort task in this taskList
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
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
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
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
                // TODO: 2019/11/13 to put extra
                context.startActivity(intent);
            }
        });
        childViewHolder.moveImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.MOVE_TASK);
                context.startActivity(intent);
            }
        });
        childViewHolder.copyImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoveActivity.class);
                intent.putExtra(Constant.MOVE_OR_COPY, Constant.COPY_TASK);
                context.startActivity(intent);
            }
        });
        childViewHolder.deleteImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manifest.get(groupPosition).getTaskList().remove(childPosition);
                // TODO: 2019/11/13 to notify ?
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

    }

    static class ChildViewHolder {
        TextView typeTv;

        TextView titleTv;

        TextView timeTv;

        ImageView deleteImv;

        ImageView moveImv;

        ImageView copyImv;

        CardView itemSelf;

        RadioButton radioButton;
    }
}
