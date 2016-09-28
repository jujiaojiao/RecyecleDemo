package com.example.administrator.recyecledemo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.recyecledemo.R;
import com.example.administrator.recyecledemo.adapter.ImageAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {
    private List<String> list;
    private RecyclerView recycle;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        recycle = ((RecyclerView) findViewById(R.id.main_image_recycler));
        list = new ArrayList<>();
        try {
            String[] ss = getAssets().list("");
            for (String s : ss) {
                if (s.endsWith(".jpg")) {
                    list.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
//        recycle.setLayoutManager(staggeredGridLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycle.setLayoutManager(gridLayoutManager);
        adapter = new ImageAdapter(this,list);
        recycle.setAdapter(adapter);
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.GRAY);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5, 5, 5, 5);
            }
        });
    }
}
