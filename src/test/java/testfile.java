import java.io.*;
import java.util.Scanner;

public class testfile {

    public static void main(String[] args) throws Exception {
        int k1 = 2;
        int k2 = 7;
        newFile( k1, k2);
        newFile2();
        filereader();
    }


    public static void newFile(int k1, int k2) throws Exception {
        FileWriter nFile = new FileWriter("file1.txt");

        for(int i = k1; i <= k2; i++) {

            nFile.write(String.valueOf(i)+"\n");

        }

        nFile.close();
    }
    public static void newFile2 () throws Exception {

        FileWriter nFile = new FileWriter("file2.txt");

        nFile.write("Хокку \nПодобен лучу самурайский клинок \nИ тот затупился \nПроклятая килька в томате!!");

        nFile.close();
    }

    public static void filereader () throws Exception {

        FileReader fr = new FileReader("file2.txt");
        Scanner scan = new Scanner(fr);

        int i = 1;

        while (scan.hasNextLine()) {
            System.out.println(i + "= " + scan.nextLine());
            i++;
        }


        fr.close();
    }
}
