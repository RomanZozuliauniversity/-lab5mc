package ua.lpnu.lab5.methods;

import java.util.ArrayList;
import java.util.List;

public class Distribution {
    private double[] arr;
    private double delta, max, Kn;
    private int N, K;
    private List<Double> intervals = new ArrayList<>();
    private List<Integer> countElemOnIntervals = new ArrayList<>();
    private double [] theorIntervals;
    private double [] experIntervals;

    public Distribution(double [] arr) {
        this.arr = arr;
        N = arr.length;

        findDist();
        findTheorintervals();
        findExperIntervals();
        findMax();
    }

    private void findDist() {
        double min, max;
        K = (int)(1 + 3.2 * Math.log10(N));

        min = arr[0];
        max = arr[0];
        for(double elem : arr) {
            if (elem > max)
                max = elem;
            else if (elem < min)
                min = elem;
        }
        delta = (max - min) / K;

        int countOnInterval = 0;
        for (double start = min, end = min + delta; end <= max; start = end, end = end + delta) {
            intervals.add(start);
            for (double elem : arr)
            if ((elem >= start && elem < end) || (elem >= start && elem <= end && elem == max))
                countOnInterval++;
            countElemOnIntervals.add(countOnInterval);
            countOnInterval = 0;
        }
        intervals.add(max);
    }

    private void findExperIntervals() {
        experIntervals = new double[countElemOnIntervals.size()];

        for(int i = 0; i < countElemOnIntervals.size(); i++) {
            for(int j = 0; j <= i; j++) {
                experIntervals[i] += countElemOnIntervals.get(j);
            }
        }
    }

    private void findTheorintervals() {
        theorIntervals = new double[countElemOnIntervals.size()];
        double perfectCount = (double)N / (double)K;
        for (int i = 0; i < countElemOnIntervals.size(); i++)
            for (int j = 0; j <= i; j++)
                theorIntervals[i] += perfectCount;
    }

    private void findMax() {
        max = Math.abs(theorIntervals[0] - experIntervals[0]);
        for(int i = 1; i < theorIntervals.length; i++) {
            if (Math.abs(theorIntervals[i] - experIntervals[i]) > max)
                max = Math.abs(theorIntervals[i] - experIntervals[i]);
        }
        Kn = max / (double)N;
    }

    public double findCriticalValue(String num) {
        if(num.equals("0.99"))
            return 1.63 / Math.sqrt(N);
        else if(num.equals("0.95"))
            return 1.36 / Math.sqrt(N);
        else if(num.equals("0.90"))
            return 1.22 / Math.sqrt(N);

        return -1;
    }

    public double getKN() {
        return Kn;
    }

    public double [] getTheorIntervals() {
        return theorIntervals;
    }

    public double [] getExperIntervals() {
        return experIntervals;
    }

    public List<Double> getIntervals() {
        return intervals;
    }

    public List<Integer> getCountElementOnInterval() {
        return countElemOnIntervals;
    }
}