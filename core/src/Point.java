/**
 * Created by 3 on 21.01.14.
 */
public class Point {
    private static int currentId;
    public int x;
    public int y;
    private int id;

    public Point() {
       id = currentId;
        currentId++;
    }

    public static Point getRandomIntance(int min, int max) {
        Point point = new Point();
        point.x = (int) Math.round(Math.random() * max);
        point.y = (int) Math.round(Math.random() * max);
        return point;
    }

    @Override
    public String toString() {
        return " Point: " + id + " x: " + x + " y: " + y;
    }
}
