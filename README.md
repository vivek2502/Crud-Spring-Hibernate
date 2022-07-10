# Crud-Spring-Hibernate

Spring-Hibernate based web application having functionality of CRUD operation.

![image](https://user-images.githubusercontent.com/42957988/178133845-0c30eecc-c5f6-49be-8e87-b15b599439d9.png)

# Tech Stack

HTML, CSS, JSP

Java 1.8, Spring 5, Spring MVC, Hibernate

MySQL workbench

Tomcat Server

# Define db connection in : spring-mvc-crud-demo-servlet.xml

<!-- Step 1: Define Database DataSource / connection pool -->

	<bean id="myDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
          
        <property name="driverClass" value="com.mysql.cj.jdbc.Driver" />
        
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&amp;serverTimezone=UTC" />
        
        <property name="user" value="springstudent" />
        
        <property name="password" value="springstudent" /> 
        


