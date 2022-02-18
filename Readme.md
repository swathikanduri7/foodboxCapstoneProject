## Building a Full Stack Food Box application  with Spring Boot, Spring Security, JWT, Angular 8 and Ant Design


## Steps to Setup the Spring Boot Back end app (foodboxBackendSpringboot)

1. **Clone the application**

	```bash
	git clone https://github.com/swathikanduri7/foodboxCapstoneProject.git
	cd foodboxBackendSpringboot
	

2. **Create MySQL database**

	```bash
	create database polling_app
	```

3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.properties` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

	You can run the spring boot app by typing the following command -

	```bash
	mvn spring-boot:run
	```

	The server will start on port 3000.

	You can also package the application in the form of a `jar` file and then run it like so -

	```bash
	mvn package
	java -jar target/foodboxBackendSpringboot-0.0.1-SNAPSHOT.jar
	```
5. **Default Roles**
	
	The spring boot app uses role based authorization powered by spring security. To add the default roles in the database, I have added the following sql queries in `src/main/resources/data.sql` file. Spring boot will automatically execute this script on startup -

	```sql
	INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
	INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');
	```

	Any new user who signs up to the app is assigned the `ROLE_USER` by default.

## Steps to Setup the React Front end app (OnlineFoodOrderingSystemAngular)

First go to the `OnlineFoodOrderingSystemAngular` folder -

```bash
cd OnlineFoodOrderingSystemAngular
```

Then type the following command to install the dependencies and start the application -

```bash
npm install && ng serve
```

The front-end server will start on port `4200`.


=======================================================================================
### using docker-compose
#to build and push images in local repositories
docker-compose up

#to push images in local repositories to remote repository
docker tag backendfoodbox:capstone swathikothapeta/capstone_project_1234:backendfoodbox
docker push swathikothapeta/capstone_project_1234:backendfoodbox

#to push images in local repositories to remote repository
docker tag angularfoodbox:capstone swathikothapeta/capstone_project_1234:angularfoodbox
docker push swathikothapeta/capstone_project_1234:angularfoodbox

#to remove images from local repository
docker-compose down
=============================================================================

using docker 

cd foodboxBackendSpringboot
and run below commands
docker build -t="capstone-java" .
docker run -p 3000:3000 -it --rm capstone-java
docker stop `docker container ls | grep "capstone-java:*" | awk '{ print $1 }'`

cd ../OnlineFoodOrderingSystemAngular
and run below commands

docker build -t="capstone-angular" .
docker run -p 4200:4200 -it --rm capstone-angular

docker build -t capstone-angular-image .
docker run --name capstone-angular-container -d -p 8080:80 capstone-angular

docker stop `docker container ls | grep "capstone-angular-container:*" | awk '{ print $1 }'`
