package lambda.seniorCollect;

import lambda.vo.Comment;
import lambda.vo.StringCombiner;
import lambda.vo.User;
import lambda.vo.WeiBo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringDemo {
    public static void main(String[] args) {
        //将微博中所有评论的用户名输出为格式化好的列表
        List<Comment> comments = new ArrayList<>();

        //迭代方法
        StringBuffer builder = new StringBuffer("[");
        for(Comment comment: comments){
            if (builder.length() >1){
                builder.append(",");
            }
            String name = comment.getName();
            builder.append(name);
        }
        builder.append("]");
        String result1 = builder.toString();


        //使用流和收集器⬇️
        String result2 =
                comments.stream()
                .map(Comment::getUserName)
                .collect(Collectors.joining(", ",  "[","]"));

        //用reducing简便定制收集器
        List<User> users = new ArrayList<>();
        users.add(new User("kuang"));
        users.add(new User("jun"));
        users.add(new User("lin"));

        String result3 =
                users.stream()
                .map(User::getName)
                .collect(Collectors.reducing(
                        new StringCombiner(", ", "[", "]"),
                        name -> new StringCombiner(",", "[", "]").add(name),
                        StringCombiner::merge))
                .toString();

        System.out.println(result3);

    }


}
