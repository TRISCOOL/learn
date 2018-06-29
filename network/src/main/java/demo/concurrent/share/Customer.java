package demo.concurrent.share;

import java.util.List;

public class Customer implements Runnable{

    private List<String> queue;

    public Customer(List<String> queue){
        this.queue = queue;
    }

    public void run() {
        while (true){
            synchronized (queue){
                while (queue.size() <= 0){
                    try {
                        System.out.println(Thread.currentThread().getName()+" not get result ,so wait");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //唤醒其他线程
                System.out.println(Thread.currentThread().getName()+"can get a result from queue,size:"+queue.size());
                queue.remove(queue.size()-1<0?0:queue.size()-1);
                System.out.println(Thread.currentThread().getName()+"have got a result,size:"+queue.size());
                queue.notifyAll();
            }
        }
    }
}
