package lambda.seniorCollect;

import lambda.vo.BoWen;
import lambda.vo.User;
import lambda.vo.WeiBo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.Map.*;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CombinationCollector {
    //每个用户的博文标题
    public Map<User, List<String>> titleOfBoWenDumb(Stream<BoWen> boWens){
        Map<User, List<BoWen>> boWenByUser =
                boWens.collect(groupingBy(boWen ->boWen.getUser()));

        Map<User, List<String>> titleOfBoWen = new HashMap<>();
        for (Entry<User, List<BoWen>> entry : boWenByUser.entrySet()){
            titleOfBoWen.put(entry.getKey(),
                    entry.getValue()
                    .stream()
                    .map(BoWen::getTitle)
                    .collect(toList()));
        }
        return titleOfBoWen;
    }

    //改善⬇️  ???

//    public Map<User, List<String>> titleOfBoWenBetter(Stream<BoWen> boWens){
//        return boWens.collect(groupingBy(BoWen::getUser,
//                                         mapping(BoWen::getTitle), toList()));
//    }

    //其它细节⬇️

    //假设使用以下定义缓存
    Map<String, User> userCache;

    public User getUserFromDB(String name){
        return new User(name);
    }

    //显式判断空值的方式缓存
    public User getUser(String name){
        User user = userCache.get(name);
        if (user == null){
            //  从数据库中查询  user == this.getUserFromDB(name);
            //  再加入缓存     userCache.put(name, user);
        }
        return user;
    }

    //lambda重写上面的代码
    public User getUserBetter(String name){
        return userCache.computeIfAbsent(name, this::getUserFromDB);
    }


    //丑陋的迭代Map的方式
    public Map<User, Integer> countAlbums(Map<User, List<WeiBo>> weiBosByUser) {
        Map<User, Integer> countOfWeiBo = new HashMap<>();

        for(Map.Entry<User, List<WeiBo>> entry : weiBosByUser.entrySet()) {
            User user = entry.getKey();
            List<WeiBo> weiBos = entry.getValue();
            countOfWeiBo.put(user, weiBos.size());
        }
        return countOfWeiBo;
    }
    //使用内部迭代遍历Map里的值⬇️
    public Map<User, Integer> countAlbumsBetter(Map<User, List<WeiBo>> weiBosByUser) {
        Map<User, Integer> countOfWeiBo = new HashMap<>();

        weiBosByUser.forEach(((user, weiBos) -> {
            countOfWeiBo.put(user, weiBos.size());
        }));
        return countOfWeiBo;
    }


}
