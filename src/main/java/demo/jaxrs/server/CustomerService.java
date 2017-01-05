package demo.jaxrs.server;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("/customerservice/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerService {
    long currentId = 2;
    Map<Long, UserData> users = new HashMap<Long, UserData>();

    public CustomerService() {
        init();
    }

    @GET
    @Path("/customers/tot/")
    public List<UserData> getTotalCustomer() {
        List<UserData> res = new ArrayList<UserData>();
        for (long i = 1; i <= currentId; i++)
            res.add(users.get(i));
        return res;
    }

    @GET
    @Path("/customers/{id}/")
    public UserData getCustomer(@PathParam("id") String id) {
        System.out.println("----invoking getCustomer, Customer id is: " + id);
        long idNumber = Long.parseLong(id);
        UserData u = users.get(idNumber);
        return u;
    }

    @POST
    @Path("/customers/update")
    public Response updateCustomer(UserData user) {
        System.out.println("----invoking updateCustomer, Customer id is: " + user.getId());
        UserData c = users.get(user.getId());
        Response r;
        if (c != null) {
            users.put(user.getId(), user);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/customers/add")
    public Response addCustomer(UserData userData) {
        userData.setId(++currentId);

        System.out.println("----invoking addCustomer, Customer id is: " + userData.getId());

        users.put(userData.getId(), userData);

        return Response.ok(userData).build();
    }

    final void init() {
        UserData u1 = new UserData(1, 20, 30, "10:00", "User1");
        UserData u2 = new UserData(2, 20, 30, "11:00", "User2");
        users.put(u1.getId(), u1);
        users.put(u2.getId(), u2);
    }

}
