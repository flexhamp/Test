import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lambda {

    public static void main(String[] args) {
        BirthDate birthDate = new BirthDate();
        birthDate.setValue(new Date());
        IndividualName individualName = new IndividualName();
        individualName.setFirstName("Test1");
        individualName.setLastName("Testov1");
        individualName.setSecondName("Testovich1");

        List<IndividualName> names = new ArrayList<>();
        names.add(individualName);

        Individual individual = new Individual();
        individual.setNames(names);
        individual.setBirthDate(birthDate);

        BirthDate birthDate1 = new BirthDate();
        birthDate.setValue(new Date());
        IndividualName individualName1 = new IndividualName();
        individualName1.setFirstName("Test1");
        individualName1.setLastName("Testov1");
        individualName1.setSecondName("Testovich1");

        List<IndividualName> names1 = new ArrayList<>();
        names.add(individualName);

        Individual individual1 = new Individual();
        individual1.setNames(names1);
        individual1.setBirthDate(birthDate1);

//        System.out.println(individual);
//        System.out.println(individual1);

        System.out.println(individualComparator("").compare(individual, individual1));
        collectionsComparator(IndividualName)

    }

    public static double calculate(double a, double b, Action action){
        return action.operation(a, b);
    }

    @FunctionalInterface
    interface Action{
        double operation(double a, double b);
    }


    public static Comparator<Individual> individualComparator(String searchStrategy) {
        Comparator<Individual> comp = Comparator.comparing(Individual::getNames, collectionsComparator(namesComparator()));
        if (searchStrategy == "sd") {
            comp = comp.thenComparing(Individual::getBirthDate, Comparator.comparing(BirthDate::getValue));
        }
        return comp;
    }

    private static <T> Comparator<Collection<T>> collectionsComparator(Comparator<T> elemComparator) {
        return (col1, col2) -> {
            System.out.println(col1);
            System.out.println(col2);
            TreeSet<T> treeSet = new TreeSet<>(elemComparator);
            treeSet.forEach(System.out::println);
            treeSet.addAll(col1);
            return treeSet.containsAll(col2) ? 0 : 1;
        };
    }

    public static Comparator<IndividualName> namesComparator() {
        return Comparator.comparing(IndividualName::getFirstName).thenComparing(IndividualName::getLastName);
    }


}
