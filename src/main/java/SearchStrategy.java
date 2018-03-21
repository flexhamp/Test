import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Norg on 20.03.2018.
 */
@SuppressWarnings("unchecked")
public enum SearchStrategy {
    FIO {
        @Override
        List<Function<IndividualName, String>> getStrategy() {
            return Arrays.asList(IndividualName::getFirstName, IndividualName::getLastName, IndividualName::getSecondName);
        }
    },
    BIRTHDATE {
        @Override
        List<Function<BirthDate, Date>> getStrategy() {
            return Arrays.asList(BirthDate::getValue);
        }
    };

    abstract <T, R> List<Function<T, R>> getStrategy();
}
