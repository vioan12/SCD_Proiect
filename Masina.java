/**
 * Created by ioan on 12/2/17.
 */
public class Masina
{
    private String producator,model,anul,numarinmatriculare;
    private int address;
    Masina(int valueofaddress, String valueofproducator, String valueofmodel, String valueofanul, String valueofnumarinmatriculare)
    {
        this.address=valueofaddress;
        this.producator=valueofproducator;
        this.model=valueofmodel;
        this.anul=valueofanul;
        this.numarinmatriculare=valueofnumarinmatriculare;
    }
    protected int Get_address()
    {
        return this.address;
    }
    protected String Get_producator()
    {
        return this.producator;
    }
    protected String Get_model()
    {
        return this.model;
    }
    protected String Get_anul()
    {
        return this.anul;
    }
    protected String Get_numarinmatriculare()
    {
        return this.numarinmatriculare;
    }
}
