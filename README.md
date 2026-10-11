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
    │   ├── DapatDisewa.java
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
| `model` | `DapatDisewa` | Mendefinisikan kontrak method cekKetersediaan(int jumlahUnit) untuk memeriksa ketersediaan alat. |
| `model` | `Penyewa` | Menyimpan data penyewa. |
| `model` | `Penyewaan` | Merepresentasikan data transaksi penyewaan. |
| `dao` | `AlatDAO` | Menangani operasi database untuk data alat. |
| `dao` | `PenyewaDAO` | Menangani penyimpanan data penyewa. |
| `dao` | `PenyewaanDAO` | Menangani penyimpanan, pencarian, dan pembaruan data penyewaan. |
| `view` | `SewaAlatView` | Menangani tampilan menu dan input pengguna. |
| `controller` | `SewaAlatController` | Mengatur alur program dan menghubungkan View, Model, serta DAO. |

### Tampilan Struktur Project di NetBeans

Struktur project pada Apache NetBeans dibagi ke dalam beberapa package, yaitu `config`, `controller`, `dao`, `main`, `model`, dan `view`. Setiap package memiliki tanggung jawab masing-masing untuk mengatur koneksi database, logika program, pengelolaan data, objek, serta tampilan aplikasi.

**Gambar 1. Struktur Project Sistem Manajemen Sewa Alat Snorkeling & Diving di NetBeans**

<img width="295" height="521" alt="image" src="https://github.com/user-attachments/assets/f3e6da08-c147-46e5-a299-29a83f805dc1" />

## Struktur Database

Database yang digunakan dalam program adalah `db_sewa_alat`, dengan tiga tabel utama.

| Tabel | Fungsi |
|---|---|
| `alat` | Menyimpan informasi alat, tarif, kondisi, stok, jenis alat, dan atribut khusus. |
| `penyewa` | Menyimpan identitas penyewa dan nomor telepon. |
| `penyewaan` | Menyimpan transaksi, jumlah unit, durasi, total biaya, dan status penyewaan. |

Tabel `penyewaan` memiliki relasi dengan tabel `penyewa` dan `alat` melalui foreign key. Relasi tersebut menghubungkan transaksi dengan penyewa serta alat yang disewa.

**Gambar 1. Struktur Database `db_sewa_alat` pada phpMyAdmin**

<img width="190" height="146" alt="image" src="https://github.com/user-attachments/assets/ab9ec9c5-44e1-4a4e-8887-9f85a1076e48" />

Screenshot menunjukkan database `db_sewa_alat` yang memiliki tiga tabel utama, yaitu `alat` untuk menyimpan data alat, `penyewa` untuk menyimpan identitas penyewa, dan `penyewaan` untuk menyimpan transaksi penyewaan.

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

Gambar 1. Tampilan Menu Utama Sistem Manajemen Sewa Alat Snorkeling & Diving

Screenshot berikut menunjukkan menu utama aplikasi yang dijalankan melalui console. Pengguna dapat memilih fitur pengelolaan data alat, pembuatan penyewaan, penampilan data penyewaan, pengembalian alat, atau keluar dari program.

<img width="357" height="188" alt="image" src="https://github.com/user-attachments/assets/6c7ab249-0a70-4e80-a776-967458c0f310" />

## Integrasi Database dengan JDBC

JDBC digunakan agar aplikasi Java dapat berkomunikasi dengan database MySQL. Koneksi database diatur melalui class `Koneksi` dalam package `config`.

### 1. Koneksi Database

Class `Koneksi` menyediakan method `getConnection()` untuk membuat koneksi menggunakan `DriverManager`. Konfigurasi tersebut menggunakan URL database, username, dan password MySQL.

Gambar 1. Implementasi Koneksi Database

<img width="665" height="611" alt="image" src="https://github.com/user-attachments/assets/c6bea206-b85b-44ef-aa30-28c3f971fe42" />

Class Koneksi digunakan untuk menghubungkan aplikasi Java dengan database MySQL melalui JDBC. Method getConnection() memanfaatkan DriverManager untuk membuat koneksi, sedangkan SQLException digunakan untuk menangani kesalahan koneksi.

Gambar 2. Hasil Pengujian Koneksi Database

