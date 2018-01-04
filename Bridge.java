import java.util.concurrent.Semaphore;

/**
 * Created by ioan on 12/26/17.
 */
public class Bridge
{
    private volatile boolean isRunning = true;
    private Masina[] bridge;
    private int n;
    private Masina masina;
    protected static Semaphore semaphore_bridge_0;
    protected static Semaphore semaphore_bridge_n;
    Bridge(int valueofn)
    {
        this.n=valueofn;
        bridge = new Masina[n];
        for(int i=0;i<n;i++){
            bridge[i]=null;
        }
        semaphore_bridge_0 = new Semaphore(1);
        semaphore_bridge_n = new Semaphore(1);
    }
    protected synchronized boolean add_S(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if ((semaphore_bridge_n.availablePermits() == 0)&&(semaphore_bridge_0.availablePermits() == 1)){
            if (bridge[0] == null) {
                int x = -1;
                for (int i = 0; i < n; i++) {
                    if (bridge[i] == masina)
                        x = i;
                }
                if (x == -1) {
                    bridge[0] = masina;
                    //MainThread.S[masina.Get_address()] = null;
                    //cnt++;
                    result=true;
                    Write.console();
                }
            }
            notify();
            return result;
        }else{
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized boolean add_D(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if((semaphore_bridge_n.availablePermits() == 1)&&(semaphore_bridge_0.availablePermits() == 0)){
            if (bridge[n - 1] == null) {
                int x = -1;
                for (int i = 0; i < n; i++) {
                    if (bridge[i] == masina)
                        x = i;
                }
                if (x == -1) {
                    bridge[n - 1] = masina;
                    //MainThread.D[masina.Get_address()] = null;
                    //cnt++;
                    result=true;
                    Write.console();
                }
            }
            notify();
            return result;
        }else{
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized boolean remove_S(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if ((semaphore_bridge_n.availablePermits() == 0)&&(semaphore_bridge_0.availablePermits() == 1)) {
            if (masina == bridge[n - 1]) {
                //MainThread.S[masina.Get_address()] = masina;
                bridge[n - 1] = null;
                result=true;
                Write.console();
            }
            notify();
            return result;
        }else{
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized boolean remove_D(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if ((semaphore_bridge_n.availablePermits() == 1)&&(semaphore_bridge_0.availablePermits() == 0)) {
            if (masina == bridge[0]) {
                //MainThread.D[masina.Get_address()]=masina;
                bridge[0] = null;
                result=true;
                Write.console();
            }
            notify();
            return result;
        }else{
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized boolean move_S(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if ((semaphore_bridge_n.availablePermits() == 0)&&(semaphore_bridge_0.availablePermits() == 1)) {
            int x = -1;
            for (int i = 0; (i < n - 1) && (x == -1); i++) {
                if (bridge[i] == masina) {
                    x = i;
                }
            }
            if (x != -1) {
                if (bridge[x + 1] == null) {
                    bridge[x + 1] = bridge[x];
                    bridge[x] = null;
                    result=true;
                    Write.console();
                }
            }
            notify();
            return result;
        }else{
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized boolean move_D(Masina valueofmasina)
    {
        boolean result = false;
        this.masina = valueofmasina;
        if ((semaphore_bridge_n.availablePermits() == 1)&&(semaphore_bridge_0.availablePermits() == 0)) {
            int x = -1;
            for (int i = 1; (i < n) && (x == -1); i++) {
                if (bridge[i] == masina) {
                    x = i;
                }
            }
            if (x != -1) {
                if (bridge[x - 1] == null) {
                    bridge[x - 1] = bridge[x];
                    bridge[x] = null;
                    result = true;
                    Write.console();
                }
            }
            notify();
            return result;
        } else {
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
            return result;
        }
    }
    protected synchronized void semaphore_sw_S()
    {
        if ((semaphore_bridge_n.availablePermits() == 0)&&(semaphore_bridge_0.availablePermits() == 1)) {
            semaphore_bridge_0.acquireUninterruptibly(1);
            System.out.println("Semafor 0 rosu");
            semaphore_bridge_n.release(1);
            System.out.println("Semafor n verde");
            notify();
        } else {
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }
    protected synchronized void semaphore_sw_D()
    {
        if ((semaphore_bridge_n.availablePermits() == 1)&&(semaphore_bridge_0.availablePermits() == 0)) {
            semaphore_bridge_0.release(1);
            System.out.println("Semafor 0 verde");
            semaphore_bridge_n.acquireUninterruptibly(1);
            System.out.println("Semafor n rosu");
            notify();
        } else {
            try {
                if(isRunning==true) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }
    protected synchronized boolean isclean()
    {
        boolean sw=true;
        for(int i=0;(i<Get_n())&&(sw==true);i++) {
            if (Get_bridge(i) != null) {
                sw = false;
            }
        }
        return sw;
    }
    protected synchronized Masina Get_bridge(int index)
    {
        if(bridge[index]!=null){
            return bridge[index];
        }else {
            return null;
        }
    }
    protected synchronized int Get_n()
    {
        return this.n;
    }
    protected synchronized void stopThread() {
        isRunning = false;
    }

}
