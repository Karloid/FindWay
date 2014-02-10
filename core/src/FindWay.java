import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3 on 21.01.14.
 */
public class FindWay {
    public static final int DELAY = 1;
    private static final double THRESHOLD = 0.0001;
    protected List<Point> points;
    private int sizeField;
    private int countPoints;
    public FrameFindWayView debugWindow;

    public void init() {
        points = new ArrayList<Point>();
        sizeField = 100;
        countPoints = 666;
        for (int i = 0; i < countPoints; i++) {
            points.add(Point.getRandomIntance(0, sizeField));
        }
       /* printPoints();
        System.out.println();
        listSwap(points, 0, 1);
        printPoints();
*/
    }

    public void findBestWay() {
        double currentEnergy = calcEnergy(points);
        System.out.println(" путь по всем точкам: " + currentEnergy);

        double initialTemp = 10000;
        double endTemp = 0.003d;
        double temp = initialTemp;


        for (int i = 0; i < 1000000; i++) {
            List<Point> candidatePoints = generateCandidatePoints(points);
            double candidateEnergy = calcEnergy(candidatePoints);
            if (candidateEnergy < currentEnergy) {
                points = candidatePoints;
                currentEnergy = candidateEnergy;
           //     System.out.println(Math.round(currentEnergy));
                if (debugWindow != null) {
                    debugWindow.repaint();
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } else if (Math.abs(candidateEnergy - currentEnergy) >= THRESHOLD) {
                double p = getProb(candidateEnergy - currentEnergy, temp);
            //    System.out.println(p);
                if (makeTransit(p)) {
                    points = candidatePoints;
                    currentEnergy = candidateEnergy;
          //          System.out.println(Math.round(currentEnergy));
                    if (debugWindow != null) {
                        debugWindow.repaint();
                        try {
                             Thread.sleep(DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }

            temp = DecreaseTemperature(initialTemp, i);
       //       System.out.println("temp: " + temp);

            if (temp <= endTemp)  {
                System.out.println("iteration count: " + i);
                break;
            }
        }

    }

    private double DecreaseTemperature(double initialTemp, int i) {
        return initialTemp * 0.1 / i;
        //   return initialTemp - 0.01;
    }

    private boolean makeTransit(double p) {
        return Math.random() < p;
    }

    private double getProb(double e, double temp) {
        return Math.exp(-e / temp);
    }

    private List<Point> generateCandidatePoints(List<Point> points) {
        List<Point> candidatePoints = new ArrayList<Point>();
        candidatePoints.addAll(points);
        int i = (int) Math.round(Math.random() * (countPoints - 1));
        int j = (int) Math.round(Math.random() * (countPoints - 1));
        //   System.out.println("i j " + i + " " + j);
        listSwap(candidatePoints, i, j);
        return candidatePoints;
    }

    public static void listSwap(List<Point> points, int i, int j) {
        int startSwap = Math.min(i, j);
        int endSwap = Math.max(i, j);

        if (startSwap + 1 == endSwap) {
            Point tmp = points.get(startSwap);
            points.add(startSwap, points.get(endSwap));
            points.remove(startSwap + 1);

            points.add(endSwap, tmp);
            points.remove(endSwap + 1);
        } else
            for (int leftSwapIndex = startSwap; leftSwapIndex < startSwap + (endSwap - startSwap) / 2; leftSwapIndex++) {
                Point tmp = points.get(leftSwapIndex);
                int rightSwapIndex = endSwap - (leftSwapIndex - startSwap);

                points.add(leftSwapIndex, points.get(rightSwapIndex));
                points.remove(leftSwapIndex + 1);

                points.add(rightSwapIndex, tmp);
                points.remove(rightSwapIndex + 1);

                //    points.add(k, points.get);
            }
    }
//  0 1 4 3 2 5
//

    private double calcEnergy(List<Point> points) {
        double energy = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            Point currentPoint = points.get(i);
            Point nextPoint = points.get(i + 1);
            energy += getDistance(currentPoint, nextPoint);
        }
        Point startPoint = points.get(0);
        Point endPoint = points.get(points.size() - 1);
        energy += getDistance(startPoint, endPoint);

        return energy;
    }

    private double getDistance(Point currentPoint, Point nextPoint) {
        return Math.sqrt(Math.pow(currentPoint.x - nextPoint.x, 2) + Math.pow(currentPoint.y - nextPoint.y, 2));
    }

    public void printPoints() {
        for (Point point : points) {
            System.out.println(point);
        }
    }


    public int getSizeField() {
        return sizeField;
    }

    public void setSizeField(int sizeField) {
        this.sizeField = sizeField;
    }
}
