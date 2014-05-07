package fundamentals.abstracts;

public class AbstractPrimitives {

    public void methodWithoutParam() {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final byte b) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final short s) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final int i) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final long l) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final float f) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final double d) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final boolean b) {
        System.out.println(this.getClass());
    }

    public void methodWithPrimitiveParam(final char c) {
        System.out.println(this.getClass());
    }

    public void methodWithAllPrimitivesParam(final byte b,
                                             final short s,
                                             final int i,
                                             final long l,
                                             final float f,
                                             final double d,
                                             final boolean bool,
                                             final char c) {
        System.out.println(this.getClass());
    }

}
