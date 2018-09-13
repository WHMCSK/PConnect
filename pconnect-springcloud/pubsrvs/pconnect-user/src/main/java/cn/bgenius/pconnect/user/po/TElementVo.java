package cn.bgenius.pconnect.user.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 金飞 on 2017/11/22.
 */
@Data
public class TElementVo implements Serializable{

    private Integer id;

    private String code;

    private String type;

    private String name;

    private String uri;

    private Integer menuId;

    private Integer parentId;

    private String path;

    private String method;

    private Date createTime;

    private Date updateTime;
}
