package oop.matrix_nicht_fertig;

public class Operation
{

    private static int xAchsenMatrixNeu = 0;

    private static int yAchsenMatrixNeu = 0;

    public static void setXAchsenMatrixNeu(int x)
    {
        Operation.xAchsenMatrixNeu = x;
    }

    public static void setYAchsenMatrixNeu(int y)
    {
        Operation.yAchsenMatrixNeu = y;
    }

    public static void transpose(int[][] matrix)
    {
        matrixEinlesen(matrix); // Anzahl der X- und Y-Achsen ermitteln
        int[][] matrixNeu = new int[yAchsenMatrixNeu][xAchsenMatrixNeu];
        // neue leere transponierte Matrix erstellen
        for (int i = 0; i < yAchsenMatrixNeu; i++)
        {
            for (int j = 0; j < xAchsenMatrixNeu; j++)
            {
                matrixNeu[i][j] = matrix[j][i];
            }
        }
        matrixAusgeben(matrixNeu);
    }

    private static void matrixAusgeben(int[][] matrix)
    {
        System.out.print("{");
        for (int arrays[] : matrix)
        {
            System.out.print("{ ");
            for (int inhalt : arrays)
            {
                System.out.print(inhalt + " ");
            }
            System.out.print("}");
            System.out.println();
        }
        System.out.print("}");
    }

    private static void matrixEinlesen(int[][] matrix)
    {
        int anzZahlen = 0;
        int xAchsen = 0;
        int yAchsen;
        for (int arrays[] : matrix)
        {
            xAchsen++;
            for (int inhalt : arrays)
            {
                anzZahlen++;
            }
        }
        yAchsen = anzZahlen / xAchsen;
        setXAchsenMatrixNeu(xAchsen);
        setYAchsenMatrixNeu(yAchsen);
        // xAchsenMatrixNeu X-Achsen mit jeweils
        // (anzZahlenMatrixNeu/xAchsenMatrixNeu) Zahlen
    }

    public static void main(String[] args)
    {
        int[][] matrix =
        {
            { 1, 2 },
            { 4, 5 },
            { 7, 6 } };
        transpose(matrix);
        // 1,4,7
        // 2,5,6
    }

}
