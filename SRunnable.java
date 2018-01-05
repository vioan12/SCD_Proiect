/**
 * Created by ioan on 12/27/17.
 */
public class SRunnable implements Runnable
{
    private Bridge bridge;
    private int cnt_k;
    private Masina masina;
    private int id;
    private Thread thread;

    SRunnable(Masina valueofmasina, int valueofid)
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
        System.out.println("XXXXX");
        thread.interrupt();
    }
    private void princ()
    {
        while (MainThread.cnt_stop < MainThread.stop) {
            if (cnt_k < MainThread.k) {
                if (bridge.add_S(masina) == true) {
                    cnt_k++;
                } else {
                    if (bridge.remove_S(masina) == true) {
                    } else {
                        if (bridge.move_S(masina) == true) {
                        }
                    }
                }
            } else {
                synchronized (bridge) {
                    if (bridge.isclean() == true) {
                        Bridge.flag = "D";
                        cnt_k = 0;
                        MainThread.cnt_stop++;
                        if(MainThread.cnt_stop == MainThread.stop){
                            bridge.stopThread();
                        }
                    } else {
                        if (bridge.remove_S(masina) == true) {
                        } else {
                            if (bridge.move_S(masina) == true) {
                            }
                        }
                    }
                }
            }
        }
    }

}
