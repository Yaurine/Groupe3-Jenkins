package bibli.api;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import bibli.dto.request.AuthRequest;
import bibli.dto.response.AuthResponse;

@Path("/auth")
public class AuthResource {

    @Inject
    private JpaIdentityProvider identityProvider;

    @Inject
    private EntityManager entityManager;

    @POST
    public AuthResponse auth(@Valid AuthRequest request) {
        UsernamePasswordAuthenticationRequest authenticationRequest = new UsernamePasswordAuthenticationRequest(
                request.getUsername(),
                new PasswordCredential(request.getPassword().toCharArray()));
        SecurityIdentity identity = this.identityProvider.authenticate(this.entityManager, authenticationRequest);

        String jwt = Jwt.issuer("bibli-quarkus-issuer")
                .upn(request.getUsername()) // User Principal Name
                .groups(identity.getRoles()) // Le ou les rôles
                .sign() // On signe le jeton avec la clé privée
        ;
        return new AuthResponse(jwt);
    }
}