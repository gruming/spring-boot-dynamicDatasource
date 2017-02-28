package com.kevin.boot.web.utils;
import java.util.*;

/**
 * Created by KevinBlandy on 2017/1/9 17:30
 * 通用工具类
 */
public class GeneralUtils {
    
    /**
     * 数字
     */
    private static final int[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
    
    /**
     *字母
     */
    private static final String[] LETTERS = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t", "u","v","w","x","y","z"};
    
    /**
     * 数组是否为空
     * @param t
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T[] t){
        return t == null || t.length == 0;
    }

    /**
     * 字符串是否为空,或者为""
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        return string == null || string.trim().isEmpty();
    }

    /**
     * Set集合是否为空
     * @param set
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Set<T> set){
        return set == null || set.isEmpty();
    }

    /**
     * Collection是否为空
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * Map是否为空
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> boolean isEmpty(Map<K,V> map){
        return map == null || map.isEmpty();
    }
    
    /**
     * 获取32位无符号大写UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
    
    /**
     * 获取0-9随机字符串,自定义长度
     * @param length
     * @return
     */
    public static String getRandomNum(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int x = 0;x < length ; x++){
            sb.append(NUMBERS[random.nextInt(NUMBERS.length)]);
        }
        return sb.toString();
    }
    
    /**
     * 获取a-z随机字符串,自定义长度
     * @param length
     * @return
     */
    public static String getRandomLetter(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int x = 0;x < length ; x++){
            sb.append(LETTERS[random.nextInt(LETTERS.length)]);
        }
        return sb.toString();
    }
}
