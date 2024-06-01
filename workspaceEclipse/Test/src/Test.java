import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        list1.addAll(list2); // Fügt alle Elemente von list2 zu list1 hinzu

        System.out.println("Zusammengeführte Liste: " + list1);
    }
}
