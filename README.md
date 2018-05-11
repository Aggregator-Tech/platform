This repository hosts the common components that can be assembled to develop an reactive microservices based application.

# Build instructions

## Compile and run unit tests
$gradlew build

## Generate the executable application
$ gradlew installDist
For webTemplate project, the executable application will be assembled under webTemplate/build/install/webTemplate directory.

## Run the application
### Option 1:
$ sh webTemplate/build/install/webTemplate/bin/webTemplate
* The default port will be 9501
* To start the application on a different port set the java system property server.port by setting the WEB_TEMPLATE_OPTS environment variable before running the above command. For example, export WEB_TEMPLATE_OPTS=-Dserver.port=9500

### Option 2: 
$gradlew run

## Generate docker image
$ gradlew distDocker
