package com.mwee.android.tools;

import android.text.TextUtils;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

/**
 * JsonUtil
 * Created by virgil on 16/8/9.
 */
public class JsonUtil {
    public static String getString(com.alibaba.fastjson.JSONObject ob, String key) {
        String result = getInfo(ob, key, String.class);
        if (result == null) {
            result = "";
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInfo(com.alibaba.fastjson.JSONObject ob, String key, Class<T> type) {
        if (ob == null || TextUtils.isEmpty(key)) {
            if (String.class.equals(type)) {
                return (T) "";
            }
            return null;
        }
        if (ob.size() < 1) {
            if (String.class.equals(type)) {
                return (T) "";
            }
            return null;
        }
        Object value = ob.get(key);
        if (value == null) {
            Set<String> ite = ob.keySet();
            for (String temp : ite) {
                if (key.equalsIgnoreCase(temp)) {
                    value = ob.get(temp);
                }
            }
        }
        return buildValue(value, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInfo(JSONObject ob, String key, Class<T> type) {
        if (ob == null || TextUtils.isEmpty(key)) {
            if (String.class.equals(type)) {
                return (T) "";
            }
            return null;
        }
        if (ob.length() < 1) {
            if (String.class.equals(type)) {
                return (T) "";
            }
            return null;
        }
        Object value = ob.opt(key);
        if (value == null) {
            Iterator<String> ite = ob.keys();
            while (ite.hasNext()) {
                String jsonKey = ite.next();
                if (key.equalsIgnoreCase(jsonKey)) {
                    value = ob.opt(jsonKey);
                    break;
                }
            }
        }
        return buildValue(value, type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T buildValue(Object obj, Class<T> type) {
        T value = null;

        if (type.isPrimitive()) {
            String targeValue = obj + "";
            if (type.equals(int.class)) {
                value = (T) Integer.valueOf(targeValue);
            } else if (type.equals(long.class)) {
                value = (T) Long.valueOf(targeValue);
            } else if (type.equals(double.class)) {
                value = (T) Double.valueOf(targeValue);
            } else if (type.equals(char.class)) {
                if (!TextUtils.isEmpty(targeValue)) {
                    value = (T) Character.valueOf(targeValue.charAt(0));
                } else {
                    value = (T) String.valueOf("");
                }
            } else if (type.equals(boolean.class)) {
                value = (T) Boolean.valueOf(toBoolean(targeValue));
            }
        } else if (type.equals(Integer.class)) {
            String targeValue = obj + "";
            value = (T) Integer.valueOf(targeValue);
        } else if (type.equals(Long.class)) {
            String targeValue = obj + "";

            value = (T) Long.valueOf(targeValue);
        } else if (type.equals(Double.class)) {
            String targeValue = obj + "";

            value = (T) Double.valueOf(targeValue);
        } else if (type.equals(Character.class)) {
            String targeValue = obj + "";

            if (!TextUtils.isEmpty(targeValue)) {
                value = (T) Character.valueOf(targeValue.charAt(0));
            } else {
                value = (T) String.valueOf("");
            }
        } else if (type.equals(Boolean.class)) {
            String targeValue = obj + "";

            value = (T) Boolean.valueOf(targeValue);
        } else if (String.class.equals(type)) {
            if (obj == null) {
                value = (T) "";
            } else if (obj instanceof Float) {
                BigDecimal decValue = new BigDecimal((Float) obj);
                decValue = decValue.setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                if (decValue.compareTo(BigDecimal.ZERO) == 0) {
                    decValue = BigDecimal.ZERO;
                }
                value = (T) decValue.toPlainString();
            } else if (obj instanceof Double) {
                BigDecimal decValue = new BigDecimal((Double) obj);
                decValue = decValue.setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                if (decValue.compareTo(BigDecimal.ZERO) == 0) {
                    decValue = BigDecimal.ZERO;
                }
                value = (T) decValue.toPlainString();
            } else if (obj instanceof BigDecimal) {
                BigDecimal decValue = (BigDecimal) obj;
                decValue = decValue.setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                if (decValue.compareTo(BigDecimal.ZERO) == 0) {
                    decValue = BigDecimal.ZERO;
                }
                value = (T) decValue.toPlainString();
            } else {
                value = (T) (obj + "");
            }
        } else if (BigDecimal.class.equals(type)) {
            String targeValue = obj + "";
            if (TextUtils.isEmpty(targeValue) || TextUtils.equals(targeValue, "0")) {
                value = (T) BigDecimal.ZERO;
            }else {
                BigDecimal valueB = new BigDecimal(targeValue).setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                if (valueB.toString().contains("E")) {
                    value = (T) new BigDecimal(valueB.toPlainString());
                } else if (valueB.compareTo(BigDecimal.ZERO) == 0) {
                    value = (T) BigDecimal.ZERO;
                } else {
                    value = (T) valueB;
                }
            }
        } else {
            value = (T) obj;
        }
        return value;
    }

    private static boolean toBoolean(String info) {
        return TextUtils.equals("true", info);
    }

    /**
     * 获取JSON下节点
     *
     * @param ob
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getValueByName(com.alibaba.fastjson.JSONObject ob, String key, Class<T> type) {
        if (ob == null || TextUtils.isEmpty(key)) {
            return null;
        }
        if (ob.containsKey(key)) {
            return getInfo(ob, key, type);
        }
        // 根节点不包含name, 递归遍历子节点
        for (String element : ob.keySet()) {
            T result = getValueByName(ob.getJSONObject(element), key, type);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
