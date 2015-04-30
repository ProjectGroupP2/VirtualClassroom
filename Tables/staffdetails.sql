-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2015 at 06:29 AM
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
-- Table structure for table `staffdetails`
--

CREATE TABLE IF NOT EXISTS `staffdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(100) NOT NULL,
  `middleName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmPassword` varchar(100) NOT NULL,
  `securityQuestion` varchar(100) NOT NULL,
  `securityQuestionAnswer` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `staffdetails`
--

INSERT INTO `staffdetails` (`id`, `firstName`, `middleName`, `lastName`, `username`, `password`, `confirmPassword`, `securityQuestion`, `securityQuestionAnswer`, `status`) VALUES
(1, 'test', 'test', 'test', 'testStaff', 'test', 'test', 'test', 'test', 'Disabled'),
(2, 'test', 'test', 'test', 'test', 'test123', 'test', 'test', 'test', 'Enabled'),
(3, 'swati', 'raghunath', 'shinde', 'srs', 'srs', 'srs', '', '', 'Enabled'),
(4, 'madhuri', 'manohar', 'sutar', 'mms', 'test', 'test', 'hello', 'hello', 'Disabled'),
(5, 'swati', 'raghunath', 'shinde', 'swa', 'swa', 'swa', 'swa', 'swa', 'Disabled');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
