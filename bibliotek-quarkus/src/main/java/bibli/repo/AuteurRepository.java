package bibli.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import bibli.model.Auteur;

@ApplicationScoped
public class AuteurRepository implements PanacheRepositoryBase<Auteur, Integer> {

}
