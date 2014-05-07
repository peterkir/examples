package de.kirschners.example.jetty.fileserver;

import java.util.Map;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import de.kirschners.example.jetty.util.Browser;

@Component(immediate = true)
public class FileServer {

    @Activate
    void activate(final Map<String,Object> configProps) {
        // Create a basic Jetty server object that will listen on port 8080. Note that if you set this to port 0
        // then a randomly available port will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(1234);

        // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
        // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
        ResourceHandler resource_handler = new ResourceHandler();
        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[] {
            "index.html"
        });
        /**
         * URI is created from the argument inside org.eclipse.jetty.util.resource.Resource.newResource() valid
         * arguments are e.g. <c:/> <file:///c:/> <jar:file:///hugo.jar!/>
         */
        resource_handler.setResourceBase("jar:file:///C:/pekirsc/download/jetty-distribution-9.1.3.v20140225/lib/jetty-jaas-9.1.3.v20140225.jar!/");

        // Add the ResourceHandler to the server.
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {
                resource_handler, new DefaultHandler()
        });
        server.setHandler(handlers);

        // Start things up! By using the server.join() the server thread will join with the current thread.
        // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
        try {
            server.start();
            Browser.open("http://localhost:1234");
            server.join();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
