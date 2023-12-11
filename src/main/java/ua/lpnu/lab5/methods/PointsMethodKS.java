package ua.lpnu.lab5.methods;

import java.util.Arrays;

public class PointsMethodKS {
    double [] arr,  theorArr;
    double maxDelta = 0, Kn;
    int N;


    public PointsMethodKS(double [] arr) {
        this.arr = arr;
        theorArr = new double[arr.length];
        N = arr.length;

        Arrays.sort(arr);
        findTheorArr();
        findMaxDelta();
        findKn();
    }

    private void findTheorArr() {
        double delta = 1 / (double)N;
        theorArr[0] = 0;
        for(int i = 1; i < arr.length; i++){
            theorArr[i] = theorArr[i - 1] + delta;
        }
    }

    private void findMaxDelta() {
        for(int i = 0; i < arr.length; i++)
            if (Math.abs(theorArr[i] - arr[i]) > maxDelta)
                maxDelta = Math.abs(theorArr[i] - arr[i]);
    }

    private void findKn() {
        Kn = maxDelta * Math.sqrt(N);
    }

    public double findCriticalValue(String P) {
        if (P.equals("0.01"))
            return 0.07089 - (0.15 / Math.sqrt(N));
        else if (P.equals("0.05"))
            return 0.1601 - (0.14 / Math.sqrt(N));
        else if (P.equals("0.25"))
            return 0.3793 - (0.15 / Math.sqrt(N));
        else if (P.equals("0.50"))
            return 0.5887 - (0.15 / Math.sqrt(N));
        else if (P.equals("0.75"))
            return 0.8326 - (0.16 / Math.sqrt(N));
        else if (P.equals("0.95"))
            return 1.2239 - (0.17 / Math.sqrt(N));
        else if (P.equals("0.99"))
            return 1.5174 - (0.20 / Math.sqrt(N));

        return -1;
    }

    public double getKn() {
        return Kn;
    }

    public double [] getTheorArr() {
        return theorArr;
    }

    public double [] getSortArr() {
        return arr;
    }
}