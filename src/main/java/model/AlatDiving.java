/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Z Series
 */
public class AlatDiving extends AlatSelam {

    private int kapasitasTabung;

    public AlatDiving(String idAlat, String namaAlat, String ukuran,
            double tarifPerHari, String kondisi, int jumlahTersedia,
            int kapasitasTabung) {

        super(idAlat, namaAlat, ukuran, tarifPerHari, kondisi, jumlahTersedia);
        setKapasitasTabung(kapasitasTabung);
    }

    public int getKapasitasTabung() {
        return kapasitasTabung;
    }

    public void setKapasitasTabung(int kapasitasTabung) {
        if (kapasitasTabung > 0) {
            this.kapasitasTabung = kapasitasTabung;
        } else {
            System.out.println("Kapasitas tabung harus lebih dari 0.");
        }
    }

    @Override
    public void tampilkanInfo() {
        tampilkanDataDasar();
        System.out.println("Kapasitas Tabung: " + getKapasitasTabung());
    }
    
    @Override
    public boolean cekKetersediaan(int jumlahUnit) {
        return jumlahUnit > 0 && jumlahUnit <= getJumlahTersedia();
    }
    
}
