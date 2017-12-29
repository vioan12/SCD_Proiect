import java.util.concurrent.Semaphore;

/**
 * Created by ioan on 12/2/17.
 */
public class MainThread extends Thread
{
    protected static Thread[] S_Th,D_Th;
    protected static Masina[] bridge,S,D;
    protected static int nS,nD,n;
    protected static Semaphore semaphore_bridge_0;
    protected static Semaphore semaphore_bridge_n;
    protected static Semaphore Sectiune_critica;

    MainThread(int valueofnS,int valueofnD, int valueofn)
    {
        nS=valueofnS;
        nD=valueofnD;
        n=valueofn;
        S_Th = new SThread[nS];
        D_Th = new DThread[nD];
        bridge = new Masina[n];
        S= new Masina[nS];
        D = new Masina[nD];
        semaphore_bridge_0 = new Semaphore(1);
        semaphore_bridge_n = new Semaphore(1);
        Sectiune_critica = new Semaphore(1);
        Read.init();
        for (int i=0;i<nS;i++) {
            S_Th[i] = new SThread(S[i],i);
        }
        for (int i=0;i<nD;i++) {
            D_Th[i] = new DThread(D[i],i);
        }
        for(int i=0;i<n;i++){
            bridge[i]=null;
        }
        semaphore_bridge_n.acquireUninterruptibly(1);
    }
    public void run()
    {
        for (int i = 0; i < nS; i++) {
            S_Th[i].start();
        }
        for (int i = 0; i < nD; i++) {
            D_Th[i].start();
        }
    }
}
