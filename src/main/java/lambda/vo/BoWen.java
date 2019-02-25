package lambda.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BoWen  {
    private String title;
    private User user;
    private List<User> commentUser;

    public BoWen copy() {
//        List<Track> tracks = getTracks().map(Track::copy).collect(toList());
        List<User> users = getCommentUser().map(User::copy).collect(toList());
        return new BoWen(title, users);
    }
    public BoWen(String name, List<User> members) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(members);
        this.title = name;
        this.commentUser = new ArrayList<>(members);

    }

    public Stream<User> getCommentUser() {
        return commentUser.stream();
    }

    public void setCommentUser(List<User> commentUser) {
        this.commentUser = commentUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
