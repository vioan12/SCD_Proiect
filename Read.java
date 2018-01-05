import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by ioan on 12/16/17.
 */
public class Read
{
    static private FileReader in;
    static private BufferedReader br;
    static void init()
    {
        try {
            String temp1,temp2,temp3,temp4;
            in = new FileReader("Input.txt");
            br = new BufferedReader(in);
            Masina tempMasina;
            for(int i=0;i<5;i++) {
                temp1 = br.readLine();
            }

            for(int i=0;i<MainThread.nS;i++){
                temp1 = "";
                temp1.replaceAll("[^a-zA-Z0-9 ]", "");
                temp2 = "";
                temp2.replaceAll("[^a-zA-Z0-9 ]", "");
                temp3 = "";
                temp3.replaceAll("[^a-zA-Z0-9 ]", "");
                temp4 = "";
                temp4.replaceAll("[^a-zA-Z0-9 ]", "");
                temp1=br.readLine();
                temp2=br.readLine();
                temp3=br.readLine();
                temp4=br.readLine();
                tempMasina = new Masina(i,temp1,temp2,temp3,temp4);
                MainThread.S[i]=tempMasina;
            }
            for(int i=0;i<MainThread.nD;i++){
                temp1 = "";
                temp1.replaceAll("[^a-zA-Z0-9 ]", "");
                temp2 = "";
                temp2.replaceAll("[^a-zA-Z0-9 ]", "");
                temp3 = "";
                temp3.replaceAll("[^a-zA-Z0-9 ]", "");
                temp4 = "";
                temp4.replaceAll("[^a-zA-Z0-9 ]", "");
                temp1=br.readLine();
                temp2=br.readLine();
                temp3=br.readLine();
                temp4=br.readLine();
                tempMasina = new Masina(i,temp1,temp2,temp3,temp4);
                MainThread.D[i]=tempMasina;
            }
            in.close();
            br.close();
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    static void init0()
    {
        try {
            String temp;
            in = new FileReader("Input.txt");
            br = new BufferedReader(in);
            temp = br.readLine();
            Main.tempn=Integer.parseInt(temp);
            temp = br.readLine();
            Main.tempk=Integer.parseInt(temp);
            temp = br.readLine();
            Main.tempstop=Integer.parseInt(temp);
            temp = br.readLine();
            Main.tempnS=Integer.parseInt(temp);
            temp = br.readLine();
            Main.tempnD=Integer.parseInt(temp);
        }catch (Exception e) {
            System.err.println(e);
        }
    }

}
