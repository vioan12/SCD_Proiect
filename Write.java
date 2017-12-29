/**
 * Created by ioan on 12/16/17.
 */
public class Write
{
    protected static void console()
    {
        for (int i = 0; i < MainThread.n; i++) {
            if (MainThread.bridge[i] != null) {
                System.out.print(MainThread.bridge[i].Get_numarinmatriculare() + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println("");
    }
}