This repository hosts the common components that can be assembled to develop an reactive microservices based application.

# Build instructions

## Compile and run unit tests
$gradlew build

## Assembly
### As stadalone application
$ gradlew installDist
For webTemplate project, the executable application will be assembled under webTemplate/build/install/webTemplate directory.

### Using Docker
$ gradlew distDocker

## Execution
### As stadalone application
$ sh webTemplate/build/install/webTemplate/bin/webTemplate
* The default port will be 9501
* To start the application on a different port set the java system property server.port by setting the WEB_TEMPLATE_OPTS environment variable before running the above command. For example, export WEB_TEMPLATE_OPTS=-Dserver.port=9500

### Using docker:
* $docker run -p <exposed_port>:9501 aggregatortech/webtemplate:1.0
* For example docker run -p 9500:9501 aggregatortech/webtemplate:1.0

### Using gradle: 
$gradlew run


## Testing
* The url for the webTemplate app uses the pattern http://<host>:<port>/webTemplate/v1/about. eg http://localhost:9501/webTemplate/v1/about
* For automated testing, Refer to https://github.com/Aggregator-Tech/platformIntegTest
