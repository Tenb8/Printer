import java.util.Comparator;

public class ComporatorByName implements Comparator<Documents> {
    @Override
    public int compare(Documents a, Documents b) {
       return a.getName().compareTo(b.getName());
    }
}
