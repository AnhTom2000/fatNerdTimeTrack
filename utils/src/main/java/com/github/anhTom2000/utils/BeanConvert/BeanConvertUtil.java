package com.github.anhTom2000.utils.BeanConvert;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Description : TODO    Bean对象转换器
 * @Author : Weleness
 * @Date : 2020/05/02
 */
public class BeanConvertUtil {

    //存储已经使用过的Bean转换器
    private static final ConcurrentHashMap<String, BeanCopier> beanCopiers = new ConcurrentHashMap<>();

    /**
     * 将source转换为target
     *
     * @param source 原对象
     * @param target 目标对象
     * @return 转换后的目标对象
     */
    public static <S, T> T convert(S source, T target) {
        if (source == null || target == null) {
            throw new NullPointerException("转换对象不能为null");
        }
        getBeanCopier(source.getClass(),target.getClass()).copy(source,target,null);
        return target;
    }

    public static <S, T> T create(S source, Class<T> tClass) {
        if (source == null || tClass == null) {
            throw new RuntimeException("转换对象不能为空");
        }
        return create(getBeanCopier(source.getClass(), tClass), source, tClass);
    }

    private static <S, T> T create(BeanCopier beanCopier, S source, Class<T> tClass) {
        T target = null;
        try {
            target = tClass.getConstructor().newInstance();
            beanCopier.copy(source, target, null);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return target;
    }
    /**
     * 将原目标对象集合转为目标Class集合
     * @param srcList           原对象集合
     * @param targetClass       目标Class
     * @return                  目标对象集和
     */
    public static <S,T> List<T> convertList(List<S> srcList, Class<T> targetClass)
    {
        if(srcList == null || srcList.isEmpty() || targetClass == null)
        {
            throw new NullPointerException("转换对象或类型不能为null");
        }
        return Optional.of(srcList).orElse(new ArrayList<>()).
                stream().map(s -> create(getBeanCopier(srcList.get(0).getClass(),targetClass),s,targetClass)).collect(Collectors.toList());
    }

    private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        BeanCopier beanCopier = null;
        String key = null;
        if ((beanCopier = beanCopiers.get(key = generateKey(source, target))) == null) {
            beanCopier = BeanCopier.create(source, target, false);
            beanCopiers.put(key, beanCopier);
        }
        return beanCopier;
    }

    private static String generateKey(Class<?> source, Class<?> target) {
        return source.toString().concat(target.toString());
    }


}
