package uebung9;

import java.util.Objects;


//a)
@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) && other.test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) || other.test(t);
    }

    static <T> Predicate<T> not(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t) -> !predicate.test(t);
    }
}

//b) nicht richtig
public class Aufgabe6
{
	public static void main(String[] args) {
        Predicate<Number> isPositive = n -> n.intValue() > 0;
        Predicate<Integer> isEven = i -> i % 2 == 0;

        Predicate<Integer> isPositiveAndEven = isEven.and(isPositive);
        
        System.out.println(isPositiveAndEven.test(4));     // Ausgabe: true
    }
}