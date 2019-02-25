package lambda.classStock.exercise;

import lambda.vo.Comment;
import lambda.vo.User;

import java.util.List;
import java.util.Optional;

public class CommentFix {
    private String text;
    private Integer upVote;
    private String name;
    private List<Comment> comments;
    private List<User> relatedUser;

    public CommentFix(List<User> relatedUser) {
        this.relatedUser = relatedUser;
    }

    public Optional<Comment> getComments(int index){
        if (index < 0 || index >= comments.size()){
            return Optional.empty();
        }
        return Optional.of(comments.get(index));
    }

    public String getCommentText(int index){
        Optional<Comment> comment = getComments(index);
        return comment.map(Comment::getText)
                .orElse("unknown");
    }
}
