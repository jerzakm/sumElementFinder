package app;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;

public class GetAllSubsetByStack {
    private static HashSet<String> resultSet = new HashSet<>();

    int operations = 0;


    /** Set a value for target sum */
    public static double TARGET_SUM = 0;
    public static double largest_SUM = 0;

    private Stack<Double> stack = new Stack<Double>();

    /** Store the sum of current elements stored in stack */
    private double sumInStack = 0;

    private static double lastPrinted = 0;

    public void populateSubset(double[] data, int fromIndex, int endIndex, double targetsum) {
        TARGET_SUM = targetsum;
        operations++;
        /*
         * Check if sum of elements stored in Stack is equal to the expected
         * target sum.
         *
         * If so, call print method to print the candidate satisfied result.
         */

        if (sumInStack == TARGET_SUM) {
            print(stack,sumInStack);
        }

        for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {

            if (sumInStack + data[currentIndex] <= TARGET_SUM) {
                stack.push(data[currentIndex]);
                sumInStack += data[currentIndex];

                /*
                 * Make the currentIndex +1, and then use recursion to proceed
                 * further.
                 */
                populateSubset(data, currentIndex + 1, endIndex, targetsum);
                if(data[currentIndex]>lastPrinted) {
                    lastPrinted=data[currentIndex];
                    LocalTime now = LocalTime.now();
                    String c = lastPrinted+";"+now+";OPS: "+operations+";\n";
                    System.out.print(c);
                    appendToFile(c, "queries.csv");
                }
                sumInStack -= (Double) stack.pop();
            }

        }
        /*if(sumInStack<=TARGET_SUM) {
            largest_SUM=sumInStack;
            print(stack, sumInStack);
        }*/
    }

    /**
     * Print satisfied result. i.e. 15 = 4+6+5
     */

    private void print(Stack<Double> stack, double sumInStack) {
        StringBuilder sb = new StringBuilder();
        sb.append(sumInStack).append(" = ");
        for (double i : stack) {
            sb.append(i).append(" + ");
        }
        if(!resultSet.contains(sb.toString())) {
            resultSet.add(sb.toString());
            System.out.println(sb.toString());
        }
        appendToFile(sb.toString()+";\n","results.csv");
    }

    private void appendToFile(String line, String filename){
        try {
            FileUtils.writeStringToFile(new File(filename),line,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}