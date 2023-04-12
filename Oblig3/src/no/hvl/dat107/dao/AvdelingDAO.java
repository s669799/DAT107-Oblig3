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

public class AvdelingDAO {

	private EntityManagerFactory emf;

	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPU", Map.of("jakarta.persistence.jdbc.password", "pass"));
	}

	/* --------------------------------------------------------------------- */

	public Avdeling finnAvdelingMedId(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Avdeling.class, id);

		} finally {
			em.close();
		}

	}

	public List<Avdeling> listUtAvdelinger() {

		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a", Avdeling.class);
			return query.getResultList();

		} finally {
			em.close();
		}
	}

	public List<Ansatt> listUtAnsattePaAvd(Avdeling avdeling) {

		EntityManager em = emf.createEntityManager();
		Ansatt sjef = avdeling.getSjef();
		String queryString = "SELECT a FROM Ansatt a WHERE a.avdeling = :avdelingId";

		try {

			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			query.setParameter("avdelingId", avdeling);
			return query.getResultList();
		} finally {
			em.close();
		}

	}

	public void leggTilNyAvdeling(String avdNavn, int ansattId) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt sjef = em.find(Ansatt.class, ansattId);
			if (!sjef.erSjef()) {
				Avdeling avd = new Avdeling(avdNavn, sjef);
				sjef.setAvdeling(avd);
				em.persist(avd);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

	}

}
