package com.czm;

import com.czm.bean.TestHash;
import org.junit.Test;

import java.util.*;

/**
 * Created by mac on 17/4/18.
 */
public class HashTest {
    @Test
    public void testhash(){
        TestHash testHash=new TestHash("xx");
        TestHash testHash2=new TestHash("xx");
        HashSet<TestHash> set=new HashSet<TestHash>();
        set.add(testHash);
        set.add(testHash2);
        Iterator<TestHash> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void testhash2(){//输出null2,testHash2
        TestHash testHash=new TestHash("xx");
        TestHash testHash2=new TestHash("xx");
        HashMap<TestHash,String> map=new HashMap<TestHash,String>();
       map.put(null,"null1");
        map.put(null,"null2");
        map.put(testHash,"testHash");
        map.put(testHash2,"testHash2");
        Set<Map.Entry<TestHash, String>> entries = map.entrySet();
        for (Map.Entry<TestHash, String> entry:entries){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
    @Test
    public void testMapKey(){
        HashMap<Integer,String> aa=new HashMap<Integer, String>();
        aa.put(1,"1"); aa.put(1,"2");
        for (Map.Entry<Integer, String> entry:aa.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
