package lambda;

import lambda.vo.Comment;
import lambda.vo.User;
import lambda.vo.WeiBo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RefactoringDemo {
    //找出某条微博下点赞数大于100的评论的内容
    public Set<String> findAgeIsBig(List<WeiBo> weiBos){
        Set<String> hotComment = new HashSet<>();
        for (WeiBo weiBo : weiBos){
            for (Comment comment : weiBo.getCommentList()){
                if (comment.getUpVote()>100){
                    hotComment.add(comment.getText());
                }
            }
        }
        return hotComment;
    }
    //重构⬇️
    public Set<String> findAgeIsBigByLam(List<WeiBo> weiBos){
        Set<String> hotComment = new HashSet<>();
        weiBos.stream()
                .forEach(weiBo -> {
                    weiBo.getCommentList()
                            .forEach(comment -> {
                                if (comment.getUpVote()>100){
                                    String text = comment.getText();
                                    hotComment.add(text);
                                }
                            });
                });
        return hotComment;
    }
}
