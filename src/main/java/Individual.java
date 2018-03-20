import java.util.Date;
import java.util.List;

public class Individual {

   private List<IndividualName> names;

   private BirthDate birthDate;

    public List<IndividualName> getNames() {
        return names;
    }

    public void setNames(List<IndividualName> names) {
        this.names = names;
    }

    public BirthDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "names=" + names +
                ", birthDate=" + birthDate.getValue() +
                '}';
    }
}
