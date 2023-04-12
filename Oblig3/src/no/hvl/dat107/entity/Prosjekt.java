package no.hvl.dat107.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prosjekt", schema = "Oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjekt_id;
	private String prosjektnavn;
	private String beskrivelse;

	@OneToMany(mappedBy = "prosjekt", cascade = CascadeType.PERSIST)
	private List<Prosjektdeltagelse> deltagelser;

	public Prosjekt() {

	}

	public Prosjekt(String prosjektnavn, String beskrivelse) {
		this.prosjektnavn = prosjektnavn;
		this.beskrivelse = beskrivelse;
		this.deltagelser = new ArrayList<Prosjektdeltagelse>();
	}

	public int getProsjekt_id() {
		return prosjekt_id;
	}

	public void setProsjekt_id(int prosjekt_id) {
		this.prosjekt_id = prosjekt_id;
	}

	public String getProsjektnavn() {
		return prosjektnavn;
	}

	public void setProsjektnavn(String prosjektnavn) {
		this.prosjektnavn = prosjektnavn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void leggTilDeltagelse(Prosjektdeltagelse deltagelse) {
		deltagelser.add(deltagelse);
	}

	public void setDeltagelser(List<Prosjektdeltagelse> deltagelser) {
		this.deltagelser = deltagelser;
	}

	@Override
	public String toString() {
		return "Prosjekt [prosjekt_id=" + prosjekt_id + ", prosjektnavn=" + prosjektnavn + ", beskrivelse="
				+ beskrivelse + "]";
	}

}
