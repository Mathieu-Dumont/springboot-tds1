package edu.supavenir.td0.models;

import java.util.List;

public class Categorie {
	private String libelle;
	private List<Element> element;

	// Getters & Setters
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Element> getElement() {
		return element;
	}

	public void setElement(List<Element> element) {
		this.element = element;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Categorie)) {
			return false;
		}
		return ((Categorie) obj).getLibelle().equals(this.libelle);
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public void AddItems(Element elm) {
		this.element.add(elm);
	}

	public void DeleteItems(Element elm) {
		this.element.remove(elm);
	}

}