<img width="948" height="457" alt="image" src="https://github.com/user-attachments/assets/fe6d89ff-9364-4f9c-b0b2-dee6ff447e3d" />

Pengujian dilakukan untuk memastikan aplikasi Java dapat terhubung dengan database MySQL db_sewa_alat. Output menunjukkan pesan BERHASIL: Database siap digunakan. dan BUILD SUCCESS, yang menandakan pengujian koneksi selesai dengan sukses.

### 2. PreparedStatement

`PreparedStatement` digunakan untuk menjalankan perintah SQL dengan parameter. Penerapannya membantu memisahkan perintah SQL dari nilai input yang diberikan pengguna.

Operasi yang menggunakan mekanisme ini mencakup penambahan data, pencarian data berdasarkan ID, perubahan data, dan penghapusan data.

**Gambar 1. Implementasi Query PreparedStatement pada Method tambahAlat()**

<img width="481" height="157" alt="Cuplikan layar 2026-10-12 004022" src="https://github.com/user-attachments/assets/c043f2d6-2a1d-4dea-b363-3fc5e577f5ce" />

Kode tersebut menyusun perintah SQL `INSERT` untuk menambahkan data alat ke database. Placeholder `?` digunakan sebagai parameter, kemudian objek `PreparedStatement` dibuat untuk menjalankan query tersebut.

**Gambar 2. Pengisian Parameter PreparedStatement pada Method tambahAlat()**

<img width="460" height="486" alt="Cuplikan layar 2026-10-12 004052" src="https://github.com/user-attachments/assets/d91e03fe-f17c-402e-98f6-2faf2e3421c5" />

Parameter query diisi menggunakan method seperti `setString()`, `setDouble()`, dan `setInt()`. Sistem juga membedakan alat snorkeling dan diving untuk mengisi atribut khususnya, kemudian menjalankan query melalui `executeUpdate()`.

### 3. Data Access Object (DAO)

Pola DAO digunakan untuk memisahkan operasi database dari logika utama program.

- `AlatDAO` menangani operasi CRUD (*Create, Read, Update, Delete*) pada data alat.
- `PenyewaDAO` menangani penyimpanan dan pembuatan ID penyewa.
- `PenyewaanDAO` menangani transaksi penyewaan, pencarian detail transaksi, perubahan status, dan pengambilan daftar penyewaan.

Gambar 1. Implementasi Method getAllAlat()

<img width="460" height="812" alt="image" src="https://github.com/user-attachments/assets/6370104a-6b4d-407c-9459-ecdac7bc9271" />

Method getAllAlat() digunakan untuk mengambil seluruh data alat dari tabel alat pada database MySQL. Data dibaca menggunakan ResultSet, kemudian diproses berdasarkan jenis alat. Sistem membentuk objek AlatSnorkeling atau AlatDiving sesuai nilai jenis_alat, lalu menambahkan objek tersebut ke dalam daftar daftarAlat untuk dikembalikan kepada pemanggil.

Gambar 2. Hasil Pengujian Menampilkan Data Alat

<img width="382" height="766" alt="image" src="https://github.com/user-attachments/assets/81ba3733-95f1-4cab-8132-dba17822eaa0" />

Output program menunjukkan bahwa sistem berhasil mengambil dan menampilkan data alat dari database MySQL. Data yang ditampilkan mencakup Tabung Diving (DIV01) dengan stok 5 unit dan Set Snorkeling (SNK01) dengan stok 10 unit, beserta tarif, kondisi, dan atribut khusus masing-masing alat.

Gambar 3. Implementasi Method cariAlatById()

<img width="557" height="793" alt="image" src="https://github.com/user-attachments/assets/f1a90621-fee7-450e-89aa-d414db0011c6" />

Method cariAlatById() digunakan untuk mencari data alat berdasarkan ID alat. Sistem menjalankan query menggunakan PreparedStatement, kemudian membaca hasil pencarian melalui ResultSet. Jika data ditemukan, sistem membentuk objek AlatSnorkeling atau AlatDiving sesuai nilai jenis_alat. Jika data tidak ditemukan, method mengembalikan nilai null.

Gambar 4. Implementasi Method tambahAlat()

