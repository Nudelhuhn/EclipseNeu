package execution;

public class ExpressionEvaluationNoStaticMethods
{
    public int plus(int a, int b)
    {
        return a + b;
    }
    
    public int minus(int a, int b)
    {
        int result = a - b;
        return result;
    }
    
    public int times(int a, int b)
    {
        int x = a;
        int y = b;
        int result = x * y;
        return result;
    }

    //computes (a + b)**2
    public int binom1(int a, int b)
    {
        int sum = plus(a, b);
        int result = times(sum, sum);
        return result;
    }

    //computes a**2 + 2*a*b + b**2
    public int binom2(int a, int b)
    {
        int aSquare = times(a, a);
        int bSquare = times(b, b);
        int ab = times(a, b);
        int abDouble = times(ab, 2);
        int result = plus(aSquare, abDouble);
        result = plus(result, bSquare);
        return result;
    }

    public static void main(String[] args)
    {
    	ExpressionEvaluationNoStaticMethods obj = new ExpressionEvaluationNoStaticMethods();
        int x = 5;
        int y = 6;
        int r1 = obj.binom1(x, y);
        System.out.println("Ergebnis von binom1(" + x + ", " + y + "): " + r1);
        int r2 = obj.binom2(x, y);
        System.out.println("Ergebnis von binom2(" + x + ", " + y + "): " + r2);
    }
}
