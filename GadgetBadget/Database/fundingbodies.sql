-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 04:56 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fund1`
--

-- --------------------------------------------------------

--
-- Table structure for table `fundingbodies`
--

CREATE TABLE `fundingbodies` (
  `fundID` int(11) NOT NULL,
  `fundOrganizationName` varchar(100) NOT NULL,
  `fundDescrption` varchar(100) NOT NULL,
  `fundCountry` varchar(50) NOT NULL,
  `fundAmount` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fundingbodies`
--

INSERT INTO `fundingbodies` (`fundID`, `fundOrganizationName`, `fundDescrption`, `fundCountry`, `fundAmount`) VALUES
(1, 'Beliver', 'Nothing', 'india', '1500000.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fundingbodies`
--
ALTER TABLE `fundingbodies`
  ADD PRIMARY KEY (`fundID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fundingbodies`
--
ALTER TABLE `fundingbodies`
  MODIFY `fundID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
