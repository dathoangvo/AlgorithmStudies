import java.util.Arrays;
import java.util.Scanner;

public class matrixElimination {
    private int[] matrix;
    private int height;
    private int width;
    private boolean augmented;

    public matrixElimination(int[] init_matrix, int init_matrix_height, int init_matrix_width, boolean init_augmented) {
        matrix = init_matrix;
        height = init_matrix_height;
        width = init_matrix_width;
        augmented = init_augmented;
    }

    public matrixElimination() {

    }

    public void manualMatrixSetup() {
        Scanner keyboard_input = new Scanner(System.in);

        setMatrixAugmentation(keyboard_input);
        setMatrixSize(keyboard_input);
        setMatrixVariables(keyboard_input);
    }

    private void setMatrixAugmentation(Scanner keyInput) {
        System.out.print("Is this an augmented matrix? y or any key");
        augmented = keyInput.nextLine().equals("y");
    }

    private void setMatrixSize(Scanner keyInput) {
        System.out.print("How many rows are there? ");
        height = keyInput.nextInt();
        System.out.print("How many columns are there? ");
        width = keyInput.nextInt();
        matrix = new int[height * width];
        Arrays.fill(matrix, 9999);
    }

    private void setMatrixVariables(Scanner keyInput) {
        System.out.println("Input the matrix for variable 1111");
        for (int i = 0; i < matrix.length; i++) {
            int widthScoller = 0;
            matrix[i] = 1111;
            for (int value : matrix) {
                if (augmented && (widthScoller == width - 1)) {
                    System.out.print(" ");
                }
                if (widthScoller == width) {
                    widthScoller = 0;
                    System.out.println();
                    System.out.print("[" + value + "]");
                } else {
                    System.out.print("[" + value + "]");
                }
                widthScoller++;
            }
            matrix[i] = keyInput.nextInt();
        }
    }
}
