This repository hosts the common components that can be assembled to develop an reactive microservices based application.

# Build instruction

##  Compile and run unit tests
$gradlew build

## Assembly
### As standalone  application
$ gradlew installDist
* For webTemplate project,  webTemplate/build/distributions  directory will contain the binaries for the application  packages as zip and tar files. The  executable application will also be installed under webTemplate/build/install/webTemplate directory.

### Using Docker
$ gradlew distDocker

## Execution
### As Stadalone Application
$ sh webTemplate/build/install/webTemplate/bin/webTemplate
* The default port will be 9501
* To start the application on a different  port set the java system property server.port by setting the WEB_TEMPLATE_OPTS environment variable before running the above command. For example, export WEB_TEMPLATE_OPTS=-Dserver.port=9500

### Using Docker:
* $docker run -p <exposed_port>:9501 aggregatortech/webtemplate:1.0
* For example docker run -p 9500:9501 aggregatortech/webtemplate:1.0

### Using Gradle: 
$gradlew run


## Integration Testing
* The url for the webTemplate app uses the pattern http://<host>:<port>/webTemplate/v1/about. eg http://localhost:9501/webTemplate/v1/about
* Integration test pertaining to sub project reside under <subProjectRoot>/src/integrationTest directory

To run integration tests for the all projects under platform 
$gradlew integrationTest -DbaseUrl=<baseUrl of the microservice>

For example ./gradlew integrationTest -DbaseUrl=http://webhost1:9501/webTemplate

To run integration tests for specific sub project under platform, say webTemplate
$gradlew :webTemplate:integrationTest -DbaseUrl=<baseUrl of the microservice>

## Publishing
### Using Docker
* $ docker login
* $ docker push aggregatortech/webtemplate
* Check the image in available in docker hub by visiting the repository url: https://hub.docker.com/r/aggregatortech/webtemplate/tags/
* For more details, refer to https://docs.docker.com/docker-cloud/builds/push-images/

## Deployment
### Using Docker
* docker pull aggregatortech/webtemplate:1.0
* To pull and run docker image in a single command, use the docker run command documented in the Execution section.
