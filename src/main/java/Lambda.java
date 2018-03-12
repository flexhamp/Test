import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lambda {
    public static void main (String[] args) {
        System.out.println(calculate(5, 7, (a, b) -> a + b));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("3");

//        list.forEach(System.out::println);
//        String[] strings = list.stream().toArray(String[]::new);
//        System.out.println(Arrays.toString(strings));
//        String[] strings1 = list.toArray(new String[0]);
//        System.out.println(Arrays.toString(strings1));

    }

    public static double calculate(double a, double b, Action action){
        return action.operation(a, b);
    }

    @FunctionalInterface
    interface Action{
        double operation(double a, double b);
    }
}
