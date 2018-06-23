REST Web service based retail store billing application. JAX-RS implementation : Apache CXF, Spring Framework is used for strutting and transaction. JPA with HIbernate implementation is used for CRUD operations.

To run:

$ mvn clean install

Deploy on tomcat by:

$ ./catalina.sh run
$ mvn tomcat7:deploy

Open browser and hit url to test the endpoints: 

http://localhost:8080/retailcounter/service/api-docs?/url=/retailcounter/service/swagger.json




