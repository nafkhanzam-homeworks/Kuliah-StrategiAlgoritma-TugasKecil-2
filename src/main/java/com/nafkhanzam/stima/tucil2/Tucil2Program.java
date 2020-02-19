package com.nafkhanzam.stima.tucil2;

import com.nafkhanzam.stima.tucil2.multiply.BasePolynomMultiply;
import com.nafkhanzam.stima.tucil2.multiply.PolynomMultiplyBF;
import com.nafkhanzam.stima.tucil2.multiply.PolynomMultiplyDNC;
import com.nafkhanzam.utils.Console;
import com.nafkhanzam.utils.RandomUtils;

public class Tucil2Program {

    private static Polynom a = new Polynom(), b = new Polynom();

    public static void main(String[] args) {
        int menu = 0;
        printMenu();
        while (true) {
            try {
                Console.outln();
                Console.out("Pilihan menu: ");
                menu = Console.num();
                Console.outln();
            } catch (Exception e) {
                notValid();
                continue;
            }
            switch (menu) {
                case 0:
                    printMenu();
                    break;
                case 1:
                case 2:
                    Console.out((char) ('A' + menu - 1) + " = ");
                    try {
                        String str = Console.line();
                        Polynom p;
                        if (str.toLowerCase().startsWith("r")) {
                            p = getRandomPolynom(Integer.parseInt(str.substring(1)));
                        } else {
                            p = new Polynom(str);
                        }
                        Console.outln();
                        if (menu == 1) {
                            a = p;
                        } else {
                            b = p;
                        }
                        Console.outln("Berhasil diubah!");
                        printPolynoms();
                    } catch (Exception e) {
                        notValid();
                        continue;
                    }
                    break;
                case 3:
                case 4:
                    BasePolynomMultiply multiplier = menu == 3 ? new PolynomMultiplyBF(a, b)
                            : new PolynomMultiplyDNC(a, b);
                    long time = System.nanoTime();
                    Polynom res = multiplier.multiply();
                    time = System.nanoTime() - time;
                    Console.outln("A*B = " + res);
                    String satuan = "nanoseconds";
                    if (time >= 1000000) {
                        time /= 1000000;
                        satuan = "milliseconds";
                    }
                    if (time >= 1000) {
                        time /= 1000;
                        satuan = "seconds";
                    }
                    Console.outln("Time elapsed: " + time + " " + satuan);
                    break;
                case 5:
                    System.exit(0);
                default:
                    notValid();
                    break;
            }
        }
    }

    private static Polynom getRandomPolynom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = RandomUtils.random(-1000, 1000);
        }
        return new Polynom(arr);
    }

    private static void notValid() {
        Console.outln("Tidak valid!");
    }

    private static void printPolynoms() {
        Console.outln("Persamaan polynom saat ini:");
        Console.outln("A = " + a);
        Console.outln("B = " + b);
    }

    private static void printMenu() {
        printPolynoms();
        Console.outln("Pilih menu di bawah ini!");
        Console.outln("[0] Tunjukkan menu ulang!");
        Console.outln("[1] Ubah persamaan polynom A");
        Console.outln("[2] Ubah persamaan polynom B");
        Console.outln("[3] Hitung A*B alg. Brute Force");
        Console.outln("[4] Hitung A*B alg. Divide and Conquer");
        Console.outln("[5] Keluar dari program");
    }
}
