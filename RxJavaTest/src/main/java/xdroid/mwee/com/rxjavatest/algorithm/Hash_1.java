package xdroid.mwee.com.rxjavatest.algorithm;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Created by zhangmin on 2018/12/27.
 */

public class Hash_1 {

    public static void main(String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        Hashtable<String, Integer> hashTable = new Hashtable<>();
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        for (int i = 0; i < 20; i++) {
            hashMap.put(i + "", i);
            hashTable.put(i + "", i);
            linkedHashMap.put(i + "", i);
        }

        for (int i = 0; i < 20; i++) {
            hashMap.put(i + "", i);
            hashTable.put(i + "", i);
            linkedHashMap.put(i + "", i);
        }

        //todo hashMap key value 可以为null
        hashMap.put(null,100);
        hashMap.put(null,null);

        //todo hashTable key value 不为null
        //hashTable.put(233+"",null);
        //hashTable.put(null,null);
        //hashTable.put(null,23);

        linkedHashMap.put(null,null);
        linkedHashMap.put(null,100);

        Iterator<Map.Entry<String, Integer>> iteratorHashMap = hashMap.entrySet().iterator();
        while (iteratorHashMap.hasNext()) {
            Map.Entry<String, Integer> entry = iteratorHashMap.next();
            System.out.println("HashMap ----key-->" + entry.getKey() + "       value-->" + entry.getValue());
        }

        System.out.println("****************************");
        Iterator<Map.Entry<String, Integer>> iteratorHashTable = hashTable.entrySet().iterator();
        while (iteratorHashTable.hasNext()) {
            Map.Entry<String, Integer> entry = iteratorHashTable.next();
            System.out.println("Hashtable ----key-->" + entry.getKey() + "      value -->" + entry.getValue());
        }
        System.out.println("****************************");
        Iterator<Map.Entry<String, Integer>> iteratorLinkedHashMap = linkedHashMap.entrySet().iterator();
        while (iteratorLinkedHashMap.hasNext()) {
            Map.Entry<String, Integer> entry = iteratorLinkedHashMap.next();
            System.out.println("LinkedHashMap ----key-->" + entry.getKey() + "       value-->" + entry.getValue());
        }

    }
}
