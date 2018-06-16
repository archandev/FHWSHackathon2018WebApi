package webapi;

import bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;

@Path("")
public class StartService {
    @Context
    private UriInfo uriInfo;

    @Context
    protected HttpServletRequest httpServletRequest;

    @GET
    public Response getDispatcher() {
        final Response.ResponseBuilder builder = Response.ok();

        Hyperlinks.addLink(this.uriInfo, builder, "/demo/api/messages", "getAllPersons", MediaType.APPLICATION_JSON);

        return builder.build();
    }
}
