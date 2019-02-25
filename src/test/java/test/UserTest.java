package test;

import lambda.classStock.exercise.CommentFix;
import lambda.vo.Comment;
import lambda.vo.SampleData;
import org.junit.Test;
import org.junit.Assert;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

public class UserTest {
    public final CommentFix optionalExamples = new CommentFix(SampleData.getThreeUsers());

    @org.junit.jupiter.api.Test
    public void indexWithinRange() {
        Optional<Comment> comment = optionalExamples.getComments(0);
        assertTrue(comment.isPresent());
    }

    @Test
    public void indexOutsideRange() {
        Optional<Comment> comment = optionalExamples.getComments(2);
        assertFalse(comment.isPresent());
    }

    @Test
    public void nameIndexInsideRange() {
        String artist = optionalExamples.getCommentText(0);
        Assert.assertEquals("John Coltrane", artist);
    }

    @Test
    public void nameIndexOutsideRange() {
        String text = optionalExamples.getCommentText(4);
        assertEquals("unknown", text);
    }

}
