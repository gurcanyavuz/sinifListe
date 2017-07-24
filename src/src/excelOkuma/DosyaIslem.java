package excelOkuma;

/**
 * Kaynak :
 * http://stackoverflow.com/questions/4228975/how-to-randomize-arraylist
 * https://bitbucket.org/xerial/sqlite-jdbc#markdown-header-usage
 *
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DosyaIslem {

    private int satirSayisi;
    private XSSFSheet sayfa;
    Connection connection;
    Statement statement;

    private void veritabaninaBaglan() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = null;
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:liste.sqlite");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void veritabaniniKapat() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Sinif dizidindeki sınıfların toplam kapasitesi bulunuyor.
     *
     * @param sinifListe
     * @return sinif boş sayısı
     */
    public static int sinifToplamKapasiteBul(Object[] sinifListe) {
        int kapasite = 0;
        for (int sayac = 0; sayac < sinifListe.length; sayac++) {
            kapasite += ((Sinif) sinifListe[sayac]).getSinifMevcut();
        }
        return kapasite;
    }

    /**
     * Gönderilen listelerde bulunan toplam öğrenci sayısı bulunmaktadır.
     *
     * @param Listeler
     * @return
     */
    public static int ogrenciToplamSayiBul(Object[] Listeler) {
        int kapasite = 0;
        DosyaIslem dosya = new DosyaIslem();

        for (int sayac = 0; sayac < Listeler.length; sayac++) {
            kapasite += dosya.ogrenciSayisiniGetir(((Liste) Listeler[sayac]));
        }
        return kapasite;
    }

    /**
     * Tüm öğrencilerin sıralı listesi vermektedir.
     *
     * @param Listeler
     * @return
     */
    public ArrayList<Ogrenci> siraliOgrenciListesiAl(Object[] Listeler) {

        ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();

        for (Object liste : Listeler) {
            ogrenciler.addAll(tumOgrencileriAl((Liste) liste));
        }

        return ogrenciler;
    }

    /**
     * Sıralı liste geriye döndür.
     *
     * @param Listeler
     * @param siniflar
     * @return
     */
    public ArrayList<Ogrenci> ogrenciSiraliSinifAta(Object[] Listeler, Object[] siniflar) {

        ArrayList<Ogrenci> ogrenciler = siraliOgrenciListesiAl(Listeler);

        if (ogrenciToplamSayiBul(Listeler) > sinifToplamKapasiteBul(siniflar)) {
            JOptionPane.showMessageDialog(null, "Yeterli yer yok");
            return null;
        } else {

            for (int ogrenciSayac = 0; ogrenciSayac < ogrenciler.size();) {
                //System.out.println("boyut :: " + ogrenciler.size());
                for (Object sinif : siniflar) {
                    for (int sayac = 0; sayac < ((Sinif) sinif).getSinifMevcut(); sayac++) { // burası düzeltilecek
                        //      System.out.println("işte :: " + sayac + " " + ogrenciSayac);
                        ogrenciler.get(ogrenciSayac).setSinavSalonu((Sinif) sinif);
                        //    System.out.println(ogrenciSayac + " " + ogrenciler.get(ogrenciSayac).toString());
                        ogrenciSayac++;
                        if (!(ogrenciSayac < ogrenciler.size())) {
                            break;
                        }
                    }
                    if (!(ogrenciSayac < ogrenciler.size())) {
                        break;
                    }
                }
            }

            return ogrenciler;

        }// end-of-else

    }

    public void tutanakpdfYazdir(ArrayList<Ogrenci> ogrenciler, String dersAdi) throws JRException {
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;

        Map parameters = new HashMap();

        //jasperReport = (JasperReport) JRLoader.loadObjectFromFile("report6.jasper");
        // öğrencilere göre sıralanmış rapor
        //jasperReport = (JasperReport) JRLoader.loadObjectFromFile("No_rapor_1.jasper");
        //Collections.sort(ogrenciler, Ogrenci.OgrenciNoComparator);

        // sınıfa göre sıralanmış rapor
        jasperReport = (JasperReport) JRLoader.loadObjectFromFile("No_rapor_2.jasper");
        Collections.sort(ogrenciler, Ogrenci.OgrenciSinifComparator);

        parameters.put("DersAdi", dersAdi);

        // öğrenciler sınıfa göre sıralanmıştır.
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                new JRBeanCollectionDataSource(ogrenciler));

        JasperExportManager.exportReportToPdfFile(jasperPrint, "Liste.pdf");
        JasperViewer.viewReport(jasperPrint, false);
    }
    
        public void ogrenciNumaraliListepdfYazdir(ArrayList<Ogrenci> ogrenciler, String dersAdi) throws JRException {
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;

        Map parameters = new HashMap();

        //jasperReport = (JasperReport) JRLoader.loadObjectFromFile("report6.jasper");
        // öğrencilere göre sıralanmış rapor
        jasperReport = (JasperReport) JRLoader.loadObjectFromFile("No_rapor.jasper");
        Collections.sort(ogrenciler, Ogrenci.OgrenciNoComparator);

        // sınıfa göre sıralanmış rapor
        //jasperReport = (JasperReport) JRLoader.loadObjectFromFile("No_rapor_2.jasper");
        //Collections.sort(ogrenciler, Ogrenci.OgrenciSinifComparator);

        parameters.put("DersAdi", dersAdi);

        // öğrenciler sınıfa göre sıralanmıştır.
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                new JRBeanCollectionDataSource(ogrenciler));

        JasperExportManager.exportReportToPdfFile(jasperPrint, "Liste.pdf");
        JasperViewer.viewReport(jasperPrint, false);
    }
    

    public DosyaIslem() {
    }

    public DosyaIslem(String isim) {
        FileInputStream file;
        try {
            file = new FileInputStream(new File(isim));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            sayfa = workbook.getSheetAt(0);

            satirSayisi = satirSayisiBul(sayfa);

            System.out.println("satır sayısı : " + satirSayisi + " diğeri " + sayfa.getLastRowNum());

            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public DosyaIslem(Liste liste) {
        FileInputStream file;
        try {
            file = new FileInputStream(liste);

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            sayfa = workbook.getSheetAt(0);

            satirSayisi = satirSayisiBul(sayfa);
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private int satirSayisiBul(XSSFSheet sheet) {

//        int sayac = 0;
//
//        for (Row r : sheet) {
//            sayac++;
//        }
        return sheet.getLastRowNum() + 1;
    }

    private ArrayList<Cell> sutunAl(int sutunIndex) {
        ArrayList<Cell> hucreElemanlari = new ArrayList<Cell>();

        try {
            for (Row r : sayfa) {
                Cell hucre = r.getCell(sutunIndex);

                if (hucre != null) {
                    hucreElemanlari.add(hucre);
                }

            }
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Geçersiz giriş");
        }
        return hucreElemanlari;

    }

    private ArrayList<Cell> sutunAl(int sutunIndex, XSSFSheet sayfa) {
        ArrayList<Cell> hucreElemanlari = new ArrayList<Cell>();

        try {
            for (Row r : sayfa) {
                Cell hucre = r.getCell(sutunIndex);

                if (hucre != null) {
                    hucreElemanlari.add(hucre);
                }

            }
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Geçersiz giriş");
        }
        return hucreElemanlari;

    }

    protected void sutunYazdir(int sutunIndex) {
        ArrayList<Cell> hucreElemanlari = sutunAl(sutunIndex);
        for (Cell hucre : hucreElemanlari) {
            if (hucre.getCellType() == Cell.CELL_TYPE_STRING) {
                System.out.println(hucre.getStringCellValue());
            }
        }

    }

    /**
     *
     * Dosyadan öğrenci isim ve numaralarını almaktadır.
     *
     * @return
     */
    protected ArrayList<Ogrenci> ogrenciListesiAl(ArrayList<Cell> hucreler) {
        ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();

        for (int i = 1; i < satirSayisi; i++) {
            ogrenciler.add(new Ogrenci(hucreler.get(i).getStringCellValue(),
                    sutunAl(1).get(i).getStringCellValue()));
        }

        return ogrenciler;
    }

    protected ArrayList<Ogrenci> ogrenciListesiAl() {
        ArrayList<Ogrenci> ogrenciler = new ArrayList<Ogrenci>();

        for (int i = 1; i < satirSayisi; i++) {
            ogrenciler.add(new Ogrenci(sutunAl(1).get(i).getStringCellValue(),
                    sutunAl(0).get(i).getStringCellValue()));
        }

        return ogrenciler;
    }

    protected void ogrenciListesiYazdir(ArrayList<Ogrenci> ogrenciler) {

        for (Ogrenci ogrenci : ogrenciler) {
            System.out.println(ogrenci.toString());
        }

    }

    protected ArrayList<Ogrenci> KarisikListeAl() {
        ArrayList<Ogrenci> karisik = ogrenciListesiAl();
        long seed = System.nanoTime();
        Collections.shuffle(karisik, new Random(seed));
        return karisik;
    }

    /**
     * Listedeki öğrenci sayısını getirir.
     *
     * @return Öğrenci sayısı
     */
    public int ogrenciSayisiniGetir(Liste liste) {
        int kapasite = 0;
        try {
            //kapasite = ogrenciListesiAl(sutunAl(0, sayfaGetir(liste, 0))).size();
            kapasite = ogrenciListesiAl(sutunAl(0, sayfaGetir(liste))).size();
        } catch (IOException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return kapasite;

    }

    private XSSFSheet sayfaGetir(Liste liste, int sayfaNo) throws IOException {
        FileInputStream file;

        file = new FileInputStream(liste);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        sayfa = workbook.getSheetAt(sayfaNo);

        satirSayisi = satirSayisiBul(sayfa);
//        System.out.println("satır sayısı : " + satirSayisi + " diğeri " + sayfa.getLastRowNum());
        file.close();

        return sayfa;
    }

    private XSSFSheet sayfaGetir(Liste liste) throws IOException {
        FileInputStream file;

        file = new FileInputStream(liste);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        sayfa = workbook.getSheetAt(0);

        satirSayisi = satirSayisiBul(sayfa);
//        System.out.println("satır sayısı : " + satirSayisi + " diğeri " + sayfa.getLastRowNum());

        file.close();

        return sayfa;
    }

    /**
     * Listedeki tüm öğrencileri geriya döndürmektedir.
     *
     * @param liste
     * @return
     */
    public static ArrayList<Ogrenci> tumOgrencileriAl(Liste liste) {

        DosyaIslem islem = new DosyaIslem(liste);

        return islem.ogrenciListesiAl();
    }

    /**
     * sınıf isimleri sıralı şekilde getiriliyor.
     *
     * @return
     */
    public static ArrayList<Sinif> sinifListesiAl() {
        ArrayList<Sinif> siniflar = new ArrayList<>();
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("siniflar.properties"));

            for (String key : properties.stringPropertyNames()) {

                String value = properties.getProperty(key);
                siniflar.add(new Sinif(Integer.parseInt(value), key));
            }

            // arraylist sort ediliyor
            Collections.sort(siniflar, new Comparator<Sinif>() {
                @Override
                public int compare(Sinif sinif1, Sinif sinif2) {

                    return sinif1.getSinifAdi().compareTo(sinif2.getSinifAdi());
                }
            });

            return siniflar;
        } catch (IOException ex) {

            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * sınıf isimleri databaseden getirilmektedir.
     *
     * @return
     */
    public ArrayList<Sinif> sinifListesiGetir() {
        veritabaninaBaglan();
        ArrayList<Sinif> siniflar = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("select * from tumSiniflarSirali");
            while (rs.next()) {
                siniflar.add(new Sinif(rs.getInt(2), rs.getString(1)));
            }
            veritabaniniKapat();
            return siniflar;
        } catch (SQLException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }

    }
}
