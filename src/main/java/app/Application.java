package app;

import com.google.gson.Gson;

import java.util.Arrays;

public class Application {
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        double[] data1 = Inputs.dataSet1();
        double[] data2 = Inputs.dataSet2();
        Arrays.sort(data2);
        double sum = 937.86;
        //sum = 100;

        //recursion(data2,sum);
        SumOfElements soe = new SumOfElements(data2, sum);

    }

    private static void recursion(double[] array, double result){
        GetAllSubsetByStack get = new GetAllSubsetByStack();
        get.populateSubset(array, 0, array.length, result);
    }

    private static double[] reverse(double[] data) {
        for (int left = 0, right = data.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            double temp = data[left];
            data[left]  = data[right];
            data[right] = temp;
        }

        return data;
    }
}

