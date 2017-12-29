import java.util.concurrent.Semaphore;

/**
 * Created by ioan on 12/2/17.
 */
public class MainThread extends Thread
{
    protected static Masina[] S,D;
    protected static SRunnable[] S_R;
    protected static DRunnable[] D_R;
    protected static int nS,nD;
    protected static int k;
    protected static int stop;
    protected static int cnt_stop;

    MainThread(int valueofk, int valueofstop, int valueofnS, int valueofnD)
    {
        this.k=valueofk;
        this.stop=valueofstop;
        this.nS=valueofnS;
        this.nD=valueofnD;
        S= new Masina[nS];
        D = new Masina[nD];
        S_R = new SRunnable[nS];
        D_R = new DRunnable[nD];
        Read.init();
        cnt_stop=0;
        Bridge.semaphore_bridge_n.acquireUninterruptibly(1);
    }
    public void run()
    {
        for (int i=0;i<nS;i++) {
            S_R[i] = new SRunnable(S[i],i);
        }
        for (int i=0;i<nD;i++) {
            D_R[i] = new DRunnable(D[i],i);
        }
    }
}
