package p1;
/**
 * @author Arian Shaafi, Al3397, DT
 * Klassen läser av text file med hjälp av UTF-8 och skapar ett flertal "Messages" som sparas i en array.
 * Konstruktorn har en String kallad filename som används när read metoden körs
 * @param filename is a name to the files
 */

import javax.swing.*;
import java.io.*;

public class TextfileProducer implements MessageProducer {
    private String filename;
    private int times;
    private int delay;
    private int size;
    private Message[] messages;
    private Message message;
    private int currentIndex = -1;
    private String mes;
    private String ico;


    public TextfileProducer(String filename) {
        this.filename = filename;
        read();
    }


    public Message[] read(){

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) {

            times = Integer.parseInt(br.readLine());

            delay = Integer.parseInt(br.readLine());

            size = Integer.parseInt(br.readLine());

            for(int i=0; i<size; i++) {
                mes = br.readLine();
                ico = br.readLine();
                addM(mes,ico,i);
            }

        } catch (IOException e) {
            System.err.println(e);
        }
        return messages;
    }

    /**
     * @param xxx  En string med en extraherat meddelande, separat från filename
     * @param yyy  String, för placering/destination för xxx, som är lagrad i en hård disk
     * @param i En integer, som ser till att arrayen skapas i rätt ordning
     */
    private void addM(String xxx, String yyy,int i) {
        message= new Message(xxx, new ImageIcon(yyy));
        if (messages==null)
            messages=new Message[size];
        messages[i]= message;
    }

    /**
     *
     * @return Används som delay, räknat i ms
     */

    @Override
    public int delay() {
        return delay;
    }

    /**
     * @return Visar antalet gånger som bilderna visas
     */
    @Override
    public int times() {
        return times;
    }

    /**
     *
     * @return Används fär att bestämma antalet objekt
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @return Printar ut meddelande eller eventuellt objekt
     */
    @Override
    public Message nextMessage() {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}