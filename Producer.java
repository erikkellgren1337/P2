package p1;

/**
 * Producer Class
 * Producer hämtar meddelanden från andra klasser, och expanderar samt så placerar den meddelanden i messageBuffer.
 * @author Arian Shaafi, Al3397, DT
 */
public class Producer extends Thread{
    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;
    private Inner thread = new Inner();

    /***
     * @param producerBuffer Buffer nummer 1
     * @param messageBuffer  Buffer nummer 2
     */
    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer)
    {
        this.producerBuffer = producerBuffer;
        this.messageBuffer = messageBuffer;
    }

    /**
     * Startar programmet
     */
    public void start() {
        thread.start();
    }

    /**
     * Inner extends Thread
     * Thread används för att hämta ett "Message Object" från MessageProducers buffer, och därefter placeras meddelandet till messageBuffer.
     */
    private class Inner extends Thread {

        public void run() {
            while( !Thread.interrupted() ) {
                try {
                    MessageProducer mp = producerBuffer.get();
                    Message[] message = new Message[mp.size()];

                    for(int i = 0; i < mp.times(); i++) {
                        for(int j = 0; j < message.length; j++) {
                            message[j] = mp.nextMessage();
                            messageBuffer.put(message[j]);
                            Thread.sleep(mp.delay());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
