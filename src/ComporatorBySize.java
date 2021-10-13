import java.util.Comparator;

public class ComporatorBySize implements Comparator<Documents> {
    @Override
    public int compare(Documents a, Documents b) {
        return a.getSize().compareTo(b.getSize());
    }
}
