# Spring Security
Taking a closer look at User or how we allow certain 'Logins'.
Let's deep dive into:
- Problems with logins
- SecurityFilterChain
- Creation of Users
- Permissions

### SecurityFilterChain

**.authorizeHttpRequest()**

This is where we prepare a restriction.

**.requestMatchers()**

Takes in *endpoints* in the query of what to override.

**.permitAll()**

Opens up and endpoint to give users access.

**.anyRequest()**

For every request we should...

**.authenticated()**

... Specify that URL's are only available to authenticated users. 


### Enumeration AKA ENUM
An Enum is a list of 'final' variables. We can assign value to the variable.
If that is the case, don't forget the constructor!

```
public enum Roles {
        WARRIOR("The warrior is Strong),
        MAGE("The Mage is swift with its' magic"),
        ROUGE("The Rouge is extremely cunning")
}
```

**SimpleGrantedAuthority**

If we want to add multiple values to an Enum, we would need to use a List instead. 
To handle this type of problem, we want to use the SimpleGrantedAuthority class. This Interface 
is responsible for both ROLE and PERMISSIONS (*= Authorities*). 

The Interface represents a single authority granted to a User. 
A user can have *multiple* authorities by return a list of SimpleGrantedAuthority objects.

```ADMIN.getSimpleGrantedAuthority()``` ----> ```[ROLE_NAME, READ, WRITE, DELETE]```

The "ROLE_" prefix is a convention for roles, while the other string represent permissions.

