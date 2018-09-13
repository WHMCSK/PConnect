package cn.bgenius.pconnect.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cissalcliu on 2017/11/7.
 * welcome to www.xfdmao.com
 */
public class CheckUtil {
    /**
     * 正则表达式校验邮箱
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    public static boolean checkEmaile(String emaile){
        try{
            String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            //正则表达式的模式
            Pattern p = Pattern.compile(RULE_EMAIL);
            //正则表达式的匹配器
            Matcher m = p.matcher(emaile);
            //进行正则匹配
            return m.matches();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
