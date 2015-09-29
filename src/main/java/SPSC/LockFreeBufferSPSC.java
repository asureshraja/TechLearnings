package SPSC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by suresh on 28/9/15.
 */
class Queue{
    CyclicCounter head = new CyclicCounter(8);
    CyclicCounter tail= new CyclicCounter(8);
    int[] buffer = new int[8];

    public boolean enqueue(int element){

        if(head.getValue()<=tail.getValue()){
            buffer[tail.cyclicallyIncrementAndGet()]=element;
            return true;
        }else{
            return false;
        }

    }

    public int dequeue(){
        if(head.getValue()<tail.getValue()) {
            return buffer[head.cyclicallyIncrementAndGet()];
        }else {
            return 0;
        }
    }
}

class CyclicCounter {

    private final int maxVal;
    private final AtomicInteger ai = new AtomicInteger(0);
    public void setValue(int value){
        this.ai.set(value);
    }
    public CyclicCounter(int maxVal) {

        this.maxVal = maxVal;
    }
    public int getValue(){
        return this.ai.get();
    }
    public int cyclicallyIncrementAndGet() {
        int curVal, newVal;
        do {
            curVal = this.ai.get();
            newVal = (curVal + 1) % this.maxVal;
        } while (!this.ai.compareAndSet(curVal, newVal));
        return newVal;
    }
}
public class LockFreeBufferSPSC {
    public static void main(String[] args) {
       Queue q = new Queue();
        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(q.dequeue());
        }

    }
}
