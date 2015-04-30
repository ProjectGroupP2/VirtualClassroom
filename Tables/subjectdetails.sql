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
-- Table structure for table `subjectdetails`
--

CREATE TABLE IF NOT EXISTS `subjectdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `standard` varchar(23) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `subject` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `subjectdetails`
--

INSERT INTO `subjectdetails` (`id`, `standard`, `branch`, `subject`) VALUES
(1, 'SE', 'CSE', 'AT'),
(2, 'SE', 'CSE', 'DSM'),
(3, 'TE', 'CSE', 'Database Engineering'),
(4, 'TE', 'CSE', 'Programming Lab-IV');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
