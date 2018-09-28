-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2018 at 07:30 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbvisitor`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama_admin` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Menampung data akun admin operator program manajemen pengunj';

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`, `nama_admin`) VALUES
(7, 'elis', 'elis', 'Eliskarini'),
(8, 'sidin', 'sidin', 'Sidin Rahman'),
(9, 'ilkomunud', 'admin', 'Ilmu Komputer'),
(10, 'arimogi', 'arimogi', 'Komang Ari Mogi'),
(11, 'gun', 'gun', 'Anggun Dwiguna'),
(12, 'admin', 'admin', 'MASTER');

-- --------------------------------------------------------

--
-- Table structure for table `daftar_hakakses`
--

CREATE TABLE `daftar_hakakses` (
  `kode_ikat` varchar(8) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_ruangan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar_hakakses`
--

INSERT INTO `daftar_hakakses` (`kode_ikat`, `id_role`, `id_ruangan`) VALUES
('1-1', 1, 1),
('1-5', 1, 5),
('1-6', 1, 6),
('2-4', 2, 4),
('2-5', 2, 5),
('2-6', 2, 6),
('28-3', 28, 3),
('28-4', 28, 4),
('28-5', 28, 5),
('29-1', 29, 1),
('29-3', 29, 3);

-- --------------------------------------------------------

--
-- Table structure for table `daftar_log`
--

