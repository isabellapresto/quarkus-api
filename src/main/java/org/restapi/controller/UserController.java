package org.restapi.controller;
import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;
import org.restapi.service.UserService;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response; 

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @POST
    public User createUser(@Valid UserDto userDto) {
        return userService.saveUser(userDto.toUser());
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(id, userDto.toUser());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static class UserDto {

        @NotBlank
        private String firstName;

        @NotBlank
        private String country;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public User toUser() {
            User user = new User();
            user.setFirstName(firstName);
            user.setCountry(country);
            return user;
        }
    }
}

