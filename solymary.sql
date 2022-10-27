-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2020 at 10:32 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `solymary`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` smallint(6) NOT NULL,
  `names` text NOT NULL,
  `lastname` text NOT NULL,
  `address` text NOT NULL,
  `phone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `names`, `lastname`, `address`, `phone`) VALUES
(78, 'José Eddie', 'Aguilar Ceballos', 'Loma Bonita', '3326539333'),
(79, ' pedro', ' Pérez', ' aqui', ' 638624'),
(80, ' quique', ' barba', ' alla', ' 637746');

-- --------------------------------------------------------

--
-- Table structure for table `costume`
--

CREATE TABLE `costume` (
  `id_costume` smallint(6) NOT NULL,
  `name` text NOT NULL,
  `rented` tinyint(1) NOT NULL,
  `id_season` tinyint(4) NOT NULL,
  `id_product` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `costume`
--

INSERT INTO `costume` (`id_costume`, `name`, `rented`, `id_season`, `id_product`) VALUES
(21, 'Harry Potter', 1, 26, 34),
(22, 'bruja', 1, 26, 35),
(23, ' taco', 1, 27, 38),
(24, 'Desconocido', 0, 28, 39),
(25, 'Desconocido', 0, 29, 40),
(26, 'Desconocido', 0, 30, 41),
(27, ' robot', 0, 31, 49);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id_product` smallint(6) NOT NULL,
  `stock_status` text NOT NULL,
  `id_size` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `stock_status`, `id_size`) VALUES
(33, 'Sin unidades', 35),
(34, 'actualmente rentados', 35),
(35, 'actualmente rentados', 35),
(36, 'Sin unidades', 36),
(37, 'Sin unidades', 37),
(38, 'available', 38),
(39, 'available', 39),
(40, 'Sin unidades', 40),
(41, 'Sin unidades', 41),
(42, 'Sin unidades', 42),
(43, 'available', 43),
(44, 'Sin unidades', 44),
(45, 'available', 45),
(46, 'Sin unidades', 47),
(47, 'disponibles', 48),
(48, 'Sin unidades', 49),
(49, 'disponibles', 50);

-- --------------------------------------------------------

--
-- Table structure for table `prop`
--

CREATE TABLE `prop` (
  `id_prop` smallint(6) NOT NULL,
  `name` text NOT NULL,
  `color` text NOT NULL,
  `quantity_available` tinyint(4) NOT NULL,
  `quantity_rented` int(11) NOT NULL,
  `rented` tinyint(1) NOT NULL,
  `id_product` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prop`
--

INSERT INTO `prop` (`id_prop`, `name`, `color`, `quantity_available`, `quantity_rented`, `rented`, `id_product`) VALUES
(9, 'Desconocido', ' ', 0, 0, 0, 33),
(10, 'Desconocido', ' ', 0, 0, 0, 36),
(11, 'Desconocido', ' ', 0, 0, 0, 37),
(12, 'Desconocido', ' ', 0, 0, 0, 42),
(13, 'Desconocido', ' ', 1, -1, 0, 43),
(14, ' adorno', ' rojo', 10, 0, 0, 44),
(15, ' lentes', ' negros', 24, 0, 0, 45),
(16, ' ', ' ', 0, 0, 0, 46),
(17, ' sombrero', ' cafe', 1, 1, 1, 47),
(18, ' corbata', ' amarilla', 35, 0, 0, 48);

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `id_rent` mediumint(9) NOT NULL,
  `day_give` tinyint(4) NOT NULL,
  `month_give` tinyint(4) NOT NULL,
  `year_give` int(11) NOT NULL,
  `day_return` tinyint(4) NOT NULL,
  `month_return` tinyint(4) NOT NULL,
  `year_return` int(11) NOT NULL,
  `days` smallint(6) NOT NULL,
  `price` smallint(6) NOT NULL,
  `quantity` tinyint(4) NOT NULL,
  `id_client` smallint(6) NOT NULL,
  `id_product` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`id_rent`, `day_give`, `month_give`, `year_give`, `day_return`, `month_return`, `year_return`, `days`, `price`, `quantity`, `id_client`, `id_product`) VALUES
