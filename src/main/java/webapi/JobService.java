package webapi;

import bean.Job;
import bean.User;
import database.dataaccessobjekts.JobDAO;

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

@Path("jobs")
public class JobService {
    private static List<Job> jobCache = new ArrayList<>();

    @Context
    protected UriInfo uriInfo;

    @Context
    protected HttpServletRequest httpServletRequest;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOpenTasks () {
        return Response.ok(jobCache.stream().filter(e -> e.getWorker() == null).collect(Collectors.toList())).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJob (final Job job) {
        if (!isSuperUser(httpServletRequest.getHeader("X-userid")))
            return Response.status(403).build();

        job.setJobId(getNewJobId());
        job.setWorker(null);

        JobDAO.insertJob(job);

        final URI locationURI = uriInfo.getAbsolutePathBuilder().path(job.getJobId()).build(new Object[0]);
        return Response.created(locationURI).build();
    }

    @GET
    @Path("{jobId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSingleJob (@PathParam("jobId") final String jobId) {
        return Response.ok(getJobById(jobId)).build();
    }

    @DELETE
    @Path("{jobId}")
    public Response deleteJob (@PathParam("jobId") final String jobId) {
        if (!isSuperUser(httpServletRequest.getHeader("X-userid")))
            return Response.status(403).build();

        removeJob(jobId);
        return Response.noContent().build();
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

    private boolean isSuperUser (String userId) {
        User user = RegisterService.getUserById(userId);
        return user != null && user.getSuperuser();

    }

    private void removeJob (String jobId) {
        for (Job e : jobCache)
            if (e.getJobId().equals(jobId)) {
                jobCache.remove(e);
                return;
            }
    }

    private Job getJobById (String jobId) {
        return jobCache.stream().filter(e -> e.getJobId().equals(jobId)).findAny().orElse(null);
    }
}
