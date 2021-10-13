import javax.print.Doc;
import javax.swing.*;
import java.util.*;
import java.util.   concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class printDispecher {
    public static boolean flag = false;
    private Queue<Documents> documentList=new ConcurrentLinkedQueue<>();
    private CopyOnWriteArrayList<Documents> printedDoc=new CopyOnWriteArrayList<>();
//        public void createDocs(int num) {
//            int j=1;
//        for (int i = 1; i <= num; i++) {
//            System.out.println("Создались следующие документы: ");
//            orders = new Orders("DocOrders " +"№ "+ j, "Размер "+"A4 "+ "Время печати:");
//            acts = new Acts("DocActs "+"№ " + j,"Размер "+ "A5 "+ "Время печати:");
//            anyDoc = new AnyDoc("AnyDocs "+"№ " + j,"Размер "+ "A4 "+ "Время печати:");
//            documentList.add(orders);
//            System.out.println(orders.getName()+" "+orders.getSize()+" "+orders.getTIME());
//            documentList.add(acts);
//            System.out.println(acts.getName()+" "+acts.getSize()+" "+acts.getTIME());
//            documentList.add(anyDoc);
//            System.out.println(anyDoc.getName()+" "+anyDoc.getSize()+" "+anyDoc.getTIME());
//            System.out.println("===================================================");
//            j+=10;
//        }
//    }



    public Queue<Documents> getDocumentList() {
        return documentList;
    }

    public CopyOnWriteArrayList<Documents> getPrintedDoc() {
        return printedDoc;
    }

    public List<Documents> stopDispecher(){
        flag=!flag;
        List<Documents> list=new ArrayList<Documents>(documentList);
        System.out.println("Ненапечатаны следующие документы: ");
            //documentList.clear();
            return list;
    }
    public Documents addToPrint(Documents documents)  {
       documentList.add(documents);
       return documents;
            }

        public void printDoc() {
            first:
            while (!flag ) {
                    if (!flag && !documentList.isEmpty()) {
                        Documents documents = documentList.poll();
                        System.out.println("from first print: " + documents);
                        try {
                            Thread.sleep(documents.getTIME() * 0);
                            System.out.println("Документ напечатан " + documents);
                            printedDoc.add(documents);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break first;
                        } catch (NullPointerException ex) {
                            System.out.println("Pas de document dans la file d'attente");
                            break first;
                        }
                    }
                }
            }
    public String cancelPrint(Documents documents) {
        if (!documentList.isEmpty() && !flag) {

            documentList.remove(documents);

        } else {
            return  "La file d'attente est vide";
        }
        return "Les doc suivant sont non imprimes: "+documents;
    }
    public ArrayList<Documents> sortBy(String sort){
        ArrayList <Documents> arrayList=new  ArrayList<>();
            if(!(printedDoc.isEmpty())) {
            arrayList.addAll(printedDoc);
                if (sort.startsWith("sortByName")) {
                    arrayList.sort(new ComporatorByName());
                } else if (sort.startsWith("sortBySize")) {
                    arrayList.sort(new ComporatorBySize());
                } else if (sort.startsWith("sortByTime")) {
                    arrayList.sort(new ComporatorByTime());
                }else if(sort.startsWith("exit")) {
                    System.exit(0);
                }else {
                    System.out.println("Unknown command");
                }
            }else{
                System.out.println("Pas question de tri , так как нет напечатанных документов");
            }
            return arrayList;
    }
    public String avrTimePrint(){//return
            double avr=0;
        for(Documents documents: printedDoc){
            avr+=documents.getTIME();
        }
        return  "Среднее время печати: "+avr/printedDoc.size()+" секунд(а)";
    }
}
