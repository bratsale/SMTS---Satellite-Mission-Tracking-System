CREATE DATABASE IF NOT EXISTS smts_baza
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE smts_baza;

CREATE TABLE Misija (
    misija_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    datum_pocetka DATE,
    datum_kraja DATE,
    cilj_misije TEXT,
    status ENUM('Aktivna', 'Završena', 'Planirana')
);

CREATE TABLE Zemlja (
    zemlja_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv_zemlje VARCHAR(100) NOT NULL,
    kontinent VARCHAR(50)
);

CREATE TABLE Raketa_Nosac (
    raketa_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    proizvodjac VARCHAR(100),
    kapacitet_tovar_kg INT
);

CREATE TABLE Tip_Satelita (
    tip_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv_tipa VARCHAR(100) NOT NULL,
    opis_tipa TEXT
);

CREATE TABLE Mjesto_Lansiranja (
    mjesto_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    država VARCHAR(100),
    geografska_duzina DECIMAL(9, 6),
    geografska_sirina DECIMAL(9, 6)
);

CREATE TABLE Zemaljska_Stanica (
    stanica_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    lokacija VARCHAR(255),
    zemlja_stanice VARCHAR(100)
);

CREATE TABLE Uloga_Operatera (
    uloga_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv_uloge VARCHAR(100) NOT NULL,
    opis_uloge TEXT
);

CREATE TABLE Operater (
    operater_id INT PRIMARY KEY AUTO_INCREMENT,
    ime_operatera VARCHAR(255) NOT NULL,
    tip_organizacije VARCHAR(100),
    zemlja_id INT,
    FOREIGN KEY (zemlja_id) REFERENCES Zemlja(zemlja_id)
);

CREATE TABLE Satelit (
    satelit_id INT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    zemlja_proizvodnje VARCHAR(100),
    masa_kg INT,
    misija_id INT,
    tip_id INT,
    FOREIGN KEY (misija_id) REFERENCES Misija(misija_id),
    FOREIGN KEY (tip_id) REFERENCES Tip_Satelita(tip_id)
);

CREATE TABLE Lansiranje (
    lansiranje_id INT PRIMARY KEY AUTO_INCREMENT,
    vrijeme_lansiranja DATETIME,
    raketa_id INT,
    misija_id INT,
    satelit_id INT,
    mjesto_id INT,
    FOREIGN KEY (raketa_id) REFERENCES Raketa_Nosac(raketa_id),
    FOREIGN KEY (misija_id) REFERENCES Misija(misija_id),
    FOREIGN KEY (satelit_id) REFERENCES Satelit(satelit_id),
    FOREIGN KEY (mjesto_id) REFERENCES Mjesto_Lansiranja(mjesto_id)
);

CREATE TABLE Orbita (
    orbita_id INT PRIMARY KEY AUTO_INCREMENT,
    visina_km INT,
    inklinacija_stepeni DECIMAL(5, 2),
    period_min INT,
    datum_pocetka DATE,
    datum_kraja DATE,
    satelit_id INT,
    FOREIGN KEY (satelit_id) REFERENCES Satelit(satelit_id)
);

CREATE TABLE Komunikacija (
    komunikacija_id INT PRIMARY KEY AUTO_INCREMENT,
    satelit_id INT,
    stanica_id INT,
    datum_komunikacije DATE,
    tip_komunikacije VARCHAR(100),
    sadrzaj_poruke TEXT,
    FOREIGN KEY (satelit_id) REFERENCES Satelit(satelit_id),
    FOREIGN KEY (stanica_id) REFERENCES Zemaljska_Stanica(stanica_id)
);

CREATE TABLE Zemlja_u_Misiji (
    zemlja_u_misiji_id INT PRIMARY KEY AUTO_INCREMENT,
    misija_id INT,
    zemlja_id INT,
    doprinos VARCHAR(100),
    FOREIGN KEY (misija_id) REFERENCES Misija(misija_id),
    FOREIGN KEY (zemlja_id) REFERENCES Zemlja(zemlja_id)
);

CREATE TABLE Ucestvovanje (
    ucestvovanje_id INT PRIMARY KEY AUTO_INCREMENT,
    misija_id INT,
    operater_id INT,
    uloga_id INT,
    FOREIGN KEY (misija_id) REFERENCES Misija(misija_id),
    FOREIGN KEY (operater_id) REFERENCES Operater(operater_id),
    FOREIGN KEY (uloga_id) REFERENCES Uloga_Operatera(uloga_id)
);
