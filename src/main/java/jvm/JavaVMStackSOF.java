package jvm;

public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }
    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
//        try {
//            oom.stackLeak();
//        }catch (Throwable e){
//            System.out.println("stack length:" +oom.stackLength);
//            throw e;
//        }

        oom.stackLeakByThread();
    }
}
