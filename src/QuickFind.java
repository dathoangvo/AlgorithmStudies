import java.util.Arrays;

/**Quick Find Algorithm
 */

public class QuickFind {
    private int[] list;

    /**
     * Constructs the list length and sets all value to its array position.
     * @param list_length Length of list
     */
    public QuickFind(int list_length) {
        list = new int[list_length];

        for (int i = 0; i < list_length; i++) {
            list[i] = i;
        }
    }

    /**Checks to see if the two nodes have a possible connection.
     * @param var1 First node
     * @param var2 Second node
     * @return True if they are connected, false otherwise.
     */
    public boolean find(int var1, int var2) {
        return list[var1] == list[var2];
    }

    /**Connects two nodes by changing every array slot sharing the same value as variable 1 into variable 2
     * @param var1 First node
     * @param var2 Second node
     */
    public void union(int var1, int var2) {
        int temp1 = list[var1];

        for (int i = 0; i < list.length; i++) {
            if (list[i] == temp1) {
                list[i] = list[var2];
            }
        }
    }

    /**Prints out the Array
     */
    public void printList() {
        System.out.println(Arrays.toString(list));
    }
}
