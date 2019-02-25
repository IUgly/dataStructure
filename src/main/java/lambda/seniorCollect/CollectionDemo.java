package lambda.seniorCollect;

import lambda.vo.User;
import lambda.vo.WeiBo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDemo {
    public Optional<WeiBo> biggestCommentCount(Stream<WeiBo> weiboList){
        Function<WeiBo,Long> getCount = weiBo -> weiBo.getCommentList().count();
        return weiboList.collect(Collectors.maxBy(Comparator.comparing(getCount)));
    }

    public double averageCommentNumberOfWeiBos(List<WeiBo> weiBos){
        return weiBos.stream()
                .collect(Collectors.averagingInt(weibo -> weibo.getCommentNum()));
    }
    //将流一分为二
    // 微博集合中有热门和非热门
    public Map<Boolean, List<WeiBo>> hotAndNot(Stream<WeiBo> weiBos){
        return weiBos.collect(Collectors.partitioningBy(weibo -> weibo.getHot()));
    }

    //使用方法引用实现⬆️同样效果
    public Map<Boolean, List<WeiBo>> hotAndNotRef(Stream<WeiBo> weiBos){
        return weiBos.collect(Collectors.partitioningBy(WeiBo::getHot));
    }

    //根据发布者对一个由微博组成的流进行数据分组
    public Map<User, List<WeiBo>> weiBosByType(Stream<WeiBo> weiBos){
        return weiBos.collect(Collectors.groupingBy(weibo ->weibo.getUser()));
    }
}
