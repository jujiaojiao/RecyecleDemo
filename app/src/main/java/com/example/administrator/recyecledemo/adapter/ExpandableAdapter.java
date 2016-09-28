package com.example.administrator.recyecledemo.adapter;
//
//                  加油，fingting
//

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.recyecledemo.R;
import com.example.administrator.recyecledemo.TreeNode;

import java.util.List;

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ViewHoLder> implements View.OnClickListener {
    private Context context;
    private List<TreeNode> list;
    private RecyclerView recyclerView;

    public ExpandableAdapter(Context context, List<TreeNode> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHoLder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType,parent,false);
        view.setOnClickListener(this);
        return new ViewHoLder(view);
    }

    @Override
    public void onBindViewHolder(ViewHoLder holder, int position) {
        TreeNode treeNode = list.get(position);
        if (holder.parenttext != null) {
            holder.parenttext.setText(treeNode.getData());
        }
        if (holder.subtexet != null) {
            holder.subtexet.setText(treeNode.getData());
        }
        if (holder.image != null) {
            if (list.containsAll(treeNode.getList())) {
                ViewCompat.setRotation(holder.image,90);
            }else{
                ViewCompat.setRotation(holder.image,0);
            }
        }
        holder.index = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        GridLayoutManager layout = new GridLayoutManager(context, 3);
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (list.get(position).getList().isEmpty()) {
                    return 1;
                }else{
                    return 3;
                }
            }
        });
        recyclerView.setLayoutManager(layout);
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getList().isEmpty()) {
            return R.layout.sub_item;
        }else
            return R.layout.parent_item;
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        TreeNode treeNode = list.get(position);
        if (treeNode.getList().isEmpty()) {
            if (list.containsAll(treeNode.getList())) {
                list.removeAll(treeNode.getList());
                notifyItemChanged(position);
                notifyItemRangeRemoved(position + 1, treeNode.getList().size());
            } else {
                list.addAll(position + 1, treeNode.getList());
                notifyItemChanged(position);
                notifyItemRangeInserted(position + 1, treeNode.getList().size());
            }
        }

    }

    public static class ViewHoLder extends RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView parenttext;
        private final TextView subtexet;
        private int index;
        public ViewHoLder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.parent_image));
            parenttext = ((TextView) itemView.findViewById(R.id.parent_text));
            subtexet = ((TextView) itemView.findViewById(R.id.sub_text));
        }
        public int getIndex(){
            return index;
        }
    }
}
