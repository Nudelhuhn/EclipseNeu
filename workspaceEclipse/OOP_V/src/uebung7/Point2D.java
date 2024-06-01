package uebung7;

import java.util.*;

public class Point2D<T extends Number> {
    private T x;
    private T y;

    public Point2D(T x, T y) {
        this.x = x;
        this.y = y;
    }
}


class Point3D <T extends Number> extends Point2D<T>{
	private T z;
	
	public Point3D(T x, T y, T z) {
        super(x, y);
        this.z = z;
    }
}

class Polygon <T extends Number, P extends Point2D<T>>{
	private List<P> points;
	
	public Polygon() {
		points = new ArrayList<>();
	}
	public void addPoint(P point) {
        points.add(point);
    }

    public List<P> getPoints() {
        return points;
    }
}
