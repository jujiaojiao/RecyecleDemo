package com.example.administrator.recyecledemo;
//
//                  加油，fingting
//

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String data;
    private List<TreeNode> list  = new ArrayList<>();

    public TreeNode(String data) {
        this.data = data;
    }

    public List<TreeNode> getList() {
        return list;
    }

    public void setList(List<TreeNode> list) {
        this.list = list;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
