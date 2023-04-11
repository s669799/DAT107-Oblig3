------------------------- part 1-------------------------------------------------------------------------
DROP SCHEMA IF EXISTS Oblig3 CASCADE;
CREATE SCHEMA Oblig3;
SET search_path TO Oblig3;

CREATE TABLE ansatt(
ansatt_id SERIAL PRIMARY KEY,
brukernavn VARCHAR(4) UNIQUE,
fornavn VARCHAR(25),
etternavn VARCHAR(25),
ansattdato DATE,
stilling VARCHAR(40),
manedslonn int
--avdeling int NOT NULl ON DELETE RESTRICT,
--FOREIGN KEY (avdeling) REFERENCES avdeling(avdeling_id) 
);

INSERT INTO 
ansatt(brukernavn, fornavn, etternavn, ansattdato, stilling, manedslonn)
VALUES
('anju' , 'Anton', 'Julius', '1999-12-01', 'Corporate Mobility Facilitator', 500000),
('dust' , 'Durek', 'Strand', '2012-03-20', 'Human Program Producer', 600000),
('hagr' , 'Hans', 'Gregersen', '2004-07-01', 'Dynamic Optimization Strategist', 700000),
('kebj' , 'Kenneth', 'Bjørge', '2019-01-29', 'Investor Functionality Administrator', 800000);
('cobe' , 'Conrad', 'Beuile', '2010-07-01', 'Legacy Creative Director', 900000);
('stka' , 'Steffen', 'Karlsen', '2011-08-01', 'Corporate Data Agent', 400000);

-----------------------------part 2--------------------------------------------------------------------------

CREATE TABLE Oblig3.avdeling(
    avdeling_id SERIAL PRIMARY KEY,
    avdelingsnavn VARCHAR(40),
    sjef int,
    CONSTRAINT sjefFK FOREIGN KEY (sjef) REFERENCES Oblig3.ansatt(ansatt_id)
)

ALTER TABLE Oblig3.ansatt
	ADD avdeling int,
    ADD CONSTRAINT avdeling FOREIGN KEY (avdeling) REFERENCES Oblig3.avdeling(avdeling_id);

INSERT INTO 
avdeling(avdelingsnavn, sjef)
VALUES
('R&D', 1),
('Production', 3),
('HR', 2);

UPDATE Oblig3.ansatt
SET avdeling = 10
WHERE ansatt_id = 1;

UPDATE Oblig3.ansatt
SET avdeling = 11
WHERE ansatt_id = 3;

UPDATE Oblig3.ansatt
SET avdeling = 12
WHERE ansatt_id = 2;

--@OneToMany (ansatt - avdeling, sjef) i tabell avdeling, mange ansatte, få avdelinger
--@ManyToOne der FK ligger ()

ALTER TABLE Oblig3.ansatt
ALTER COLUMN avdeling SET NOT NULL;

ALTER TABLE Oblig3.avdeling
ALTER COLUMN sjef SET NOT NULL;


-------------------------part 3-------------------------------------------------------------------------


CREATE TABLE Oblig3.prosjekt(
    prosjekt_id SERIAL PRIMARY KEY,
    prosjektnavn VARCHAR(40),
    beskrivelse VARCHAR(200)
);

CREATE TABLE Oblig3.prosjektdeltagelse(
    deltagelse_id SERIAL PRIMARY KEY,
    ansatt_id int,
    prosjekt_id int,
    rolle VARCHAR(40),
    timer int,
    CONSTRAINT ansattProsjektUnik UNIQUE (ansatt_id, prosjekt_id),
    CONSTRAINT ansattFK FOREIGN KEY (ansatt_id) REFERENCES Oblig3.ansatt(ansatt_id),
    CONSTRAINT prosjektFK FOREIGN KEY (prosjekt_id) REFERENCES Oblig3.prosjekt(prosjekt_id) 
);
