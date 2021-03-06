/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import excelOkuma.DosyaIslem;
import excelOkuma.Sinif;
import java.util.Enumeration;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author gurcan
 */
public class SinifUi extends javax.swing.JDialog {

    Ana ana;

    /**
     * Creates new form SinifUi
     */
    public SinifUi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    SinifUi(Ana ana) {
        initComponents();

        siniflariDoldur();

        this.ana = ana;
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sinifEtiket = new javax.swing.JLabel();
        kapasiteEtiket = new javax.swing.JLabel();
        kapasiteAlani = new javax.swing.JTextField();
        sinifCombo = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        tamamButon = new javax.swing.JButton();
        iptalButonu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sınıf Ekle");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        sinifEtiket.setText("Sınıf Adı");

        kapasiteEtiket.setText("Kapasite");

        sinifCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinifComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(kapasiteEtiket)
                        .addGap(18, 18, 18)
                        .addComponent(kapasiteAlani))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sinifEtiket)
                        .addGap(18, 18, 18)
                        .addComponent(sinifCombo, 0, 191, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sinifEtiket)
                    .addComponent(sinifCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kapasiteEtiket)
                    .addComponent(kapasiteAlani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tamamButon.setText("TAMAM");
        tamamButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamamButonActionPerformed(evt);
            }
        });

        iptalButonu.setText("İPTAL");
        iptalButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iptalButonuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(tamamButon)
                .addGap(18, 18, 18)
                .addComponent(iptalButonu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tamamButon)
                    .addComponent(iptalButonu))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iptalButonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iptalButonuActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_iptalButonuActionPerformed

    private void tamamButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamamButonActionPerformed
        // TODO add your handling code here:
        sinifEkle();
    }//GEN-LAST:event_tamamButonActionPerformed

    private void sinifComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinifComboActionPerformed
        // TODO add your handling code here:
        sinifSec();
    }//GEN-LAST:event_sinifComboActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton iptalButonu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField kapasiteAlani;
    private javax.swing.JLabel kapasiteEtiket;
    private javax.swing.JComboBox sinifCombo;
    private javax.swing.JLabel sinifEtiket;
    private javax.swing.JButton tamamButon;
    // End of variables declaration//GEN-END:variables

    protected Sinif sinifAl() {
        if (kapasiteAlani.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Geçersiz Giriş");
            return null;
        } else {
            try {
                //return new Sinif(Integer.parseInt(kapasiteAlani.getText()), (String) sinifCombo.getSelectedItem());
                Sinif s = (Sinif) sinifCombo.getSelectedItem();
                s.setSinifMevcut(Integer.parseInt(kapasiteAlani.getText()));
                return s;
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Geçersiz Giriş");
                return null;
            }
        }
    }

    private void sinifEkle() {

        Sinif s = sinifAl();

        if (s != null) {

//            System.out.println("burada 1");
            if (sinifVarMi(s)) { // listede eleman varsa.
                // düzelt       
//                System.out.println("eleman varmış");
                JOptionPane.showMessageDialog(tamamButon, "Sınıf listede mevcut.", "UYARI", JOptionPane.ERROR_MESSAGE);

            } else { // eleman yoksa
                ana.getModel().addElement(s);
                ana.sayilariGuncelle();
                dispose();

            }

        }

    }

    private boolean sinifVarMi(Sinif s) {

        boolean bayrak = false;

        Enumeration<Sinif> e = ana.getModel().elements();

        while (e.hasMoreElements()) {

            Sinif tmp = (Sinif) e.nextElement();

            if (tmp.getSinifAdi().compareTo(s.getSinifAdi()) == 0) {
//                System.out.println("varmış");
                bayrak = true;
                break;
            } else { // eleman yoksa
                //System.out.println("bulunamadı");
                bayrak = false;
            }

        }
        return bayrak;
    }

    private void siniflariDoldur() {
        // debug
        DosyaIslem dosya = new DosyaIslem();
        //dosya.sinifListesiGetir();
        for (Sinif sinif : dosya.sinifListesiAl()) {
            sinifCombo.addItem(sinif);
        }

    }

    private void sinifSec() {
        int sinifMevcut = ((Sinif) sinifCombo.getSelectedItem()).getSinifMevcut();
        kapasiteAlani.setText(String.valueOf(sinifMevcut));
    }
}
