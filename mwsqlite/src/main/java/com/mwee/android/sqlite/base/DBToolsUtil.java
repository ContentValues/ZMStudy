package com.mwee.android.sqlite.base;

import android.database.Cursor;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * Created by virgil on 2017/8/7.
 */

public class DBToolsUtil {
    /**
     * 根据Value本身的类型来构建value
     *
     * @param cursor Cursor
     * @param index  int
     * @param type   Class<T>
     * @param <T>    T
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T buildValueByCursor(Cursor cursor, int index, Class<T> type) {
        if (index < 0) {
            return null;
        }
        T value = null;
        if (type.isPrimitive()) {
            if (type.equals(int.class)) {
                value = (T) Integer.valueOf(cursor.getInt(index));
            } else if (type.equals(long.class)) {
                value = (T) Long.valueOf(cursor.getLong(index));
            } else if (type.equals(double.class)) {
                value = (T) Double.valueOf(cursor.getDouble(index));
            } else if (type.equals(char.class)) {
                String charTemp = cursor.getString(index);
                if (!TextUtils.isEmpty(charTemp)) {
                    value = (T) Character.valueOf(charTemp.charAt(0));
                } else {
                    value = (T) String.valueOf("");
                }
            } else if (type.equals(boolean.class)) {
                value = (T) Boolean.valueOf(toBoolean(cursor.getString(index)));
            }
        } else if (type.equals(Integer.class)) {
            value = (T) Integer.valueOf(cursor.getInt(index));
        } else if (type.equals(Long.class)) {
            value = (T) Long.valueOf(cursor.getLong(index));
        } else if (type.equals(Double.class)) {
            value = (T) Double.valueOf(cursor.getDouble(index));
        } else if (type.equals(Character.class)) {
            String charTemp = cursor.getString(index);
            if (!TextUtils.isEmpty(charTemp)) {
                value = (T) Character.valueOf(charTemp.charAt(0));
            } else {
                value = (T) String.valueOf("");
            }
        } else if (type.equals(Boolean.class)) {
            value = (T) Boolean.valueOf(toBoolean(cursor.getString(index)));
        } else if (String.class.equals(type)) {
            String str = cursor.getString(index);
            if ("null".equalsIgnoreCase(str)) {
                str = "";
            }
            value = (T) str;
        } else if (BigDecimal.class.equals(type)) {
            if (!cursor.isNull(index)) {
                double temp = cursor.getDouble(index);
                value = (T) new BigDecimal(temp).setScale(3, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
                BigDecimal valueB=(BigDecimal)value;
                if (((BigDecimal) value).compareTo(BigDecimal.ZERO) == 0) {
                    value = (T) BigDecimal.ZERO;
                } else if (valueB.toString().contains("E")) {
                    value = (T) new BigDecimal(valueB.toPlainString());
                }
            } else {
                value = (T) BigDecimal.ZERO;
            }
        } else {
            value = JSON.parseObject(cursor.getString(index), type);
        }
        return value;
    }

    /**
     * 根据列名获取index
     *
     * @param cursor     Cursor
     * @param columnName String
     * @return int
     */
    public static int getColumnIndex(Cursor cursor, String columnName) {
        String[] names = cursor.getColumnNames();
        int index = cursor.getColumnIndex(columnName);
        if (index < 0) {
            String lowerName = columnName.toLowerCase();
            for (String temp : names) {
                if (TextUtils.equals(temp.toLowerCase(), lowerName)) {
                    index = cursor.getColumnIndex(temp);
                    break;
                }
            }
        }
        return index;
    }

    /**
     * String 映射为Boolean
     *
     * @param info String
     * @return boolean
     */
    protected static boolean toBoolean(String info) {
        if (TextUtils.isEmpty(info)) {
            return false;
        }
        return TextUtils.equals("true", info.toLowerCase());
    }
}
