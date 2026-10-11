/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Penyewa;
/**
 *
 * @author MyBook Z Series
 */
public class PenyewaDAO {

    public boolean tambahPenyewa(Penyewa penyewa) {
        String sql = "INSERT INTO penyewa "
                + "(id_penyewa, nama_penyewa, no_hp) "
                + "VALUES (?, ?, ?)";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, penyewa.getIdPenyewa());
            ps.setString(2, penyewa.getNamaPenyewa());
            ps.setString(3, penyewa.getNoHp());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menyimpan data penyewa: " + e.getMessage()
            );
            return false;
        }
    }
    
    
    public String generateIdPenyewa() {
        String sql = "SELECT COUNT(*) AS total FROM penyewa";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                int nomorBerikutnya = rs.getInt("total") + 1;
                return String.format("PNYW%03d", nomorBerikutnya);
            }
        } catch (SQLException e) {
            System.out.println(
                    "Gagal membuat ID penyewa: " + e.getMessage()
            );
        }

        return null;
    }

}
