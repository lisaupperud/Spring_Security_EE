# Spring Security

### User Details & UserDetailsService

**UserDetails**

A template to fetch the Users attributes. 
An old approach would be to let your Entity implement UserDetails, but that leads 
to issues with Separation of Concerns. 

**UserDetailsService**

A template to load a User from the database. 