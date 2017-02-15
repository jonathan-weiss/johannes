package ch.johannes.rest.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Path("/version")
@Api(value = "/version", tags = {"VersionRestService"})
@Transactional
public class VersionRestService {

    @GET
    @Produces("application/json")
    @ApiResponses({@ApiResponse(code = 200, response = GetVersionResponse.class, message = "returns the current server version")})
    @PermitAll
    public Response getVersion() {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF")) {
            Manifest manifest = new Manifest(inputStream);
            Attributes sMainManifestAttributes = manifest.getMainAttributes();

            String version = sMainManifestAttributes.getValue("Build-Version");
            String buildtime = sMainManifestAttributes.getValue("Build-Time");

            return Response.status(Response.Status.OK).entity(String.format("{ version: '%s', buildtime: '%s' }", version, buildtime)).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static interface GetVersionResponse<String> {
        // Tagging interface for swagger
    }
}
