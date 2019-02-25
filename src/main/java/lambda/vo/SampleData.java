package lambda.vo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class SampleData {
    public static final User kuangJunLin = new User(20, "匡俊霖");

    public static final User huCang = new User(19, "胡仓");

    public static final User yangRuiXing = new User(17, "杨瑞星");

    public static final User yangJuanPing = new User(18, "杨娟萍");

    public static final List<User> membersOfTWebBack = Arrays.asList(kuangJunLin, huCang, yangRuiXing, yangJuanPing);

    public static final User theWebBack = new User("后端", membersOfTWebBack, "Web");

    public static final WeiBo firstWeiBo = new WeiBo("服务器挂啦", asList(new Comment("不是我的锅", 10, "匡俊霖")),  asList(new User("胡仓"), new User("杨瑞星"), new User("杨娟萍")));

    public static Stream<WeiBo> weiBoStream = Stream.of(firstWeiBo);

    public static Stream<User> threeUsers() {
        return Stream.of(kuangJunLin, yangRuiXing, yangJuanPing);
    }

    public static List<User> getThreeUsers() {
        return Arrays.asList(kuangJunLin, huCang, yangJuanPing);
    }
}
