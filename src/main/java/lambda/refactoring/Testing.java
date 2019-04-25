package lambda.refactoring;

import java.util.List;
import java.util.stream.Collectors;

public class Testing {
    //将首字母转换为大写
//       public static List<String> elementFirstToUpperCaseLambdas(List<String> words){
//        return words.stream()
//                .map(value -> {
//                    char firstChar = Character.toUpperCase(value.charAt(0));
//                    return firstChar + value.substring(1);
//                })
//                .collect(Collectors.toList());
//    }

    public static List<String> elementFirstToUpperCaseLambdas(List<String> words){
        return words.stream()
                .map(Testing::firstToUppercase)
                .collect(Collectors.toList());
    }

    public static String firstToUppercase(String value){
        char first = Character.toUpperCase(value.charAt(0));
        return first + value.substring(1);
    }
}