<img width="520" height="813" alt="image" src="https://github.com/user-attachments/assets/b91d2936-1b11-47b6-86c1-d9722a8fbff1" />

Method tambahAlat() digunakan untuk menambahkan data alat ke tabel alat pada database MySQL. Data dimasukkan menggunakan perintah SQL INSERT dengan parameter PreparedStatement. Sistem menentukan jenis alat, yaitu snorkeling atau diving, untuk menyimpan atribut khusus yang sesuai. Jika proses penyimpanan berhasil, method mengembalikan nilai true.

Gambar 5. Implementasi Method ubahAlat()

<img width="580" height="742" alt="image" src="https://github.com/user-attachments/assets/7f2446af-3b9b-4b9c-a0e1-61c8dec63a9e" />

Method ubahAlat() digunakan untuk memperbarui informasi alat berdasarkan ID alat. Sistem menjalankan perintah SQL UPDATE menggunakan PreparedStatement untuk mengubah nama, ukuran, tarif, kondisi, stok, dan atribut khusus alat. Kondisi WHERE id_alat = ? memastikan perubahan dilakukan pada data alat yang sesuai dengan ID yang diberikan.

Gambar 6. Implementasi Method hapusAlat()

<img width="580" height="292" alt="image" src="https://github.com/user-attachments/assets/990c63a0-d4a3-47b3-90aa-1ca42cb59d0c" />

Method hapusAlat() digunakan untuk menghapus data alat dari tabel alat berdasarkan ID alat. Sistem menjalankan perintah SQL DELETE menggunakan PreparedStatement. Jika penghapusan berhasil, method mengembalikan nilai true, sedangkan jika terjadi kesalahan SQL, sistem menampilkan pesan kesalahan dan mengembalikan nilai false.

Gambar 7. Implementasi Method updateJumlahTersedia()

<img width="630" height="322" alt="image" src="https://github.com/user-attachments/assets/a7af920c-2cdc-4bd4-a49e-00f1df9bf0f9" />

Method updateJumlahTersedia() digunakan untuk memperbarui jumlah stok alat pada tabel alat berdasarkan ID alat. Sistem menjalankan perintah SQL UPDATE dengan PreparedStatement, kemudian mengisi parameter jumlah stok dan ID alat. Method mengembalikan nilai true apabila pembaruan berhasil dilakukan.

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

**Gambar 1. Penerapan Encapsulation melalui Atribut Private**

<img width="308" height="147" alt="image" src="https://github.com/user-attachments/assets/5a8843a7-4151-4c44-b2e1-b7ac924d1760" />

Atribut pada class `AlatSelam` menggunakan access modifier `private`, sehingga atribut tidak dapat diakses secara langsung dari luar class. Pendekatan ini membantu membatasi akses terhadap data objek.

**Gambar 2. Implementasi Method Getter pada Class AlatSelam**

<img width="305" height="77" alt="image" src="https://github.com/user-attachments/assets/9d33d301-9ef1-4849-a54a-bdb4cdf90c0a" />

Method `getNamaAlat()` digunakan untuk memperoleh nilai atribut `namaAlat` melalui method publik, tanpa mengakses atribut tersebut secara langsung dari luar class.

**Gambar 3. Implementasi Method Setter dengan Validasi Data**

<img width="623" height="170" alt="image" src="https://github.com/user-attachments/assets/a2068c6d-78ae-41af-9e53-a8c4c15c8dcc" />

Method `setNamaAlat()` mengatur nilai atribut `namaAlat` dengan memeriksa agar nilai tidak `null` dan tidak kosong setelah spasi di awal atau akhir dihapus. Jika nilai tidak memenuhi ketentuan, sistem menampilkan pesan bahwa nama alat tidak boleh kosong.

### 2. Inheritance

Inheritance diterapkan melalui hubungan antara `AlatSelam` sebagai class dasar dan dua subclass, yaitu `AlatSnorkeling` dan `AlatDiving`.

```text
AlatSelam
├── AlatSnorkeling
└── AlatDiving
```

Kedua subclass mewarisi atribut dan method umum dari `AlatSelam`, serta memiliki atribut tambahan sesuai karakteristik masing-masing jenis alat.

**Gambar 1. Deklarasi Class Induk AlatSelam**

