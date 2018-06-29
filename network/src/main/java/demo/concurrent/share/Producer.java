package demo.concurrent.share;

import java.util.List;

public class Producer implements Runnable{

    private List<String> queue;

    public Producer(List<String> queue){
        this.queue = queue;
    }

    public void run() {
        while (true){
            synchronized (queue){
                while (queue.size() == 100){
                    try {
                        System.out.println(Thread.currentThread().getName()+" queue size is "+queue.size()+" so , wait");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.fillInStackTrace();
                    }
                }

                queue.add("result");
                System.out.println(Thread.currentThread().getName()+" queue size is "+queue.size());
/*                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                queue.notifyAll();
            }
        }
    }
}
