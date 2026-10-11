/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.AlatSelam;

/**
 *
 * @author MyBook Z Series
 */
public class TestAlatDAO {

    public static void main(String[] args) {
        AlatDAO dao = new AlatDAO();

        List<AlatSelam> daftarAlat = dao.getAllAlat();

        System.out.println("=== DATA ALAT DARI DATABASE ===");

        if (daftarAlat.isEmpty()) {
            System.out.println("Data alat kosong atau gagal dibaca.");
            return;
        }

        for (AlatSelam alat : daftarAlat) {
            alat.tampilkanInfo();
            System.out.println("------------------------------");
        }
    }
}
