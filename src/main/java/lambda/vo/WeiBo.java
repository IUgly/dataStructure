package lambda.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class WeiBo {
    private String text;
    private List<Comment> commentList;
    private String type;
    private List<User> relatedUser;
    private Integer upVote;
    private Boolean hot;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(List<User> relatedUser) {
        this.relatedUser = relatedUser;
    }

    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WeiBo(String text, List<Comment> commentList, List<User> relatedUser) {
        Objects.requireNonNull(text);
        Objects.requireNonNull(commentList);
        Objects.requireNonNull(relatedUser);

        this.text = text;
        this.commentList = new ArrayList<>(commentList);
        this.relatedUser = new ArrayList<>(relatedUser);
    }

    public Stream<Comment> getCommentList() {
        return commentList.stream();
    }
    public Integer getCommentNum(){
        return commentList.size();
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
