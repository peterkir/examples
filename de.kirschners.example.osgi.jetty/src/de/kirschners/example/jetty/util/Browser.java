package de.kirschners.example.jetty.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Browser {

    public static void open(final String uriString) {
        try {
            open(new URI(uriString));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void open(final URI uri) {
        try {
            Desktop.getDesktop().browse(uri);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
