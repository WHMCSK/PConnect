package cn.bgenius.pconnect.common.cache;

import java.util.List;

/**
 * Created by 金飞 on 2017/10/16.
 */
public interface CacheSimple<T> {
    public void reload();// 重新加载

    public T get(String key);// 通过key获取对应的对象

    public List<T> getList();// 获取所有的对象

    public int size();

    /**
     * clear 清除缓存
     * @author jonex 2015年4月3日
     *
     */
    public void clearAll();

    /**
     * clear 清除某个缓存
     * @author jonex 2015年4月3日
     *
     */
    public void clear(String key);
}

