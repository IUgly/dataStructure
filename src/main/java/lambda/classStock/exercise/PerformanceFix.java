package lambda.classStock.exercise;

import lambda.vo.Comment;

import java.util.stream.Stream;
import static java.util.stream.Stream.concat;

public interface PerformanceFix {
    String getName();

    Stream<Comment> getComments();

    //获得评论下的评论
    default Stream<Comment> getAllComments(){
        return getComments()
                .flatMap(comments -> concat(Stream.of(comments), comments.getComments()));
    }

}
