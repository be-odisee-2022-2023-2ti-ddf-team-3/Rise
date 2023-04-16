-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 13 apr 2023 om 15:58
-- Serverversie: 10.4.27-MariaDB
-- PHP-versie: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rise`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `ambassadeur`
--

CREATE TABLE `ambassadeur` (
                               `id` int(11) NOT NULL,
                               `voornaam` varchar(255) DEFAULT NULL,
                               `familienaam` varchar(255) DEFAULT NULL,
                               `emailadres` varchar(255) DEFAULT NULL,
                               `paswoord` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `ambassadeur`
--

INSERT INTO `ambassadeur` (`id`, `voornaam`, `familienaam`, `emailadres`, `paswoord`) VALUES
                                                                                          (1, 'Thomas', 'Roelandts', 'roelandts.th@gmail.com', 'test'),
                                                                                          (2, 'Jonathan', 'Roelandts', 'jonathan.roelandts@gmail.com', 'test');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `datum`
--

CREATE TABLE `datum` (
                         `id` int(11) NOT NULL,
                         `start` datetime NOT NULL,
                         `einde` datetime NOT NULL,
                         `status` enum('onbevestigd','bevestigd','voorgaand') NOT NULL DEFAULT 'onbevestigd',
                         `demo_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `datum`
--

INSERT INTO `datum` (`id`, `start`, `einde`, `status`, `demo_id`) VALUES
                                                                      (1, '2023-04-07 14:00:00', '2023-04-07 16:00:00', 'onbevestigd', 1),
                                                                      (2, '2023-04-01 07:00:00', '2023-04-01 09:00:00', 'onbevestigd', 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `demo`
--

CREATE TABLE `demo` (
                        `id` int(11) NOT NULL,
                        `naam` varchar(100) NOT NULL,
                        `adres` varchar(100) NOT NULL,
                        `status` enum('aangemaakt','ingepland','binnenkort','voorbij') NOT NULL DEFAULT 'aangemaakt'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Gegevens worden geëxporteerd voor tabel `demo`
--

INSERT INTO `demo` (`id`, `naam`, `adres`, `status`) VALUES
    (1, 'Odisee demo', 'Stormstraat 2, 1000 Brussel', 'aangemaakt');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `planning`
--

CREATE TABLE `planning` (
                            `id` int(11) NOT NULL,
                            `status` enum('afgewezen','bevestigd') NOT NULL,
                            `datum_id` int(11) NOT NULL,
                            `ambassadeur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `ambassadeur`
--
ALTER TABLE `ambassadeur`
    ADD PRIMARY KEY (`id`),
  ADD KEY `IAmbassadeur_naam` (`voornaam`,`familienaam`) USING BTREE,
  ADD KEY `IAmbassadeur_email` (`emailadres`) USING BTREE;

--
-- Indexen voor tabel `datum`
--
ALTER TABLE `datum`
    ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `demo`
--
ALTER TABLE `demo`
    ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `planning`
--
ALTER TABLE `planning`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `ambassadeur`
--
ALTER TABLE `ambassadeur`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT voor een tabel `datum`
--
ALTER TABLE `datum`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT voor een tabel `demo`
--
ALTER TABLE `demo`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT voor een tabel `planning`
--
ALTER TABLE `planning`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;