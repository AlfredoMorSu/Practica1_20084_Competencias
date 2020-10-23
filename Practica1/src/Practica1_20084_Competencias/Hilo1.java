package practica1_20084_competencias;

/**
 *
 * @author José Alfredo Moreno Suárez
 */
import javax.swing.*;

public class Hilo1 extends Thread{
    private JTextArea area;
    private boolean pausa = false;
    private boolean corriendo = true;
    public int i;
    
    public Hilo1(JTextArea area){
        this.area=area;
    }
    
    @Override
    public void run(){
        try{
            i=0;
            while(true){
                area.append(i+"\n");
                i++;
                sleep(1500);
                synchronized(this){
                    if(pausa)
                        wait();
                    if(!corriendo)
                        join();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void pausa(){
        this.pausa = true;
    }
    
    public void reanudar(){
        synchronized(this){
            pausa = false;
            notifyAll();
        }
    }
    
    public void detener(){
        this.corriendo = false;
    }
}
