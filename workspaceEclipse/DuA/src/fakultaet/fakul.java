package fakultaet;

public class fakul
{

	public static int fak(int n) {
		if(n <= 0) {
			return 1;
		}
		return fak(n-1)*n;
	}
	
	public static void main(String[] args)
	{
		int n = 10;
		for(int i = 0; i < n; i++) {
			System.out.println(fak(i));
		}
	}

}
