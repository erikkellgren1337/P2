/**
 * @author Arian Shaafi, Al3397, DT
 */
package p1;

import javax.security.auth.callback.*;
import java.util.*;

public class MessageManager {
    private Buffer<Message> buffer;
    private Thread thread;
    private LinkedList<Callback> list = new LinkedList<>();


    public MessageManager(Buffer<Message> buffer) {
        this.buffer = buffer;
    }

    public void addCallback(Callback callback) {
        list.add(callback);
    }

    public void start() {
        if (thread == null) {
            thread = new InnerThread();
            thread.start();
        }
    }

    private class InnerThread extends Thread {
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Message message = buffer.get();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).update(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread = null; /*Makes thread empty so it can be started again*/
            }
        }
    }
}
