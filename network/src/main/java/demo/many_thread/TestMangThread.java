package demo.many_thread;

import demo.many_thread.lock.OutPutInfo;
import demo.many_thread.lock.Task;

public class TestMangThread {

    public static void main(String[] args){
        Thread a  = new Thread(new Task(new OutPutInfo()));
        Thread b = new Thread(new Task(new OutPutInfo()));
        a.start();
        b.start();
    }
}
