import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.*;

class Timer implements Runnable{
    long elapsed = 0;
    Lock lock = new ReentrantLock();
    final Condition qq = lock.newCondition();

    @Override
    public void run(){
        while(true){
            lock.lock();
            try{
                elapsed++;
                System.out.print(elapsed + " ");
                qq.signalAll();
            }finally{
                lock.unlock();
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
