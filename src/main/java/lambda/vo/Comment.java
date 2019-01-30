package lambda.vo;

public class Comment {
    private String text;
    private Integer upVote;
    private String name;

    public Comment(String text, Integer upVote) {
        this.text = text;
        this.upVote = upVote;
    }

    public Comment(String text, Integer upVote, String name) {
        this.text = text;
        this.upVote = upVote;
        this.name = name;
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
