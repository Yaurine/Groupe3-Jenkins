package bibli.repo;

import java.util.Optional;

import bibli.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, String> {

    public Optional<Utilisateur> findByUsername(String username) {
        return this.find("username", username).firstResultOptional();
    }
}
