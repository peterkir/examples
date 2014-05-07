package de.kirschners.example.jetty.connector.one;

import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Deactivate;
import de.kirschners.example.jetty.util.Browser;
import de.kirschners.example.jetty.util.HelloHandler;

@Component(immediate = true)
public class ConnectorOne {

    @Activate
    void activate(final Map<String,Object> configProps) {
        Server server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(1239);
        http.setIdleTimeout(30000);
        server.addConnector(http);
        server.setHandler(new HelloHandler("One Connector"));
        try {
            server.start();
            Browser.open("http://localhost:1239");
            server.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deactivate
    void deactivate() {
    }

}
