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

        BirthDate birthDate4 = new BirthDate();
        birthDate4.setValue(value);

        BirthDate birthDate5 = new BirthDate();
        birthDate5.setValue(value);

        //IndividualName
        IndividualName individualName1 = new IndividualName();
        individualName1.setFirstName("Test1");
        individualName1.setLastName("Testov1");
        individualName1.setSecondName("Test1");

        IndividualName individualName2 = new IndividualName();
        individualName2.setFirstName("Test2");
        individualName2.setLastName("Testov2");
        individualName2.setSecondName("Test2");

        IndividualName individualName3 = new IndividualName();
        individualName3.setFirstName("Test3");
        individualName3.setLastName("Testov3");
        individualName3.setSecondName("Test3");

        IndividualName individualName4 = new IndividualName();
        individualName4.setFirstName("Test4");
        individualName4.setLastName("Testov4");
        individualName4.setSecondName("Test4");

        IndividualName individualName5 = new IndividualName();
        individualName5.setFirstName("Test5");
        individualName5.setLastName("Testov5");
        individualName5.setSecondName("Test5");

        List<IndividualName> names1 = new ArrayList<>();
        names1.add(individualName1);

        List<IndividualName> names2 = new ArrayList<>();
        names2.add(individualName2);
        names2.add(individualName1);

        List<IndividualName> names3 = new ArrayList<>();
        names3.add(individualName2);
        names3.add(individualName4);
        names3.add(individualName5);
        names3.add(individualName1);

        List<IndividualName> names4 = new ArrayList<>();
        names4.add(individualName5);
        names4.add(individualName1);
        names4.add(individualName2);

        List<IndividualName> names5 = new ArrayList<>();
        names5.add(individualName2);
        names5.add(individualName3);
        names5.add(individualName1);
        names5.add(individualName5);

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

        //Individual 4
        Individual individual4 = new Individual();
        individual4.setNames(names4);
        individual4.setBirthDate(birthDate4);

        //Individual 5
        Individual individual5 = new Individual();
        individual5.setNames(names5);
        individual5.setBirthDate(birthDate5);

        List<Individual> list = Arrays.asList(individual2, individual3, individual4, individual5);

        System.out.println(collectionsComparator(individualComparator()).compare(Arrays.asList(individual1), list));
//        System.out.println(dateComparator().compare(individual3, individual2));
    }


    public static <T> Comparator<Collection<T>> collectionsComparator(Comparator<T> elemComparator) {
        return (col1, col2) -> {
            TreeSet<T> treeSet = new TreeSet<>(elemComparator);
            treeSet.addAll(col1);
            return treeSet.containsAll(col2) ? 0 : 1;
        };
    }

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
            comparator = comparator.thenComparing(thenComparing[i]);
        }
        return comparator;
    }


}
