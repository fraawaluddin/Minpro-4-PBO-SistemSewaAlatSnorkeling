/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Penyewaan;

/**
 *
 * @author MyBook Z Series
 */
public class PenyewaanDAO {

    public boolean tambahPenyewaan(
            Penyewaan penyewaan, String idPenyewa) {

        String sql = "INSERT INTO penyewaan "
                + "(id_penyewaan, id_penyewa, id_alat, "
                + "jumlah_unit, durasi_hari, total_biaya, status_sewa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, penyewaan.getIdPenyewaan());
            ps.setString(2, idPenyewa);
            ps.setString(3, penyewaan.getAlat().getIdAlat());
            ps.setInt(4, penyewaan.getJumlahUnit());
            ps.setInt(5, penyewaan.getDurasiHari());
            ps.setDouble(6, penyewaan.getTotalBiaya());
            ps.setString(7, penyewaan.getStatusSewa());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Gagal menyimpan penyewaan: " + e.getMessage()
            );
            return false;
        }
    }
    
    
    public String generateIdPenyewaan() {
        String sql = "SELECT COUNT(*) AS total FROM penyewaan";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                int nomorBerikutnya = rs.getInt("total") + 1;
                return String.format("SW%03d", nomorBerikutnya);
            }
        } catch (SQLException e) {
            System.out.println(
                    "Gagal membuat ID penyewaan: " + e.getMessage()
            );
        }

        return null;
    }
    
    
    public boolean updateStatusPenyewaan(
            String idPenyewaan, String statusBaru) {

        String sql = "UPDATE penyewaan "
                + "SET status_sewa = ? "
                + "WHERE id_penyewaan = ?";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, statusBaru);
            ps.setString(2, idPenyewaan);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Gagal memperbarui status penyewaan: "
                    + e.getMessage()
            );
            return false;
        }
    }
    
    
    public String cariIdAlatByPenyewaan(String idPenyewaan) {
        String sql = "SELECT id_alat FROM penyewaan "
                + "WHERE id_penyewaan = ?";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, idPenyewaan);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("id_alat");
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mencari penyewaan: " + e.getMessage()
            );
        }

        return null;
    }
    
    
    public java.util.Map<String, Object> cariDetailPenyewaan(
            String idPenyewaan) {

        String sql = "SELECT id_alat, jumlah_unit, status_sewa "
                + "FROM penyewaan WHERE id_penyewaan = ?";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, idPenyewaan);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    java.util.Map<String, Object> data =
                            new java.util.HashMap<>();

                    data.put("id_alat", rs.getString("id_alat"));
                    data.put("jumlah_unit", rs.getInt("jumlah_unit"));
                    data.put("status_sewa", rs.getString("status_sewa"));

                    return data;
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengambil detail penyewaan: " + e.getMessage()
            );
        }

        return null;
    }
    
    
    public List<java.util.Map<String, Object>> getAllPenyewaan() {
        List<java.util.Map<String, Object>> daftar =
                new ArrayList<>();

        String sql = "SELECT p.id_penyewaan, "
                + "p.id_penyewa, py.nama_penyewa, "
                + "p.id_alat, a.nama_alat, "
                + "p.jumlah_unit, p.durasi_hari, "
                + "p.total_biaya, p.status_sewa "
                + "FROM penyewaan p "
                + "JOIN penyewa py "
                + "ON p.id_penyewa = py.id_penyewa "
                + "JOIN alat a ON p.id_alat = a.id_alat "
                + "ORDER BY p.id_penyewaan";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                java.util.Map<String, Object> data =
                        new java.util.HashMap<>();

                data.put("id_penyewaan",
                        rs.getString("id_penyewaan"));
                data.put("id_penyewa",
                        rs.getString("id_penyewa"));
                data.put("nama_penyewa",
                        rs.getString("nama_penyewa"));
                data.put("id_alat",
                        rs.getString("id_alat"));
                data.put("nama_alat",
                        rs.getString("nama_alat"));
                data.put("jumlah_unit",
                        rs.getInt("jumlah_unit"));
                data.put("durasi_hari",
                        rs.getInt("durasi_hari"));
                data.put("total_biaya",
                        rs.getDouble("total_biaya"));
                data.put("status_sewa",
                        rs.getString("status_sewa"));

                daftar.add(data);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal mengambil data penyewaan: "
                    + e.getMessage()
            );
        }

        return daftar;
    }

}
