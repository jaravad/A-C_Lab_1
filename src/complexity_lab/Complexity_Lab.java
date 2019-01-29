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
        BigInteger bigInteger = new BigInteger("99999999999999999999999999999999999999");// uper limit
        BigInteger min = new BigInteger("10000000000000000000000000000000000000");// lower limit
        BigInteger bigInteger1 = bigInteger.subtract(min);
        Random rnd = new Random();
        int maxNumBitLength = bigInteger.bitLength();

        BigInteger aRandomBigInt;
        Scanner s = new Scanner(System.in);
        System.out.println("Digite n");
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
            if (aRandomBigInt.compareTo(min) < 0) {
                aRandomBigInt = aRandomBigInt.add(min);
            }
            if (aRandomBigInt.compareTo(bigInteger) >= 0) {
                aRandomBigInt = aRandomBigInt.mod(bigInteger1).add(min);
            }

            System.out.println(aRandomBigInt);
        }
    }

}
