package cn.bgenius.pconnect.user.constant;

/**
 * Created by 金飞 on 2017/7/25.
 */
public class UserConstant {
    public final static int ROOT = -1;
    public final static int DEFAULT_GROUP_TYPE = 0;

    /**
     * 普通用户角色
     */
    public final static String ROLE_USER_CODE = "USER";
    /**
     * 权限关联类型
     */
    public final static String AUTHORITY_TYPE_GROUP = "group";
    /**
     * 权限资源类型
     */
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";

    public final static String RESOURCE_REQUEST_METHOD_GET = "GET";
    public final static String RESOURCE_REQUEST_METHOD_PUT = "PUT";
    public final static String RESOURCE_REQUEST_METHOD_DELETE = "DELETE";
    public final static String RESOURCE_REQUEST_METHOD_POST = "POST";

    /**
     * 用户组类型：管理员
     */
    public final static String USER_GROUP_TYPE_LEADER = "leader";

    /**
     * 用户组类型：普通成员
     */
    public final static String USER_GROUP_TYPE_MEMBER = "member";

}
