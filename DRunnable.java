/**
 * Created by ioan on 12/27/17.
 */
public class DRunnable implements Runnable
{
    private Bridge bridge;
    private int cnt_k;
    private Masina masina;
    private int id;
    private Thread thread;

    DRunnable(Masina valueofmasina, int valueofid)
    {
        this.masina = valueofmasina;
        this.bridge = Main.Obj_Bridge;
        this.id = valueofid;
        thread= new Thread(this);
        thread.start();
    }
    public void run()
    {
        princ();
        System.out.println("YYYYY");
        thread.interrupt();
    }
    private void princ()
    {
        while (MainThread.cnt_stop < MainThread.stop) {
            if (cnt_k < MainThread.k) {
                if (bridge.add_D(masina) == true) {
                    cnt_k++;
                } else {
                    if (bridge.remove_D(masina) == true) {
                    } else {
                        if (bridge.move_D(masina) == true) {
                        }
                    }
                }
            } else {
                synchronized (bridge) {
                    if (bridge.isclean() == true) {
                        bridge.semaphore_sw_D();
                        cnt_k = 0;
                        MainThread.cnt_stop++;
                        if(MainThread.cnt_stop == MainThread.stop){
                            bridge.stopThread();
                        }
                    } else {
                        if (bridge.remove_D(masina) == true) {
                        } else {
                            if (bridge.move_D(masina) == true) {
                            }
                        }
                    }
                }
            }
        }
    }

}
