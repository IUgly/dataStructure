package test;

import lambda.refactoring.Testing;
import lambda.refactoring.vo.Order;
import org.junit.Test;

public class UserTest {
    @Test
    public void twoLetterStringConvertedToUpperCaseLambdas(){
//        List<String> input = Arrays.asList("a111", "b222", "c333");
//        List<String> result = Testing.elementFirstToUpperCaseLambdas(input);
//        System.out.println(result.toString());
        String input = "ab";
        String result = Testing.firstToUppercase(input);
        System.out.println(result);
    }

    @Test
    public void canCountFeatures(){
    }
}
