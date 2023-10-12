package org.restapi.controller;
import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;
// import org.restapi.controller.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    private static final SortedSet<User> dummyUsers = new TreeSet<>();

    static {
        dummyUsers.addAll(Set.of(
                createDummyUser(1, "Leonardo", "DiCaprio", 45),
                createDummyUser(2, "Will", "Smith", 51),
                createDummyUser(3, "Denzel", "Washington", 65))
        );
    }


    private static User createDummyUser(int id, String firstName, String lastName, int age) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        return user;
    }

    //Get all users
    @GET
public List<User> getAllUsers() {
    return new ArrayList<>(dummyUsers); 
}

    //get user by id
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return getUserById(id);
    }
    
   //DTO
   public class UserDto {
   
       @NotBlank
       private String firstName;
   
       @NotBlank
       private String lastName;
   
       @Min(value = 1, message = "The value must be more than 0")
       @Max(value = 200, message = "The value must be less than 200")
       private int age;
   
       public String getFirstName() {
           return firstName;
       }
   
       public void setFirstName(String firstName) {
           this.firstName = firstName;
       }
   
       public String getLastName() {
           return lastName;
       }
   
       public void setLastName(String lastName) {
           this.lastName = lastName;
       }
   
       public int getAge() {
           return age;
       }
   
       public void setAge(int age) {
           this.age = age;
       }
   }

//Create user
       @POST
    public User createUser(@Valid UserDto userDto) {
        User user = createDummyUser(dummyUsers.last().getId() + 1, userDto.firstName, userDto.lastName, userDto.age);
        dummyUsers.add(user);
        return user;
    }

//Update user
        @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        User user = getUserById(id);
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);
        user.setAge(userDto.age);
        return user;
    }

//Delete User    
       @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        dummyUsers.remove(getUserById(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }
  
    private User getUserById(int id) throws UserNotFoundException {
        return dummyUsers.stream().filter(user -> user.getId() == id).findFirst()
                .orElseThrow(() -> new UserNotFoundException("The user doesn't exist"));
    }
}


 
