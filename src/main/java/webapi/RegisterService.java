package webapi;

import bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("register")
public class RegisterService {
    @Context
    protected HttpServletRequest httpServletRequest;

    final static List<User> userCache = new ArrayList<>();

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser () {
        String name = httpServletRequest.getHeader("username");
        String password = httpServletRequest.getHeader("password");
        User user = new User();
        user.setUserId(getNewUserId());
        user.setUserName(name == null ? "" : name);
        user.setUserPassword(password == null ? "" : password);
        user.setCredit(0);
        user.setSuperuser(true);

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

    static User getUserById (String id) {
        for (User u : userCache)
            if (u.getUserId().equals(id))
                return u;

        return null;
    }
}
