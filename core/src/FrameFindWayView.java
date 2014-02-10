import javax.swing.*;
import java.awt.*;

/**
 * Created by 3 on 21.01.14.
 */
public class FrameFindWayView extends JFrame {
    private static final int CELL_SIZE = 9;
    public static final int MARGIN = 100;
    private final FindWay findWay;
    private int windowWidth;
    private int windowHeight;

    public FrameFindWayView(FindWay findWay) throws HeadlessException {
        this.findWay = findWay;
        findWay.debugWindow = this;
        windowWidth = findWay.getSizeField() * CELL_SIZE + MARGIN;
        windowHeight = findWay.getSizeField() * CELL_SIZE + MARGIN;

        setSize(windowWidth, windowHeight);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(new MyPanel());
    }

    private class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.translate(25, 25);
            drawBackgroundRectangle(g);
            drawPoints(g);
            drawWay(g);
        }

        private void drawWay(Graphics g) {
            for (int i = 0; i < findWay.points.size() - 1; i++) {
                Point currentPoint = findWay.points.get(i);
                Point nextPoint = findWay.points.get(i + 1);
           //     g.drawString(i + " ", currentPoint.x * CELL_SIZE, currentPoint.y * CELL_SIZE);
                g.drawLine(currentPoint.x * CELL_SIZE, currentPoint.y * CELL_SIZE, nextPoint.x * CELL_SIZE, nextPoint.y * CELL_SIZE);
            }
            Point firstPoint = findWay.points.get(0);
            Point endPoint = findWay.points.get(findWay.points.size() - 1);

            g.drawLine(firstPoint.x * CELL_SIZE, firstPoint.y * CELL_SIZE, endPoint.x * CELL_SIZE, endPoint.y * CELL_SIZE);
        }

        private void drawPoints(Graphics g) {
            g.setColor(Color.RED);
            for (Point point : findWay.points) {
                g.drawOval(point.x * CELL_SIZE - CELL_SIZE / 2, point.y * CELL_SIZE - CELL_SIZE / 2, CELL_SIZE, CELL_SIZE);
            }
        }

        private void drawBackgroundRectangle(Graphics g) {
            g.setColor(Color.BLUE);
            g.drawRect(0, 0, findWay.getSizeField() * CELL_SIZE, findWay.getSizeField() * CELL_SIZE);
        }
    }
}