<img width="567" height="27" alt="Cuplikan layar 2026-10-12 005338" src="https://github.com/user-attachments/assets/308e5bb8-6cfa-4ebe-a39a-c9c9f68a85d6" />

Class `AlatSelam` merupakan class induk abstrak yang mengimplementasikan interface `DapatDisewa`. Class ini menjadi dasar bagi class alat snorkeling dan diving.

**Gambar 2. Penerapan Inheritance pada Class AlatSnorkeling**

<img width="492" height="32" alt="Cuplikan layar 2026-10-12 005351" src="https://github.com/user-attachments/assets/2456366e-2a39-41b1-8e90-fed0acdba2b0" />

Class `AlatSnorkeling` menggunakan keyword `extends AlatSelam` untuk mewarisi atribut dan perilaku umum dari class induknya.

**Gambar 3. Penerapan Inheritance pada Class AlatDiving**

<img width="448" height="20" alt="Cuplikan layar 2026-10-12 005401" src="https://github.com/user-attachments/assets/ea2f275b-0b4f-4a2b-985e-f020b28fb02b" />

Class `AlatDiving` juga mewarisi atribut dan perilaku umum dari `AlatSelam`, sehingga kedua jenis alat dapat menggunakan struktur dasar yang sama dengan atribut khusus masing-masing.

### 3. Polymorphism

Polymorphism memungkinkan objek dari subclass diperlakukan melalui tipe class induknya. Dalam sistem ini, konsep tersebut berkaitan dengan pengelolaan objek alat snorkeling dan diving yang sama-sama diturunkan dari `AlatSelam`.

Method yang di-override pada subclass dapat memberikan implementasi sesuai karakteristik masing-masing jenis alat.

**Gambar 1. Implementasi Method Overriding pada Class AlatSnorkeling**

<img width="743" height="130" alt="Cuplikan layar 2026-10-12 010158" src="https://github.com/user-attachments/assets/d346e6cb-5bc7-4c4e-8caf-6fd3e0bd0821" />

Class `AlatSnorkeling` melakukan overriding terhadap method `tampilkanInfo()` dari class induk `AlatSelam`. Implementasi ini menampilkan data dasar alat serta informasi khusus berupa jenis perlengkapan snorkeling.

**Gambar 2. Implementasi Method Overriding pada Class AlatDiving**

<img width="691" height="122" alt="Cuplikan layar 2026-10-12 010210" src="https://github.com/user-attachments/assets/6a3d729b-f9eb-44ec-b4d9-c6a5e8bef7e4" />

Class `AlatDiving` mengimplementasikan kembali method `tampilkanInfo()` untuk menampilkan data dasar alat serta informasi khusus berupa kapasitas tabung diving.

**Gambar 3. Implementasi Method Overloading pada Class AlatSelam**

<img width="695" height="210" alt="Cuplikan layar 2026-10-12 010335" src="https://github.com/user-attachments/assets/ae562921-d9b8-470e-9827-b7521f52b32c" />

Class `AlatSelam` memiliki dua method bernama `tampilkanInfo()`, yaitu method tanpa parameter dan method dengan parameter `boolean`. Perbedaan parameter tersebut memungkinkan informasi alat ditampilkan dalam bentuk ringkas atau lengkap.

### 4. Abstraction

Abstraction digunakan untuk memisahkan struktur umum alat dari detail khusus masing-masing jenis alat. Class dasar `AlatSelam` menjadi acuan bagi subclass, sedangkan atribut dan perilaku khusus ditempatkan pada class yang sesuai.

**Gambar 1. Deklarasi Abstract Class AlatSelam**

<img width="577" height="22" alt="image" src="https://github.com/user-attachments/assets/f524bda1-6978-4b78-84a5-e208c5351888" />

Class `AlatSelam` dideklarasikan menggunakan keyword `abstract` sehingga berfungsi sebagai class dasar yang tidak dibuat menjadi objek secara langsung. Class ini menjadi struktur umum bagi class alat snorkeling dan diving.

**Gambar 2. Deklarasi Abstract Method tampilkanInfo()**

<img width="381" height="28" alt="image" src="https://github.com/user-attachments/assets/a145ef7f-5a94-4d3d-8f8f-61c2cec3ddc5" />

