

import java.util.Scanner;

public class LambdaExpression {
    public static void main(String[] args) {
        System.out.println("Please enter five numbers (enter return to continue): ");
        Scanner in = new Scanner(System.in);
        double[] number =new double[5];
        for(int i = 0; i < number.length; i++){
            number[i]=in.nextInt();
        }


        System.out.println("Minimum of five numbers is: "
                + miner.apply(number) );
        System.out.println("Maximum of five numbers is: "
                + maxer.apply(number) );
        System.out.println("Sum of five numbers is: "
                + sumer.apply(number) );
        System.out.println("Average of five numbers is: "
                + averager.apply(number) );

        System.out.println("******************************");
        System.out.println("Count of 5 in five numbers: "
                + counter(5).apply(number) );
        System.out.println("Count of 0 in five numbers: "
                + counter(0).apply(number) );
    }


    public static ArrayProcessor counter( double value ) {
        return array -> {
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if ( array[i] == value )
                    count++;
            }
            return count;
        };
    }


    public static final ArrayProcessor maxer = array -> {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if ( array[i] > max)
                max = array[i];
        }
        return max;
    };



    public static final ArrayProcessor miner = array -> {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if ( array[i] < min)
                min = array[i];
        }
        return min;
    };


    public static final ArrayProcessor sumer = array -> {
        double total = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        return total;
    };



    public static final ArrayProcessor averager =
            array -> sumer.apply(array) / array.length;
}



