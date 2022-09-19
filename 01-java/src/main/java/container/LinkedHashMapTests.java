package container;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTests {
    public static void main(String[] args) {
        LinkedHashMap<String,Integer> map=
                new LinkedHashMap<String,Integer>(3,0.75f,true){
                    @Override
                    protected boolean removeEldestEntry(Map.Entry eldest) {
                        return size()>3;
                    }
                };

        map.put("A",100);
        map.put("B",200);
        map.put("C",300);
        map.get("A");
        map.put("D",400);
        System.out.println(map);

    }//LruCache
}
