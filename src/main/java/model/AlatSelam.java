/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Z Series
 */
public abstract class AlatSelam implements DapatDisewa {

    private String idAlat;
    private String namaAlat;
    private String ukuran;
    private double tarifPerHari;
    private String kondisi;
    private int jumlahTersedia;

    public AlatSelam(String idAlat, String namaAlat, String ukuran, double tarifPerHari, String kondisi, int jumlahTersedia) {

        this.idAlat = idAlat;
        setNamaAlat(namaAlat);
        setUkuran(ukuran);
        setTarifPerHari(tarifPerHari);
        setKondisi(kondisi);
        setJumlahTersedia(jumlahTersedia);
    }

    public String getIdAlat() {
        return idAlat;
    }

    public String getNamaAlat() {
        return namaAlat;
    }

    public String getUkuran() {
        return ukuran;
    }

    public double getTarifPerHari() {
        return tarifPerHari;
    }

    public String getKondisi() {
        return kondisi;
    }

    public int getJumlahTersedia() {
        return jumlahTersedia;
    }

    public void setNamaAlat(String namaAlat) {
        if (namaAlat != null && !namaAlat.trim().isEmpty()) {
            this.namaAlat = namaAlat;
        } else {
            System.out.println("Nama alat tidak boleh kosong.");
        }
    }

    public void setUkuran(String ukuran) {
        if (ukuran != null && !ukuran.trim().isEmpty()) {
            this.ukuran = ukuran;
        } else {
            System.out.println("Ukuran alat tidak boleh kosong.");
        }
    }

    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari > 0) {
            this.tarifPerHari = tarifPerHari;
        } else {
            System.out.println("Tarif per hari harus lebih dari 0.");
        }
    }

    public void setKondisi(String kondisi) {
        if (kondisi != null && !kondisi.trim().isEmpty()) {
            this.kondisi = kondisi;
        } else {
            System.out.println("Kondisi alat tidak boleh kosong.");
        }
    }

    public void setJumlahTersedia(int jumlahTersedia) {
        if (jumlahTersedia >= 0) {
            this.jumlahTersedia = jumlahTersedia;
        } else {
            System.out.println("Jumlah tersedia tidak boleh negatif.");
        }
    }

    public abstract void tampilkanInfo();
    
    public final void tampilkanStatusTerdaftar() {
        System.out.println("Status: Alat terdaftar dalam sistem.");
    }
    
    public void tampilkanDataDasar() {
        System.out.println("ID Alat        : " + getIdAlat());
        System.out.println("Nama Alat      : " + getNamaAlat());
        System.out.println("Ukuran         : " + getUkuran());
        System.out.println("Tarif Per Hari : " + getTarifPerHari());
        System.out.println("Kondisi        : " + getKondisi());
        System.out.println("Jumlah Tersedia: " + getJumlahTersedia());
    }
    
    public void tampilkanInfo(boolean ringkas) {
        if (ringkas) {
            System.out.println("ID Alat        : " + getIdAlat());
            System.out.println("Nama Alat      : " + getNamaAlat());
            System.out.println("Tarif Per Hari : " + getTarifPerHari());
        } else {
            tampilkanInfo();
        }
    }

}
