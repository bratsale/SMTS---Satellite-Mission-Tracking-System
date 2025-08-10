USE smts_baza;

INSERT INTO Zemlja (naziv_zemlje, kontinent) VALUES
('SAD', 'Sjeverna Amerika'),
('Rusija', 'Evropa'),
('Kina', 'Azija'),
('Evropska unija', 'Evropa'),
('Japan', 'Azija'),
('Indija', 'Azija');

INSERT INTO Raketa_Nosac (naziv, proizvodjac, kapacitet_tovar_kg) VALUES
('Falcon 9', 'SpaceX', 22800),
('Soyuz-2', 'Roscosmos', 8200),
('Long March 5', 'CASC', 25000),
('Ariane 5', 'Arianespace', 21000);

INSERT INTO Tip_Satelita (naziv_tipa, opis_tipa) VALUES
('Komunikacijski', 'Satelit za telekomunikacije i prenos podataka.'),
('Osmatrački', 'Satelit za posmatranje Zemlje, meteorologiju i kartiranje.'),
('Navigacijski', 'Dio globalnih navigacijskih sistema poput GPS-a.'),
('Naučni', 'Satelit za naučna istraživanja, astronomiju i fiziku.');

INSERT INTO Mjesto_Lansiranja (naziv, država, geografska_duzina, geografska_sirina) VALUES
('Cape Canaveral', 'SAD', -80.604, 28.562),
('Bajkonur', 'Kazahstan', 63.342, 45.965),
('Xichang', 'Kina', 102.029, 28.246),
('Kourou', 'Francuska Gvajana', -52.768, 5.234);

INSERT INTO Zemaljska_Stanica (naziv, lokacija, zemlja_stanice) VALUES
('Kennedy Space Center', 'Florida, SAD', 'SAD'),
('Plesetsk Cosmodrome', 'Arkhangelsk, Rusija', 'Rusija'),
('ESA Ground Station', 'Fucino, Italija', 'Evropska unija');

INSERT INTO Uloga_Operatera (naziv_uloge, opis_uloge) VALUES
('Vođa projekta', 'Odgovoran za cjelokupno vođenje misije.'),
('Inženjer', 'Tehnička podrška i održavanje sistema.'),
('Analitičar podataka', 'Analizira podatke prikupljene sa satelita.');

INSERT INTO Misija (naziv, datum_pocetka, datum_kraja, cilj_misije, status) VALUES
('Artemis 1', '2022-11-16', '2022-12-11', 'Testiranje Orion svemirske letjelice.', 'Završena'),
('Hubble', '1990-04-24', NULL, 'Snimanje svemira i slanje slika na Zemlju.', 'Aktivna'),
('Starlink 1', '2019-05-24', '2020-01-01', 'Uspostavljanje globalne mreže satelita za internet.', 'Završena');

INSERT INTO Operater (ime_operatera, tip_organizacije, zemlja_id) VALUES
('NASA', 'Vladina', 1), -- Zemlja: SAD
('Roscosmos', 'Vladina', 2), -- Zemlja: Rusija
('SpaceX', 'Komercijalna', 1), -- Zemlja: SAD
('JAXA', 'Vladina', 5), -- Zemlja: Japan
('ISRO', 'Vladina', 6); -- Zemlja: Indija

INSERT INTO Satelit (naziv, zemlja_proizvodnje, masa_kg, misija_id, tip_id) VALUES
('Starlink-1', 'SAD', 260, 3, 1), -- Misija 'Starlink 1', Tip 'Komunikacijski'
('Hubble Telescope', 'SAD', 11110, 2, 4), -- Misija 'Hubble', Tip 'Naučni'
('Orion', 'SAD', 10432, 1, 4), -- Misija 'Artemis 1', Tip 'Naučni'
('Gagarin', 'Rusija', 7500, 2, 2); -- Misija 'Hubble', Tip 'Osmatrački'

INSERT INTO Lansiranje (vrijeme_lansiranja, raketa_id, misija_id, satelit_id, mjesto_id) VALUES
('2019-05-24 02:30:00', 1, 3, 1, 1), -- Raketa 'Falcon 9', Satelit 'Starlink-1', Mjesto 'Cape Canaveral'
('2022-11-16 06:47:00', 3, 1, 3, 1); -- Raketa 'Long March 5', Satelit 'Orion', Mjesto 'Cape Canaveral'

INSERT INTO Orbita (visina_km, inklinacija_stepeni, period_min, datum_pocetka, datum_kraja, satelit_id) VALUES
(550, 53.0, 95, '2019-05-24', '2020-01-01', 1), -- Orbita za Starlink-1
(540, 52.0, 94, '2020-01-02', NULL, 1), -- Druga orbita za Starlink-1 (trenutna)
(540, 28.5, 96, '1990-04-24', NULL, 2); -- Orbita za Hubble Telescope (trenutna)

INSERT INTO Komunikacija (satelit_id, stanica_id, datum_komunikacije, tip_komunikacije, sadrzaj_poruke) VALUES
(2, 1, '2025-08-10', 'Telemetry', 'Slanje telemetrijskih podataka. Satelit u dobrom stanju.'),
(2, 3, '2025-08-10', 'Scientific Data', 'Slanje slika i naučnih podataka s teleskopa.');

INSERT INTO Zemlja_u_Misiji (misija_id, zemlja_id, doprinos) VALUES
(1, 1, 'Lansiranje, vođenje misije'), -- Misija Artemis 1, Zemlja SAD
(2, 1, 'Lansiranje, financiranje'), -- Misija Hubble, Zemlja SAD
(2, 4, 'Naučna podrška'), -- Misija Hubble, Zemlja Evropska unija
(3, 1, 'Lansiranje, proizvodnja, vođenje misije'); -- Misija Starlink 1, Zemlja SAD

INSERT INTO Ucestvovanje (misija_id, operater_id, uloga_id) VALUES
(1, 1, 1), -- Misija Artemis 1, Operater NASA, Uloga Vođa projekta
(1, 1, 2), -- Misija Artemis 1, Operater NASA, Uloga Inženjer
(2, 2, 3), -- Misija Hubble, Operater Roscosmos, Uloga Analitičar podataka
(3, 3, 1); -- Misija Starlink 1, Operater SpaceX, Uloga Vođa projekta

