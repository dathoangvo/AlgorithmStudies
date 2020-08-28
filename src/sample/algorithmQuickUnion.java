package sample;

import java.util.Arrays;

public class algorithmQuickUnion {
    private int[] open;
    private int[] display;
    private int[] parent;
    private int[] weight;
    private int min;
    private int max;

    private int width;
    private int height;


    public algorithmQuickUnion(int _width, int _height) {
        width = _width;
        height = _height;
        int size = width * height;
        min = 0;
        max = size - 1;
        open = new int[size];
        display = new int[size];
        parent = new int[size];
        weight = new int[size];
        for (int i = 0; i < size; i++) {
            display[i] = i;
            parent[i] = i;
            weight[i] = 1;
            open[i] = 0;
        }
    }

    public int getParent(int position) {
        return parent[position];
    }

    public void updateOpen(int position) {
        if (validRange(position) && open[position] == 0) {
            open[position] = 1;
            connectAbove(position);
            connectBelow(position);
            connectLeft(position);
            connectRight(position);
        }

        else if (validRange(position) && open[position] == 1) {
            int newRoot = -1;
            for (int i = 0; i < size(); i++) {
                if (parent[i] == parent[position]) {
                    newRoot = i;
                    i = size();
                }
            }
            for (int i = 0; i < size(); i++) {
                if (parent[i] == position && newRoot != -1) {
                    parent[i] = newRoot;
                } else if (newRoot == -1) {
                    i = size();
                }
            }
            parent[position] = position;
            open[position] = 0;
        }



    }

    private void connectAbove(int position) {
        if (position >= width && checkOpen(position - width)) {
            union(position, position - width);
        }
    }

    private void connectLeft(int position) {
        if (position > 0 && checkOpen(position - 1)) {
            union(position, position - 1);
        }
    }

    private void connectRight(int position) {
        if (position < (width * height) - 1 && checkOpen(position + 1)) {
            union(position, position + 1);
        }
    }

    private void connectBelow(int position) {
        if (position < (width * (height - 1)) && checkOpen(position + width)) {
            union(position, position + width);
        }
    }

    public boolean checkOpen(int position) {
        return open[position] == 1;
    }

    public boolean percolate() {
        for (int i = 0; i < width; i++) {
            for (int j = width * (height - 1); j < width * height; j++) {
                if (connected(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return width * height;
    }

    public void union(int var1, int var2) {
        if (!validRange(var1) || !validRange(var2)) {
            System.out.println("One of your variables is not within the scope of the array");
        }
        int root1 = findRoot(var1);
        int root2 = findRoot(var2);

        connect(root1, root2);
    }

    public boolean connected(int var1, int var2) {
        return findRoot(var1) == findRoot(var2);
    }

    public void printArray() {
        System.out.println(Arrays.toString(display));
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(weight));
    }

    private int findRoot(int var) {
        int root = var;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[var] != root) {
            parent[var] = root;
        }
        return root;
    }

    private void connect(int root1, int root2) {
        if (!connected(root1, root2)) {
            if (weight[root1] < weight[root2]) {
                parent[root1] = root2;
                weight[root2] += weight[root1];
            } else {
                parent[root2] = root1;
                weight[root1] += weight[root2];
            }
        }
    }

    private boolean validRange(int var) {
        return var >= min && var <= max;
    }
}
