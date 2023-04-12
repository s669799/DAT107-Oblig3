package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prosjektdeltagelse", schema = "Oblig3")
public class Prosjektdeltagelse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deltagelse_id;

	private String rolle;
	private int timer;

	@ManyToOne
	@JoinColumn(name = "ansatt_id", referencedColumnName = "ansatt_id")
	private Ansatt ansatt;

	@ManyToOne
	@JoinColumn(name = "prosjekt_id", referencedColumnName = "prosjekt_id")
	private Prosjekt prosjekt;

	public Prosjektdeltagelse() {
	}
	
	public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int timer) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.rolle = rolle;
        this.timer = timer;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	@Override
	public String toString() {
		return "Prosjektdeltagelse [deltagelse_id=" + deltagelse_id + ", rolle=" + rolle + ", timer=" + timer
				+ ", ansatt=" + ansatt + ", prosjekt=" + prosjekt + "]";
	}
	
	

}
