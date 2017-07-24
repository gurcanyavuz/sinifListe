package excelOkuma;

public class Sinif {

    private int sinifMevcut;
    private String sinifAdi;

    public Sinif() {
        super();

        this.sinifMevcut = 0;
        this.sinifAdi = "";
    }

    public Sinif(int sinifMevcut, String sinifAdi) {
        super();

        this.sinifMevcut = sinifMevcut;
        this.sinifAdi = sinifAdi.toUpperCase();
    }

    public int getSinifMevcut() {
        return sinifMevcut;
    }

    public void setSinifMevcut(int sinifMevcut) {
        this.sinifMevcut = sinifMevcut;
    }

    public String getSinifAdi() {
        return sinifAdi;
    }

    public void setSinifAdi(String sinifAdi) {
        this.sinifAdi = sinifAdi.toUpperCase();
    }

    @Override
    public String toString() {

        return sinifAdi + " sınıfı kapasitesi " + sinifMevcut + " kişiliktir.";
    }
}
