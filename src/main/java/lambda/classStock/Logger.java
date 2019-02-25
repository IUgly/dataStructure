package lambda.classStock;

public class Logger {
    private Integer debug;

    public boolean isDebugEnabled(){
        if (this.debug==0){
            return true;
        }else {
            return false;
        }
    }
    public void debug(String message){
        if (isDebugEnabled()){
            debug(message);
        }
    }
}
