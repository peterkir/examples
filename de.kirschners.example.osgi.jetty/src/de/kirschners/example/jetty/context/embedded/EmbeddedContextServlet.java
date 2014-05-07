package de.kirschners.example.jetty.context.embedded;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import de.kirschners.example.jetty.util.Browser;
import de.kirschners.example.jetty.util.DumpServlet;

@Component(immediate = true)
public class EmbeddedContextServlet {

    @Activate
    public void activate() throws Exception {
        Server server = new Server(1238);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/");
        context.addServlet(new ServletHolder(new DumpServlet()), "/dump/*");
        server.start();
        Browser.open("http://localhost:1238");
        server.join();
    }
}