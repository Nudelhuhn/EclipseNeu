package oop.interfaces;

interface I0
{
    public void m();
}

interface I1
{
    public void m(int i);
}

interface I2
{
    public void m(int i1, int i2);
}

interface I3
{
    public void m(int i1, int i2, int i3);
}

interface I1Alt
{
    public void m(MethodRefs mr);
}

interface I2Alt
{
    public void m(MethodRefs mr, int i);
}

interface I3Alt
{
    public void m(MethodRefs mr, int i1, int i2);
}

public class MethodRefs
{
    public static void statMeth0()
    {
        System.out.println("statMeth0()");
    }

    public static void statMeth1(int i)
    {
        System.out.println("statMeth1(" + i + ")");
    }

    public static void statMeth2(int i1, int i2)
    {
        System.out.println("statMeth2(" + i1 + ", " + i2 + ")");
    }

    public static void statMeth3(int i1, int i2, int i3)
    {
        System.out.println("statMeth3(" + i1 + ", " + i2 + ", " + i3 + ")");
    }

    public void meth0()
    {
        System.out.println("meth0()");
    }

    public void meth1(int i)
    {
        System.out.println("meth1(" + i + ")");
    }

    public void meth2(int i1, int i2)
    {
        System.out.println("meth2(" + i1 + ", " + i2 + ")");
    }

    public void meth3(int i1, int i2, int i3)
    {
        System.out.println("meth3(" + i1 + ", " + i2 + ", " + i3 + ")");
    }

    public static void testMeth(I0 interf)
    {
        interf.m();
    }

    public static void testMeth(I1 interf, int i)
    {
        interf.m(i);
    }

    public static void testMeth(I2 interf, int i1, int i2)
    {
        interf.m(i1, i2);
    }

    public static void testMeth(I3 interf, int i1, int i2, int i3)
    {
        interf.m(i1, i2, i3);
    }

    public static void testMeth(I1Alt interf, MethodRefs mr)
    {
        interf.m(mr);
    }

    public static void testMeth(I2Alt interf, MethodRefs mr, int i)
    {
        interf.m(mr, i);
    }

    public static void testMeth(I3Alt interf, MethodRefs mr, int i1, int i2)
    {
        interf.m(mr, i1, i2);
    }

    public static void main(String[] args)
    {
        testMeth(MethodRefs::statMeth0);
        testMeth(()->MethodRefs.statMeth0());

        testMeth(MethodRefs::statMeth1, 1);
        testMeth(i->MethodRefs.statMeth1(i), 11);

        testMeth(MethodRefs::statMeth2, 1, 2);
        testMeth((i1, i2)->MethodRefs.statMeth2(i1, i2), 11, 22);

        testMeth(MethodRefs::statMeth3, 1, 2, 3);
        testMeth((i1, i2, i3)->MethodRefs.statMeth3(i1, i2, i3), 11, 22, 33);

        System.out.println("=============");

        MethodRefs mr = new MethodRefs();

        testMeth(mr::meth0);
        testMeth(()->mr.meth0());

        testMeth(mr::meth1, 1);
        testMeth(i->mr.meth1(i), 11);

        testMeth(mr::meth2, 1, 2);
        testMeth((i1, i2)->mr.meth2(i1, i2), 11, 22);

        testMeth(mr::meth3, 1, 2, 3);
        testMeth((i1, i2, i3)->mr.meth3(i1, i2, i3), 11, 22, 33);

        System.out.println("=============");

        testMeth(MethodRefs::meth0, mr);
        testMeth(x->x.meth0(), mr);

        testMeth(MethodRefs::meth1, mr, 1);
        testMeth((x, i)->x.meth1(i), mr, 11);

        testMeth(MethodRefs::meth2, mr, 1, 2);
        testMeth((x, i1, i2)->x.meth2(i1, i2), mr, 11, 22);
    }
}
