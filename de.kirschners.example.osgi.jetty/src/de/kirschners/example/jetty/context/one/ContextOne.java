package de.kirschners.example.jetty.context.one;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import de.kirschners.example.jetty.util.Browser;
import de.kirschners.example.jetty.util.HelloHandler;

@Component(immediate = true)
public class ContextOne {

    @Activate
    public void activate() throws Exception {
        Server server = new Server(1236);
        ContextHandler context = new ContextHandler();
        context.setContextPath("/");
        context.setResourceBase(".");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setHandler(new HelloHandler("One Context"));
        server.setHandler(context);
        server.start();
        Browser.open("http://localhost:1236");
        server.join();
    }

}