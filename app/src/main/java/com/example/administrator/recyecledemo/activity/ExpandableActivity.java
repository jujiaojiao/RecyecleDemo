package com.example.administrator.recyecledemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.recyecledemo.R;
import com.example.administrator.recyecledemo.TreeNode;
import com.example.administrator.recyecledemo.adapter.ExpandableAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpandableActivity extends AppCompatActivity {

    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        recycle = ((RecyclerView) findViewById(R.id.expandable_recycle));
        List<TreeNode> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TreeNode node = new TreeNode(String.format(Locale.CHINA, "第%02d组", i + 1));
            for (int j = 0; j < 10; j++) {
                node.getList().add(new TreeNode(String.format(Locale.CHINA, "第%02d组 第%02d项", i + 1, j + 1)));
            }
            list.add(node);
        }
        recycle.setAdapter(new ExpandableAdapter(this,list));
    }
}
