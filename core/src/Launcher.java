/**
 * Created by 3 on 21.01.14.
 */
public class Launcher {
    public static void main(String[] args) {
        FindWay findWay = new FindWay();
        findWay.init();
      //  findWay.printPoints();

        FrameFindWayView frame = new FrameFindWayView(findWay);
        findWay.findBestWay();
    }
}
