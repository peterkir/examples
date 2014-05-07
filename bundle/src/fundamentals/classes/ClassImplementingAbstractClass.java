package fundamentals.classes;

import util.Print;
import fundamentals.abstracts.AbstractPrimitives;

public class ClassImplementingAbstractClass extends AbstractPrimitives {

    @Override
    public void methodWithoutParam() {
        super.methodWithoutParam();
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final byte b) {
        super.methodWithPrimitiveParam(b);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final short s) {
        super.methodWithPrimitiveParam(s);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final int i) {
        super.methodWithPrimitiveParam(i);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final long l) {
        System.out.println(this.getClass());
        super.methodWithPrimitiveParam(l);
    }

    @Override
    public void methodWithPrimitiveParam(final float f) {
        System.out.println(this.getClass());
        super.methodWithPrimitiveParam(f);
    }

    @Override
    public void methodWithPrimitiveParam(final double d) {
        super.methodWithPrimitiveParam(d);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final boolean b) {
        super.methodWithPrimitiveParam(b);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithPrimitiveParam(final char c) {
        super.methodWithPrimitiveParam(c);
        System.out.println(this.getClass());
    }

    @Override
    public void methodWithAllPrimitivesParam(final byte b,
                                             final short s,
                                             final int i,
                                             final long l,
                                             final float f,
                                             final double d,
                                             final boolean bool,
                                             final char c) {
        super.methodWithAllPrimitivesParam(b, s, i, l, f, d, bool, c);
        System.out.println(this.getClass() + " " + Print.params());
    }
}
