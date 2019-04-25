package lambda.parallel;

import lambda.vo.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayDemo {

    //使用for循环初始化数组
    public static double[] imperativeInitialize(int size){
        double[] values = new double[size];
        for (int i = 0; i< values.length; i++){
            values[i] = i;
        }
        return values;
    }

    //使用并行化数组操作 初始化数组
    public static double[] parallelInitialize(int size){
        double[] values = new double[size];
        Arrays.parallelSetAll(values, i -> i);
        return values;
    }

    //  parallelPrefix 操作擅长对时间序列数据做累加， 它更新一个数组
    //  将每一个元素替换为当前元素和其前驱元素的和，

    //计算简单的滑动平均数（在一个大小为3的滑动窗口中的数字的平均数）
    public static double[] simpleMovingAverage(double[] values, int n){  //n是时间窗口的大小
        double[] sums = Arrays.copyOf(values, n);   //复制一份输入数据
        Arrays.parallelPrefix(sums, Double::sum);   //执行并行操作  将数组的元素相加。 sums中保存求和结果
        int start = n -1;
        return IntStream.range(start, sums.length)   //得到包含所需元素下标的流
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;      // 使用总和减去窗口起始值， 再除以n
                })
                .toArray();     //将流转换为数组
    }

}
