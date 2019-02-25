package lambda.seniorCollect;

import lambda.vo.StringCombiner;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

//java8的 java.util.StringJoiner类作用与StringCombiner一样
//定制自己的收集器⬇️
public class StringCollector implements Collector<String, StringCombiner, String> {

    //特征（描述收集器的对象）
    private static final Set<Characteristics> characteristics = Collections.emptySet();

    private final String delim;
    private final String prefix;
    private final String suffix;

    public StringCollector(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }
    @Override
    //创建容器工厂
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(delim, prefix, suffix);
    }

    @Override
    //结合之前的操作结果 和当前值， 生成并返回新的值  （已经在StringCombiner的add方法中实现）
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    @Override
    //合并两个容器
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    //将流中的值叠加入一个可变容器中
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }
}
