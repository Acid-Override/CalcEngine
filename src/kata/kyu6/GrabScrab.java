package kata.kyu6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GrabScrab {

    public static List<String> grabScrab(String letters, List<String> dictionary) {

//        for (String word : dictionary) {
//            System.out.println(word + " " +
//                    Arrays.stream(letters.split("")).sorted().collect(Collectors.joining(""))
//                    + " "
//                    + Arrays.stream(word.split("")).sorted().collect(Collectors.joining("")));
//            result.add(Arrays.stream(word.split("")).sorted().collect(Collectors.joining("")));
//        }
        List<String> collect = dictionary.stream().filter((each) -> isPirateTranslation(letters, each)).collect(Collectors.toList());
        return collect;
    }

    private static boolean isPirateTranslation (String str1, String str2) {

        if ( str1.length() != str2.length()) return false;

        if (Arrays.stream(str1.split("")).sorted().collect(Collectors.joining(""))
                .equals(Arrays.stream(str2.split("")).sorted().collect(Collectors.joining("")))) {
            return true;
        }
        return false;
    }
}
