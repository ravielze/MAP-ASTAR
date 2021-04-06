package me.ravielze;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nama File Graf: ");
        String fileName = sc.next();
        try {
            Reader reader = new Reader(fileName);
            AStar aStar = new AStar(reader.getResult());
            System.out.print("Nama Node Awal: ");
            String start = sc.next();
            System.out.print("Nama Node Akhir: ");
            String end = sc.next();
            String result = aStar.run(start, end);
            System.out.print(result);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Terjadi kesalahan format pada file.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalStateException ex) {
            System.out.println("Unknown error has occured.");
        }

        sc.close();
    }
}
