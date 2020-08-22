public class MainTester {
    public static void main(String[] args) {
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        weightedQuickUnion.connect(4, 3);
        weightedQuickUnion.connect(8, 3);
        weightedQuickUnion.connect(9, 8);
        weightedQuickUnion.connect(2, 1);
        weightedQuickUnion.connect(2, 0);
        weightedQuickUnion.connect(4, 7);
        weightedQuickUnion.connect(7, 2);
        weightedQuickUnion.printList();
        System.out.println(weightedQuickUnion.connected(3, 1));
        System.out.println(weightedQuickUnion.getAction());
    }
}
