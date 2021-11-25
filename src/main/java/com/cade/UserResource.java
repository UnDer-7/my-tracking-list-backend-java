package com.cade;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    public static class UserDTO {
        public String id;
        public String name;
        public String email;
    }

    @Inject
    UserRepository repository;

    @POST
    public Uni<UserDTO> save(UserDTO user) {
        var entity = new User();
        entity.email = user.email;
        entity.name = user.name;
        return repository.persist(entity)
            .map(u -> {
                var dto = new UserDTO();
                dto.email = u.email;
                dto.name = u.name;
                dto.id = u.id.toHexString();
                return dto;
            });
    }

    @GET
    @Path("/{id}")
    public Uni<UserDTO> get(@PathParam("id") String id) {
        return repository.findById(new ObjectId(id))
            .map(u -> {
                var dto = new UserDTO();
                dto.email = u.email;
                dto.name = u.name;
                dto.id = u.id.toHexString();
                return dto;
            });
    }
}
