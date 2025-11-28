package bibli.repo;

import bibli.model.Livre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivreRepository implements PanacheRepositoryBase<Livre, Integer> {

}
