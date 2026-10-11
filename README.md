# Minpro 4 PBO - Sistem Manajemen Sewa Alat Snorkeling & Diving

**Nama** : Muhammad Farel Awaluddin

**NIM** : 2509116055

**Kelas** : B

---

## Deskripsi Singkat Program

Program ini merupakan Sistem Manajemen Sewa Alat Snorkeling & Diving berbasis Java yang dijalankan melalui console/CLI. Program dikembangkan dari Mini Project 3 dengan menambahkan integrasi database MySQL menggunakan JDBC (*Java Database Connectivity*).

Pada Mini Project 4, penyimpanan data yang sebelumnya menggunakan `ArrayList` sebagai penyimpanan utama dikembangkan menjadi penyimpanan berbasis database. Program menerapkan `PreparedStatement` untuk menjalankan perintah SQL, DAO (*Data Access Object*) untuk memisahkan akses database, serta pola arsitektur MVC (*Model-View-Controller*).

Pengguna dapat melakukan beberapa proses, yaitu:

- Menambahkan data alat snorkeling dan diving.
- Menampilkan seluruh data alat.
- Mengubah data alat.
- Menghapus data alat.
- Membuat penyewaan alat.
- Menampilkan data penyewaan.
- Melakukan pengembalian alat.
- Mengakhiri program melalui menu keluar.

Program menggunakan database `db_sewa_alat` yang terdiri dari tabel `alat`, `penyewa`, dan `penyewaan`. Data alat dan transaksi disimpan ke database sehingga dapat dibaca kembali ketika program dijalankan ulang.

## Teknologi yang Digunakan

| Teknologi | Fungsi |
|---|---|
| Java | Bahasa pemrograman untuk membangun aplikasi. |
| Apache NetBeans | IDE untuk mengembangkan dan menjalankan program. |
| Maven | Mengelola konfigurasi project dan dependensi. |
| MySQL | Menyimpan data alat, penyewa, dan transaksi penyewaan. |
| JDBC | Menghubungkan aplikasi Java dengan database. |
| PreparedStatement | Menjalankan perintah SQL menggunakan parameter. |
| DAO | Memisahkan proses akses dan pengelolaan data database. |
| Laragon | Menjalankan layanan database MySQL pada lingkungan lokal. |

## Struktur Project

Project disusun menggunakan beberapa package agar setiap bagian program memiliki tanggung jawab yang berbeda.

```text
SistemSewaAlatSnorkeling
└── src/main/java
    ├── main
    │   └── Main.java
    ├── config
    │   ├── Koneksi.java
    │   └── TestKoneksi.java
    ├── model
    │   ├── AlatSelam.java
    │   ├── AlatSnorkeling.java
    │   ├── AlatDiving.java
    │   ├── Penyewa.java
    │   └── Penyewaan.java
    ├── dao
    │   ├── AlatDAO.java
    │   ├── PenyewaDAO.java
    │   ├── PenyewaanDAO.java
    │   └── TestAlatDAO.java
    ├── view
    │   └── SewaAlatView.java
    └── controller
        └── SewaAlatController.java

db_sewa_alat.sql
pom.xml
README.md
```

### Pembagian Package

| Package | Class | Fungsi |
|---|---|---|
| `main` | `Main` | Menjadi titik awal untuk menjalankan aplikasi. |
| `config` | `Koneksi` | Mengatur koneksi antara aplikasi Java dan database MySQL. |
| `config` | `TestKoneksi` | Menguji koneksi database. |
| `model` | `AlatSelam` | Menjadi class dasar untuk objek alat selam. |
| `model` | `AlatSnorkeling` | Merepresentasikan alat snorkeling beserta atribut khususnya. |
| `model` | `AlatDiving` | Merepresentasikan alat diving beserta atribut khususnya. |
| `model` | `Penyewa` | Menyimpan data penyewa. |
| `model` | `Penyewaan` | Merepresentasikan data transaksi penyewaan. |
| `dao` | `AlatDAO` | Menangani operasi database untuk data alat. |
| `dao` | `PenyewaDAO` | Menangani penyimpanan data penyewa. |
| `dao` | `PenyewaanDAO` | Menangani penyimpanan, pencarian, dan pembaruan data penyewaan. |
| `view` | `SewaAlatView` | Menangani tampilan menu dan input pengguna. |
| `controller` | `SewaAlatController` | Mengatur alur program dan menghubungkan View, Model, serta DAO. |

## Struktur Database

Database yang digunakan dalam program adalah `db_sewa_alat`, dengan tiga tabel utama.

