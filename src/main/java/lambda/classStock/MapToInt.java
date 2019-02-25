package lambda.classStock;

import lambda.vo.WeiBo;

import java.util.IntSummaryStatistics;

public class MapToInt {
    public static void printCommentUpVoteStatistics(WeiBo weiBo){
        IntSummaryStatistics commentUpVoteStatics
                = weiBo.getCommentList()
                .mapToInt(comment ->comment.getUpVote())
                .summaryStatistics();
        System.out.println(
                "Max:"+commentUpVoteStatics.getMax()+
                "Min:"+commentUpVoteStatics.getMin()+
                "Ave" +commentUpVoteStatics.getAverage()+
                "Sum" +commentUpVoteStatics.getSum());
    }
}