Method `tampilkanInfo()` dideklarasikan sebagai abstract method tanpa implementasi pada class induk. Implementasi method tersebut diberikan oleh class turunan `AlatSnorkeling` dan `AlatDiving` sesuai karakteristik masing-masing alat.

### 5. Interface

Interface diterapkan melalui `DapatDisewa`, yang mendefinisikan perilaku untuk memeriksa ketersediaan alat berdasarkan jumlah unit yang diminta. Class `AlatSelam` mengimplementasikan interface tersebut, sedangkan class `AlatSnorkeling` dan `AlatDiving` menyediakan implementasi method sesuai kebutuhan sistem.

**Gambar 1. Implementasi Interface DapatDisewa**

<img width="456" height="93" alt="image" src="https://github.com/user-attachments/assets/b28eb199-667e-4200-b7e0-884923934c0b" />

Interface `DapatDisewa` menjadi kontrak yang harus dipenuhi oleh class yang mengimplementasikannya. Dalam program ini, method `cekKetersediaan(int jumlahUnit)` digunakan untuk memeriksa apakah jumlah alat yang diminta tersedia.

## Penerapan MVC (Model-View-Controller)

MVC digunakan untuk memisahkan tanggung jawab program.

**Gambar 1. Struktur Package untuk Penerapan MVC**

<img width="290" height="521" alt="image" src="https://github.com/user-attachments/assets/aab3e1ae-41b5-449f-bfc0-0743235c50c9" />

Struktur project menunjukkan pemisahan tanggung jawab melalui package `model`, `view`, dan `controller`. Package `model` merepresentasikan data dan objek sistem, package `view` menangani tampilan serta input pengguna, sedangkan package `controller` mengatur alur program. Package `dao` mendukung proses akses database.

### Model

Package `model` berisi class yang merepresentasikan objek dalam sistem, seperti `AlatSelam`, `AlatSnorkeling`, `AlatDiving`, `Penyewa`, dan `Penyewaan`.

### View

Package `view` berisi `SewaAlatView`, yang menangani tampilan menu serta penerimaan input pengguna.

### Controller

Package `controller` berisi `SewaAlatController`, yang mengatur alur program, menjalankan logika penyewaan dan pengembalian, serta menghubungkan View dengan Model dan DAO.

### Hubungan MVC dengan DAO

Ketika pengguna memilih menu, View meneruskan input untuk diproses oleh Controller. Controller kemudian menggunakan DAO untuk menjalankan operasi database dan mengolah hasilnya sebelum ditampilkan kembali kepada pengguna.

Pemisahan ini membuat tanggung jawab tampilan, logika program, representasi data, dan akses database lebih terorganisir.

## Dokumentasi Pengujian Program

Bagian ini menampilkan hasil pengujian Sistem Manajemen Sewa Alat Snorkeling & Diving. Pengujian dilakukan untuk memastikan koneksi database, pengelolaan data alat, transaksi penyewaan, dan proses pengembalian berjalan sesuai fungsinya.

### 1. Pengujian Koneksi Database

Pengujian koneksi dilakukan untuk memastikan aplikasi Java berhasil terhubung dengan database MySQL `db_sewa_alat`.

**Gambar 1. Hasil Pengujian Koneksi Database**

<img width="942" height="462" alt="01-koneksi-database" src="https://github.com/user-attachments/assets/5f9db083-be72-48ab-8e0f-b8dbf5710269" />

Output menunjukkan bahwa aplikasi berhasil terhubung dengan database MySQL dan pengujian selesai dengan status BUILD SUCCESS.

### 2. Pengujian Menampilkan Data Alat

Pengujian ini memastikan sistem dapat mengambil dan menampilkan data alat yang tersimpan dalam database MySQL.

**Gambar 2. Hasil Pengujian Menampilkan Data Alat**

<img width="367" height="797" alt="02-data-alat-database" src="https://github.com/user-attachments/assets/3cac9c9e-094f-406f-ab96-f48c32f56144" />

Output menampilkan data Tabung Diving (DIV01) dengan stok 5 unit dan Set Snorkeling (SNK01) dengan stok 10 unit, termasuk tarif dan atribut masing-masing alat.

