package kata.kyu5;

import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;

@Slf4j
public class Scramble {

    private String str1;
    private String str2;

    public Scramble() {}
    public Scramble(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public static boolean scramble(String str1, String str2) {
        HashMap<Character, Integer> hash = new HashMap<>();

        for(char ch : str1.toCharArray()) {
            hash.put(ch, 1 + hash.getOrDefault(ch, 0));
        }

        for(char ch : str2.toCharArray()) {
            Integer count = hash.getOrDefault(ch, 0);
            if ( count == 0 ) return false;
            hash.put(ch, --count);
        }
        return true;
    }

    public static boolean scrambleV2(String str1, String str2) {
        if (str2.length() > str1.length()) {
            return false;
        }
        for (String s : str2.split("")) {
            if(!str1.contains(s)) {
                return false;
            }
            str1 = str1.replaceFirst(s, "");
        }
        return true;
    }

    public static boolean scrambleV3(String str1, String str2) {
        boolean toReturn = true;
        StringBuilder sb = new StringBuilder(str1);
        try {
            str2                    .chars()
                    .forEach(c -> sb.deleteCharAt(sb.indexOf(String.valueOf((char) c))));
        } catch (StringIndexOutOfBoundsException e) {
            toReturn = false;
        }
        return toReturn;
    }

    public String getStr1() {
        return str1;
    }
    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }
    public void setStr2(String str2) {
        this.str2 = str2;
    }


    public static void test() {
        Integer num = 23;
        log.info(num.toString());
        int numm = 23;
        log.info(String.valueOf(numm));
    }



}
