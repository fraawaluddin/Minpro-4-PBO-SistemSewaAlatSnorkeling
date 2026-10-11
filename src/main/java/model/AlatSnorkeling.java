/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Z Series
 */
public class AlatSnorkeling extends AlatSelam {

    private String jenisPerlengkapan;

    public AlatSnorkeling(String idAlat, String namaAlat, String ukuran,
            double tarifPerHari, String kondisi, int jumlahTersedia,
            String jenisPerlengkapan) {

        super(idAlat, namaAlat, ukuran, tarifPerHari, kondisi, jumlahTersedia);
        setJenisPerlengkapan(jenisPerlengkapan);
    }

    public String getJenisPerlengkapan() {
        return jenisPerlengkapan;
    }

    public void setJenisPerlengkapan(String jenisPerlengkapan) {
        if (jenisPerlengkapan != null && !jenisPerlengkapan.trim().isEmpty()) {
            this.jenisPerlengkapan = jenisPerlengkapan;
        } else {
            System.out.println("Jenis perlengkapan tidak boleh kosong.");
        }
    }

    @Override
    public void tampilkanInfo() {
        tampilkanDataDasar();
        System.out.println("Jenis Perlengkapan: " + getJenisPerlengkapan());
    }
    
    @Override
    public boolean cekKetersediaan(int jumlahUnit) {
        return jumlahUnit > 0 && jumlahUnit <= getJumlahTersedia();
    }

}
