package top.lionxxw.house.util;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 对象拷贝工具类
 * <p>
 * 1、基于Dozer转换对象的类型
 * 2、基于Dozer将对象A的值拷贝到对象B中
 * 3、List集合转换其他对象集合
 * </p>
 */
public class BeanUtil {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.**
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型
     *
     * @param obj   需要转换的对象
     * @param toObj 转换后的类型
     * @param <T>   返回对象类型泛型
     * @return 返回对象
     */
    public static <T> T objConvert(Object obj, Class<T> toObj) {
        if (null == obj) {
            return null;
        }
        return dozer.map(obj, toObj);
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中
     *
     * @param source 需要转换的对象
     * @param toObj  转换后对象类型
     */
    public static void copy(Object source, Object toObj) {
        if (null != source) {
            dozer.map(source, toObj);
        }
    }

    /**
     * List集合转换其他对象集合
     *
     * @param collection 需要转换List集合
     * @param toObj      转换后的对象类型
     * @param <T>        转换后的对象类型
     * @return 转换后的List集合
     */
    public static <T> List<T> convertList(Collection<?> collection, Class<T> toObj) {
        if (collection == null) {
            return null;
        }
        if (toObj == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (Object obj : collection) {
            list.add(dozer.map(obj, toObj));
        }
        return list;
    }

}