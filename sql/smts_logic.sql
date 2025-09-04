USE smts_baza;

-- Pogled za detaljan prikaz lansiranja
CREATE OR REPLACE VIEW Detalji_Lansiranja AS
SELECT
    s.naziv AS satelit,
    l.vrijeme_lansiranja,
    rn.naziv AS raketa_nosac,
    m.naziv AS misija,
    ml.naziv AS mjesto_lansiranja
FROM Lansiranje AS l
JOIN Satelit AS s ON l.satelit_id = s.satelit_id
JOIN Raketa_Nosac AS rn ON l.raketa_id = rn.raketa_id
JOIN Misija AS m ON l.misija_id = m.misija_id
JOIN Mjesto_Lansiranja AS ml ON l.mjesto_id = ml.mjesto_id;

SELECT * FROM Detalji_Lansiranja;

-- Pogled za prikaz zemalja i operatera ukljucenih u pojedinu misiju
CREATE OR REPLACE VIEW Misije_Detalji_Partnera AS
SELECT
    m.naziv AS naziv_misije,
    m.status AS status_misije,
    z.naziv_zemlje AS zemlja,
    o.ime_operatera AS operater,
    u.naziv_uloge AS uloga_operatera
FROM Misija AS m
JOIN Zemlja_u_Misiji AS zum ON m.misija_id = zum.misija_id
JOIN Zemlja AS z ON zum.zemlja_id = z.zemlja_id
JOIN Ucestvovanje AS uc ON m.misija_id = uc.misija_id
JOIN Operater AS o ON uc.operater_id = o.operater_id
JOIN Uloga_Operatera AS u ON uc.uloga_id = u.uloga_id;

SELECT * FROM Misije_Detalji_Partnera;

-- Pogled za prikaz broja misija po svakoj zemlji
CREATE OR REPLACE VIEW Misije_Po_Zemlji AS
SELECT
    m.status,
    z.naziv_zemlje AS zemlja,
    COUNT(DISTINCT m.misija_id) AS broj_misija
FROM Misija AS m
JOIN Zemlja_u_Misiji AS zum ON m.misija_id = zum.misija_id
JOIN Zemlja AS z ON zum.zemlja_id = z.zemlja_id
GROUP BY m.status, z.naziv_zemlje;

SELECT * FROM Misije_Po_Zemlji;

-- Pogled koji pruza pregled satelita, njihovog tipa, misije i trenutnih karakteristika orbite
CREATE OR REPLACE VIEW Sateliti_i_Orbite AS
SELECT
    s.naziv AS naziv_satelita,
    ts.naziv_tipa AS tip_satelita,
    m.naziv AS naziv_misije,
    o.visina_km AS visina_orbite_km,
    o.inklinacija_stepeni AS inklinacija_orbite
FROM Satelit AS s
JOIN Misija AS m ON s.misija_id = m.misija_id
JOIN Tip_Satelita AS ts ON s.tip_id = ts.tip_id
LEFT JOIN Orbita AS o ON s.satelit_id = o.satelit_id AND o.datum_kraja IS NULL; -- Left join da dohvati sve satelite (jer neki sateliti mozda nemaju postavljenu trenutnu orbitu), takodje AND o.datum_kraja IS NULL -> osigurava da je uslov spajanja orbite i satelita samo ako orbita postoji

-- Pogled koji nam daje uvid u to koje zemaljske stanice komuniciraju s kojim satelitima, kao i o tipu komunikacije
CREATE VIEW Komunikacija_Stanica_Satelit AS
SELECT
    k.komunikacija_id,  -- Dodajte ovu liniju
    zs.naziv AS naziv_stanice,
    zs.lokacija AS lokacija_stanice,
    s.naziv AS naziv_satelita,
    k.datum_komunikacije,
    k.tip_komunikacije,
    k.sadrzaj_poruke
FROM
    Komunikacija k
JOIN
    Zemaljska_Stanica zs ON k.stanica_id = zs.stanica_id
JOIN
    Satelit s ON k.satelit_id = s.satelit_id;

-- Pogled koji nam pruza statistiku o broju lansiranja nekog proizvodjaca raketa nosaca
CREATE OR REPLACE VIEW Lansiranja_Po_Proizvodjacu AS
SELECT
    rn.proizvodjac,
    rn.naziv AS raketa_nosac,
    COUNT(l.lansiranje_id) AS broj_lansiranja
FROM Raketa_Nosac AS rn
JOIN Lansiranje AS l ON rn.raketa_id = l.raketa_id
GROUP BY rn.proizvodjac, rn.naziv
ORDER BY COUNT(l.lansiranje_id) DESC;

-- Pogled koji omogucava pregled misija po statusu
CREATE OR REPLACE VIEW Misije_Po_Statusu AS
SELECT
    m.misija_id,
    m.naziv AS naziv_misije,
    m.datum_pocetka,
    m.datum_kraja,
    m.cilj_misije,
    m.status,
    CASE
        WHEN m.datum_kraja IS NULL THEN 'Aktivna'
        ELSE 'Završena'
    END AS status_detalji
