/**
 * @author Arian Shaafi, Al3397, DT
 */
package p1;

public class P1Viewer extends Viewer {


    public P1Viewer(MessageManager messageManager, int x, int y) {
        super(x,y);
        messageManager.addCallback(new UpdateMessage());
    }


    private class UpdateMessage implements Callback {
        public void update(Message message) {
            setMessage(message);
        }
    }


}