package com.example.administrator.recyecledemo.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.recyecledemo.R;
import com.example.administrator.recyecledemo.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TestAdapter.OnChildClickListener {
    private List<String> list;
    private RecyclerView recycler;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = ((RecyclerView) findViewById(R.id.main_recycle));
        list= new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(Locale.CANADA,"item%03d",i+1));
        }
        adapter = new TestAdapter(this,list);
        GridLayoutManager layoutManager = new GridLayoutManager(this,6);
        recycler.setAdapter(adapter);
        adapter.setOnChildClickListener(this);
        recycler.setLayoutManager(layoutManager);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position<6){
                    return 3;
                }else
                    return 2;
            }
        });
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.GRAY);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5,5,5,5);
            }
        });
    }

    @Override
    public void onChildClick(RecyclerView parent, View v, int position, long id, String data) {
//        adapter.remove(position);
//        adapter.add(position,"新增数据");
//        Intent intent = new Intent(this,ImageActivity.class);
//        startActivity(intent);
        Intent intent  = new Intent(this, ExpandableActivity.class);
        startActivity(intent);
    }
}
