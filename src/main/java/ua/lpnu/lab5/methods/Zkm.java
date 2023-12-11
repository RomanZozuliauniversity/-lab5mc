package ua.lpnu.lab5.methods;

public class Zkm {
    int count, x0, a, b, c;
    double[] arr;

    public Zkm(int x0, int a, int c, int b, int count) {
        this.count = count;
        arr = new double[count];
        this.a = a;
        this.b = b;
        this.c = c;
        this.x0 = x0;
        double prev = (x0 * a + c) % Math.pow(2, b);
        arr[0] = prev / Math.pow(2, b);
        generator(prev);
    }

    private void generator(double prevNum) {
        double num;
        for (int i = 1; i < count; i++) {
            num = (prevNum * a + c) % Math.pow(2, b);
            arr[i] = num / Math.pow(2, b);
            prevNum = num;
        }
    }

    public double[] getArray() {
        return arr;
    }
}