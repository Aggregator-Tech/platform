package webTemplate.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.net.URI;

public class Server {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://" + NetworkListener.DEFAULT_NETWORK_HOST + ":%s/webTemplate/";

    public String getBaseUri() {
        System.out.println("Port is"+System.getProperty("PORT"));
        return String.format(BASE_URI, System.getProperty("PORT"));
    }

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig rc = new ResourceConfig().packages("webTemplate.resource");
        rc.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(getBaseUri()), rc);
    }


    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        final HttpServer httpServer = server.startServer();
    }}
