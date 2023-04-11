package no.hvl.dat107.dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU", Map.of("jakarta.persistence.jdbc.password", "pass"));
	}

	/* --------------------------------------------------------------------- */

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
	}

	public Ansatt finnAnsattMedBrukerN(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn LIKE :brukernavn",
					Ansatt.class);
			query.setParameter("brukernavn", brukernavn);

			return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater

		} finally {
			em.close();
		}
	}

	public void leggTilAnsatt(Ansatt a) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(a);
			tx.commit();
		} finally {
			em.close();
		}

	}

	public void fjernAnsatt(int id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			em.remove(a);
			tx.commit();
		} finally {
			em.close();
		}

	}

	public List<Ansatt> listUtAlle() {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public void oppdatereStilling(int id, String nyStilling) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			a.setStilling(nyStilling);
			em.merge(a);
			tx.commit();
		} finally {
			em.close();
		}

	}

	public void oppdatereLonn(int id, int nyLonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			a.setManedslonn(nyLonn);
			em.merge(a);
			tx.commit();

		} finally {
			em.close();
		}
	}

	public void oppdatereAvd(int id, int nyAvdId) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			Avdeling nyAvd = em.find(Avdeling.class, nyAvdId);
			
			if (!a.erSjef()) {
				a.setAvdeling(nyAvd);
			}
			tx.commit();

		} finally {
			em.close();
		}
	}


	public void registrerProsjektdeltakelse(int ansattid, int prosjektid) {

	}
}
