package ch.johannes.rest.endpoints.person;

import ch.johannes.example.data.dao.person.Person;
import ch.johannes.example.service.person.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/person")
@Api(value = "/person", tags = {"PersonRestService"})
@Transactional
public class PersonRestService {

    @Inject
    private PersonService personService;

    @GET
    @Produces("application/json")
    @ApiResponses({@ApiResponse(code = 200, response = GetPersonListResponse.class, message = "returns all person")})
    @PermitAll
    public Response getVersion() {
        final List<Person> allPerson = personService.getAllPerson();

        return Response.status(Response.Status.OK).entity(allPerson).build();

    }

    private static interface GetPersonListResponse<List> {
        // Tagging interface for swagger
    }
}
