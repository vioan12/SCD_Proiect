/**
 * Created by ioan on 12/16/17.
 */
public class Write
{
    protected synchronized static void console()
    {
        for (int i = 0; i < Main.tempn; i++) {
            if (Main.Obj_Bridge.Get_bridge(i) != null) {
                System.out.print(Main.Obj_Bridge.Get_bridge(i).Get_numarinmatriculare() + " ");
            } else {
                System.out.print("null ");
            }
        }
        System.out.println("");
    }
}