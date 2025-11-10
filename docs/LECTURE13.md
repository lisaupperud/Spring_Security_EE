# Spring Security

## Thread, ThreadLocal & WorkerThreads

Default Thread Pool Size: 200

** Tomcat always works with Threads.

Threads makes it possible for Tomcat to talk to Spring.

### Theory:

*If we had 201 simultaneous requests against our app?*

Q: What would happen?

A: User must wait (average 150ms)

*What we want, is pausing threads that aren't busy.*

*Imagine a restaurant where the **waitress** (WorkerThread) takes your order on a post-it and puts it
in her backpack. That post-it is a **ThreadLocal**, containing information about your **order** (request).*

**Thread**

- TBD

**ThreadLocal**

- Varje WorkerThread har en map - en ThreadLocal
- En Map<> - En lista med information
- At the end of its lifespan - Clear Data
- Contains SpringSecurityContext (Details about ex. User Credentials, Authorities)

**WorkerThread**

- Executes piece of code, usually a quite small part of code
- A WorkerThread contains a ThreadLocal

### Authentication Object & AuthenticationManager

"Hey Spring, here is a login attempt. Figure out if it's valid and return a verified identity."

**Authentication Object**

- Interface
- Represents the token for an authentication request or for an authenticated principal (ex. username)

**AuthenticationManager**

- Interface
- Checks if the Authentication object is valid
- Sets authorities

**Auth Hierarchy**

1. Authentication (interface)
2. AbstractAuthenticationToken (abstract class)
3. UsernamePasswordAuthenticationToken (concrete class)   The *result* of authentication

| Name                  | Type                  | Description                              | 
|-----------------------|-----------------------|------------------------------------------|
| Principal             | Usually a UserDetails | The *user identity*                      |
| Authentication        | Interface             | The *result* of authentication           |
| SecurityContext       | Container             | Holds the current authentication         | 
| SecurityContextHolder | ThreadLocal Manager   | Gives you access to that SecurityContext |

## JWT - JSON Web Token

- TBD

### JWT Structure

1. Header
    * Encoded (base64)
    * Contains type of algorithm used
    * Contains type of Token
2. Payload
    * Encoded (base64)
    * Contains information about Entity
    * Contains Claims
3. Signature
    * Hashed (hmacsha256)
    * Contains Header + Payload

### JWT - Payloads

- Contains the **Claims**
- **Claims** = statements about an entity & additional data
- **Claims** = Represented as key-value pairs
- Claims is the *content* of a Payload

### JWT - Signature

- Includes both you Header, Payload and Secret, that is hashed with HMAC(SHA256).
- A Secret / Stamp
- NOT Encryption
- Signing JWTs **do not** make their data unreadable
- Signatures *only verify* that the content of the JWT was not changed
- The goal is **not** to hide data
- Hashed output of the Header, Payload and Secret Key

# TBD

## Spring Sessions

Varje request som gör en request mot vår Spring app:

- Requestet görs *egentligen* mot Tomcat - vår server (innehåller vår port)
- Tomcat == synkront (blocking calls)
- Tomcat skickar vidare ett request (Http Servlet Request - ett objekt i Spring)
- Http Servlet Request innehåller referensen till HttpSession (Spring Objekt)
- Via Http Servlet Request kan vi hämta ut requestet och jobba med responses
- HttpSession bor på samma thread som Spring APP (JVM)
- Request -> Tomcat -> Request mot Spring
- Detta ger oss kontroll i Spring
- ** Vi kan komma åt allting i Spring via argument ex. via Authenticated, HttpSession
