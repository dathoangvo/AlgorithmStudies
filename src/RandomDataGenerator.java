import java.util.Random;

public class RandomDataGenerator {
    private int data_length;
    private int[] array_data;
    private Random rand = new Random();

    private int upperbound;
    private int lowerbound;

    public RandomDataGenerator() {
    }

    public void setUpperbound(int init_upperbound) {
        upperbound = init_upperbound;
    }

    public void setLowerbound(int init_lowerbound) {
        lowerbound = init_lowerbound;
    }

    public void setData_length(int init_datalength) {
        data_length = init_datalength;
    }

    public int[] getArray_data() {
        array_data = new int[data_length];
        for (int i = 0; i < data_length; i++) {
            array_data[i] = rand.nextInt(upperbound - lowerbound) + lowerbound;
        }
        return array_data;
    }
}
