package lambda.vo;

import lambda.classStock.exercise.Performance;

import java.util.List;
import java.util.stream.Stream;

public class Comment implements Performance {
    private String text;
    private Integer upVote;
    private String name;
    private List<Comment> comments;
    private User user;

    public String getUserName() {
        return user.getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment(String text, Integer upVote) {
        this.text = text;
        this.upVote = upVote;
    }

    public Comment(String text, Integer upVote, String name) {
        this.text = text;
        this.upVote = upVote;
        this.name = name;
    }

    public Stream<Comment> getComments() {
        return comments.stream();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
