package bibli.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.SubscribeRequest;
import bibli.model.Utilisateur;
import bibli.repo.UtilisateurRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UtilisateurService {
    private final static Logger log = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Utilisateur subscribe(SubscribeRequest request) {
        log.debug("Ajout de l'utilisateur {}", request.username());

        Utilisateur utilisateur = new Utilisateur();
        String hashedPassword = BcryptUtil.bcryptHash(request.password());

        utilisateur.setUsername(request.username());
        utilisateur.setPassword(hashedPassword);
        utilisateur.setAdmin(false);

        this.repository.persist(utilisateur);

        return utilisateur;
    }
}
