
import java.util.Comparator;

public class ComporatorByTime implements Comparator<Documents> {
    @Override
    public int compare(Documents a, Documents b) {
        if(a.getTIME()>b.getTIME()){
            return 1;
        }else if(a.getTIME()<b.getTIME()){
            return -1;
        }else{
            return 0;
        }
    }
}
