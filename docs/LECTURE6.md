# Spring Security

### What happens?

When the Spring Security Framework is added to an application, it automatically registers a **filter chain** 
that catches all incoming requests. This chain consists of different **filters**, each one of them handling a special use case.

**For example**
- Checks if the requested address is publicly available, based on configuration.
- With session-based authentication, it checks if the user is already authenticated in the current session.
- Checks if the user is authorized to perform the requested action, and so on.

An important detail is that the Spring Security filters are registered with the lowest order and are the first
filters to be called. In some cases, if you want to prioritize you custom filter, you have to add padding to their order:

```Spring.security.filter.order=10```

When we add this configuration in ```application.properties```, we add space for 10 custom filters.

## Security Filter

### Servlet

**Java Servlet Filter**
"Java Servlet Filter is *used to intercept the client request and do some pre-processing*"

**Pre-processing**
"*A preliminary processing of data in order to prepare it for the primary processing or for further analysis*"

- Data for Processing may refer to manipulation or deletion of data before it is used.

### Security Filter Chain
The web infrastructure of Spring Security is based entirely on standard servlet filters, It **does not**
use servlets or servlet-based frameworks internally. It deals with HttpServletRequests and HtpServletResponses and does not care whether the 
requests come from a browser, web service client, an HttpInvoker orr an AJAX application.

**DelegatingFilterProxy**

A servlet filter embedded in the Spring context. Its job is to delegate the incoming request to multiple filters, provided 
by the Spring web framework - hence the name *DelegatingFilterProxy*.

**SecurityFilter(s)**

A class containing a list of actual filters that need to be invoked *before* the controller can handle the request.

Contains (selection):
- SecurityContextPersistenceFilter
- CsrfFiler
- LogoutFilter
- UsernamePasswordAuthenticationFilter

The **FilterChainProxy** queries this class to invoke each filter in a loop. 
NOTE - There can be multiple SecurityFilterChain, each one having its own stack of filters.

### Conclusion

With the help of filters, Spring Security checks it the user for example has authorization to enter a certain address. 

## MVC Configuration
With the help of the ```WebMvcConfigurer``` interface, we can apply new structuring of HTML files that we provide
within the app. Ìt defines callback methods to customize the Java-based configuration for Spring MVC, enabled via ```@EnableWebMvc```.
Configuration classes with this annotation may implement the interface to be called back, and given a chance to customize the default configuration.

## Debugging User
When the application is first created and is missing a custom **SecurityFilterChain**, a User has access to everything. 

Example:
- localhost:8080/ -- Accessible
- localhost:808/adminPage - Accessible

#### Security Configuration
The configuration class is where we will configure our user, and also information and data that
will be exceeded for the application.  

**UserDetailsService()**
- An Interface with *one* method: ```loadUserByUsername()```
- Returns a ```UserDetails````

**UserDetails**
- An Interface with *multiple* methods
- ```getAuthorities()```
- ```password()```
- ```username()```
- ```isAccountNonExpired()```
- ```isAccountNonLocked()```
- ```isCredentialsNonExpired()```
- ```isEnbaled()```

These Interfaces are used to define a User.

**Credentials**
- .username() -- Username for the Users account
- .password() -- Password for the Users account