### 3. Pengujian Penambahan Data Alat

Pengujian ini dilakukan untuk memastikan sistem dapat menambahkan data alat snorkeling baru ke dalam database MySQL.

**Gambar 3. Hasil Pengujian Penambahan Data Alat**

<img width="570" height="460" alt="03-tambah-alat" src="https://github.com/user-attachments/assets/70a0c2fc-d97a-4623-8ee1-e6048d5f8978" />

Output menunjukkan bahwa data alat dengan ID `SNK02`, nama Set Snorkeling Anak, berhasil ditambahkan ke database.

### 4. Pengujian Verifikasi Data Setelah Penambahan

Pengujian ini dilakukan untuk memastikan data alat yang baru ditambahkan benar-benar tersimpan dan dapat ditampilkan kembali dari database.

**Gambar 4. Hasil Verifikasi Data Setelah Penambahan**

<img width="337" height="787" alt="04-verifikasi-data-alat" src="https://github.com/user-attachments/assets/626784d9-314d-4071-8394-4c038a82690f" />

Output menunjukkan bahwa alat dengan ID `SNK02` dan nama Set Snorkeling Anak muncul dalam daftar alat. Hal ini membuktikan bahwa data hasil penambahan berhasil dibaca kembali dari database.

### 5. Pengujian Perubahan Data Alat

Pengujian ini dilakukan untuk memastikan sistem dapat memperbarui informasi alat yang sudah tersimpan dalam database berdasarkan ID alat.

**Gambar 5. Hasil Pengujian Perubahan Data Alat**

<img width="553" height="326" alt="05-ubah-alat" src="https://github.com/user-attachments/assets/6d031e2f-1ede-49fb-9970-62adad234092" />

Output menunjukkan bahwa data alat `SNK02` berhasil diubah menjadi Set Snorkeling Anak Premium dengan tarif Rp14.000 per hari, stok 7 unit, dan kondisi Baik.

### 6. Pengujian Verifikasi Perubahan Data Alat

Pengujian ini dilakukan untuk memastikan perubahan data alat berhasil disimpan dan ditampilkan kembali dari database.

**Gambar 6. Hasil Verifikasi Perubahan Data Alat**

<img width="362" height="722" alt="06-verifikasi-ubah-alat" src="https://github.com/user-attachments/assets/52154152-89ee-4b55-b59d-2981b3eec391" />

Output menunjukkan bahwa data `SNK02` telah berubah menjadi Set Snorkeling Anak Premium dengan tarif Rp14.000 per hari dan stok 7 unit. Hasil ini membuktikan bahwa perubahan data berhasil tersimpan dan dapat dibaca kembali dari database.

### 7. Pengujian Penghapusan Data Alat

Pengujian ini dilakukan untuk memastikan sistem dapat menghapus data alat berdasarkan ID alat yang dipilih dari database MySQL.

**Gambar 7. Hasil Pengujian Penghapusan Data Alat**

<img width="275" height="797" alt="07-hapus-alat" src="https://github.com/user-attachments/assets/6b2e16dd-8c2a-4849-93f1-5ed89937a299" />

Output menunjukkan bahwa data alat dengan ID `SNK02` berhasil dihapus dari database. Sistem menampilkan pesan keberhasilan setelah operasi penghapusan selesai dilakukan.

### 8. Pengujian Pembuatan Transaksi Penyewaan

Pengujian ini dilakukan untuk memastikan sistem dapat menyimpan transaksi penyewaan alat, menghitung total biaya, dan memperbarui stok berdasarkan jumlah unit yang disewa.

**Gambar 8. Hasil Pengujian Pembuatan Transaksi Penyewaan**

<img width="282" height="628" alt="08-buat-penyewaan" src="https://github.com/user-attachments/assets/64a25d4a-acd9-4654-ad2a-5c59739abca5" />

Output menunjukkan bahwa transaksi `SW0002` berhasil dibuat untuk dua unit Set Snorkeling dengan durasi penyewaan tiga hari. Total biaya yang tercatat adalah Rp90.000.

### 9. Pengujian Menampilkan Data Penyewaan

Pengujian ini dilakukan untuk memastikan sistem dapat mengambil dan menampilkan daftar transaksi penyewaan yang tersimpan dalam database MySQL.

