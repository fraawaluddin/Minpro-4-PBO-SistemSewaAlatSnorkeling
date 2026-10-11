/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.AlatSelam;
import model.AlatSnorkeling;
import model.AlatDiving;
/**
 *
 * @author MyBook Z Series
 */
public class AlatDAO {

    public List<AlatSelam> getAllAlat() {
        List<AlatSelam> daftarAlat = new ArrayList<>();

        String sql = "SELECT * FROM alat";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                String jenisAlat = rs.getString("jenis_alat");

                AlatSelam alat;

                if ("Snorkeling".equalsIgnoreCase(jenisAlat)) {
                    alat = new AlatSnorkeling(
                        rs.getString("id_alat"),
                        rs.getString("nama_alat"),
                        rs.getString("ukuran"),
                        rs.getDouble("tarif_per_hari"),
                        rs.getString("kondisi"),
                        rs.getInt("jumlah_tersedia"),
                        rs.getString("jenis_perlengkapan")
                    );
                } else if ("Diving".equalsIgnoreCase(jenisAlat)) {
                    alat = new AlatDiving(
                        rs.getString("id_alat"),
                        rs.getString("nama_alat"),
                        rs.getString("ukuran"),
                        rs.getDouble("tarif_per_hari"),
                        rs.getString("kondisi"),
                        rs.getInt("jumlah_tersedia"),
                        rs.getInt("kapasitas_tabung")
                    );
                } else {
                    continue;
                }

                daftarAlat.add(alat);
            }

        } catch (SQLException e) {
            System.out.println(
                "Gagal mengambil data alat: " + e.getMessage()
            );
        }

        return daftarAlat;
    }
    
    
    public AlatSelam cariAlatById(String idAlat) {
        String sql = "SELECT * FROM alat WHERE id_alat = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idAlat);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String jenisAlat = rs.getString("jenis_alat");

                    if ("Snorkeling".equalsIgnoreCase(jenisAlat)) {
                        return new AlatSnorkeling(
                                rs.getString("id_alat"),
                                rs.getString("nama_alat"),
                                rs.getString("ukuran"),
                                rs.getDouble("tarif_per_hari"),
                                rs.getString("kondisi"),
                                rs.getInt("jumlah_tersedia"),
                                rs.getString("jenis_perlengkapan")
                        );
                    } else if ("Diving".equalsIgnoreCase(jenisAlat)) {
                        return new AlatDiving(
                                rs.getString("id_alat"),
                                rs.getString("nama_alat"),
                                rs.getString("ukuran"),
                                rs.getDouble("tarif_per_hari"),
                                rs.getString("kondisi"),
                                rs.getInt("jumlah_tersedia"),
                                rs.getInt("kapasitas_tabung")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari alat: " + e.getMessage());
        }

        return null;
    }   
    
    public boolean tambahAlat(AlatSelam alat) {
        String sql = "INSERT INTO alat "
                + "(id_alat, nama_alat, ukuran, tarif_per_hari, "
                + "kondisi, jumlah_tersedia, jenis_alat, "
                + "jenis_perlengkapan, kapasitas_tabung) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alat.getIdAlat());
            ps.setString(2, alat.getNamaAlat());
            ps.setString(3, alat.getUkuran());
            ps.setDouble(4, alat.getTarifPerHari());
            ps.setString(5, alat.getKondisi());
            ps.setInt(6, alat.getJumlahTersedia());

            if (alat instanceof AlatSnorkeling) {
                AlatSnorkeling snorkeling = (AlatSnorkeling) alat;

                ps.setString(7, "Snorkeling");
                ps.setString(8, snorkeling.getJenisPerlengkapan());
                ps.setNull(9, java.sql.Types.INTEGER);

            } else if (alat instanceof AlatDiving) {
                AlatDiving diving = (AlatDiving) alat;

                ps.setString(7, "Diving");
                ps.setNull(8, java.sql.Types.VARCHAR);
                ps.setInt(9, diving.getKapasitasTabung());

            } else {
                return false;
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan alat: "
                    + e.getMessage());
            return false;
        }
    }
       
    public boolean ubahAlat(AlatSelam alat) {
        String sql = "UPDATE alat SET "
                + "nama_alat = ?, ukuran = ?, tarif_per_hari = ?, "
                + "kondisi = ?, jumlah_tersedia = ?, "
                + "jenis_perlengkapan = ?, kapasitas_tabung = ? "
                + "WHERE id_alat = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alat.getNamaAlat());
            ps.setString(2, alat.getUkuran());
            ps.setDouble(3, alat.getTarifPerHari());
            ps.setString(4, alat.getKondisi());
            ps.setInt(5, alat.getJumlahTersedia());

            if (alat instanceof AlatSnorkeling) {
                AlatSnorkeling snorkeling = (AlatSnorkeling) alat;
                ps.setString(6, snorkeling.getJenisPerlengkapan());
                ps.setNull(7, java.sql.Types.INTEGER);

            } else if (alat instanceof AlatDiving) {
                AlatDiving diving = (AlatDiving) alat;
                ps.setNull(6, java.sql.Types.VARCHAR);
                ps.setInt(7, diving.getKapasitasTabung());

            } else {
                return false;
            }

            ps.setString(8, alat.getIdAlat());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah alat: " + e.getMessage());
            return false;
        }
    }
       
    public boolean hapusAlat(String idAlat) {
        String sql = "DELETE FROM alat WHERE id_alat = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idAlat);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus alat: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean updateJumlahTersedia(String idAlat, int jumlahTersedia) {
        String sql = "UPDATE alat SET jumlah_tersedia = ? WHERE id_alat = ?";

        try (
            Connection conn = Koneksi.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, jumlahTersedia);
            ps.setString(2, idAlat);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui stok: " + e.getMessage());
            return false;
        }
    }

}
