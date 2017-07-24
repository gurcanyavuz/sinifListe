package excelOkuma;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Okuma {

    /**
     *
     * http://stackoverflow.com/questions/2922692/to-get-columns-from-excel-
     * files-using-apache-poi
     * http://www.java2s.com/Code/Java/Swing-JFC/DemonstrationofFiledialogboxes.htm
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        DosyaIslem.sinifListesiAl();

    }

    /**
     *
     * @param liste
     * @throws JRException
     */
    public static void pdfYazdir(Liste liste) throws JRException {
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;

        Map parameters = new HashMap();

        jasperDesign = JRXmlLoader.load("/home/gurcan/NetBeansProjects/raporlar/report2.jrxml");

        jasperReport = JasperCompileManager.compileReport(jasperDesign);

        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                new JRBeanCollectionDataSource(DosyaIslem.tumOgrencileriAl(liste)));



        JasperExportManager.exportReportToPdfFile(jasperPrint, "StudentInfo.pdf");
        JasperViewer.viewReport(jasperPrint);
    }
}
