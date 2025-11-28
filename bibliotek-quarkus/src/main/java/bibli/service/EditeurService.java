package bibli.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.CreateOrUpdateEditeurRequest;
import bibli.model.Editeur;
import bibli.repo.EditeurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EditeurService {
    private final static Logger log = LoggerFactory.getLogger(EditeurService.class);

    private final EditeurRepository repository;

    public EditeurService(EditeurRepository repository) {
        this.repository = repository;
    }

    public Stream<Editeur> findAll() {
        log.debug("Liste des éditeurs");

        return this.repository.findAll().stream();
    }

    public Optional<Editeur> findById(int id) {
        log.debug("Récupération de l'éditeur' {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Editeur create(CreateOrUpdateEditeurRequest request) {
        log.debug("Création de l'éditeur' {}", request.getNom());

        Editeur editeur = new Editeur();

        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repository.persist(editeur);

        return editeur;
    }

    @Transactional
    public Editeur update(int id, CreateOrUpdateEditeurRequest request) {
        log.debug("Mise à jour de l'éditeur' {}", id);

        Editeur editeur = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repository.persist(editeur);

        return editeur;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression de l'éditeur' {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer l'éditeur' {} : {}", id, ex.getMessage());
            return false;
        }
    }
}
