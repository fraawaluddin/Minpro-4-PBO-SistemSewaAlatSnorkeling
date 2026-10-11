/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.AlatSelam;
import model.AlatSnorkeling;
import model.AlatDiving;
import model.Penyewa;
import model.Penyewaan;
import view.SewaAlatView;
import dao.AlatDAO;
import dao.PenyewaDAO;
import dao.PenyewaanDAO;

/**
 *
 * @author MyBook Z Series
 */
public class SewaAlatController {

    private SewaAlatView view;
    private AlatDAO alatDAO = new AlatDAO();
    private PenyewaDAO penyewaDAO = new PenyewaDAO();
    private PenyewaanDAO penyewaanDAO = new PenyewaanDAO();
    private ArrayList<AlatSelam> daftarAlat = new ArrayList<>();
    private ArrayList<Penyewa> daftarPenyewa = new ArrayList<>();
    private ArrayList<Penyewaan> daftarPenyewaan = new ArrayList<>();

    
    public SewaAlatController(SewaAlatView view) {
        this.view = view;
    }
    
    public void jalankanProgram() {
        int pilihan;

        do {
            pilihan = view.tampilkanMenuUtama();

            switch (pilihan) {
                case 1:
                    kelolaDataAlat();
                    break;

                case 2:
                    buatPenyewaan();
                    break;

                case 3:
                    lihatDataPenyewaan();
                    break;

                case 4:
                    pengembalianAlat();
                    break;

                case 5:
                    System.out.println("Program Selesai");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia");
                    break;
            }
        } while (pilihan != 5);
    }

    private void kelolaDataAlat() {

        int pilihanAlat;

        do {
            pilihanAlat = view.tampilkanMenuAlat();

            switch (pilihanAlat) {
                case 1:
                    tambahAlat();
                    break;

                case 2:
                    lihatDataAlat();
                    break;

                case 3:
                    ubahAlat();
                    break;

                case 4:
                    hapusAlat();
                    break;

                case 5:
                    System.out.println("Kembali ke menu utama");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia");
                    break;
            }

        } while (pilihanAlat != 5);
    }

    
    private void lihatDataAlat() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|               DAFTAR ALAT                |");
        System.out.println("+------------------------------------------+");

        ArrayList<AlatSelam> dataDatabase =
                new ArrayList<>(alatDAO.getAllAlat());

        if (dataDatabase.isEmpty()) {
            System.out.println("Belum ada data alat di database.");
            return;
        }

        for (AlatSelam alat : dataDatabase) {
            alat.tampilkanInfo(true);
            System.out.println("+------------------------------------------+");
        }

        System.out.println("\n--- DETAIL LENGKAP ALAT ---");

