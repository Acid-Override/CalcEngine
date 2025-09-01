package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class HashMapExample {

    HashMap<Integer, Integer> hashMap = new HashMap<>();

    public HashMapExample() {
        this.hashMap.put(0,0);
    }


    public static void main(String[] args) {
        HashMapExample hashMapExample = new HashMapExample();

        int result = hashMapExample.sum(10);

        //int result = sum(10);
        log.info(String.valueOf(result));
        }
        public int sum(int k) {
            //if k is in hashmap
            //return value for k
            //else
            //calculate value for k
            //store value in hashmap

            if (hashMap.containsKey(k)) {
                return (int) hashMap.get(k);
            }
            if (k > 0) {
                int result =  k + sum(k - 1);
                hashMap.put(k, result);
                return result;
            } else {
                return 0;
            }
        }


}
