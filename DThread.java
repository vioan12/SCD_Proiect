import java.util.concurrent.Semaphore;

/**
 * Created by ioan on 12/2/17.
 */
public class DThread extends Thread
{
    private Masina masina;
    private int id;
    protected static int cnt;

    DThread(Masina valueofmasina, int valueofid)
    {
        masina=valueofmasina;
        id=valueofid;
        cnt=0;
    }
    public void run()
    {
        while (true) {

            try {
                boolean sw = false;
                if ((MainThread.bridge[MainThread.n-1] == null)&&(MainThread.semaphore_bridge_n.availablePermits()==1)&&(MainThread.semaphore_bridge_0.availablePermits()==0)) {
                    MainThread.Sectiune_critica.acquireUninterruptibly(1);
                    //BEGIN - Sectiune critica
                    int x = -1;
                    for (int i = 0; i < MainThread.n; i++) {
                        if (MainThread.bridge[i] == masina)
                            x = i;
                    }
                    if (x == -1) {
                        MainThread.bridge[MainThread.n-1] = masina;
                        MainThread.D[id]=null;
                        sw = true;
                        cnt++;
                    }
                    //END - Sectiune critica
                    MainThread.Sectiune_critica.release(1);
                }
                if (sw == false) {
                    if (masina == MainThread.bridge[0]) {
                        MainThread.Sectiune_critica.acquireUninterruptibly(1);
                        //BEGIN - Sectiune critica
                        MainThread.D[id]=masina;
                        MainThread.bridge[0] = null;
                        //END - Sectiune critica
                        MainThread.Sectiune_critica.release(1);
                        sw = true;
                    }
                }
                if (sw == false) {
                    int x = -1;
                    MainThread.Sectiune_critica.acquireUninterruptibly(1);
                    //BEGIN - Sectiune critica
                    for (int i = 1; i < (MainThread.n) && (x == -1); i++) {
                        if (MainThread.bridge[i] == masina) {
                            x = i;
                        }
                    }
                    if (x != -1) {
                        if (MainThread.bridge[x - 1] == null) {
                            MainThread.bridge[x - 1] = MainThread.bridge[x];
                            MainThread.bridge[x] = null;
                        }
                        sw = true;
                    }
                    //END - Sectiune critica
                    MainThread.Sectiune_critica.release(1);
                }

                if(sw==true) {
                    MainThread.Sectiune_critica.acquireUninterruptibly(1);
                    //BEGIN - Sectiune critica
                    if(cnt>=20) {
                        MainThread.semaphore_bridge_0.release(1);
                        boolean sw2=false;
                        for(int i=0;i<(MainThread.n)&&(sw2==false);i++){
                            if(MainThread.bridge[i]!=null){
                                sw2=true;
                            }
                        }
                        if(sw2==false) {
                            MainThread.semaphore_bridge_n.acquireUninterruptibly(1);
                            cnt = 0;
                            synchronized (MainThread.S_Th) {
                                MainThread.S_Th.notifyAll();
                            }
                            Write.console();
                            MainThread.Sectiune_critica.release(1);
                        }
                    }
                    Write.console();
                    //END - Sectiune critica
                    MainThread.Sectiune_critica.release(1);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

}
