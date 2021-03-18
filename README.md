### Name
Hybris Internship Test Project Part 2 - Java EE (Servlets)
### Description
That is the project for simple illustration how servlets work. 
You can add the product to data base, see lists of all product. You can also remove product by name or update price.
Configuration of servlets are located in  `` src/main/webapp/WEB-INF/web.xml.`` 
##### Start
To get started is needed to run Apache Tomcat and then open in your web-browser `` http://localhost:8080/home.html. `` After that please follow menu.
You can create a new product and put it to database, show all products from database, delete products and change price for product.
##### Connection to data base
All configurations to connect to MySQL are to find in `` src/main/java/com/ciklum/hybris/internship/eesection/connection/Connector.java `` 
##### Docker
If you use Docker, you can run docker-compose, which is located in `` src/main/resources/docker-compose.yml``  
#### Project builds with
Maven.