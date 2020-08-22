import java.awt.*;
import java.util.Random;

public class Chart {
    DrawingPanel window_layer = new DrawingPanel(600, 600);
    Graphics graphical_layer = window_layer.getGraphics();
    Random rand = new Random();

    public Chart() {

    }

    public void drawQuickUnion() {
        for (int i = 0 ; i <= 1000; i += 100) {
            graphical_layer.setColor(Color.cyan);
            long time = 0;
            WeightedQuickUnion temp_union = new WeightedQuickUnion(i);
            for (int j = 0; j < i; j++) {
                temp_union.connect(rand.nextInt(i), rand.nextInt(i));
            }
            graphical_layer.fillRect(40 + (i * 30 / 100), 550 - (int) (temp_union.getAction() / 6), 10,  (int) (temp_union.getAction() / 6));
            graphical_layer.setColor(Color.BLACK);
            graphical_layer.drawString("" + i, 40 + (i * 30 / 100), 560);
        }
    }

}