CREATE TABLE `daftar_log` (
  `id_log` varchar(10) NOT NULL,
  `nama_kejadian` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar_log`
--

INSERT INTO `daftar_log` (`id_log`, `nama_kejadian`) VALUES
('ADDADM', 'Menambah Admin baru ke dalam sistem.'),
('ADDAUTH', 'Menambah hak akases role terhadap ruangan.'),
('ADDROLE', 'Menambahkan role baru ke dalam sistem.'),
('ADDROOM', 'Menambah ruangan baru ke dalam sistem.'),
('ADDTAG', 'Mendaftarkan tag baru ke dalam sistem.'),
('CHOWN', 'Status kepemilikan tag diubah.'),
('CHROLE', 'Mengubah nama role.'),
('CHROOM', 'Mengubah nama ruangan.'),
('FALSEIN', 'Tag masuk ke dalam ruangan yang tidak terautorisasi.'),
('FALSEOUT', 'Tag keluar dari ruangan tidak terautorisasi.'),
('LOGIN', 'Admin login ke dalam sistem.'),
('LOGOUT', 'Admin keluar dari sistem.'),
('RMADM', 'Menghapus admin dari sistem.'),
('RMAUTH', 'Menghapus hak akses role terhadap ruangan.\r\n'),
('RMROLE', 'Menghapus role dari sistem'),
('RMROOM', 'Mengghapus ruangan dari sistem.'),
('RMTAG', 'Mereset tag dan menghapus dari sistem'),
('STAT0', 'Status tag dinonaktifkan.'),
('STAT1', 'Status tag diaktifkan.'),
('TRUEIN', 'Tag masuk ke dalam ruangan terautorisasi.'),
('TRUEOUT', 'Tag keluar dari ruangan terautorisasi.');

-- --------------------------------------------------------

--
-- Table structure for table `daftar_role`
--

CREATE TABLE `daftar_role` (
  `id_role` int(11) NOT NULL,
  `nama_role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar_role`
--

INSERT INTO `daftar_role` (`id_role`, `nama_role`) VALUES
(29, 'Kunjungan Mahasiswa Ilmu Komputer'),
(30, 'Kunjungan Mancanegara'),
(2, 'Kunjungan Wisata Sejarah Kemerdekaan'),
(1, 'Staff Pegawai'),
(28, 'Wisata Studi Magister');

-- --------------------------------------------------------

--
-- Table structure for table `daftar_ruangan`
--

CREATE TABLE `daftar_ruangan` (
  `id_ruangan` int(11) NOT NULL,
  `nama_ruangan` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar_ruangan`
--

INSERT INTO `daftar_ruangan` (`id_ruangan`, `nama_ruangan`) VALUES
(4, 'Ruang Auditorium Purnabangsa'),
(6, 'Ruang Bangsa'),
(1, 'Ruang Geologi Indonesia'),
(3, 'Ruang Pameran Miniatur Sejarah Kemerdekaan'),
(5, 'Ruang Perpustakaan');

-- --------------------------------------------------------

--
-- Table structure for table `daftar_tag`
--

CREATE TABLE `daftar_tag` (
  `NUID` varchar(15) NOT NULL,
  `status_aktif` tinyint(1) NOT NULL DEFAULT '1',
  `status_ruang` tinyint(1) NOT NULL DEFAULT '0',
  `id_role` int(11) NOT NULL,
  `nama_pemilik` varchar(50) NOT NULL,
  `no_identitas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `daftar_tag`
--

INSERT INTO `daftar_tag` (`NUID`, `status_aktif`, `status_ruang`, `id_role`, `nama_pemilik`, `no_identitas`) VALUES
('10EF12A3', 1, 1, 29, 'Sidin Rahman', 'qR2olssdQkEEX8Kb6V51IQ=='),
('3360B879', 1, 0, 28, 'I Komang Ari Mogi', 'JJrj4uHJePq/THVBKvalvE1gdkOQw5bd'),
('3EC3BB79', 1, 0, 29, 'Kadek Eliskarini', 'ixzmxvQHo+mdJTvafAk1uA=='),
('900E6356', 1, 0, 30, 'Anggun', 'vIZWdKiTT6syH6TdNm2FXw==');

-- --------------------------------------------------------

--
-- Table structure for table `log_history`
--

CREATE TABLE `log_history` (
  `id_log` varchar(10) NOT NULL,
  `date_time` datetime NOT NULL,
  `subjek` varchar(50) NOT NULL,
  `objek` varchar(50) NOT NULL,
  `nama_ruangan` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log_history`
--

INSERT INTO `log_history` (`id_log`, `date_time`, `subjek`, `objek`, `nama_ruangan`) VALUES
('FALSEIN', '2018-05-19 23:49:01', '10EF12A3', '', 'Ruang Inventaris Kepresidenan'),
('FALSEOUT', '2018-05-19 23:49:06', '10EF12A3', '', 'Ruang Inventaris Kepresidenan'),
('LOGIN', '2018-05-19 23:58:09', 'Eliskarini', 'null', ''),
('LOGIN', '2018-05-19 23:58:36', 'Anggun Dwiguna', 'null', ''),
('RMTAG', '2018-05-20 02:50:18', 'Anggun Dwiguna', '3EC3BB79', ''),
('ADDADM', '2018-05-20 03:11:57', 'Anggun Dwiguna', 'anggun', ''),
('RMTAG', '2018-05-20 14:16:33', 'Anggun Dwiguna', '3EC3BB79', ''),
('RMTAG', '2018-05-20 14:16:40', 'Anggun Dwiguna', '3EC3BB79', ''),
('ADDADM', '2018-05-26 20:16:22', 'MASTER', 'CSBEGUN', ''),
('ADDADM', '2018-05-26 20:23:14', 'MASTER', 'gun', ''),
('RMADM', '2018-05-26 20:23:18', 'gun', 'gun', ''),
('ADDAUTH', '2018-05-26 21:10:56', 'MASTER', 'Staff Pegawai', 'Ruang Perpustakaan'),
('RMAUTH', '2018-05-26 21:11:00', 'MASTER', 'Staff Pegawai', 'Ruang Auditorium Purnabangsa'),
('ADDAUTH', '2018-05-26 21:11:39', 'MASTER', 'Staff Pegawai', 'Ruang Geologi Indonesia'),
('RMAUTH', '2018-05-26 21:11:41', 'MASTER', 'Staff Pegawai', 'Ruang Perpustakaan'),
('RMTAG', '2018-05-26 23:07:59', 'MASTER', '3EC3BB79', ''),
('ADDAUTH', '2018-05-26 23:10:03', 'MASTER', 'Kunjungan Mancanegara', 'Ruang Inventaris Kepresidenan'),
('ADDAUTH', '2018-05-26 23:10:05', 'MASTER', 'Kunjungan Mancanegara', 'Ruang Bangsa'),
('ADDAUTH', '2018-05-26 23:10:06', 'MASTER', 'Kunjungan Mancanegara', 'Ruang Perpustakaan'),
('TRUEIN', '2018-05-26 23:12:30', '3EC3BB79', '', 'Ruang Geologi Indonesia'),
('TRUEOUT', '2018-05-26 23:12:42', '3EC3BB79', '', 'Ruang Geologi Indonesia'),
('TRUEIN', '2018-05-26 23:13:08', '10EF12A3', '', 'Ruang Geologi Indonesia'),
('TRUEOUT', '2018-05-26 23:13:13', '10EF12A3', '', 'Ruang Geologi Indonesia'),
('FALSEIN', '2018-05-26 23:13:25', '900E6356', '', 'Ruang Geologi Indonesia'),
('FALSEOUT', '2018-05-26 23:13:30', '900E6356', '', 'Ruang Geologi Indonesia'),
('RMADM', '2018-05-27 03:08:18', 'elis', 'elis', ''),
('ADDADM', '2018-05-27 03:09:22', 'MASTER', 'Eliskarini', ''),
('ADDADM', '2018-05-27 03:09:44', 'MASTER', 'Sidin Rahman', ''),
('ADDADM', '2018-05-27 03:10:44', 'MASTER', 'Ilmu Komputer', ''),
('ADDADM', '2018-05-27 03:11:23', 'MASTER', 'Komang Ari Mogi', ''),
('ADDADM', '2018-05-27 03:12:01', 'MASTER', 'Anggun Dwiguna', ''),
('RMADM', '2018-05-27 03:12:10', 'admin', 'admin', ''),
('ADDADM', '2018-05-27 03:12:27', 'MASTER', 'MASTER', ''),
('RMROLE', '2018-05-27 15:46:33', 'MASTER', 'Kunjungan Mancanegara', ''),
('ADDROLE', '2018-05-27 15:47:25', 'MASTER', 'Kunjungan Mancanegara', ''),
('ADDROOM', '2018-05-27 15:47:54', 'MASTER', '', ''),
('RMROOM', '2018-05-27 15:47:58', 'MASTER', 'Ruang Inventaris(7)', ''),
('ADDROOM', '2018-05-27 15:48:10', 'MASTER', '', ''),
('ADDROOM', '2018-05-27 15:49:07', 'MASTER', '', ''),
('RMROOM', '2018-05-27 15:49:11', 'MASTER', 'Ruang Pameran Miniatur Sejarah Nasional(9)', ''),
('RMAUTH', '2018-05-27 15:51:26', 'MASTER', 'Staff Pegawai', 'Ruang Pameran Miniatur Sejarah Kemerdekaan'),
('ADDAUTH', '2018-05-27 15:51:28', 'MASTER', 'Staff Pegawai', 'Ruang Perpustakaan'),
('FALSEIN', '2018-05-27 16:32:11', '3360B879', '', 'Ruang Geologi Indonesia'),
('TRUEIN', '2018-05-27 16:32:19', '900E6356', '', 'Ruang Geologi Indonesia'),
('TRUEIN', '2018-05-27 16:32:23', '10EF12A3', '', 'Ruang Geologi Indonesia'),
('TRUEIN', '2018-05-27 16:32:26', '3EC3BB79', '', 'Ruang Geologi Indonesia'),
('FALSEOUT', '2018-05-27 16:32:30', '3360B879', '', 'Ruang Geologi Indonesia'),
('TRUEOUT', '2018-05-27 16:32:41', '3EC3BB79', '', 'Ruang Geologi Indonesia'),
('FALSEIN', '2018-05-27 16:32:44', '3360B879', '', 'Ruang Geologi Indonesia'),
('FALSEOUT', '2018-05-27 16:32:53', '3360B879', '', 'Ruang Geologi Indonesia'),
('FALSEIN', '2018-05-27 16:32:57', '3360B879', '', 'Ruang Geologi Indonesia'),
('FALSEOUT', '2018-05-27 17:30:35', '3360B879', '', 'Ruang Geologi Indonesia'),
('RMTAG', '2018-05-27 20:43:50', 'MASTER', '900E6356', ''),
('ADDTAG', '2018-05-27 20:47:31', 'MASTER', 'NUID', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `daftar_hakakses`
--
ALTER TABLE `daftar_hakakses`
  ADD UNIQUE KEY `kode_ikat` (`kode_ikat`);

--
-- Indexes for table `daftar_log`
--
ALTER TABLE `daftar_log`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `daftar_role`
--
ALTER TABLE `daftar_role`
  ADD PRIMARY KEY (`id_role`),
  ADD UNIQUE KEY `nama_role` (`nama_role`);

--
-- Indexes for table `daftar_ruangan`
--
ALTER TABLE `daftar_ruangan`
  ADD PRIMARY KEY (`id_ruangan`),
  ADD UNIQUE KEY `nama_ruangan` (`nama_ruangan`);

--
-- Indexes for table `daftar_tag`
--
ALTER TABLE `daftar_tag`
  ADD PRIMARY KEY (`NUID`),
  ADD UNIQUE KEY `NUID` (`NUID`),
  ADD KEY `NUID_2` (`NUID`),
  ADD KEY `NUID_3` (`NUID`);

--
-- Indexes for table `log_history`
--
ALTER TABLE `log_history`
  ADD UNIQUE KEY `date_time` (`date_time`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `daftar_role`
--
ALTER TABLE `daftar_role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `daftar_ruangan`
--
ALTER TABLE `daftar_ruangan`
  MODIFY `id_ruangan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
