package bibli.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibli.dto.request.CreateOrUpdateEditeurRequest;
import bibli.dto.response.EditeurResponse;
import bibli.model.Editeur;
import bibli.service.EditeurService;
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

@Path("/editeur")
@Authenticated
public class EditeurResource {
    private final static Logger log = LoggerFactory.getLogger(EditeurResource.class);

    private final EditeurService service;

    public EditeurResource(EditeurService service) {
        this.service = service;
    }

    @GET
    public List<EditeurResponse> findAll() {
        log.debug("Lister les editeurs");

        return this.service.findAll().map(EditeurResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher l'editeur {}", id);

        Optional<Editeur> optEditeur = this.service.findById(id);

        if (optEditeur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(EditeurResponse.convert(optEditeur.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Créer l'editeur {}", request.getNom());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Mettre à jour l'editeur {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") int id) {
        log.debug("Supprimer l'editeur' {}", id);

        return this.service.deleteById(id);
    }
}
