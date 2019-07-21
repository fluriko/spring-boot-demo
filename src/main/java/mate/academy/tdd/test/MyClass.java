package mate.academy.tdd.test;

public class MyClass {

    private MyClassCollaborator mcc;

    public MyClass(MyClassCollaborator mcc) {
        this.mcc = mcc;
    }

    public int sum(int x, int y) {
        return x + y;
    }

    public String extendString(String s) {
        return mcc.getSomeString().concat(s);
    }

    public String replaceChars(String s) {
        return mcc.replaceAWith2(s);
    }
}
