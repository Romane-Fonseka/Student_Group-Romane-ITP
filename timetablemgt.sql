-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2021 at 05:07 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timetablemgt`
--

-- --------------------------------------------------------

--
-- Table structure for table `cons_ss`
--

CREATE TABLE `cons_ss` (
  `GID` int(11) NOT NULL,
  `Subj` int(11) NOT NULL,
  `Sub_code` varchar(255) NOT NULL,
  `first_s` varchar(255) NOT NULL,
  `lec_1` varchar(255) NOT NULL,
  `second_s` varchar(255) NOT NULL,
  `lec_2` varchar(255) NOT NULL,
  `thrid_s` varchar(255) NOT NULL,
  `lec_3` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `non_overlap`
--

CREATE TABLE `non_overlap` (
  `Cat_ID` int(11) NOT NULL,
  `Cat1` varchar(255) NOT NULL,
  `Cat2` varchar(255) NOT NULL,
  `Cat3` varchar(255) NOT NULL,
  `Cat4` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `parl_ss`
--

CREATE TABLE `parl_ss` (
  `GID` varchar(255) NOT NULL,
  `mod_1` varchar(255) NOT NULL,
  `mod_2` varchar(255) NOT NULL,
  `mod_3` varchar(255) NOT NULL,
  `hrs` varchar(6) NOT NULL,
  `min` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stu_grp`
--

CREATE TABLE `stu_grp` (
  `AcadY_sem` varchar(255) NOT NULL,
  `enroll_c` varchar(255) NOT NULL,
  `G_no` varchar(255) NOT NULL,
  `G_ID` varchar(255) NOT NULL,
  `sub_no` varchar(255) NOT NULL,
  `Sub_ID` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `sub_name` varchar(255) NOT NULL,
  `sub_code` varchar(255) NOT NULL,
  `rel_tags` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `non_overlap`
--
ALTER TABLE `non_overlap`
  ADD PRIMARY KEY (`Cat_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `non_overlap`
--
ALTER TABLE `non_overlap`
  MODIFY `Cat_ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
