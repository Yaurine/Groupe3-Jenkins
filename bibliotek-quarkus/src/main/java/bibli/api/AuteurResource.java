package bibli.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import bibli.dto.request.CreateOrUpdateAuteurRequest;
import bibli.dto.response.AuteurResponse;
import bibli.model.Auteur;
import bibli.service.AuteurService;

@Path("/auteur")
public class AuteurResource {
    private final static Logger log = LoggerFactory.getLogger(AuteurResource.class);

    private final AuteurService service;

    public AuteurResource(AuteurService service) {
        this.service = service;
    }

    @GET
    public List<AuteurResponse> findAll() {
        log.debug("Lister les auteurs");

        return this.service.findAll().map(AuteurResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher l'auteur {}", id);

        Optional<Auteur> optAuteur = this.service.findById(id);

        if (optAuteur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(AuteurResponse.convert(optAuteur.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Créer la l'auteur {}", request.getNom());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Mettre à jour l'auteur {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") int id) {
        log.debug("Supprimer l'auteur' {}", id);

        return this.service.deleteById(id);
    }
}
