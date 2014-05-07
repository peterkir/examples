//
//  ========================================================================
//  Copyright (c) 1995-2014 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package de.kirschners.example.jetty.fileserver.split;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import de.kirschners.example.jetty.util.Browser;

/* ------------------------------------------------------------ */
/**
 * A {@link ContextHandlerCollection} handler may be used to direct a request to a specific Context. The URI path prefix
 * and optional virtual host is used to select the context.
 * 
 */
@Component(immediate = true)
public class SplitFileServer {

    @Activate
    void activate(final Map<String,Object> configProps) {
        try {
            // Create the Server object and a corresponding ServerConnector and then set the port for the connector. In
            // this example the server will listen on port 8090. If you set this to port 0 then when the server has been
            // started you can called connector.getLocalPort() to programmatically get the port the server started on.
            Server server = new Server();
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(8090);
            server.setConnectors(new Connector[] {
                connector
            });

            // Create a Context Handler and ResourceHandler. The ContextHandler is getting set to "/" path but this
            // could
            // be anything you like for building out your url. Note how we are setting the ResourceBase using our jetty
            // maven testing utilities to get the proper resource directory, you needn't use these,
            // you simply need to supply the paths you are looking to serve content from.
            ContextHandler context0 = new ContextHandler();
            context0.setContextPath("/");
            ResourceHandler rh0 = new ResourceHandler();
            /** only the first directory is listed */
            rh0.setDirectoriesListed(true);
            rh0.setBaseResource(Resource.newResource(System.getProperty("java.io.tmpdir")));
            context0.setHandler(rh0);

            // file based resource base - directory listing is ONLY working for sub-directories of the resource base
            // (not for the base itself)
            ContextHandler context1 = create("f:/www");

            // URI based resource base - directory listing is NOT working ( gives an empty list )
            ContextHandler context2 = create("file:///f:/read-only");

            // bundle contained entry as resource base - directory listing is NOT working
            Bundle bundle = FrameworkUtil.getBundle(this.getClass());
            ContextHandler context3 = create(bundle.getEntry("README"));

            // Create a ContextHandlerCollection and set the context handlers to it. This will let jetty process urls
            // against the declared contexts in order to match up content.
            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[] {
                    context0, context1, context2, context3
            });

            server.setHandler(contexts);

            // Start things up! By using the server.join() the server thread will join with the current thread.
            // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
            server.start();
            System.out.println(server.dump());
            Browser.open("http://localhost:8090"); // showing folder java.io.tmpdir
            Browser.open("http://localhost:8090/download.eclipse.org"); // showing one folder from location f:/www
            Browser.open("http://localhost:8090/archive"); // showing one folder from location f:/read-only
            Browser.open("http://localhost:8090/EmbeddedExamples.url"); // showing a bundle entry
            server.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContextHandler create(final String resource) {
        ContextHandler context = new ContextHandler();
        context.setContextPath("/");
        ResourceHandler rh1 = new ResourceHandler();
        /** this is only working for sub-folders and not for the root folder */
        rh1.setDirectoriesListed(true);
        try {
            rh1.setBaseResource(Resource.newResource(resource));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        context.setHandler(rh1);
        return context;
    }

    private ContextHandler create(final URL resource) {
        return create(resource.toString());
    }
}