/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Z Series
 */
public class Penyewaan {

    private String idPenyewaan;
    private Penyewa penyewa;
    private AlatSelam alat;
    private int jumlahUnit;
    private int durasiHari;
    private double totalBiaya;
    private String statusSewa;

    public Penyewaan(String idPenyewaan, Penyewa penyewa,
            AlatSelam alat, int jumlahUnit, int durasiHari,
            double totalBiaya, String statusSewa) {

        this.idPenyewaan = idPenyewaan;
        this.penyewa = penyewa;
        this.alat = alat;
        this.jumlahUnit = jumlahUnit;
        this.durasiHari = durasiHari;
        this.totalBiaya = totalBiaya;
        this.statusSewa = statusSewa;
    }

    public String getIdPenyewaan() {
        return idPenyewaan;
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public AlatSelam getAlat() {
        return alat;
    }

    public int getJumlahUnit() {
        return jumlahUnit;
    }

    public int getDurasiHari() {
        return durasiHari;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public String getStatusSewa() {
        return statusSewa;
    }

    public void setStatusSewa(String statusSewa) {
        this.statusSewa = statusSewa;
    }
}
