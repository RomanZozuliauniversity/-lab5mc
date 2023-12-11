package ua.lpnu.lab5.methods;

public class Akm {
    int count, x0, x1, b;
    double[] arr;

    public Akm(int x0, int x1, int b, int count) {
        this.count = count;
        arr = new double[count];
        this.b = b;
        this.x0 = x0;
        this.x1 = x1;

        double num = (x0 + x1) % Math.pow(2, b);
        arr[0] = num / Math.pow(2, b);
        generator(x1, num);
    }

    private void generator(double prevNum, double curr) {
        double num;

        for (int i = 1; i < count; i++)
        {
            num = (prevNum + curr) % Math.pow(2, b);

            arr[i] = num / Math.pow(2, b);
            prevNum = curr;
            curr = num;
        }
    }

    public double[] getArray() {
        return arr;
    }
}