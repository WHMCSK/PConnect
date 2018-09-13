package cn.bgenius.pconnect.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class ObjectUtil {

	private static Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

	public static <T> T copyProperties(Object srcObj, Class<T> toClass) {
		try {
			ConvertUtils.register(new DateConverter(null), Date.class);
			T instance = toClass.newInstance();
			BeanUtils.copyProperties(instance, srcObj);
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("拷贝对象属性值异常", e);
		}
	}

	/**
	 * 获得一个对象各个属性的字节流
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getProperty(Object entityName) throws Exception {
		Class c = entityName.getClass();
		Field field[] = c.getDeclaredFields();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (Field f : field) {
			Object value = invokeMethod(entityName, f.getName(), null);
			if (value != null && !"serialVersionUID".equals(f.getName())) {
				if (!f.getType().isAssignableFrom(Date.class) && !f.getType().isAssignableFrom(List.class)) {
					String val = value.toString();
					String newval = URLEncoder.encode(val, "UTF-8");
					map.put(f.getName(), newval);
				} else if (f.getType().isAssignableFrom(Date.class)) {
					SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					map.put(f.getName(), dateFm.format(value));
				} else if (f.getType().isAssignableFrom(List.class)) {
					List<?> vs = (List<?>) value;
					int i = 0;
					for (Object o : vs) {
						i++;
						map.put(f.getName() + "|@#|" + i, JSON.toJSON(o));
					}
				}
			}
		}
		return map;
	}

	//@SuppressWarnings("rawtypes")
	private static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = owner.getClass();
		methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
			logger.error(" system throws SecurityException when invoke" + methodName + " method");
			throw e;
		} catch (NoSuchMethodException e) {
			logger.error(" can't find 'get" + methodName + "' method");
			throw e;
		}
		return method.invoke(owner);
	}

	public static boolean isNotEmptyList(Object value) {
		try {
			if (null == value) {
				return false;
			}
			Object[] os = (Object[]) value;
			if (os.length == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return true;
		}
	}
	/**
	 * 类实现 Map --> Bean  
	 * @param map
	 * @param clazz
	 */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {  
        if (map == null || clazz == null) {  
            return null;  
        }  
        try {  
        	T obj = clazz.newInstance();
            BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {  
        	logger.error(e.getMessage());
        }
        return null;
    }  
    
    /**
	 * 类实现 Maps --> Bean  
	 * @param objClass
	 * @param objMaps
	 */
    public static  <T> List<T> map2BeanList(List<Map<String, Object>> objMaps, Class<T> objClass) {  
        if (objMaps == null || objClass == null) {  
            return Collections.emptyList();  
        }  
        List<T> objList = new ArrayList<T>();
        try { 
        	for(Map<String, Object> mapObj : objMaps) {
	        	T obj = objClass.newInstance();
	            BeanUtils.populate(obj, mapObj);
	            objList.add(obj);
        	}
        } catch (Exception e) {  
        	logger.error(e.getMessage());
        } 
        return objList;
    } 
    /**
     * 对象到map
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) { 
    	Map<String, Object> map = new HashMap<String, Object>();
        if(obj == null) {
            return map;      
        }
        try{
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {    
	            String key = property.getName();    
	            if (key.compareToIgnoreCase("class") == 0) {   
	                continue;  
	            }  
	            Method getter = property.getReadMethod();  
	            Object value = getter!=null ? getter.invoke(obj) : null;  
	            map.put(key, value);  
	        }    
        }catch(Exception e) {
        	logger.error(e.getMessage());
        }
        return map;
    }    
}
