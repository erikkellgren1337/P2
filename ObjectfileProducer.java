/**
 * @author Arian Shaafi, Al3397, DT
 */
package p1;

import java.io.*;

public class ObjectfileProducer implements MessageProducer {

    private String filename;
    private int times;
    private int delay;
    private int size;
    private int currentIndex = -1;
    private Message[] messages;

    public ObjectfileProducer(String filename) {
        this.filename = filename;
        read();
    }

    public void read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            times = ois.readInt();

            delay = ois.readInt();

            size = ois.readInt();

            for(int i=0; i<size; i++) {
                if (messages==null)
                    messages=new Message[size];
                messages[i] = (Message) ois.readObject();

            }
        }catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }


    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Message nextMessage() {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
