package no.hvl.dat107;

import java.util.List;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class Main {

	public static void main(String[] args) {

		AnsattDAO ansattDAO = new AnsattDAO();
		AvdelingDAO avdelingDAO = new AvdelingDAO();
		ProsjektDAO prosjektDAO = new ProsjektDAO();

		Avdeling avd = avdelingDAO.finnAvdelingMedId(10);
		System.out.println(avd);

		Ansatt a1 = new Ansatt("lamo", "Lars", "Monsen", "2019-11-01", "Lead Optimization Producer", 550000, avd);
//		Ansatt a2 = new Ansatt("pehy", "Preben", "Hylkje", "1999-04-21", "Senior Configuration Planner", 650000, 11);
//		Ansatt a3 = new Ansatt("guwi", "Gunnar", "Wilhelmsen", "1981-03-17", "District Communications Associate", 750000, 12);
//		Ansatt a4 = new Ansatt("knol", "Knut", "Olsen", "2023-02-15", "Central Configuration Administrator", 800000, 10);

		Ansatt a = ansattDAO.finnAnsattMedId(10);
		System.out.println(a);
//
//		Ansatt b = ansattDAO.finnAnsattMedBrukerN("anju");
//		System.out.println(b);

//		ansattDAO.leggTilAnsatt(a1);
//
//		ansattDAO.fjernAnsatt(8);
//
		List<Ansatt> liste = ansattDAO.listUtAlle();
		System.out.println(liste);

		List<Avdeling> listeAvd = avdelingDAO.listUtAvdelinger();
		System.out.println(listeAvd);

		List<Ansatt> liste3 = avdelingDAO.listUtAnsattePaAvd(avd);
		System.out.println(liste3);

		Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(1);

		List<Prosjektdeltagelse> liste4 = prosjektDAO.infoProsjekt(1);
		System.out.println(liste4);


//		prosjektDAO.leggTilNyttProsjekt("Prosjekt a", "Design og produksjon av ny bil");
//		prosjektDAO.leggTilNyttProsjekt("Prosjekt b", "Forbedring av arbeidsmiljø");
//		prosjektDAO.leggTilNyttProsjekt("Prosjekt c", "Programvare til ny bil");

//		System.out.println(prosjekt);
//		prosjektDAO.regProsjektdeltagelse(1, 1, 0);
//		prosjektDAO.regProsjektdeltagelse(2, 2, "Potetskreller", 15);
//		prosjektDAO.regProsjektdeltagelse(10, 3, "Kokk", 140);

//		prosjektDAO.endreRolle(1, "Fisker");
//		prosjektDAO.registrerTimer(1, 40);

//		ansattDAO.oppdatereAvd(1, 11);

//		avdelingDAO.leggTilNyAvdeling("IT", 10);
//
//		ansattDAO.oppdatereLonn(1, 500000);
//		System.out.println(ansattDAO.finnAnsattMedId(1));
//
//		dao.fjernAnsatt(5);
//		List<Ansatt> liste2 = ansattDAO.listUtAlle();
//		System.out.println(liste2);
	}

}
