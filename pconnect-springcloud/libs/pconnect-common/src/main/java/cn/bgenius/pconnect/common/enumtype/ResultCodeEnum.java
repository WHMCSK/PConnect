package cn.bgenius.pconnect.common.enumtype;

/**
 * Created by 金飞 on 2017/7/3.
 */
public enum ResultCodeEnum {
    SUCCESS("success",0),FAIL("fail",1),NOLOGIN("未登录",100),
    EMAILCHECKFAIL("邮箱验证失败",100), USER_EXIST("用户已存在",101), MOBILE_PHONE_CHECK_FAIL("手机号码已注册", 102),
    EMAIL_EXIST("邮箱已注册",103);
    private String key;
    private Integer value;

    private ResultCodeEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 根据value值 获取key值
     *
     * @param value
     * @return
     */
    public static String getString(Integer value) {
        ResultCodeEnum[] values = ResultCodeEnum.values();
        for (ResultCodeEnum alarmStatus : values) {
            if (alarmStatus.getValue() == value) {
                return alarmStatus.getKey();
            }
        }
        return null;
    }

    /**
     * 根据value值 获取key值
     *
     * @param value
     * @return
     */
    public static boolean isContainValue(Integer value) {
        ResultCodeEnum[] values = ResultCodeEnum.values();
        for (ResultCodeEnum obj : values) {
            if (obj.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
