import java.util.LinkedList;
import java.util.List;

public class Counter implements Runnable {
    private volatile Thread control;
    private int value;
    private volatile boolean suspended;

    private List<CounterListener> listeners;

    public Counter() {
        this.listeners = new LinkedList<CounterListener>();
    }

    public synchronized void addCounterListener(CounterListener counterListener) {
        listeners.add(counterListener);
    }

    public synchronized void removeCounterListener(CounterListener counterListener) {
        listeners.remove(counterListener);
    }

    public void start() {
        this.control = new Thread(this);
        this.value = 0;
        this.suspended = false;
        this.control.start();
    }

    public void stop() {
        Thread tmp = control;
        this.control = null;
        tmp.interrupt();
    }

    public void suspend() {
        this.suspended = true;
    }

    public synchronized void resume() {
        this.suspended = false;
        notifyAll();
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();

        while (current == control) {
            while (suspended) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
            try {
                Thread.sleep(1000);
                this.value++;
                CounterEvent ce = new CounterEvent(this, value);

                synchronized (this) {
                    for (CounterListener cl : listeners) {
                        cl.counterValueChange(ce);
                    }
                }

            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
