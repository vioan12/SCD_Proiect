public class Main
{
    protected static int tempn,tempk,tempnS,tempnD,tempstop;
    private static Thread thread;
    protected static Bridge Obj_Bridge;

    public static void main(String[] args)
    {
        try {
            Read.init0();
            Obj_Bridge = new Bridge(tempn);
            thread = new MainThread(tempk,tempstop,tempnS,tempnD);
            thread.start();
            thread.join();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
