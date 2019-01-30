package datastructure;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.Collection;
import java.util.Comparator;

public class FindMaxDemo {
    public static <AnyType>AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp){
        int maxIndex = 0;

        for (int i=1; i< arr.length; i++)
            if ( cmp.compare( arr[i], arr[maxIndex] ) >0)
                maxIndex = i;
        return arr[maxIndex];
    }

    public static double totalArea(Collection<? extends Shape> arr){
        double total = 0;

        for (Shape s : arr){
            if (s != null ){
                total += s.getStrokeDashOffset();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Shape[] shapes1 = {new Circle(2.0), new Rectangle(3.0, 4.0) };

        String[] strings1 = {"joe", "Bill", "Zeke"};

//        System.out.println(findMax(shapes1));
    }
}
class CaseInsensitiveCompare implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase( o2 );
    }
}
class TestProgram
{
//    public static void main(String[] args) {
//        String[] arr = { "ZEBRA", "alligator", "crocodile"};
//        System.out.println( datastructure.FindMaxDemo.findMax( arr, new datastructure.CaseInsensitiveCompare()));
//    }

}