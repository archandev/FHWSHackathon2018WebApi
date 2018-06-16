package webapi;

import bean.Job;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("")
public class JobService {
    private List<Job> jobCache = new ArrayList<>();

    @Context
    protected UriInfo uriInfo;

    @Context
    protected HttpServletRequest httpServletRequest;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOpenTasks () {
        return Response.ok(jobCache.stream().filter(e -> e.getWorker() == null).collect(Collectors.toList())).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJob (final Job job) {
        job.setJobId(getNewJobId());
        job.setWorker(null);

        jobCache.add(job);

        final URI locationURI = uriInfo.getAbsolutePathBuilder( ).path( job.getJobId() ).build( new Object[ 0 ] );
        return Response.created(locationURI).build();
    }

    private String getNewJobId () {
        String id = UUID.randomUUID().toString();
        while (idIsInUse(id)) {
            id = UUID.randomUUID().toString();
        }

        return id;
    }

    private boolean idIsInUse (String id) {
        for (Job e : jobCache) {
            if (e.getJobId().equals(id))
                return true;
        }

        return false;
    }
}
