-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: ante-airline-mysql
-- Generation Time: Feb 27, 2021 at 10:28 AM
-- Server version: 5.7.30
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flightreservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `firstName`, `lastName`, `password`) VALUES
(1, 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE `airline` (
  `airlineID` int(11) NOT NULL,
  `departureCity` varchar(45) DEFAULT NULL,
  `arrivalCity` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `airline`
--

INSERT INTO `airline` (`airlineID`, `departureCity`, `arrivalCity`) VALUES
(1, 'Zagreb', 'Frankfurt'),
(2, 'Mostar', 'Split'),
(3, 'Zagreb', 'Sarajevo'),
(4, 'Frankfurt', 'London'),
(5, 'London', 'Toronto'),
(6, 'Toronto', 'Los Angeles');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `flightID` int(11) NOT NULL,
  `fareClass` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingID`, `customerID`, `flightID`, `fareClass`) VALUES
(2, 1, 8, 'Economy'),
(3, 3, 7, 'Coach'),
(5, 4, 8, 'First class'),
(6, 5, 7, 'Coach'),
(7, 4, 12, 'Coach'),
(8, 6, 12, 'First class'),
(9, 2, 12, 'Coach'),
(10, 1, 16, 'Coach');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerID` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `passportNumber` varchar(45) DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerID`, `age`, `passportNumber`, `firstName`, `lastName`, `phoneNumber`) VALUES
(1, 21, '12345678', 'Antonio', 'Pavkovic', '063123321'),
(2, 35, '12349876', 'Ivo', 'Ivic', '63111222'),
(3, 45, '11122233', 'Pero', 'Peric', '063987123'),
(4, 33, '91827462', 'Chris', 'Hemsworh', '063567123'),
(5, 45, '12341234', 'Robert', 'Downey JR', '63567891'),
(6, 18, '12312345', 'Ivo', 'maric', '063123123'),
(7, 28, '11223344', 'Renata', 'Maric', '63111222'),
(8, 18, '12341234', 'Marko', 'Markic', '063123123');

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE `flight` (
  `flightID` int(11) NOT NULL,
  `planeID` int(11) NOT NULL,
  `airlineID` int(11) NOT NULL,
  `scheduleID` int(11) NOT NULL,
  `firstClassLeft` int(11) NOT NULL,
  `coachLeft` int(11) NOT NULL,
  `economyLeft` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`flightID`, `planeID`, `airlineID`, `scheduleID`, `firstClassLeft`, `coachLeft`, `economyLeft`, `price`) VALUES
(1, 0, 4, 0, 0, 0, 0, 100),
(2, 1, 2, 0, 25, 50, 100, 1200),
(3, 1, 1, 17, 25, 50, 100, 1200),
(4, 2, 2, 0, 25, 30, 40, 1230),
(5, 0, 3, 0, 0, 0, 0, 1230),
(6, 0, 4, 32, 0, 0, 0, 1230),
(7, 4, 3, 24, 13, 23, 75, 1230),
(8, 1, 1, 26, 24, 49, 97, 120),
(9, 4, 2, 27, 15, 30, 75, 1230),
(10, 4, 3, 28, 15, 30, 75, 11110),
(11, 6, 2, 29, 1, 1, 1, 1110),
(12, 3, 6, 30, 19, 38, 60, 1200),
(13, 6, 4, 31, 1, 1, 1, 1000),
(14, 2, 6, 33, 25, 30, 40, 12220),
(15, 3, 3, 34, 20, 40, 60, 12310),
(16, 3, 2, 35, 20, 39, 60, 900);

-- --------------------------------------------------------

--
-- Table structure for table `plane`
--

CREATE TABLE `plane` (
  `planeID` int(11) NOT NULL,
  `firstClass` int(11) DEFAULT NULL,
  `coach` int(11) DEFAULT NULL,
  `economy` int(11) DEFAULT NULL,
  `planeName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `plane`
--

INSERT INTO `plane` (`planeID`, `firstClass`, `coach`, `economy`, `planeName`) VALUES
(1, 25, 50, 100, 'Boeing747'),
(2, 25, 30, 40, 'Boeing757'),
(3, 20, 40, 60, 'Boeing767'),
(4, 15, 30, 75, 'AirbusA330'),
(5, 20, 40, 60, 'AirbusA320'),
(6, 21, 31, 41, 'Boeing787'),
(7, 15, 30, 45, 'AirbusA333');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `scheduleID` int(11) NOT NULL,
  `departureDate` date DEFAULT NULL,
  `departureTime` varchar(255) DEFAULT NULL,
  `arrivalDate` date DEFAULT NULL,
  `arrivalTime` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`scheduleID`, `departureDate`, `departureTime`, `arrivalDate`, `arrivalTime`) VALUES
(17, '2021-02-18', '8:00', '2021-02-18', '8:00'),
(18, '2021-02-26', '7:00', '2021-02-26', '8:00'),
(19, '2021-02-18', '7:00', '2021-02-18', '8:00'),
(20, '2021-02-25', '7:00', '2021-02-25', '8:00'),
(21, '2021-03-13', '7:00', '2021-03-13', '8:00'),
(22, '2021-02-18', '7:00', '2021-02-18', '8:00'),
(23, '2021-02-18', '7:00', '2021-02-18', '8:00'),
(24, '2021-02-24', '14:00', '2021-02-24', '8:00'),
(25, '2021-02-25', '7:00', '2021-02-25', '8:00'),
(26, '2021-02-25', '7:00', '2021-02-25', '8:00'),
(27, '2021-02-19', '13:00', '2021-02-19', '8:00'),
(28, '2021-02-22', '7:00', '2021-02-22', '8:00'),
(29, '2021-02-22', '7:00', '2021-02-22', '8:00'),
(30, '2021-02-26', '7:00', '2021-02-26', '8:00'),
(31, '2021-03-12', '7:00', '2021-03-12', '8:00'),
(32, '2021-02-18', '7:00', '2021-02-18', '8:00'),
(33, '2021-03-05', '7:00', '2021-03-05', '8:00'),
(34, '2021-02-25', '10:00', '2021-02-25', '8:00'),
(35, '2021-03-14', '10:00', '2021-03-14', '8:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`airlineID`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flightID`);

--
-- Indexes for table `plane`
--
ALTER TABLE `plane`
  ADD PRIMARY KEY (`planeID`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `airline`
--
ALTER TABLE `airline`
  MODIFY `airlineID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `flight`
--
ALTER TABLE `flight`
  MODIFY `flightID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `plane`
--
ALTER TABLE `plane`
  MODIFY `planeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `scheduleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
