package platform.config.resource;

import org.jvnet.hk2.annotations.Service;
import platform.config.ConfigConstants;
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

  @Inject
  public Config(KeyValueProvider keyValueProvider) {
    this.keyValueProvider = keyValueProvider;
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
    keyValueProvider.setString(propertyId, propertyValue);
    return Response.ok().build();
  }
}
