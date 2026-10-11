/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;

/**
 *
 * @author MyBook Z Series
 */
public class SewaAlatView {

    private Scanner input = new Scanner(System.in);
    
    private void garis() {
        System.out.println("+------------------------------------------+");
    }
       
    public int tampilkanMenuUtama() {
        System.out.println();
        garis();
        System.out.println("|    SISTEM SEWA ALAT SNORKELING & DIVING  |");
        garis();
        System.out.println("|  1. Kelola Data Alat                     |");
        System.out.println("|  2. Buat Penyewaan                       |");
        System.out.println("|  3. Lihat Data Penyewaan                 |");
        System.out.println("|  4. Pengembalian Alat                    |");
        System.out.println("|  5. Keluar                               |");
        garis();
        System.out.print("Pilih menu: ");

        while (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka");
            input.next();
            System.out.print("Pilih menu: ");
        }

        return input.nextInt();
    }

    public int tampilkanMenuAlat() {
        System.out.println();
        garis();
        System.out.println("|             KELOLA DATA ALAT             |");
        garis();
        System.out.println("|  1. Tambah Alat                          |");
        System.out.println("|  2. Lihat Alat                           |");
        System.out.println("|  3. Ubah Alat                            |");
        System.out.println("|  4. Hapus Alat                           |");
        System.out.println("|  5. Kembali                              |");
        garis();
        System.out.print("Pilih menu: ");

        while (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka");
            input.next();
            System.out.print("Pilih menu: ");
        }

        return input.nextInt();
    }

    public String inputNamaAlat() {
        input.nextLine();

        while (true) {
            System.out.print("Nama Alat: ");
            String nama = input.nextLine();

            if (!nama.trim().isEmpty()) {
                return nama;
            }

            System.out.println("Nama alat tidak boleh kosong");
        }
    }

    public String inputIdAlat() {
        while (true) {
            System.out.print("ID Alat: ");
            String id = input.next();

            if (!id.trim().isEmpty()) {
                return id;
            }

            System.out.println("ID alat tidak boleh kosong");
        }
    }

    public String inputUkuran() {
        while (true) {
            System.out.print("Ukuran: ");
            String ukuran = input.next();

            if (!ukuran.trim().isEmpty()) {
                return ukuran;
            }

            System.out.println("Ukuran alat tidak boleh kosong");
        }
    }

    public double inputTarifPerHari() {
        while (true) {
            System.out.print("Tarif Per Hari: ");

            if (!input.hasNextDouble()) {
                System.out.println("Tarif harus berupa angka");
                input.next();
                continue;
            }

            double tarif = input.nextDouble();

            if (tarif > 0) {
                return tarif;
            }

            System.out.println("Tarif per hari harus lebih dari 0");
        }
    }

    public String inputKondisi() {
        input.nextLine();

        while (true) {
            System.out.print("Kondisi (Baik/Kurang Baik): ");
            String kondisi = input.nextLine();

            if (!kondisi.trim().isEmpty()) {
                return kondisi;
            }

            System.out.println("Kondisi alat tidak boleh kosong");
        }
    }

    public int inputJumlahTersedia() {
        while (true) {
            System.out.print("Jumlah Tersedia: ");

            if (!input.hasNextInt()) {
                System.out.println("Jumlah tersedia harus berupa angka.");
                input.next();
                continue;
            }

            int jumlah = input.nextInt();

            if (jumlah >= 0) {
                return jumlah;
            }

            System.out.println("Jumlah tersedia tidak boleh negatif.");
        }
    }
    
    public int inputJenisAlat() {
        System.out.println();
        garis();
        System.out.println("|               JENIS ALAT                 |");
        garis();
        System.out.println("|  1. Alat Snorkeling                      |");
        System.out.println("|  2. Alat Diving                          |");
        garis();
        System.out.print("Pilih jenis alat: ");

        while (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka");
            input.next();
            System.out.print("Pilih jenis alat: ");
        }

        return input.nextInt();
    }
    
    public String inputJenisPerlengkapan() {
        input.nextLine();

        while (true) {
            System.out.print("Jenis Perlengkapan [contoh: Set Masker dan Snorkel]: ");
            String jenisPerlengkapan = input.nextLine();

            if (!jenisPerlengkapan.trim().isEmpty()) {
                return jenisPerlengkapan;
            }

            System.out.println("Jenis perlengkapan tidak boleh kosong");
        }
    }

    public int inputKapasitasTabung() {
        while (true) {
            System.out.print("Kapasitas Tabung [contoh: 12]: ");

            if (!input.hasNextInt()) {
                System.out.println("Kapasitas tabung harus berupa angka.");
                input.next();
                continue;
            }

            int kapasitas = input.nextInt();

            if (kapasitas > 0) {
                return kapasitas;
            }

            System.out.println("Kapasitas tabung harus lebih dari 0.");
        }
    }

    public String inputNamaPenyewa() {
        input.nextLine();

        while (true) {
            System.out.print("Nama Penyewa: ");
            String nama = input.nextLine();

            if (!nama.trim().isEmpty()) {
                return nama;
            }

            System.out.println("Nama penyewa tidak boleh kosong");
        }
    }

    public String inputNoHp() {
        System.out.print("No HP: ");
        return input.next();
    }

    public int inputJumlahUnit() {
        while (true) {
            System.out.print("Jumlah Unit: ");

            if (!input.hasNextInt()) {
                System.out.println("Jumlah unit harus berupa angka.");
                input.next();
                continue;
            }

            int jumlah = input.nextInt();

            if (jumlah > 0) {
                return jumlah;
            }

            System.out.println("Jumlah unit harus lebih dari 0.");
        }
    }

    public int inputDurasiHari() {
        while (true) {
            System.out.print("Durasi Sewa (hari): ");

            if (!input.hasNextInt()) {
                System.out.println("Durasi sewa harus berupa angka.");
                input.next();
                continue;
            }

            int durasi = input.nextInt();

            if (durasi > 0) {
                return durasi;
            }

            System.out.println("Durasi sewa harus lebih dari 0 hari.");
        }
    }

    public String inputIdPenyewaan() {
        System.out.print("ID Penyewaan: ");
        return input.next();
    }
}
