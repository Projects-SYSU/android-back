/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package demo.jaxrs.server;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

@Path("/customerservice/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerService {
    long currentId = 1;
    Map<Long, UserData> users = new HashMap<Long, UserData>();

    public CustomerService() {
        init();
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
        UserData u1 = new UserData(1, 20,30,"10:00");
        users.put(u1.getId(), u1);
    }

}