        for (AlatSelam alat : dataDatabase) {
            alat.tampilkanInfo();
            System.out.println("+------------------------------------------+");
        }
    }
   

    private boolean idAlatSudahAda(String idAlat) {
        return alatDAO.cariAlatById(idAlat) != null;
    }

    
    private AlatSelam cariAlat(String idAlat) {
        return alatDAO.cariAlatById(idAlat);
    }

    
    private void buatPenyewaan() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|             BUAT PENYEWAAN               |");
        System.out.println("+------------------------------------------+");

        String namaPenyewa = view.inputNamaPenyewa();
        String noHp = view.inputNoHp();
        String idAlat = view.inputIdAlat();

        AlatSelam alat = cariAlat(idAlat);

        if (alat == null) {
            System.out.println("Alat tidak ditemukan");
            return;
        }

        System.out.println("Alat ditemukan: " + alat.getNamaAlat());

        int jumlahUnit = view.inputJumlahUnit();

        if (jumlahUnit <= 0) {
            System.out.println("Jumlah unit harus lebih dari 0");
            return;
        }

        if (!alat.cekKetersediaan(jumlahUnit)) {
            System.out.println("Jumlah unit melebihi stok yang tersedia");
            return;
        }

        int durasiHari = view.inputDurasiHari();

        if (durasiHari <= 0) {
            System.out.println("Durasi sewa harus lebih dari 0");
            return;
        }

        String idPenyewa = penyewaDAO.generateIdPenyewa();
        String idPenyewaan = penyewaanDAO.generateIdPenyewaan();

        if (idPenyewa == null || idPenyewaan == null) {
            System.out.println("Gagal membuat ID transaksi.");
            return;
        }

        Penyewa penyewaBaru = new Penyewa(
                idPenyewa, namaPenyewa, noHp
        );

        double totalBiaya = alat.getTarifPerHari()
                * jumlahUnit * durasiHari;

        Penyewaan penyewaanBaru = new Penyewaan(
                idPenyewaan,
                penyewaBaru,
                alat,
                jumlahUnit,
                durasiHari,
                totalBiaya,
                "AKTIF"
        );

        if (!penyewaDAO.tambahPenyewa(penyewaBaru)) {
            System.out.println("Gagal menyimpan data penyewa.");
            return;
        }

        if (!penyewaanDAO.tambahPenyewaan(
                penyewaanBaru, idPenyewa)) {
            System.out.println("Gagal menyimpan transaksi penyewaan.");
            return;
        }

        int stokBaru = alat.getJumlahTersedia() - jumlahUnit;

        if (!alatDAO.updateJumlahTersedia(
                alat.getIdAlat(), stokBaru)) {
            System.out.println(
                    "Transaksi tersimpan, tetapi stok gagal diperbarui."
            );
            System.out.println(
                    "Periksa database sebelum mencoba menyewa lagi."
            );
            return;
        }

        alat.setJumlahTersedia(stokBaru);
        daftarPenyewa.add(penyewaBaru);
        daftarPenyewaan.add(penyewaanBaru);

        System.out.println("Penyewaan berhasil dibuat");
        System.out.println("ID Penyewaan : " + idPenyewaan);
        System.out.println("Total Biaya  : " + totalBiaya);
    }
    
    private void lihatDataPenyewaan() {
        System.out.println();
        System.out.println("+--------------------------------------------------+");
        System.out.println("|              DATA PENYEWAAN ALAT                 |");
        System.out.println("+--------------------------------------------------+");

        java.util.List<java.util.Map<String, Object>> daftar =
                penyewaanDAO.getAllPenyewaan();

        if (daftar.isEmpty()) {
            System.out.println("Belum ada data penyewaan.");
            return;
        }

        for (java.util.Map<String, Object> data : daftar) {
            System.out.println("------------------------------------------");
            System.out.println("ID Penyewaan : "
                    + data.get("id_penyewaan"));
            System.out.println("ID Penyewa   : "
                    + data.get("id_penyewa"));
            System.out.println("Nama Penyewa : "
                    + data.get("nama_penyewa"));
            System.out.println("ID Alat      : "
                    + data.get("id_alat"));
            System.out.println("Nama Alat    : "
                    + data.get("nama_alat"));
            System.out.println("Jumlah Unit  : "
                    + data.get("jumlah_unit"));
            System.out.println("Durasi       : "
                    + data.get("durasi_hari") + " hari");
            System.out.println("Total Biaya  : Rp"
                    + data.get("total_biaya"));
            System.out.println("Status       : "
                    + data.get("status_sewa"));
        }

        System.out.println("------------------------------------------");
    }
      
    private void pengembalianAlat() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|            PENGEMBALIAN ALAT             |");
        System.out.println("+------------------------------------------+");

        String idPenyewaan = view.inputIdPenyewaan();

        java.util.Map<String, Object> data =
                penyewaanDAO.cariDetailPenyewaan(idPenyewaan);

        if (data == null) {
            System.out.println("Data penyewaan tidak ditemukan.");
            return;
        }

        String status = (String) data.get("status_sewa");

        if (!"AKTIF".equalsIgnoreCase(status)) {
            System.out.println("Penyewaan sudah dikembalikan.");
            return;
        }

        String idAlat = (String) data.get("id_alat");
        int jumlahUnit = (Integer) data.get("jumlah_unit");

        AlatSelam alat = alatDAO.cariAlatById(idAlat);

        if (alat == null) {
            System.out.println("Data alat tidak ditemukan.");
            return;
        }

        int stokBaru = alat.getJumlahTersedia() + jumlahUnit;

        if (!alatDAO.updateJumlahTersedia(idAlat, stokBaru)) {
            System.out.println("Gagal memperbarui stok alat.");
            return;
        }

        if (!penyewaanDAO.updateStatusPenyewaan(
                idPenyewaan, "DIKEMBALIKAN")) {

            // Jika status gagal diperbarui, pulihkan stok sebelumnya.
            alatDAO.updateJumlahTersedia(
                    idAlat, stokBaru - jumlahUnit);

            System.out.println("Gagal memperbarui status penyewaan.");
            return;
        }

        System.out.println("Alat berhasil dikembalikan.");
        System.out.println("ID Penyewaan : " + idPenyewaan);
        System.out.println("Stok alat telah diperbarui.");
        System.out.println("Status penyewaan: DIKEMBALIKAN");
    } 
    
    private void tambahAlat() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|               TAMBAH ALAT                |");
        System.out.println("+------------------------------------------+");

        String idAlat = view.inputIdAlat();

        if (idAlatSudahAda(idAlat)) {
            System.out.println("ID Alat sudah digunakan");
            return;
        }

        int jenisAlat = view.inputJenisAlat();

        if (jenisAlat != 1 && jenisAlat != 2) {
            System.out.println("Jenis alat tidak tersedia");
            return;
        }

        String namaAlat = view.inputNamaAlat();
        String ukuran = view.inputUkuran();

        double tarifPerHari = view.inputTarifPerHari();

        String kondisi = view.inputKondisi();

        int jumlahTersedia = view.inputJumlahTersedia();

        if (jumlahTersedia < 0) {
            System.out.println("Jumlah tersedia tidak boleh negatif");
            return;
        }

        if (jenisAlat == 1) {
            String jenisPerlengkapan = view.inputJenisPerlengkapan();

            AlatSnorkeling alatBaru = new AlatSnorkeling(
                    idAlat,
                    namaAlat,
                    ukuran,
                    tarifPerHari,
                    kondisi,
                    jumlahTersedia,
                    jenisPerlengkapan
            );

            if (alatDAO.tambahAlat(alatBaru)) {
                System.out.println("Alat berhasil ditambahkan ke database");
            } else {
                System.out.println("Gagal menambahkan alat ke database");
            }
        } else {
            int kapasitasTabung = view.inputKapasitasTabung();

            AlatDiving alatBaru = new AlatDiving(
                    idAlat,
                    namaAlat,
                    ukuran,
                    tarifPerHari,
                    kondisi,
                    jumlahTersedia,
                    kapasitasTabung
            );

            if (alatDAO.tambahAlat(alatBaru)) {
                System.out.println("Alat berhasil ditambahkan ke database");
            } else {
                System.out.println("Gagal menambahkan alat ke database");
            }
        }

    }

    
    private void ubahAlat() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|                UBAH ALAT                 |");
        System.out.println("+------------------------------------------+");

        String idAlat = view.inputIdAlat();

        AlatSelam alat = alatDAO.cariAlatById(idAlat);

        if (alat == null) {
            System.out.println("Alat tidak ditemukan");
            return;
        }

        System.out.println("Alat ditemukan: " + alat.getNamaAlat());

        String namaAlat = view.inputNamaAlat();
        String ukuran = view.inputUkuran();
        double tarifPerHari = view.inputTarifPerHari();
        String kondisi = view.inputKondisi();
        int jumlahTersedia = view.inputJumlahTersedia();

        if (jumlahTersedia < 0) {
            System.out.println("Jumlah tersedia tidak boleh negatif");
            return;
        }

        alat.setNamaAlat(namaAlat);
        alat.setUkuran(ukuran);
        alat.setTarifPerHari(tarifPerHari);
        alat.setKondisi(kondisi);
        alat.setJumlahTersedia(jumlahTersedia);

        if (alat instanceof AlatSnorkeling) {
            AlatSnorkeling snorkeling = (AlatSnorkeling) alat;
            String jenisPerlengkapan = view.inputJenisPerlengkapan();
            snorkeling.setJenisPerlengkapan(jenisPerlengkapan);

        } else if (alat instanceof AlatDiving) {
            AlatDiving diving = (AlatDiving) alat;
            int kapasitasTabung = view.inputKapasitasTabung();
            diving.setKapasitasTabung(kapasitasTabung);
        }

        if (alatDAO.ubahAlat(alat)) {
            System.out.println("Alat berhasil diubah di database");
        } else {
            System.out.println("Gagal mengubah alat di database");
        }
    }   
        
    
    private void hapusAlat() {
        System.out.println();
        System.out.println("+------------------------------------------+");
        System.out.println("|               HAPUS ALAT                 |");
        System.out.println("+------------------------------------------+");

        String idAlat = view.inputIdAlat();

        AlatSelam alat = alatDAO.cariAlatById(idAlat);

        if (alat == null) {
            System.out.println("Alat tidak ditemukan");
            return;
        }

        System.out.println("Alat ditemukan: " + alat.getNamaAlat());

        if (alatDAO.hapusAlat(idAlat)) {
            System.out.println("Alat berhasil dihapus dari database");
        } else {
            System.out.println("Gagal menghapus alat dari database");
        }
    }

}
