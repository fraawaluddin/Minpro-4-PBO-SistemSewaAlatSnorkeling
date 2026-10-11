/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;

/**
 *
 * @author MyBook Z Series
 */
public class TestKoneksi {

    public static void main(String[] args) {
        System.out.println("Mengecek koneksi database...");

        try (Connection conn = Koneksi.getConnection()) {
            if (conn != null) {
                System.out.println(
                        "BERHASIL: Database siap digunakan."
                );
            } else {
                System.out.println(
                        "GAGAL: Periksa konfigurasi database."
                );
            }
        } catch (Exception e) {
            System.out.println(
                    "Terjadi kesalahan: " + e.getMessage()
            );
        }
    }
}
