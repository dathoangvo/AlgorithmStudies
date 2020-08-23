import java.util.Arrays;

/**
 * Quick Union:
 * When a branch is connected, change the root to the other root.
 * Weighted:
 * Change the root with the smaller max length to the other root.
 * Implement a way to find the largest branch.
 * Path Compressed:
 *
 *
 *
 */

public class WeightedQuickUnion {
    private int[] list;
    private int[] listWeight;
    private int[] default_list;

    public WeightedQuickUnion(int list_length) {
        list = new int[list_length];
        default_list = new int[list_length];
        listWeight = new int[list_length];
        for (int i = 0; i < list_length; i++) {
            list[i] = i;
            default_list[i] = i;
            listWeight[i] = 1;
        }
    }

    public void connect(int var1, int var2) {
        int root1 = findRoot(var1);
        int root2 = findRoot(var2);

        if (!connected(var1, var2)) {
            link(root1, root2);
        }
    }

    private void link(int root1, int root2) {
        if (listWeight[root1] >= listWeight[root2]) {
            list[root2] = root1;
            listWeight[root1] += listWeight[root2];
        } else {
            list[root1] = root2;
            listWeight[root2] += listWeight[root1];
        }
    }

    public boolean connected(int var1, int var2) {
        int var1_head = findRoot(var1);
        int var2_head = findRoot(var2);

        return var1_head == var2_head;
    }

    private int findRoot(int head) {
        while (list[head] != head) {
            head = list[head];
        }
        return head;
    }

    public void printList() {
        System.out.println(Arrays.toString(default_list));
        System.out.println(Arrays.toString(list));
        System.out.println(Arrays.toString(listWeight));
    }
}
