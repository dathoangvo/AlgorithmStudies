import java.util.Arrays;

public class WeightedQuickUnion {
    private int[] list;
    private int[] default_list;
    private int action = 0;

    public WeightedQuickUnion(int list_length) {
        list = new int[list_length];
        default_list = new int[list_length];
        for (int i = 0; i < list_length; i++) {
            list[i] = i;
            default_list[i] = i;
        }
    }

    public void connect(int var1, int var2) {
        action++;
        int var1_head = findHead(var1);
        int var1_length = findSizeofRoot(var1);
        int var2_head = findHead(var2);
        int var2_length = findSizeofRoot(var2);

        if (!connected(var1, var2)) {
            if (var1_length >= var2_length) {
                list[var2_head] = var1_head;
            } else {
                list[var1_head] = var2_head;
            }
        }


    }

    private int findSizeofRoot(int var) {
        int size = 0;
        while (list[var] != var) {
            action++;
            size++;
            var = list[var];
        }
        return size;
    }

    public boolean connected(int var1, int var2) {
        int var1_head = findHead(var1);
        int var2_head = findHead(var2);

        return var1_head == var2_head;
    }

    private int findHead(int head) {
        while (list[head] != head) {
            action++;
            head = list[head];
        }
        return head;
    }

    public void printList() {
        System.out.println(Arrays.toString(default_list));
        System.out.println(Arrays.toString(list));
    }

    public int getAction() {
        return action;
    }

    public void resetAction() {
        action = 0;
    }
}
