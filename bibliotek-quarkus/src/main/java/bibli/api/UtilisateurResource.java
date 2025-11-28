package bibli.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.SubscribeRequest;
import bibli.model.Utilisateur;
import bibli.service.UtilisateurService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/utilisateur")
public class UtilisateurResource {
    private final static Logger log = LoggerFactory.getLogger(UtilisateurResource.class);

    private final UtilisateurService service;

    public UtilisateurResource(UtilisateurService service) {
        this.service = service;
    }

    @Path("/inscription")
    @POST
    public Response subscribe(SubscribeRequest request) {
        log.debug("Inscription d'un nouvel utilisateur");

        Utilisateur utilisateur = this.service.subscribe(request);

        return Response.status(Status.CREATED)
                .entity(Map.of("id", utilisateur.getId()))
                .build();
    }
}