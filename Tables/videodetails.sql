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
-- Table structure for table `videodetails`
--

CREATE TABLE IF NOT EXISTS `videodetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standard` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `videoName` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `videodetails`
--

INSERT INTO `videodetails` (`id`, `standard`, `subject`, `videoName`) VALUES
(10, 'se', 'AT', 'video.mp4'),
(16, 'se', 'AT', 'VID_20150329_061951.mp4'),
(17, 'se', 'AT', 'VID_20150329_061951.mp4'),
(18, 'se', 'AT', 'VID_20150329_061951.mp4'),
(20, 'se', 'AT', 'VID_20150330_075707.mp4');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