**Gambar 9. Hasil Pengujian Menampilkan Data Penyewaan**

<img width="325" height="492" alt="09-data-penyewaan" src="https://github.com/user-attachments/assets/cfd582b2-fa78-4b2f-beac-47b5036f0810" />

Output menampilkan daftar transaksi penyewaan beserta ID transaksi, identitas penyewa, alat yang disewa, jumlah unit, durasi, total biaya, dan status transaksi. Data tersebut menunjukkan bahwa transaksi dapat dibaca kembali dari database.

### 10. Pengujian Stok Setelah Penyewaan

Pengujian ini dilakukan untuk memastikan stok alat otomatis berkurang sesuai jumlah unit yang disewa setelah transaksi berhasil dibuat.

**Gambar 10. Hasil Verifikasi Stok Setelah Penyewaan**

<img width="291" height="715" alt="10-verifikasi-stok-penyewaan" src="https://github.com/user-attachments/assets/717f2018-cc57-40f2-bdd8-25d1189ae791" />

Output menunjukkan stok SNK01 berkurang dari 10 menjadi 8 unit setelah dua unit alat disewakan. Hal ini menunjukkan bahwa sistem memperbarui stok berdasarkan jumlah unit dalam transaksi penyewaan.

### 11. Pengujian Pengembalian Alat

Pengujian ini dilakukan untuk memastikan sistem dapat memproses pengembalian alat yang sebelumnya disewa dan memperbarui status transaksi serta stok alat.

**Gambar 11. Hasil Pengujian Pengembalian Alat**

<img width="278" height="432" alt="11-pengembalian-alat" src="https://github.com/user-attachments/assets/b6f3e86c-a750-4dfc-a265-5f75b8195818" />

Output menunjukkan bahwa proses pengembalian alat berhasil dilakukan. Sistem menampilkan pesan bahwa alat berhasil dikembalikan dan stok alat telah diperbarui.

### 12. Pengujian Verifikasi Status Pengembalian

Pengujian ini dilakukan untuk memastikan status transaksi berubah setelah alat berhasil dikembalikan dan perubahan tersebut tersimpan di database.

**Gambar 12. Hasil Verifikasi Status Pengembalian**

<img width="328" height="491" alt="12-verifikasi-status-pengembalian" src="https://github.com/user-attachments/assets/5ef04618-b71f-4357-b1a3-fd9c56cef9b2" />

Output menunjukkan bahwa transaksi SW0002 memiliki status `DIKEMBALIKAN`. Hal ini membuktikan bahwa sistem berhasil memperbarui status transaksi setelah proses pengembalian alat dilakukan.

### 13. Pengujian Validasi Pengembalian Ganda

Pengujian ini dilakukan untuk memastikan sistem dapat menolak proses pengembalian apabila transaksi penyewaan sudah berstatus dikembalikan.

**Gambar 13. Hasil Pengujian Validasi Pengembalian Ganda**

<img width="360" height="317" alt="13-validasi-pengembalian-ganda" src="https://github.com/user-attachments/assets/387f0d9e-b5b9-46e6-b73e-75ac201d1c77" />

Output menampilkan pesan `Penyewaan sudah dikembalikan` ketika pengembalian dilakukan kembali pada transaksi yang sama. Hal ini menunjukkan bahwa sistem memiliki validasi untuk mencegah pengembalian ganda pada satu transaksi.

### 14. Pengujian Verifikasi Stok Setelah Pengembalian

Pengujian ini dilakukan untuk memastikan stok alat bertambah kembali setelah proses pengembalian berhasil dilakukan.

**Gambar 14. Hasil Verifikasi Stok Setelah Pengembalian**

<img width="363" height="751" alt="14-verifikasi-stok-setelah-pengembalian" src="https://github.com/user-attachments/assets/8bc48c31-c6a3-4c31-8592-fdb3c89fc19e" />

Output menunjukkan bahwa stok SNK01 kembali menjadi 10 unit setelah sebelumnya berkurang dari 10 menjadi 8 unit akibat penyewaan dua unit alat. Hal ini menunjukkan bahwa sistem berhasil memperbarui stok setelah pengembalian dilakukan.

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
