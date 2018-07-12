package platform.config.resource;

import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.annotations.Service;
import platform.common.io.log.Log;
import platform.config.ConfigConstants;
import platform.config.processor.ConfigProcessor;
import platform.data.provider.KeyValueProvider;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Service
@Path(ConfigConstants.RESOURCE_PROPERTY_PATH)
public class Config {
  private final KeyValueProvider keyValueProvider;
  private final ServiceLocator serviceLocator;
  private final Log log;

  @Inject
  public Config(ServiceLocator serviceLocator, KeyValueProvider keyValueProvider, Log log) {
    this.serviceLocator = serviceLocator;
    this.keyValueProvider = keyValueProvider;
    this.log = log;
  }

  @Path(ConfigConstants.RESOURCE_PROPERTY_PARAM_PATH)
  @GET
  public String getConfig(@PathParam(ConfigConstants.RESOURCE_PROPERTY_PARAM_NAME)
                                String propertyId) {
    return keyValueProvider.getString(propertyId);
  }

  @Path(ConfigConstants.RESOURCE_PROPERTY_PARAM_PATH)
  @PUT
  public Response setConfig(@PathParam(ConfigConstants.RESOURCE_PROPERTY_PARAM_NAME)
                                  String propertyId, String propertyValue) {
    System.out.println("Inside setConfig");
    log.info("Inside setConfig");
    keyValueProvider.setString(propertyId, propertyValue);
    ConfigProcessor configProcessor = serviceLocator.getService(ConfigProcessor.class, propertyId);
    log.info("configProcessor: " + configProcessor);
    if (configProcessor != null) {
      configProcessor.process(propertyId, propertyValue);
    }
    return Response.ok().build();
  }
}
