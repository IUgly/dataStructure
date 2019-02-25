package lambda.classStock;

public class SimplifyCode {
    public void reduceLogConsumption(){
        Logger logger = new Logger();
        if (logger.isDebugEnabled()){
            logger.debug("Look at this: "   + "error");
        }
    }
    //简化代码⬇️   //失败！！！
//    public void simplifyLog(){
//        Logger logger = new Logger();
//        logger.debug(() -> "Look at this" );
//    }

}
