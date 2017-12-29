public class Main
{
    protected static int tempn,tempnS,tempnD;
    private static Thread[] thread=new Thread[1];

    public static void main(String[] args)
    {
        try {
            Read.init0();
            thread[0] = new MainThread(tempnS,tempnD,tempn);
            thread[0].start();
            thread[0].join();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
