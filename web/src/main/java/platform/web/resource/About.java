package platform.web.resource;

import org.jvnet.hk2.annotations.Service;
import platform.common.Constants;
import platform.common.io.system.SystemHelper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("{serviceName}/{version}/about")
@Service
public class About {

  private SystemHelper systemHelper;

  @Inject
  public About(SystemHelper systemHelper) {
    this.systemHelper = systemHelper;
  }

  @GET
  public String get(@PathParam("serviceName") String serviceName) {
    String about;
    about = systemHelper.readConfigurationProperty(Constants.COMMON_ABOUT_DESCRIPTION).orElse(serviceName);
    return about;
  }
}
