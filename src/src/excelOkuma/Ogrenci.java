package excelOkuma;

import java.util.Comparator;

public class Ogrenci {

    String isim;
    String numara;
    Sinif sinavSalonu;
    String sinif;
    boolean kaldiMi;

    public Ogrenci(String isim, String numara) {
        super();
        this.isim = isim.toUpperCase();
        this.numara = numara;
    }

    public Ogrenci(String isim, String numara, boolean kaldiMi) {
        super();
        this.isim = isim.toUpperCase();
        this.numara = numara;
        this.kaldiMi = kaldiMi;
    }

    public static Comparator<Ogrenci> OgrenciNoComparator = new Comparator<Ogrenci>() {

        @Override
        public int compare(Ogrenci o1, Ogrenci o2) {
            String ogNo1 = o1.getNumara().toUpperCase();
            String ogNo2 = o2.getNumara().toUpperCase();

            //ascending order
            return ogNo1.compareTo(ogNo2);
        }
    };

    public static Comparator<Ogrenci> OgrenciSinifComparator = new Comparator<Ogrenci>() {

        @Override
        public int compare(Ogrenci o1, Ogrenci o2) {
            String ogSinif1 = o1.getSinif();
            String ogSinif2 = o2.getSinif();

            //ascending order
            return ogSinif1.compareTo(ogSinif2);
        }
    };

    public String getIsim() {
        return isim.toUpperCase();
    }

    public void setIsim(String isim) {
        this.isim = isim.toUpperCase();
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public boolean isKaldiMi() {
        return kaldiMi;
    }

    public void setKaldiMi(boolean kaldiMi) {
        this.kaldiMi = kaldiMi;
    }

    public Sinif getSinavSalonu() {
        return sinavSalonu;
    }

    public void setSinavSalonu(Sinif sinavSalonu) {
        this.sinavSalonu = sinavSalonu;
    }

    public String getSinif() {
        return sinavSalonu.getSinifAdi();
    }

    @Override
    public String toString() {
        return "Ogrenci [isim=" + isim + ", numara=" + numara + "]";
    }
}
