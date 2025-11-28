package bibli.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.CreateOrUpdateLivreRequest;
import bibli.model.Livre;
import bibli.repo.LivreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivreService {
    private final static Logger log = LoggerFactory.getLogger(LivreService.class);

    private final LivreRepository repository;

    public LivreService(LivreRepository repository) {
        this.repository = repository;
    }

    public Stream<Livre> findAll() {
        log.debug("Liste des livres");

        return this.repository.findAll().stream();
    }

    public Optional<Livre> findById(int id) {
        log.debug("Récupération du livre {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Livre create(CreateOrUpdateLivreRequest request) {
        log.debug("Création du livre {}", request.getNom());

        Livre livre = new Livre();

        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());
        livre.setAuteur(request.getAuteur());
        livre.setEditeur(request.getEditeur());
        livre.setCollection(request.getCollection());

        this.repository.persist(livre);

        return livre;
    }

    @Transactional
    public Livre update(int id, CreateOrUpdateLivreRequest request) {
        log.debug("Mise à jour du livre {}", id);

        Livre livre = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());
        livre.setAuteur(request.getAuteur());
        livre.setEditeur(request.getEditeur());
        livre.setCollection(request.getCollection());

        this.repository.persist(livre);

        return livre;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression du livre {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer le livre {} : {}", id, ex.getMessage());
            return false;
        }
    }
}
