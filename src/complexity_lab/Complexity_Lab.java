/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity_lab;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jesus David
 */
public class Complexity_Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite n");
        int m = s.nextInt();
        System.out.println("Digite número de campos");
        int n = s.nextInt();
        int i = 0;
        BigInteger[][] mat = new BigInteger[m][n];
        BigInteger big;
        mat[i][0] = GenRandom("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999", "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        System.out.println(mat[i][0]);
        i += 1;
        while (i < m) {
            big = GenRandom("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999", "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
            if (!Exists(mat, big, i)) {
                mat[i][0] = big;
                System.out.println(mat[i][0]);
                i += 1;
            }
        }

    }

    public static BigInteger GenRandom(String up, String low) {
        BigInteger bigInteger = new BigInteger(up);// uper limit
        BigInteger min = new BigInteger(low);// lower limit
        BigInteger bigInteger1 = bigInteger.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = bigInteger.bitLength();
        BigInteger aRandomBigInt;
        aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
        if (aRandomBigInt.compareTo(min) < 0) {
            aRandomBigInt = aRandomBigInt.add(min);
        }
        if (aRandomBigInt.compareTo(bigInteger) >= 0) {
            aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
        }

        return aRandomBigInt;

    }

    public static boolean Exists(BigInteger[][] mat, BigInteger num, int i) {
        boolean sw = false;
        for (int j = 0; j < i; j++) {
            if (mat[j][0].compareTo(num) == 0) {
                sw = true;
            }
        }
        return sw;
    }

}
