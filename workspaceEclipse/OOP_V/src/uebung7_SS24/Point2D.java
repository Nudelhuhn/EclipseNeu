package uebung7_SS24;

import java.util.ArrayList;
import java.util.List;

public class Point2D<T extends Number>
{

	private T x;
	private T y;
	
	public Point2D(T x, T y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Point(" + x + "," + y + ")";
	}
	
	public T getX() {
		return x;
	}
	
	public T getY() {
		return y;
	}

	
	
	public static void main(String[] args) {
		Point2D<Float> pp1 = new Point2D<Float>(1f,1f);
		Point2D<Float> pp2 = new Point2D<Float>(2f,2f);
		Point2D<Float> pp3 = new Point2D<Float>(3f,3f);
		
		Polygon<Float, Point2D<Float>> points2D = new Polygon<Float, Point2D<Float>>();
		points2D.addPoint(pp1);
		points2D.addPoint(pp2);
		points2D.addPoint(pp3);
		points2D.getPoints();
		
		
		Point3D<Float> ppp1 = new Point3D<Float>(1f,1f,1f);
		Point3D<Float> ppp2 = new Point3D<Float>(2f,2f,2f);
		Point3D<Float> ppp3 = new Point3D<Float>(3f,3f,3f);
		
		Polygon<Float, Point3D<Float>> points3D = new Polygon<Float, Point3D<Float>>();
		points3D.addPoint(ppp1);
		points3D.addPoint(ppp2);
		points3D.addPoint(ppp3);
		points3D.getPoints();
		
	}
}

class Point3D<T extends Number> extends Point2D<T>
{
	
	private T z;
	
	public Point3D(T x, T y, T z)
	{
		super(x,y);
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "Point(" + super.getX() + "," + super.getY() + "," + z + ")";
	}
}

class Polygon<T extends Number, P extends Point2D<T>>
{

	private List<P> points;
	
	public Polygon() {
		points = new ArrayList<P>();
	}
	
	public void addPoint(P point) {
		points.add(point);
	}
	
	public List<P> getPoints(){
		for(Point2D<T> point : points) {
			System.out.println(point.toString());
		}
		return points;
	}
}




