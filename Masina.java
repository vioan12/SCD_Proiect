/**
 * Created by ioan on 12/2/17.
 */
public class Masina
{
    private String producator,model,anul,numarinmatriculare;
    Masina(String valueofproducator, String valueofmodel, String valueofanul, String valueofnumarinmatriculare)
    {
        this.producator=valueofproducator;
        this.model=valueofmodel;
        this.anul=valueofanul;
        this.numarinmatriculare=valueofnumarinmatriculare;
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
