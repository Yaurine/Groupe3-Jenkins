package bibli.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.CreateOrUpdateLivreRequest;
import bibli.dto.response.LivreResponse;
import bibli.model.Livre;
import bibli.service.LivreService;
import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/livre")
@Authenticated
public class LivreResource {
    private final static Logger log = LoggerFactory.getLogger(LivreResource.class);

    private final LivreService service;

    public LivreResource(LivreService service) {
        this.service = service;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Lister les livres");

        return this.service.findAll().map(LivreResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher le livre {}", id);

        Optional<Livre> optLivre = this.service.findById(id);

        if (optLivre.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(LivreResponse.convert(optLivre.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Créer le livre {}", request.getNom());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Mettre à jour le livre {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") int id) {
        log.debug("Supprimer le livre {}", id);

        return this.service.deleteById(id);
    }
}
