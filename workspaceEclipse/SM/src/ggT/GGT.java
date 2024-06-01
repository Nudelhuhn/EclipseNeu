package ggT;

public class GGT
{
    public static int ggT(int x, int y) {
    	if(x < 0 || y < 0) {
    		System.out.println("Keine negativen Werte!");
    		return -1; 
    	}
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
    
    public static void main(String[] args) {
        int x = 10;
        int y = -5;
        int ggt = ggT(x, y);
        System.out.println("Der größte gemeinsame Teiler von " + x + " und " + y + " ist: " + ggt);
    }
}
