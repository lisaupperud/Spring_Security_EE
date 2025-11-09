# Spring Security

### Encoding

**.encode()**

A method inside Spring Security that allows us to work with encryption 
and hashing. It allows for/us to:
- Different types of encryption/hashing algorithms for different purposes.
- Safe storage of password
- Hide sensitive data

NOTE: ```.encode()``` does not mean the typical encoding. 
The method hashes and salts the password for safe storage.

#### Safety
There is no way to ensure a 100% safety for an application. The goals are to make it as
difficult as possible for a hacker to find our data.

