package de.kirschners.example.jetty.context.many;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import de.kirschners.example.jetty.util.Browser;
import de.kirschners.example.jetty.util.HelloHandler;

@Component(immediate = true)
public class ContextMany {

    @Activate
    public void activate() throws Exception {
        Server server = new Server(1237);
        ContextHandler context = new ContextHandler("/");
        context.setContextPath("/");
        context.setHandler(new HelloHandler("Root Hello"));
        ContextHandler contextFR = new ContextHandler("/fr");
        contextFR.setHandler(new HelloHandler("Bonjour"));
        ContextHandler contextIT = new ContextHandler("/it");
        contextIT.setHandler(new HelloHandler("Bongiorno"));
        ContextHandler contextV = new ContextHandler("/");
        contextV.setVirtualHosts(new String[] {
            "127.0.0.2"
        });
        contextV.setHandler(new HelloHandler("Virtual Hello"));
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] {
                context, contextFR, contextIT, contextV
        });
        server.setHandler(contexts);
        server.start();
        Browser.open("http://localhost:1237");
        Browser.open("http://127.0.0.1:1237");
        Browser.open("http://127.0.0.2:1237");
        Browser.open("http://localhost:1237/fr");
        Browser.open("http://localhost:1237/it");
        server.join();
    }

}