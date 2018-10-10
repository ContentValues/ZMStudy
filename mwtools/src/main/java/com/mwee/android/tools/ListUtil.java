package com.mwee.android.tools;

import android.text.TextUtils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mwee.android.tools.base.BusinessBean;

/**
 * Created by chris on 16/6/7.
 */
@SuppressWarnings("unchecked")
public class ListUtil {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean listIsEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean arrayIsEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static <T extends BusinessBean> List<T> cloneList(List<T> list) {
        if(list == null) {
            return null;
        } if(list.size()==0) {
            return new ArrayList<>(0);
        }else {
            ArrayList cloneList = new ArrayList(list.size());

            try {
                Iterator e = list.iterator();

                while(e.hasNext()) {
                    BusinessBean t = (BusinessBean)e.next();
                    if(t != null) {
                        cloneList.add(t.clone());
                    }
                }
            } catch (CloneNotSupportedException var4) {
                var4.printStackTrace();
            }

            return cloneList;
        }
    }

    public static String optSqlParams(List<String> paramList) {
        if (ListUtil.isEmpty(paramList)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : paramList) {
            stringBuilder.append("'").append(str).append("'").append(",");
        }

        String result = stringBuilder.toString();
        if (!TextUtils.isEmpty(result) && result.length() > 1) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }


    public static List<String> cloneStringList(List<String> list) {
        if(list == null) {
            return new ArrayList();
        } else {
            ArrayList cloneList = new ArrayList(list.size());
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                String t = (String)var2.next();
                if(t != null) {
                    cloneList.add(t);
                }
            }

            return cloneList;
        }
    }

}
