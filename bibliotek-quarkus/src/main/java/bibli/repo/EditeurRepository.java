package bibli.repo;

import bibliotek-quarkus.model.Editeur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EditeurRepository implements PanacheRepositoryBase<Editeur, Integer> {
    
}
