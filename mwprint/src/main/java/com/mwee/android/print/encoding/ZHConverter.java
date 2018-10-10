package com.mwee.android.print.encoding;

import com.mwee.android.print.R;
import com.mwee.android.print.printer.PrintLog;
import com.mwee.android.tools.LogUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ZHConverter {
    private static final int NUM_OF_CONVERTERS = 2;
    private static final ZHConverter[] converters = new ZHConverter[NUM_OF_CONVERTERS];


    private Properties charMap = new Properties();
    private Set<String> conflictingSets = new HashSet<>();

    private ZHConverter(int loLang) {
        InputStream is = null;

        //File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
        BufferedReader in = null;
        try {
            int resID = 0;
            switch (loLang) {
                case Lang.CHINESE_TRANDITIONAL:
                    resID = R.raw.zh2hant;
                    break;
                case Lang.CHINESE_SIMPLIE:
                    resID = R.raw.zh2hans;
                    break;
                default:
                    resID = R.raw.zh2hans;
                    break;
            }
            in = new BufferedReader(new InputStreamReader(LogUtil.context.getResources().openRawResource(resID)));
            charMap.load(in);
        } catch (FileNotFoundException e) {
            PrintLog.e("","",e);
        } catch (IOException e) {
            PrintLog.e("","",e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                PrintLog.e("","",e);
            }
        }
        initializeHelper();
    }

    /**
     * @param toLang 0 for traditional and 1 for simplified
     * @return
     */
    public static ZHConverter getInstance(int toLang) {
        if (toLang >= 0 && toLang < NUM_OF_CONVERTERS) {
            if (converters[toLang] == null) {
                synchronized (ZHConverter.class) {
                    if (converters[toLang] == null) {
                        converters[toLang] = new ZHConverter(toLang);
                    }
                }
            }
            return converters[toLang];
        } else {
            return null;
        }
    }

    public static String convert(String text, int converterType) {
        ZHConverter instance = getInstance(converterType);
        return instance.convert(text);
    }

    @SuppressWarnings("rawtypes")
    private void initializeHelper() {
        Map<String, Integer> stringPossibilities = new HashMap<>();
        Iterator iter = charMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (key.length() >= 1) {
                for (int i = 0; i < (key.length()); i++) {
                    String keySubstring = key.substring(0, i + 1);
                    if (stringPossibilities.containsKey(keySubstring)) {
                        Integer integer = (stringPossibilities.get(keySubstring));
                        stringPossibilities.put(keySubstring,
                                integer + 1);
                    } else {
                        stringPossibilities.put(keySubstring, 1);
                    }
                }
            }
        }
        iter = stringPossibilities.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            if (stringPossibilities.get(key) > 1) {
                conflictingSets.add(key);
            }
        }
    }

    public String convert(String in) {
        StringBuilder outString = new StringBuilder();
        StringBuilder stackString = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            String key = "" + c;
            stackString.append(key);
            if (conflictingSets.contains(stackString.toString())) {
            } else if (charMap.containsKey(stackString.toString())) {
                outString.append(charMap.get(stackString.toString()));
                stackString.setLength(0);
            } else {
                CharSequence sequence = stackString.subSequence(0, stackString.length() - 1);
                stackString.delete(0, stackString.length() - 1);
                flushStack(outString, new StringBuilder(sequence));
            }
        }
        flushStack(outString, stackString);
        return outString.toString();
    }

    private void flushStack(StringBuilder outString, StringBuilder stackString) {
        while (stackString.length() > 0) {
            if (charMap.containsKey(stackString.toString())) {
                outString.append(charMap.get(stackString.toString()));
                stackString.setLength(0);
            } else {
                outString.append("" + stackString.charAt(0));
                stackString.delete(0, 1);
            }
        }
    }

    String parseOneChar(String c) {
        if (charMap.containsKey(c)) {
            return (String) charMap.get(c);

        }
        return c;
    }
}