| Tabel | Fungsi |
|---|---|
| `alat` | Menyimpan informasi alat, tarif, kondisi, stok, jenis alat, dan atribut khusus. |
| `penyewa` | Menyimpan identitas penyewa dan nomor telepon. |
| `penyewaan` | Menyimpan transaksi, jumlah unit, durasi, total biaya, dan status penyewaan. |

Tabel `penyewaan` memiliki relasi dengan tabel `penyewa` dan `alat` melalui foreign key. Relasi tersebut menghubungkan transaksi dengan penyewa serta alat yang disewa.

File `db_sewa_alat.sql` disediakan sebagai berkas SQL untuk membuat database, tabel, dan memasukkan data awal alat. File ini dapat digunakan sebagai dasar untuk menyiapkan database sebelum aplikasi dijalankan.

## Alur Program

Program dimulai dari `Main.java`, yang membuat objek `SewaAlatView` dan `SewaAlatController`. Pengguna kemudian berinteraksi dengan sistem melalui menu utama.

Secara umum, alur pengolahan data adalah sebagai berikut:

1. Pengguna memilih menu melalui View.
2. Controller menerima input dan menjalankan logika program.
3. Controller memanggil method pada DAO sesuai operasi yang diperlukan.
4. DAO menjalankan perintah SQL melalui JDBC dan `PreparedStatement`.
5. Database memproses perintah tersebut.
6. Hasil pengolahan dikembalikan untuk ditampilkan melalui View.

### Menu Utama

| Pilihan | Fitur | Keterangan |
|---|---|---|
| 1 | **Kelola Data Alat** | Menambahkan, menampilkan, mengubah, dan menghapus data alat. |
| 2 | **Buat Penyewaan** | Menyimpan data penyewa dan membuat transaksi penyewaan. |
| 3 | **Lihat Data Penyewaan** | Menampilkan daftar transaksi dari database. |
| 4 | **Pengembalian Alat** | Memproses pengembalian, memperbarui status transaksi, dan menyesuaikan stok. |
| 5 | **Keluar** | Mengakhiri program. |

## Integrasi Database dengan JDBC

JDBC digunakan agar aplikasi Java dapat berkomunikasi dengan database MySQL. Koneksi database diatur melalui class `Koneksi` dalam package `config`.

### 1. Koneksi Database

Class `Koneksi` menyediakan method `getConnection()` untuk membuat koneksi menggunakan `DriverManager`. Konfigurasi tersebut menggunakan URL database, username, dan password MySQL.

### 2. PreparedStatement

`PreparedStatement` digunakan untuk menjalankan perintah SQL dengan parameter. Penerapannya membantu memisahkan perintah SQL dari nilai input yang diberikan pengguna.

Operasi yang menggunakan mekanisme ini mencakup penambahan data, pencarian data berdasarkan ID, perubahan data, dan penghapusan data.

### 3. Data Access Object (DAO)

Pola DAO digunakan untuk memisahkan operasi database dari logika utama program.

- `AlatDAO` menangani operasi CRUD (*Create, Read, Update, Delete*) pada data alat.
- `PenyewaDAO` menangani penyimpanan dan pembuatan ID penyewa.
- `PenyewaanDAO` menangani transaksi penyewaan, pencarian detail transaksi, perubahan status, dan pengambilan daftar penyewaan.

Dengan pemisahan tersebut, controller tidak perlu menuliskan seluruh perintah SQL secara langsung untuk setiap operasi database.

### 4. ResultSet

`ResultSet` digunakan untuk membaca hasil query dari database. Data yang diperoleh kemudian dipetakan menjadi objek model atau struktur data yang dibutuhkan oleh program.

### 5. Penyimpanan Data

Berbeda dari Mini Project 3 yang menggunakan `ArrayList` sebagai penyimpanan utama selama program berjalan, Mini Project 4 menggunakan database MySQL sebagai tempat penyimpanan data alat, penyewa, dan transaksi.

Dengan demikian, data yang sudah disimpan dapat dibaca kembali ketika aplikasi dijalankan ulang, selama data tersebut masih tersedia di database.

## Pengelolaan Penyewaan dan Pengembalian

### Membuat Penyewaan

Fitur ini digunakan untuk mencatat penyewa dan transaksi penyewaan alat. Sistem memeriksa data alat serta jumlah stok yang tersedia sebelum transaksi diproses.

Jika stok mencukupi, sistem menyimpan data penyewa dan transaksi ke database, menghitung total biaya berdasarkan tarif, jumlah unit, dan durasi sewa, kemudian memperbarui stok alat.

### Melihat Data Penyewaan

Fitur ini mengambil daftar transaksi dari database. Informasi yang ditampilkan mencakup ID penyewaan, ID dan nama penyewa, ID dan nama alat, jumlah unit, durasi, total biaya, serta status transaksi.

