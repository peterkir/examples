package mainclass;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import fundamentals.classes.ClassImplementingAbstractClass;

public class JavaMainClass {

    public static void main(final String[] args) {

        long started = System.currentTimeMillis();
        System.out.println("Java program started at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(started)
                + " with the following ... \n");

        new ClassImplementingAbstractClass().methodWithAllPrimitivesParam("a".getBytes()[0],
                                                                          (short) 0,
                                                                          1,
                                                                          2l,
                                                                          3.0f,
                                                                          4.0d,
                                                                          true,
                                                                          'c');

        System.out.println(MessageFormat.format("\nexecution took {0} ms ", System.currentTimeMillis() - started));

    }
}
