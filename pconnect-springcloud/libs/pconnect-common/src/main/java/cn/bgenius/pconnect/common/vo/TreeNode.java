package cn.bgenius.pconnect.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Data
public class TreeNode {
    protected int id;
    protected int parentId;
    List<TreeNode> children = new ArrayList<>();
    public void add(TreeNode node){
        children.add(node);
    }
}
