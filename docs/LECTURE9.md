# Spring Security

### User Details & UserDetailsService

Spring Security does not know anything about our database or entity model. 
It only knows how to work with the generic interface ```UserDetails```, which in turn 
defines what Spring needs to authenticate a user.

**UserDetails**

Acts as a bridge between *the database entity* and Spring Security's *expected format*.

````
@Entity
public class CustomUser {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private Set<UserRole> userRoles;
    // ...getters/setters...
}
````

Spring does not know what ```UserRole``` is, or how to interpret ```enabled```, or 
where to find the ```username```. That is why we need an adapter, like **UserDetailsService**. 
Implementing the interface on a custom class creates a wrapper-class for the Entity class, and *translates* 
it into what Spring expects.

This means that when Spring asks: "What is this user's password?" we can delegate to ```customUser.getPassword()```.

- At login, Spring calls the ```UserDetailsService``` and returns a ```UserDetails``` obejct.
- Spring then uses that ```UserDetails``` object to check credentials and roles.


**UserDetailsService**

Now that Spring knows what a "user" looks like, it needs to know how to load that user from the database, 
which is where ```UserDetailsService``` enters the chat. The Service tells Spring Security 
how to *load* the user from the database, by the ```loadUserByUsername()``` method.

**Login**

1. Spring Security gets the username from the login form.
2. It calls ```CustomUserDetailsService.loadUserByUsername(username)```.
3. The method loads the ```CustomUser``` entity from the database.
4. It wraps the entity into a ```CustomUserDetails``` object.
5. Spring then uses that ```UserDetails``` object to check for password, roles and credentials.