package no.hvl.dat107.dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class ProsjektDAO {

	private EntityManagerFactory emf;

	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU", Map.of("jakarta.persistence.jdbc.password", "pass"));
	}

	/* --------------------------------------------------------------------- */

	public Prosjekt finnProsjektMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Prosjekt.class, id);

		} finally {
			em.close();
		}
	}

	public void leggTilNyttProsjekt(String pNavn, String beskrivelse) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Prosjekt prosjekt = new Prosjekt(pNavn, beskrivelse);
			em.persist(prosjekt);
			tx.commit();
		} finally {
			em.close();
		}
	}

	public void regProsjektdeltagelse(int ansattId, int prosjektId, String rolle, int timer) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt ans = em.find(Ansatt.class, ansattId);
			Prosjekt pros = em.find(Prosjekt.class, prosjektId);
			Prosjektdeltagelse pd = new Prosjektdeltagelse(ans, pros, rolle, timer);
			ans.leggTilDeltagelse(pd);
			pros.leggTilDeltagelse(pd);
			tx.commit();
		} finally {
			em.close();
		}
	}

	public void endreRolle(int deltagelseId, String nyRolle) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, deltagelseId);
			pd.setRolle(nyRolle);
			em.persist(pd);
			tx.commit();
		} finally {
			em.close();
		}
	}

	public void registrerTimer(int deltagelseId, int timer) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Prosjektdeltagelse pd = em.find(Prosjektdeltagelse.class, deltagelseId);
			pd.setTimer(pd.getTimer() + timer);
			em.persist(pd);
			tx.commit();
		} finally {
			em.close();
		}
	}

	public List<Prosjektdeltagelse> infoProsjekt(int prosjektId) {

		EntityManager em = emf.createEntityManager();

//		String queryString = "SELECT p FROM Prosjekt p WHERE p.prosjekt = :prosjekt_id";

		try {
			Prosjekt p = em.find(Prosjekt.class, prosjektId);
			List<Prosjektdeltagelse> deltagere = p.getDeltagelser();
//			TypedQuery<Prosjektdeltagelse> query = em.createQuery(queryString, Prosjektdeltagelse.class);
//			query.setParameter("prosjekt_id", prosjekt);
//			return query.getResultList();
			return deltagere;
		} finally {
			em.close();
		}

	}

}
