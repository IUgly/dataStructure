package lambda.classStock.exercise;

import lambda.vo.Comment;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public interface Performance {

    public Stream<Comment> getComments();

    public String getName();


    // TODO: test
    public default Stream<Comment> getAllComments() {
        return getComments().flatMap(comment -> {
            return concat(Stream.of(comment), comment.getComments());
        });
    }
}
