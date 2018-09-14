package platform.serverlessTemplate;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class AWSLamdaTemplate implements RequestHandler<Request, Response> {

    public Response handleRequest(Request request, Context context) {
        context.getLogger().log("Input: " + request.firstName + " " + request.lastName);
        String greetingString = String.format("Hello %s %s.", request.firstName, request.lastName);
        context.getLogger().log("Output: " + greetingString);
        return new Response(greetingString);
    }
}