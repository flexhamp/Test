import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class Lambda {
    private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) throws ParseException {
        //BirthDate
        BirthDate birthDate1 = new BirthDate();
        Date value = new Date();
        birthDate1.setValue(value);
//        birthDate1.setValue(df.parse("22-03-18"));

        BirthDate birthDate2 = new BirthDate();
        birthDate2.setValue(value);

        BirthDate birthDate3 = new BirthDate();
        birthDate3.setValue(value);

        //IndividualName
        IndividualName individualName1 = new IndividualName();
        individualName1.setFirstName("test2");
        individualName1.setLastName("Testov2");
        individualName1.setSecondName("Test2");

        IndividualName individualName2 = new IndividualName();
        individualName2.setFirstName("Test2");
        individualName2.setLastName("Testov2");
        individualName2.setSecondName("Test2");

        IndividualName individualName3 = new IndividualName();
        individualName3.setFirstName("Test3");
        individualName3.setLastName("Testov3");
        individualName3.setSecondName("Test3");

        IndividualName individualName4 = new IndividualName();
        individualName4.setFirstName("Test2");
        individualName4.setLastName("Testov2");
        individualName4.setSecondName("Test2");

        List<IndividualName> names1 = new ArrayList<>();
        names1.add(individualName1);

        List<IndividualName> names2 = new ArrayList<>();
        names2.add(individualName2);

        List<IndividualName> names3 = new ArrayList<>();
        names3.add(individualName4);
        names3.add(individualName3);

        //Individual 1
        Individual individual1 = new Individual();
        individual1.setNames(names1);
        individual1.setBirthDate(birthDate1);

        //Individual 2
        Individual individual2 = new Individual();
        individual2.setNames(names2);
        individual2.setBirthDate(birthDate2);

        //Individual 3
        Individual individual3 = new Individual();
        individual3.setNames(names3);
        individual3.setBirthDate(birthDate3);

//        System.out.println(individualComparator().compare(individual3, individual1));
        List<Individual> list = Arrays.asList(individual2, individual3);

        System.out.println(collectionsComparator(individualComparator()).compare(Arrays.asList(individual1), list));
//        System.out.println(dateComparator().compare(individual3, individual2));
    }


    private static <T> Comparator<Collection<T>> collectionsComparator(Comparator<T> elemComparator) {
        return (col1, col2) -> {
            System.out.println("col2 -> " + col2);
            System.out.println("end of col2");
            TreeSet<T> treeSet = new TreeSet<>(elemComparator);
            treeSet.addAll(col1);
            System.out.println(treeSet + "\n");
            return treeSet.containsAll(col2) ? 0 : 1;
        };
    }

    @SuppressWarnings("unchecked")
    public static Comparator<Individual> individualComparator() {
        return Comparator.comparing(Individual::getNames, collectionsComparator(uniComparator(
                IndividualName::getFirstName,
                individualName1 -> normalize(individualName1.getLastName()),
                individualName2 -> normalize(individualName2.getSecondName()))))
                .thenComparing(Individual::getBirthDate, uniComparator(BirthDate::getValue));
    }

    private static String normalize(String s) {
        return s.toUpperCase();
    }


    public static <T, R extends Comparable> Comparator<T> uniComparator(Function<T, R>... thenComparing) {
        Comparator<T> comparator = Comparator.comparing(thenComparing[0]);
        for (int i = 1; i < thenComparing.length; i++) {
            System.out.println("->" + i);
            comparator = comparator.thenComparing(thenComparing[i]);
        }
        return comparator;
    }


}
