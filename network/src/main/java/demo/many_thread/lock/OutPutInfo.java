package demo.many_thread.lock;

public class OutPutInfo {

    public void outInfo(){
        String lock = new String();
        synchronized (lock){
            for (int i=0;i<10;i++){
                System.out.println(i);
            }
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j=10;j<20;j++){
                System.out.println(j);
            }
        }
    }

}
