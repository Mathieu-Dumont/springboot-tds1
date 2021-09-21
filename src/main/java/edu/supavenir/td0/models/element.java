package edu.supavenir.td0.models;

import java.util.Objects;

public class element {

	private String nom;
	private int evaluation;
	

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		element other = (element) obj;
		return evaluation == other.evaluation && Objects.equals(nom, other.nom);
	}
	
	
	
}
