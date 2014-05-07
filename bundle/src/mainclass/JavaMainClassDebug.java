package mainclass;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TreeMap;

public class JavaMainClassDebug {

    public static void main(final String[] args) {

        long started = System.currentTimeMillis();
        System.out.println("Java program started at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(started)
                + " with the following ... ");

        System.out.println("\n### program arguments ###");
        for (String arg: args) {
            System.out.println(arg);
        }

        System.out.println("\n### system properties ###");
        Properties sysProps = System.getProperties();
        TreeMap<Object,Object> sortedSysProps = new TreeMap<>(sysProps);
        for (Object key: sortedSysProps.keySet()) {
            System.out.println(key + "=" + sortedSysProps.get(key));
        }

        System.out.println(MessageFormat.format("\nexecution took {0} ms ", System.currentTimeMillis() - started));

    }

}
