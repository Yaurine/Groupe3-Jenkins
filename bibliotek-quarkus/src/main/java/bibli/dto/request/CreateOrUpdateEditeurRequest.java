package bibli.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateEditeurRequest {
    @NotBlank
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom= nom;
    }

    @NotBlank
    private String pays;

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays= pays;
    }
}