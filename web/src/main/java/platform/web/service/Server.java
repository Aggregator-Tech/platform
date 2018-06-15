package platform.web.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.annotations.Service;
import platform.common.Constants;
import platform.common.io.system.SystemHelper;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.net.URI;

public class Server {
  // Base URI the Grizzly HTTP server will listen on
  public static final String BASE_URL = "http://" + NetworkListener.DEFAULT_NETWORK_HOST + ":%s/";

  private String getBaseUrl() {
    String port = System.getenv("PORT");
    if (port == null || port.isEmpty()) {
      port = System.getProperty("server.port", "9501");
    }
    return String.format(BASE_URL, port);
  }

  /**
   * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
   *
   * @return Grizzly HTTP server.
   */
  public HttpServer startServer() {
    // create a resource config that scans for JAX-RS resources and providers
    final ResourceConfig rc = new ResourceConfig().packages("platform.web");
    rc.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

    // create and start a new instance of grizzly http server
    // exposing the Jersey application at BASE_URL
    System.out.println("baseUrl: " + getBaseUrl());

    ServiceLocator serviceLocator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
    populateServices(serviceLocator);
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUrl()), rc, serviceLocator);
  }

  private void populateServices(ServiceLocator serviceLocator) {
    try {
      ImmutableSet<ClassPath.ClassInfo> allClasses =
          ClassPath.from(ClassLoader.getSystemClassLoader())
              .getTopLevelClassesRecursive(Constants.PLATFORM_PACKAGE);
      Class<?>[] serviceClasses = allClasses.stream()
          .map(ClassPath.ClassInfo::load)
          .filter(classObject -> classObject.isAnnotationPresent(Service.class))
          .toArray(Class[]::new);
      ServiceLocatorUtilities.addClasses(serviceLocator, serviceClasses);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Main method.
   *
   */
  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.startServer();
  }
}
