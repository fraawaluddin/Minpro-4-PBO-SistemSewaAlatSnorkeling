CREATE DATABASE IF NOT EXISTS db_sewa_alat;
USE db_sewa_alat;

-- Tabel alat
CREATE TABLE IF NOT EXISTS alat (
    id_alat VARCHAR(20) PRIMARY KEY,
    nama_alat VARCHAR(100) NOT NULL,
    ukuran VARCHAR(30) NOT NULL,
    tarif_per_hari DECIMAL(12,2) NOT NULL,
    kondisi VARCHAR(30) NOT NULL,
    jumlah_tersedia INT NOT NULL,
    jenis_alat VARCHAR(30) NOT NULL,
    jenis_perlengkapan VARCHAR(100),
    kapasitas_tabung INT
) ENGINE=InnoDB;

-- Tabel penyewa
CREATE TABLE IF NOT EXISTS penyewa (
    id_penyewa VARCHAR(20) PRIMARY KEY,
    nama_penyewa VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

-- Tabel penyewaan
CREATE TABLE IF NOT EXISTS penyewaan (
    id_penyewaan VARCHAR(20) PRIMARY KEY,
    id_penyewa VARCHAR(20) NOT NULL,
    id_alat VARCHAR(20) NOT NULL,
    jumlah_unit INT NOT NULL,
    durasi_hari INT NOT NULL,
    total_biaya DECIMAL(12,2) NOT NULL,
    status_sewa VARCHAR(30) NOT NULL DEFAULT 'AKTIF',

    CONSTRAINT fk_penyewaan_penyewa
        FOREIGN KEY (id_penyewa)
        REFERENCES penyewa(id_penyewa),

    CONSTRAINT fk_penyewaan_alat
        FOREIGN KEY (id_alat)
        REFERENCES alat(id_alat)
) ENGINE=InnoDB;

-- Data dummy awal alat
INSERT INTO alat (
    id_alat, nama_alat, ukuran, tarif_per_hari,
    kondisi, jumlah_tersedia, jenis_alat,
    jenis_perlengkapan, kapasitas_tabung
) VALUES
(
    'SNK01', 'Set Snorkeling', 'M', 15000,
    'Baik', 10, 'Snorkeling',
    'Set Masker dan Snorkel', NULL
),
(
    'DIV01', 'Tabung Diving', 'L', 50000,
    'Baik', 5, 'Diving',
    NULL, 12
);