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
    private int auteurId;
    @NotBlank
    private int editeurId;
    @NotBlank
    private int collectionId;

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getPublication() {
        return this.publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public int getAuteurId() {
        return this.auteurId;
    }

    public void setAuteurId(int auteurId) {
        this.auteurId = auteurId;
    }

    public int getEditeurId() {
        return this.editeurId;
    }

    public void setEditeurId(int editeurId) {
        this.editeurId = editeurId;
    }

    public int getCollectionId() {
        return this.collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

}
