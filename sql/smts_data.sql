use smts_baza;

-- Dodavanje podataka za Starlink-1
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(550, 53.0, 95, '2019-05-24', '2020-01-01', 1), -- Orbita za Starlink-1
(540, 52.0, 94, '2020-01-02', NULL, 1); -- Druga orbita za Starlink-1 (trenutna)

-- Dodavanje podataka za Starlink-1 sa ID 39 (duplikat)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(550, 53.0, 95, '2019-05-24', '2020-01-01', 39),
(540, 52.0, 94, '2020-01-02', NULL, 39);

-- Dodavanje podataka za Hubble Telescope
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(540, 28.5, 96, '1990-04-24', NULL, 2);

-- Dodavanje podataka za Hubble Telescope sa ID 40 (duplikat)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(540, 28.5, 96, '1990-04-24', NULL, 40);

-- Dodavanje podataka za Orion
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(2200, 28.5, 128, '2022-11-16', NULL, 3);

-- Dodavanje podataka za Orion sa ID 41 (duplikat)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(2200, 28.5, 128, '2022-11-16', NULL, 41);

-- Dodavanje podataka za Gagarin
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(600, 97.8, 97, '2023-01-10', NULL, 4);

-- Dodavanje podataka za Gagarin sa ID 42 (duplikat)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(600, 97.8, 97, '2023-01-10', NULL, 42);

-- Dodavanje podataka za Satelit-5
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(35786, 0.0, 1440, '2025-10-26', NULL, 5);

-- Dodavanje podataka za Starlink-100
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(550, 53.0, 95, '2026-01-15', NULL, 6);

-- Dodavanje podataka za Apollo Lunar Module
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(110, 0.0, 107, '1969-07-20', '1969-07-24', 12);

-- Dodavanje podataka za Apollo Lunar Module (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(110, 0.0, 107, '1969-07-20', '1969-07-24', 21),
(110, 0.0, 107, '1969-07-20', '1969-07-24', 30);

-- Dodavanje podataka za James Webb Space Telescope
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(1500000, 0.0, 365*1440, '2021-12-25', NULL, 13);

-- Dodavanje podataka za James Webb Space Telescope (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(1500000, 0.0, 365*1440, '2021-12-25', NULL, 22),
(1500000, 0.0, 365*1440, '2021-12-25', NULL, 31);

-- Dodavanje podataka za Chandrayaan-2 Orbiter
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(100, 90.0, 118, '2019-09-02', NULL, 14);

-- Dodavanje podataka za Chandrayaan-2 Orbiter (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(100, 90.0, 118, '2019-09-02', NULL, 23),
(100, 90.0, 118, '2019-09-02', NULL, 32);

-- Dodavanje podataka za Perseverance Rover
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(0, 0, 0, '2020-07-30', NULL, 15);

-- Dodavanje podataka za Perseverance Rover (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(0, 0, 0, '2020-07-30', NULL, 24),
(0, 0, 0, '2020-07-30', NULL, 33);

-- Dodavanje podataka za Galileo-FOC FM1
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(23222, 56.0, 858, '2014-08-22', NULL, 16);

-- Dodavanje podataka za Galileo-FOC FM1 (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(23222, 56.0, 858, '2014-08-22', NULL, 25),
(23222, 56.0, 858, '2014-08-22', NULL, 34);

-- Dodavanje podataka za Tianwen-1 Rover
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(400, 24.5, 90, '2020-07-23', NULL, 17);

-- Dodavanje podataka za Tianwen-1 Rover (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(400, 24.5, 90, '2020-07-23', NULL, 26),
(400, 24.5, 90, '2020-07-23', NULL, 35);

-- Dodavanje podataka za BepiColombo MMO
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(9000, 0.0, 180, '2018-10-20', NULL, 18);

-- Dodavanje podataka za BepiColombo MMO (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(9000, 0.0, 180, '2018-10-20', NULL, 27),
(9000, 0.0, 180, '2018-10-20', NULL, 36);

-- Dodavanje podataka za Voyager 1 Probe
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(0, 0, 0, '1977-09-05', NULL, 19);

-- Dodavanje podataka za Voyager 1 Probe (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(0, 0, 0, '1977-09-05', NULL, 28),
(0, 0, 0, '1977-09-05', NULL, 37);

-- Dodavanje podataka za GPS III SV01
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(20200, 55.0, 720, '2018-12-23', NULL, 20);

-- Dodavanje podataka za GPS III SV01 (duplikati)
INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(20200, 55.0, 720, '2018-12-23', NULL, 29),
(20200, 55.0, 720, '2018-12-23', NULL, 38);
