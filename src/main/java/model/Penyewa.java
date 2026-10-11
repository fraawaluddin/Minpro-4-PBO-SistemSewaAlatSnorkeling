/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MyBook Z Series
 */
public class Penyewa {

    private String idPenyewa;
    private String namaPenyewa;
    private String noHp;

    public Penyewa(String idPenyewa, String namaPenyewa, String noHp) {
        this.idPenyewa = idPenyewa;
        this.namaPenyewa = namaPenyewa;
        this.noHp = noHp;
    }

    public String getIdPenyewa() {
        return idPenyewa;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public String getNoHp() {
        return noHp;
    }

    public void
            setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
