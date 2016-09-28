package com.example.administrator.recyecledemo.adapter;
//
//                  加油，fingting
//

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.recyecledemo.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<String> list;
    private OnChildClickListener onChildClickListener;
    private RecyclerView recyclerView;

    public TestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener){
        this.onChildClickListener  = onChildClickListener;
    }
    @Override
    public void onClick(View view){
        if (onChildClickListener != null) {
            int position = recyclerView.getChildAdapterPosition(view);
            onChildClickListener.onChildClick(recyclerView,view,position,getItemId(position),list.get(position));
        }
    }


    public interface OnChildClickListener{
        void onChildClick(RecyclerView parent,View v,int position,long id,String data);
    }
    public void remove(int position){
        list.remove(position);
//        notifyDataSetChanged();
        notifyItemRemoved(position);
    }
    public void add(int position,String data){
        list.add(position,data);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = ((TextView) itemView.findViewById(R.id.item_text));
        }
    }

}
