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

## Publishing
### Using Docker
* $ docker login
* $ docker push aggregatortech/webtemplate
* Check the image in available in docker hub by visiting the repository url: https://hub.docker.com/r/aggregatortech/webtemplate/tags/
* For more details, refer to https://docs.docker.com/docker-cloud/builds/push-images/

## Deployment
To start all services
* gradlew startDocker

To stop all services
* gradlew stopDocker

Individual services can also be controlled by using the respective startDocker and stopDocker tasks at module level, eg:

### WebTemplate
To start
* gradlew :webTemplate:startDocker

To stop
* gradlew :webTemplate:stopDocker

### Kafka
To start the Kafka server
* gradlew :kafka:startDocker

To stop the Kafka server
* gradlew :kafka:stopDocker

## Integration Testing
* Integration test pertaining to sub project reside under <subProjectRoot>/src/integrationTest directory

To run integration tests for the all projects under platform ( Note: this will not work till we have LBR enabled, till then we will have to run project level integ Tests with respective urls)
$gradlew integrationTest -DbaseUrl=<baseUrl of the microservice>

For example ./gradlew integrationTest -DbaseUrl=<LBR Url>

To run integration tests for specific sub project under platform, say config
$gradlew :config:integrationTest -DbaseUrl=http://localhost:9501
$gradlew :webTemplate:integrationTest -DbaseUrl=http://localhost:9499

