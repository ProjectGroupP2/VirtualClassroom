-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2015 at 06:30 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `virtualclassroom`
--

-- --------------------------------------------------------

--
-- Table structure for table `studentdetails`
--

CREATE TABLE IF NOT EXISTS `studentdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) NOT NULL,
  `middleName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `standard` varchar(100) NOT NULL,
  `branch` varchar(100) NOT NULL,
  `rollNumber` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmPassword` varchar(100) NOT NULL,
  `securityQuestion` varchar(100) NOT NULL,
  `securityQuestionAnswer` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `studentdetails`
--

INSERT INTO `studentdetails` (`id`, `firstName`, `middleName`, `lastName`, `standard`, `branch`, `rollNumber`, `password`, `confirmPassword`, `securityQuestion`, `securityQuestionAnswer`, `username`, `status`) VALUES
(2, 'akshata', 'appasaheb', 'nandgave', 'be', 'cse', '06', 'test', 'test', '0', '0', 'AANBECSE06', 'Enabled'),
(3, 'trishala ', 'prakash', 'chougule', 'te', 'cse', '08', 'trisha', 'trisha', '0', '0', 'TPCTECSE08', 'Enabled'),
(5, 'madhuri', 'manohar', 'sutar', 'be ', 'cse', '08', 'test', 'test', '', '', 'MMSBE CSE08', 'Disabled'),
(6, 'madhuri', 'manohar', 'sutar', 'be', 'cse', '08', 'test', 'test', '', '', 'MMSBECSE08', 'Disabled'),
(7, 'neha', 'ravsaheb', 'wadkar', 'be', 'cse', '08', 'test', 'test', '', '', 'NRWBECSE08', 'Disabled'),
(8, 'akshata', 'appasaheb', 'nandgave', 'be', 'cse', '06', 'test', 'test', 'what is ur nm', 'akshata', 'AANBECSE06', 'Disabled');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
