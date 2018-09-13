package cn.bgenius.pconnect.user.po;

import cn.bgenius.pconnect.common.vo.TreeNode;
import lombok.Data;

import java.util.Date;

/**
 * Created by 金飞 on 2017/10/17.
 */
@Data
public class TMenuTree extends TreeNode{
    private String code;

    private String title;

    private String href;

    private String icon;

    private Integer orderNum;

    private String path;

    private String enabled;

    private Date createTime;

    private Date updateTime;
}
