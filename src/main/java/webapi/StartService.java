package webapi;

import bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;

@Path("")
public class StartService {
    private final List<User> userCache = new ArrayList<>();

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getDispatcher() {
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/demo/api/messages", "getAllPersons", MediaType.APPLICATION_JSON);

        return builder.build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser (final User user) {
        user.setUserId(getNewUserId());
        user.setCredit(0);

        userCache.add(user);
        return Response.ok(user).build();
    }

    private String getNewUserId () {
        String id = UUID.randomUUID().toString();
        while (idIsInUse(id)) {
            id = UUID.randomUUID().toString();
        }

        return id;
    }

    private boolean idIsInUse (String id) {
        for (User e : userCache) {
            if (e.getUserId().equals(id))
                return true;
        }

        return false;
    }
}