(16, 2, 7, 2020, 5, 7, 2020, 3, 400, 1, 78, 34),
(21, 12, 2, 2020, 25, 6, 2020, 134, 350, 1, 78, 38),
(24, 1, 0, 1900, 6, 6, 120, 32767, 65, 1, 78, 39),
(29, 16, 1, 1900, 15, 1, 1900, 1, 6863, 1, 78, 43),
(30, 17, 1, 2012, 22, 8, 2073, 22498, 6598, 1, 79, 45),
(31, 6, 6, 120, 6, 6, 120, 0, 0, 1, 80, 47);

--
-- Triggers `rent`
--
DELIMITER $$
CREATE TRIGGER `finished_rent` AFTER UPDATE ON `rent` FOR EACH ROW BEGIN
    if (OLD.days=0) THEN
        UPDATE prop SET prop.quantity_available = prop.quantity_available+OLD.quantity,  prop.quantity_rented = prop.quantity_rented-OLD.quantity, prop.rented=0 WHERE prop.id_product = (SELECT product.id_product FROM product WHERE product.id_product = OLD.id_product);
        UPDATE costume SET costume.rented = 0 WHERE costume.id_product = OLD.id_product;    
        UPDATE product SET product.stock_status = "available" WHERE product.id_product = OLD.id_product;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `season`
--

CREATE TABLE `season` (
  `id_season` tinyint(4) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `season`
--

INSERT INTO `season` (`id_season`, `name`) VALUES
(26, 'Independencia'),
(27, ' '),
(28, ' '),
(29, ' '),
(30, ' '),
(31, ' ');

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `id_size` tinyint(4) NOT NULL,
  `dimension` tinyint(4) NOT NULL,
  `unit` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`id_size`, `dimension`, `unit`) VALUES
(35, 12, 'cm'),
(36, 0, ' '),
(37, 0, ' '),
(38, 0, ' '),
(39, 0, ' '),
(40, 0, ' '),
(41, 0, ' '),
(42, 0, ' '),
(43, 0, ' '),
(44, 0, ' '),
(45, 0, ' '),
(46, 12, ' cm'),
(47, 0, ' '),
(48, 0, ' '),
(49, 0, ' '),
(50, 0, ' ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Indexes for table `costume`
--
ALTER TABLE `costume`
  ADD PRIMARY KEY (`id_costume`),
  ADD KEY `id_producto` (`id_product`),
  ADD KEY `id_temporada` (`id_season`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `id_talla` (`id_size`);

--
-- Indexes for table `prop`
--
ALTER TABLE `prop`
  ADD PRIMARY KEY (`id_prop`),
  ADD KEY `id_producto` (`id_product`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`id_rent`),
  ADD KEY `id_producto` (`id_product`),
  ADD KEY `id_cliente` (`id_client`);

--
-- Indexes for table `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id_season`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id_size`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `costume`
--
ALTER TABLE `costume`
  MODIFY `id_costume` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `prop`
--
ALTER TABLE `prop`
  MODIFY `id_prop` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `id_rent` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `season`
--
ALTER TABLE `season`
  MODIFY `id_season` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `size`
--
ALTER TABLE `size`
  MODIFY `id_size` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `costume`
--
ALTER TABLE `costume`
  ADD CONSTRAINT `costume_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `costume_ibfk_2` FOREIGN KEY (`id_season`) REFERENCES `season` (`id_season`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_size`) REFERENCES `size` (`id_size`);

--
-- Constraints for table `prop`
--
ALTER TABLE `prop`
  ADD CONSTRAINT `prop_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `days_rent` ON SCHEDULE EVERY 1 DAY STARTS '2020-06-29 00:00:00' ENDS '2021-12-31 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE rent set days=days-1$$

CREATE DEFINER=`root`@`localhost` EVENT `delete_rent` ON SCHEDULE EVERY 1 DAY STARTS '2020-06-29 00:01:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM rent WHERE rent.days=0$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
