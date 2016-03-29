package codes.pedroteixeira;

/**
 * Created by Pedro on 3/29/2016.
 */
public class KeyPair {
    private int valA;
    private int valB;

    public KeyPair(int a, int b) {
        this.valA = a;
        this.valB = b;
    }

    public int a() {
        return valA;
    }

    public int b() {
        return valB;
    }
}
