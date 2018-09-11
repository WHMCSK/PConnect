package cn.bgenius.pconnect.common.util;

import com.alibaba.fastjson.JSONObject;
import cn.bgenius.pconnect.common.enumtype.ResultCodeEnum;

import java.util.List;

/**
 * Created by 金飞 on 2017/7/3.
 */
public class JsonUtil {
    private static final String CODE = "code";
    private static final String MSG = "msg";
    private JsonUtil() {

    }
    public static JSONObject getSuccessJsonObject(Object obj) {
        JSONObject resultJsonObject = getSuccessJsonObject();
        setData(resultJsonObject,obj);
        return resultJsonObject;
    }

    private static void setData(JSONObject resultJsonObject, Object obj) {
        if (obj != null) {
            if (obj instanceof List) {
                List<?> listObj = (List<?>) obj;
                if (listObj.size() > 0 && listObj.get(0) != null)
                    resultJsonObject.put("data", obj);
            } else {
                resultJsonObject.put("data", obj);
            }
        }
    }

    public static JSONObject getSuccessJsonObject() {
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put(CODE, ResultCodeEnum.SUCCESS.getValue());
        resultJsonObject.put(MSG, ResultCodeEnum.SUCCESS.getKey());
        return resultJsonObject;
    }
    public static JSONObject getFailJsonObject() {
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put(CODE, ResultCodeEnum.FAIL.getValue());
        resultJsonObject.put(MSG, ResultCodeEnum.FAIL.getKey());
        return resultJsonObject;
    }

    public static JSONObject getFailJsonObject(String msg) {
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put(CODE, ResultCodeEnum.FAIL.getValue());
        resultJsonObject.put(MSG, msg);
        return resultJsonObject;
    }


    public static JSONObject getResultJson(ResultCodeEnum resultCodeEnum, Object obj) {
        JSONObject resultJsonObject = getResultJson(resultCodeEnum);
        resultJsonObject.put(CODE, resultCodeEnum.getValue());
        resultJsonObject.put(MSG, resultCodeEnum.getKey());
        setData(resultJsonObject,obj);
        return resultJsonObject;
    }

    public static JSONObject getSuccessResultJson(Object obj) {
        JSONObject resultJsonObject = getResultJson(ResultCodeEnum.SUCCESS);
        resultJsonObject.put(CODE, ResultCodeEnum.SUCCESS.getValue());
        resultJsonObject.put(MSG, ResultCodeEnum.SUCCESS.getKey());
        setData(resultJsonObject,obj);
        return resultJsonObject;
    }

    /**
     * 格式日期后得到的返回结果，日期需要@JSONField(format="yyyy-MM-dd HH:mm:ss")进行注解
     * @param obj
     * @return
     */
    public static JSONObject getSuccessResultJsonFormatDate(Object obj) {
        JSONObject resultJsonObject = getResultJson(ResultCodeEnum.SUCCESS);
        resultJsonObject.put(CODE, ResultCodeEnum.SUCCESS.getValue());
        resultJsonObject.put(MSG, ResultCodeEnum.SUCCESS.getKey());
        setData(resultJsonObject,JSONObject.toJSON(obj));
        return resultJsonObject;
    }

    public static JSONObject getResultJson(ResultCodeEnum resultCodeEnum) {
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put(CODE, resultCodeEnum.getValue());
        resultJsonObject.put(MSG, resultCodeEnum.getKey());
        return resultJsonObject;
    }


}
