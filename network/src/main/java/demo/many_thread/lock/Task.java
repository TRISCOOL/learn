package demo.many_thread.lock;

public class Task implements Runnable{

    private OutPutInfo outPutInfo;

    public Task(OutPutInfo outPutInfo){
        this.outPutInfo = outPutInfo;
    }

    @Override
    public void run() {
        outPutInfo.outInfo();
    }
}
