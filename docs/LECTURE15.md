# Java Enterprise

## Overview
A **microservice** is a small, independent part of a bigger system.

**Monolithic:** Everything in one application

**Microservices:** Independent services communicating over HTTP or messaging.

**Message Broker:** helps systems and microservices to communicate. (Meddelande förmedlare)

## Microservices & Protocols
### Q: When to use?
Problemet med en monolitisk app:

Vi har en skål med äpplen, bär, och vid Halloween lägger vi i godis, men vi vill även ha gröt och 
kyckling. Skålen blir överflödig - precis som en växande monolitisk app. Saker smaker konstigt, och blir dåliga. 
Monolitiska appar förlitar sig på att precis allt i appen fungerar, om en liten metod går fel - så krashar hela appen.

App A: User Creation
App B: Frontend
App C: Email service

Vi har separerat äpplen och kyckling till egna skålar, och de påverkar inte varandra längre. Äpplena får inte salmonella och 
godiset blir inte blött av äpplena.

Skalbarhet och underhåll förenklas, men notan blir dyrare med fler skålar.

### Q: Why Microservice?
* Speed - Build MVP quickly
  - Eftersom vi har en så liten del som möjligt, så är det enkelt att komma igång med den. 
  - Prototyping går snabbare i Microservices.

* Change - Rapid Iterations
  - Enklare att iterera vid mindre appar.

* Maintain - Simple Components
* Scale - Product Team
* Empower - Team = Component

### Challenges
* Design
* Säkerheten
* Testning
* Operational Complexities
* Communication
  - Svårt att kommunicera med ett team som ligger i ett annat land, vi behöver kommunicera med dem om deras microtjänst.

### CONS
* Ökad komplexitet
  - Det blir mer komplext: Kommunikation, Säkerhet, Debugging 
* Distributed System Overheads
* Tillhandahålla och skicka vidare Data
  - Hur tolkas vår data av andra tjänster?
  - Kan någon ha manipulerat data efter att vi har skickat den?
* Svårt att följa flödet
* Kostsamt
* Svårare att testa

### PROS
* Team Independence
  - Parallel development
* Isolated Deployment
  - Less risk when releasing 
* Scalability per Service
  - Only scale e.g. "OrderService" 
* Better Fault Isolation
  - Email crash 1= total outage
* Tech Flexibility
  - Use Java here, Golang there

### Microservice Architecture


### Simple Architecture Overview

### Protocols (BONUS)
A *set of rules* and *guidelines* for *communicating data*.

Datorer använder sig av protokoll, som är likt regler för hur information skickas och tas emot. 

### OSI Model (The Fundamentals)

* Konceptuell modell för datorkommunikation i 7 lager.
* Här beskrivs och kategoriseras nätverkstekniker och protokoll.
1. Physical
   - Wire, Fiber, Wireless 
2. Data Link
   - Routes, Switches, Ethernets
3. Network
    - Internet Protocol (IP)
4. **Transport**
    - TCP, UDP
5. Session
   - Connection Maintenance
6. Presentation
    - Translates data into suitable formats
7. **Application** 
    - End user application protocols (HTTP, DNS, SMTP)

### TCP & UDP

#### TCP
**Transmission Control Protocol**

Skapar en stabil och kontrollerad koppling där vi försäkrar om att uppkopplingen och data ej råkar försvinna.

Data skickas men inte alltid inom rätt ordning... TCP hanterar detta.

Vi förlorar heller ingen data på vägen, vilket annars hade kunnat göra t.ex. en fil 'korrupt'.

**Slower but more reliable transfers**

*Användningsområden:*
- File Transfer Protocol
- Web Browsing
- Email

#### UDP
**User Datagram Protocol**

Är lite mer sporadisk och kaotisk, men är väldigt effektiv på det sätt att den är snabb.

Väldigt likt en ström.

Vilket gör den till ett superbra val där det inte gör något om vi förlorar data, t.ex. inom streaming eller 'voice calls'.

**Faster but not guaranteed transfer ("best effort")**

*Användningsområden:*
- Live Streaming
- Online Games
- VoIP

## OAuth2
**Q: What is it?**

Imagine if you wanted to authenticate a user from another app:

App A: Generate JWT   ========>   App B: Reads JWT & Authenticates

Klienten äger inte JWT, det gör backend:en. I frontend tar vi helt enkelt JWT och skickar tillbaka till 
backend:en för autentisering.

## RabbitMQ & Docker

### Message Broker - What is it?
- Någon som bär på ett meddelande


     App 1 | App 2 | App 3 | App 4

     \        |         |        /

       \      |         |      /

         \    |         |    /

           \  |         |  /

             \|         |/
            Message Broker

- A software that enables applications, systems and services to communicate and exchange information.

**What is it for?**
* Sending messages between services
* Decoupling communication
    - Ett sätt att säga att kommunikationen behöver inte alltid ske via ex. HTTP.
* Handling async workloads
    - Works with synchronous as well
* Buffering traffic
* Retrying or delaying processes
    - RabbitMQ kan köa (queue, hence the name)

### RabbitMQ - What is it?
* Messaging and Streaming broker that enables efficient, reliable and versatile communication for applications.
* Perfect for distributed microservices, real time data (and IoT).

### Docker & RabbitMQ - How? Where? Why?
