package bibli.dto.response;

import java.time.LocalDate;

import bibli.model.Auteur;
import bibli.model.Collection;
import bibli.model.Editeur;
import bibli.model.Livre;

public class LivreResponse {
    private int id;
    private String nom;
    private String resume;
    private LocalDate publication;
    private Auteur auteur;
    private Editeur editeur;
    private Collection collection;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public static LivreResponse convert(Livre livre) {
        LivreResponse resp = new LivreResponse();

        resp.setId(livre.getId());
        resp.setNom(livre.getNom());
        resp.setResume(livre.getResume());
        resp.setPublication(livre.getPublication());
        resp.setAuteur(livre.getAuteur());
        resp.setEditeur(livre.getEditeur());
        resp.setCollection(livre.getCollection());

        return resp;
    }

}