FROM Misija AS m;

SELECT * FROM Misije_Po_Statusu;

CREATE OR REPLACE VIEW Svi_Sateliti_Detalji AS
SELECT
    s.satelit_id,
    s.naziv AS naziv_satelita,
    s.zemlja_proizvodnje,
    s.masa_kg,
    ts.naziv_tipa AS tip_satelita,
    m.naziv AS naziv_misije
FROM Satelit AS s
LEFT JOIN Tip_Satelita AS ts ON s.tip_id = ts.tip_id
LEFT JOIN Misija AS m ON s.misija_id = m.misija_id;

SELECT * FROM Svi_Sateliti_Detalji;

-- Ovo su pogledi za sad

-- Procedura koja unosi podatke u dvije tabele (Satelit i Lansiranje) unutar jedne transakcije
DELIMITER $$

CREATE PROCEDURE KreirajNovoLansiranje (
    -- Parametri za unos novog satelita
    IN p_naziv_satelita VARCHAR(255),
    IN p_zemlja_proizvodnje VARCHAR(100),
    IN p_masa_kg DECIMAL(10, 2),
    IN p_misija_id INT,
    IN p_tip_id INT,

    -- Parametri za unos novog lansiranja
    IN p_vrijeme_lansiranja DATETIME,
    IN p_raketa_id INT,
    IN p_mjesto_id INT
)
BEGIN
    DECLARE v_satelit_id INT;

    -- Pokretanje transakcije
    START TRANSACTION;

    -- Pokusaj unosa novog satelita
    INSERT INTO Satelit (naziv, zemlja_proizvodnje, masa_kg, misija_id, tip_id)
    VALUES (p_naziv_satelita, p_zemlja_proizvodnje, p_masa_kg, p_misija_id, p_tip_id);

    -- Dohvatanje id-a upravo unesenog satelita
    SET v_satelit_id = LAST_INSERT_ID();

    -- Pokusaj unosa novog lansiranja, koristeci id satelita koji smo dobili
    INSERT INTO Lansiranje (vrijeme_lansiranja, raketa_id, misija_id, satelit_id, mjesto_id)
    VALUES (p_vrijeme_lansiranja, p_raketa_id, p_misija_id, v_satelit_id, p_mjesto_id);

    COMMIT;

END $$

DELIMITER ;

/*CALL KreirajNovoLansiranje(
    'Satelit-5',      -- p_naziv_satelita
    'Evropska unija', -- p_zemlja_proizvodnje
    4500.00,          -- p_masa_kg
    1,                -- p_misija_id (npr. Artemis 1)
    1,                -- p_tip_id (npr. Komunikacijski)
    '2025-10-26 12:00:00', -- p_vrijeme_lansiranja
    4,                -- p_raketa_id (npr. Ariane 5)
    4                 -- p_mjesto_id (npr. Kourou)
);

SELECT * FROM Satelit;
SELECT * FROM Lansiranje;
SELECT * FROM Detalji_Lansiranja; */

-- Trigger za azuriranje broja satelita u misiji
DELIMITER $$

CREATE TRIGGER AfterInsertSatelit
AFTER INSERT ON Satelit
FOR EACH ROW
BEGIN
    UPDATE Misija
    SET broj_satelita = broj_satelita + 1
    WHERE misija_id = NEW.misija_id;
END $$

DELIMITER ;

SELECT * FROM Misija; -- Test

-- Unos novog satelita u misiju 'Starlink 1' (misija_id = 3)
/*CALL KreirajNovoLansiranje(
    'Starlink-100',      -- p_naziv_satelita
    'SAD',              -- p_zemlja_proizvodnje
    260.00,             -- p_masa_kg
    3,                  -- p_misija_id
    1,                  -- p_tip_id
    '2026-01-15 10:00:00', -- p_vrijeme_lansiranja
    1,                  -- p_raketa_id
    1                   -- p_mjesto_id
); */


-- Trigger za azuriranje statusa misije pri unosu novog lansiranja
DELIMITER $$

CREATE TRIGGER nakon_lansiranja_azuriraj_status
AFTER INSERT ON Lansiranje
FOR EACH ROW
BEGIN
    UPDATE Misija
    SET status = 'Aktivna'
    WHERE misija_id = NEW.misija_id AND status = 'Planirana';
END$$

DELIMITER ;

-- test unosi: azuriranj statusa misije na planirana, zatim kreiranje novog lansiranja da vidimo da li se automatski postavlja na aktivna

/*UPDATE Misija SET status = 'Planirana' WHERE misija_id = 30;

SELECT * FROM Misija WHERE misija_id = 30;

INSERT INTO Lansiranje (vrijeme_lansiranja, raketa_id, misija_id, satelit_id, mjesto_id)
VALUES ('2025-09-03 10:30:00', 1, 30, 1, 1);

SELECT * FROM Misija WHERE misija_id = 30; */
