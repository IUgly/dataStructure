package lambda.refactoring;

import lambda.vo.User;

public class Main {
    public static void main(String[] args) {

        //从数据库中查询一个用户，  每个线程只做一次这种查询
        ThreadLocal<User> thisUser
                = ThreadLocal.withInitial(() -> new DaoImp().getOneUser());
    }
}
