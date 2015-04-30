-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 07, 2015 at 06:28 AM
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
-- Table structure for table `administratordetails`
--

CREATE TABLE IF NOT EXISTS `administratordetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmPassword` varchar(100) NOT NULL,
  `securityQuestion` text NOT NULL,
  `securityQuestionAnswer` text NOT NULL,
  `securityKey` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `administratordetails`
--

INSERT INTO `administratordetails` (`id`, `username`, `password`, `confirmPassword`, `securityQuestion`, `securityQuestionAnswer`, `securityKey`) VALUES
(1, 'testAdmin', 'testAdmin', 'testAdmin', 'test', 'test', 'test#123Test'),
(2, 'test', 'test123', 'test', 'test', 'test', 'test'),
(3, 'testAdmin', 'test', 'test', 'nick name', 'admin', 'test#123Test'),
(4, 'test', 'test', 'test', 'test', 'test', 'test#123Test'),
(5, 'swati', 'srs', 'srs', 'nick name', 'ddd', 'AA'),
(6, 'swatika', 'swati', 'swati', 'nm', 'nm', 'AA'),
(7, 'chinu', 'aaa', 'aaa', 'asd', 'asd', 'AA');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
