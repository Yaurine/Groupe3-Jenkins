package bibli.dto.request;

import java.time.LocalDate;

import bibli.model.Auteur;
import bibli.model.Collection;
import bibli.model.Editeur;
import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateLivreRequest {
    @NotBlank
    private String nom;
    @NotBlank
    private String resume;
    @NotBlank
    private LocalDate publication;
    @NotBlank
    private Auteur auteur;
    @NotBlank
    private Editeur editeur;
    @NotBlank
    private Collection collection;

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
}