Pengambilan data menggunakan query yang menggabungkan tabel `penyewaan`, `penyewa`, dan `alat` agar informasi transaksi dapat ditampilkan bersama data terkait.

### Pengembalian Alat

Fitur pengembalian mencari transaksi berdasarkan ID penyewaan dan memeriksa status transaksi tersebut.

Jika transaksi masih aktif, sistem memperbarui status menjadi `DIKEMBALIKAN` dan menambahkan kembali jumlah alat yang disewa ke stok. Transaksi yang sudah dikembalikan tidak dapat diproses kembali sehingga penambahan stok berulang dapat dicegah.

## Penerapan Konsep OOP

Program tetap menerapkan konsep *Object-Oriented Programming* (OOP) yang digunakan dalam pengembangan Mini Project sebelumnya.

### 1. Encapsulation

Encapsulation diterapkan dengan membatasi akses terhadap atribut class menggunakan access modifier, seperti `private`. Akses terhadap atribut dilakukan melalui method yang disediakan, misalnya getter dan setter.

Pendekatan ini membantu menjaga pengelolaan data objek agar dilakukan melalui method yang sesuai.

### 2. Inheritance

Inheritance diterapkan melalui hubungan antara `AlatSelam` sebagai class dasar dan dua subclass, yaitu `AlatSnorkeling` dan `AlatDiving`.

```text
AlatSelam
├── AlatSnorkeling
└── AlatDiving
```

Kedua subclass mewarisi atribut dan method umum dari `AlatSelam`, serta memiliki atribut tambahan sesuai karakteristik masing-masing jenis alat.

### 3. Polymorphism

Polymorphism memungkinkan objek dari subclass diperlakukan melalui tipe class induknya. Dalam sistem ini, konsep tersebut berkaitan dengan pengelolaan objek alat snorkeling dan diving yang sama-sama diturunkan dari `AlatSelam`.

Method yang di-override pada subclass dapat memberikan implementasi sesuai karakteristik masing-masing jenis alat.

### 4. Abstraction

Abstraction digunakan untuk memisahkan struktur umum alat dari detail khusus masing-masing jenis alat. Class dasar `AlatSelam` menjadi acuan bagi subclass, sedangkan atribut dan perilaku khusus ditempatkan pada class yang sesuai.

## Penerapan MVC (Model-View-Controller)

MVC digunakan untuk memisahkan tanggung jawab program.

### Model

Package `model` berisi class yang merepresentasikan objek dalam sistem, seperti `AlatSelam`, `AlatSnorkeling`, `AlatDiving`, `Penyewa`, dan `Penyewaan`.

### View

Package `view` berisi `SewaAlatView`, yang menangani tampilan menu serta penerimaan input pengguna.

### Controller

Package `controller` berisi `SewaAlatController`, yang mengatur alur program, menjalankan logika penyewaan dan pengembalian, serta menghubungkan View dengan Model dan DAO.

### Hubungan MVC dengan DAO

Ketika pengguna memilih menu, View meneruskan input untuk diproses oleh Controller. Controller kemudian menggunakan DAO untuk menjalankan operasi database dan mengolah hasilnya sebelum ditampilkan kembali kepada pengguna.

Pemisahan ini membuat tanggung jawab tampilan, logika program, representasi data, dan akses database lebih terorganisir.

## Cara Menjalankan Program

1. Pastikan JDK dan Apache NetBeans telah terpasang.
2. Jalankan layanan MySQL melalui Laragon atau lingkungan database yang digunakan.
3. Buka phpMyAdmin atau perangkat pengelola MySQL lainnya.
4. Jalankan file `db_sewa_alat.sql` pada database yang belum disiapkan. Jika database sudah berisi data, periksa isi database terlebih dahulu agar tidak terjadi konflik primary key saat memasukkan data awal.
5. Buka project `SistemSewaAlatSnorkeling` melalui Apache NetBeans.
6. Pastikan konfigurasi koneksi pada `config/Koneksi.java` sesuai dengan lingkungan MySQL yang digunakan.
7. Lakukan **Clean and Build** untuk memeriksa proses kompilasi project.
8. Jalankan program melalui `main/Main.java`.
9. Pilih fitur yang tersedia melalui menu utama.

## Kesimpulan

Mini Project 4 mengembangkan Sistem Manajemen Sewa Alat Snorkeling & Diving dari penyimpanan berbasis `ArrayList` menjadi aplikasi yang terintegrasi dengan database MySQL. Penggunaan JDBC, `PreparedStatement`, dan DAO memungkinkan proses pengelolaan data dilakukan melalui database, sedangkan konsep OOP dan MVC membantu menjaga struktur program tetap terorganisir.

File SQL disertakan untuk membantu menyiapkan struktur database dan data awal yang diperlukan untuk menjalankan aplikasi.
