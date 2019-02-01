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
        Object[][] mat = new Object[m][n];
        BigInteger big, ten = BigInteger.TEN, one = BigInteger.ONE;
        for (int j = 0; j < n; j++) {
            System.out.println("Campo " + j);
            System.out.println("Digite tipo de campo; 0 = Numérico, 1 = Alfanumérico");
            int type = s.nextInt();
            switch (type) {
                case 0:
                    for (int k = 0; k < m; k++) {

                        mat[k][j] = GenRandom(String.valueOf(one.multiply(ten.pow(5)).subtract(one)), String.valueOf(one.multiply(ten.pow(4))));

                    }
                    ;
                    break;
                case 1:  ;
                    
                    break;
                default:
                    System.out.println("Tipo inválido");
                    ;
                    break;
            }
        }

        //System.out.println(generatePswd(50));
        mat[i][0] = GenRandom(String.valueOf(one.multiply(ten.pow(5)).subtract(one)), String.valueOf(one.multiply(ten.pow(4))));
        System.out.println(mat[i][0] + " " + mat[i][1]);
        i += 1;
        while (i < m) {
            big = GenRandom(String.valueOf(one.multiply(ten.pow(5)).subtract(one)), String.valueOf(one.multiply(ten.pow(4))));
            if (!Exists(mat, big, i)) {
                mat[i][0] = big;
                System.out.println(mat[i][0] + " " + mat[i][1]);
                i += 1;
            }
        }

    }

    static char[] generatePswd(int len) {
        String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Chars = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String symbols = "!@#$%^&*()_+-=.,/';:?><~*/-+";
        String passSymbols = charsCaps + Chars + nums + symbols;
        Random rnd = new Random();
        char[] password = new char[len];

        for (int i = 0; i < len; i++) {
            password[i] = passSymbols.charAt(rnd.nextInt(passSymbols.length()));
        }
        return password;

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

    public static boolean Exists(Object[][] mat, BigInteger num, int i) {
        boolean sw = false;
        for (int j = 0; j < i; j++) {
            BigInteger aux = new BigInteger(String.valueOf(mat[j][0]));
            if (aux.compareTo(num) == 0) {
                sw = true;
            }
        }
        return sw;
    }

}
