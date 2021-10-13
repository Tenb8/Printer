
import javax.print.Doc;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        printDispecher dispecher = new printDispecher();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(1);
//            Thread createDoc = new Thread() {
//                @Override
//                public void run() {
//                        while (!isInterrupted()) {
//                            int num = random.nextInt(20);
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//                                break;
//                            }
//                            dispecher.createDocs(num);
//
//                    }
//                }
//            };
        for(int i=0;i<1; i++) {
            Thread AddToPrintDoc = new Thread() {
                @Override
                public void run() {
                    first:
                    while (!printDispecher.flag) {
                        int num = random.nextInt(5);
                        for (int i = 1; i < num; i++) {
                            System.out.println("Создан документ: " + dispecher.addToPrint(new Acts("Act " + i, " А4")));
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                // break first;
//                            }
                            System.out.println("Создан документ: " + dispecher.addToPrint(new Orders("Order " + i, "A5")));
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                //break first;
//                            }
                            System.out.println("Создан документ: " + dispecher.addToPrint(new AnyDoc("Doc" + i, "A3")));
                        }
                        try {

                        Thread.sleep(5);
                        }catch (InterruptedException ex){

                        }
                    }
                }
            };
            Thread printDoc = new Thread() {
                @Override
                public void run() {
                    while (!printDispecher.flag) {
                        try {
                            Thread.sleep(10);
                        }catch (InterruptedException e){
                        }
                        dispecher.printDoc();
                    }
                }

            };
            Thread cancelPrint = new Thread() {
                @Override
                public void run() {
                    while (!printDispecher.flag) {
                        int num = random.nextInt(dispecher.getDocumentList().size());
                        Documents documents = (Documents) dispecher.getDocumentList().toArray()[num];
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                        }
                        System.out.println(dispecher.cancelPrint(documents));

                    }
                }
            };
            if (!printDispecher.flag) {
                AddToPrintDoc.start();
                Thread.sleep(1000);
                printDoc.start();
                Thread.sleep(1000);
                cancelPrint.start();
            }
        }
            Thread stopDis = new Thread() {
                @Override
                public void run() {
                    System.out.println("Остановка диспечера печати: ");
                    for (Documents documents : dispecher.stopDispecher())
                        System.out.println(documents);
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {

                    }
                }
            };
            stopDis.start();
            Thread.sleep(1000);
            System.out.println(dispecher.avrTimePrint());

            System.out.println("Можете вывести список напечатанных документов\n" +
                    "Для этого используйте следующие команды\n" +
                    "sortBySize\n" +
                    "sortByTime\n" +
                    "sortByName\n");
            while (true) {
                String command = sc.nextLine();
               for(Documents documents: dispecher.sortBy(command)){
                   System.out.println(documents);
               }
        }

    }
}
