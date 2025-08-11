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
CREATE OR REPLACE VIEW Komunikacija_Stanica_Satelit AS
SELECT
    zs.naziv AS naziv_stanice,
    zs.lokacija AS lokacija_stanice,
    s.naziv AS naziv_satelita,
    k.datum_komunikacije,
    k.tip_komunikacije,
    k.sadrzaj_poruke
FROM Komunikacija AS k
JOIN Zemaljska_Stanica AS zs ON k.stanica_id = zs.stanica_id
JOIN Satelit AS s ON k.satelit_id = s.satelit_id;

SELECT * FROM Komunikacija_Stanica_Satelit;

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