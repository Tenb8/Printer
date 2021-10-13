/**
 * Created by st554-05 on 27.05.2019.
 */
public class Main1 {
    public static void main(String[] args) {
        printDispecher pd = new printDispecher();
        pd.printDoc();
        pd.addToPrint(null);
        pd.cancelPrint(null);
        pd.stopDispecher();
    }
}
