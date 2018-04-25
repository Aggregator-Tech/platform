package webTemplate.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("{version}/about")
public class About {
    @GET
    public String get() {
        return "webTemplate";
    }
}
