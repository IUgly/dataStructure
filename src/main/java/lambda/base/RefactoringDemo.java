package lambda.base;

import lambda.vo.Comment;
import lambda.vo.User;
import lambda.vo.WeiBo;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RefactoringDemo {
    //找出某条微博下点赞数大于100的评论的内容
    public Set<String> findHotComment(List<WeiBo> weiBos){
        Set<String> hotComment = new HashSet<>();
        for (WeiBo weiBo : weiBos){
            for (Comment comment : weiBo.getCommentList().collect(Collectors.toList())){
                if (comment.getUpVote()>100){
                    hotComment.add(comment.getText());
                }
            }
        }
        return hotComment;
    }
    //重构⬇️
    public Set<String> findHotCommentByLamStream(List<WeiBo> weiBos){
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
    //改进forEach⬇️
    public Set<String> findHotCommentByLamBetter(List<WeiBo> weiBos){
        Set<String> hotComment = new HashSet<>();
        weiBos.stream()
                .forEach(weiBo -> {
                    weiBo.getCommentList()
                            .filter(comment -> comment.getUpVote() >100)
                            .map(comment -> comment.getText())
                            .forEach(text -> hotComment.add(text));
                });
        return hotComment;
    }
    //改进多个Stream,合并为一个Stream⬇️
    public Set<String> findHotCommentByOneStream(List<WeiBo> weiBos){
        Set<String> hotComment = new HashSet<>();

        weiBos.stream()
                .flatMap(weiBo -> weiBo.getCommentList())
                .filter(comment -> comment.getUpVote() > 100)
                .map(comment -> comment.getText())
                .forEach(text -> hotComment.add(text));
        return hotComment;
    }
    //改进forEach，替换为collect⬇️  🐂
    public Set<String> findHotCommentByBest(List<WeiBo> weiBos){
        return weiBos.stream()
                .flatMap(weiBo -> weiBo.getCommentList())
                .filter(comment -> comment.getUpVote() > 100)
                .map(comment -> comment.getText())
                .collect(Collectors.toSet());
    }

    public Integer getUserAllUpVoteNum(List<Comment> commentList){
//        return commentList.stream();
        return 1;
    }

    public Set<String> findComments(List<Comment> commentList){
        return commentList.stream()
                .filter(comment -> comment.getUpVote()>100)
                .map(comment -> comment.getText())
                .collect(Collectors.toSet());
    }

    public Set<Comment> findCommentLists(List<Comment> commentList){
        return commentList.stream()
                .filter(comment -> comment.getUpVote() >100)
                .collect(Collectors.toSet());
    }
    public Comment findCommentOfMaxUpVote(List<Comment> commentList){
        return commentList.stream()
                .max(Comparator.comparing(comment -> comment.getUpVote()))
                .get();
    }
    public List<WeiBo> mergeTwoList(List<WeiBo> weiBo1, List<WeiBo> weiBo2){
        return Stream.of(weiBo1, weiBo2)
                .flatMap(weibo -> weibo.stream())
                .collect(Collectors.toList());
    }
}
