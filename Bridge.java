import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ioan on 12/26/17.
 */
public class Bridge
{
    private volatile boolean isRunning = true;
    private Masina[] bridge;
    private int n;
    private Masina masina;
    protected static Lock lock_bridge;
    protected static String flag;

    Bridge(int valueofn)
    {
        this.n=valueofn;
        bridge = new Masina[n];
        for(int i=0;i<n;i++){
            bridge[i]=null;
        }
        lock_bridge = new ReentrantLock();
    }
    protected synchronized boolean add_S(Masina valueofmasina)
    {
        boolean result=false;
        this.masina=valueofmasina;
        if (flag.equals("S")) {
            lock_bridge.lock();
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
            lock_bridge.unlock();
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
        if (flag.equals("D")) {
            lock_bridge.lock();
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
            lock_bridge.unlock();
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
        if (flag.equals("S")) {
            lock_bridge.lock();
            if (masina == bridge[n - 1]) {
                //MainThread.S[masina.Get_address()] = masina;
                bridge[n - 1] = null;
                result=true;
                Write.console();
            }
            notify();
            lock_bridge.unlock();
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
        if (flag.equals("D")) {
            lock_bridge.lock();
            if (masina == bridge[0]) {
                //MainThread.D[masina.Get_address()]=masina;
                bridge[0] = null;
                result=true;
                Write.console();
            }
            notify();
            lock_bridge.unlock();
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
        if (flag.equals("S")) {
            lock_bridge.lock();
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
            lock_bridge.unlock();
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
        if (flag.equals("D")) {
            lock_bridge.lock();
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
            lock_bridge.unlock();
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
