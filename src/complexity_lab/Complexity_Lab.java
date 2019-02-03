/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity_lab;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
        System.out.println("Digite número de registros");
        int m = s.nextInt();
        System.out.println("Digite número de campos (adicionales al campo llave)");
        int n = s.nextInt();
        int i = 0, num;
        Object[][] mat = new Object[m][n + 1];

        BigInteger big, ten = BigInteger.TEN, one = BigInteger.ONE;

        for (int j = 1; j <= n; j++) {
            System.out.println("➥ Campo " + j);
            System.out.println("↳ Digite tipo de campo; 0 = Numérico, 1 = Alfanumérico");
            int type = s.nextInt();
            System.out.println("↳ Digite longitud");
            int len = s.nextInt();
            switch (type) {
                case 0:
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = GenRandom(String.valueOf(one.multiply(ten.pow(len)).subtract(one)), String.valueOf(one.multiply(ten.pow(len - 1))));
                    }

                    break;
                case 1:  ;
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = generateRandomText(len).substring(0, len);
                    }

                    break;
                default:
                    System.out.println("Tipo inválido, se tomará como numérico");
                    type = 0;
                    for (int k = 0; k < m; k++) {
                        mat[k][j] = GenRandom(String.valueOf(one.multiply(ten.pow(len)).subtract(one)), String.valueOf(one.multiply(ten.pow(len - 1))));
                    }

                    break;
            }
        }

        mat[i][0] = GenRandom(String.valueOf(one.multiply(ten.pow(50)).subtract(one)), String.valueOf(one.multiply(ten.pow(49))));

        i += 1;
        while (i < m) { //Direccionamiento de llaves
            big = GenRandom(String.valueOf(one.multiply(ten.pow(50)).subtract(one)), String.valueOf(one.multiply(ten.pow(49))));
            if (!Exists(mat, big, i)) {
                mat[i][0] = big;
                i += 1;
            }
        }
        ShowMatrix(mat);

        Sort(mat, m, n);
        System.out.println("Matriz Ordenada");
        ShowMatrix(mat);

        boolean sw = false;
        while (sw == false) {
            System.out.println("Digite número de campo para buscar (0 = Campo clave)");
            num = s.nextInt();
            if (num <= n) {

                System.out.println("Digite dato a buscar");
                Scanner ss = new Scanner(System.in);
                String dat = ss.nextLine();
                if (Search(dat, mat, num)) {
                    sw = true;
                }

            } else {
                System.out.println("☹ Campo inválido, intente de nuevo");;
            }

        }
        sw = false;
        while (sw == false) {
            System.out.println("Digite número de campo para obtener valor máximo o mínimo");
            num = s.nextInt();
            System.out.println("Digite que desea obtener; 0 = valor mínimo, 1 = valor máximo");
            int op = s.nextInt();
            if (num <= n && op < 2) {
                sw = true;
                switch (op) {
                    case 0:
                        MinValue(mat, num);
                        break;
                    case 1:
                        MaxValue(mat, num);
                }
            } else if (num > n) {
                System.out.println("☹ Campo inválido, intente de nuevo");
            } else if (op >= 2) {
                System.out.println("☹ Opción inválida");
            }
        }

        sw = false;
        while (sw == false) {
            System.out.println("Digite número de campo (numérico) para obtener promedio");
            num = s.nextInt();
            if (num <= n) {
                try {
                    Average(mat, num);
                    sw = true;
                } catch (NumberFormatException e) {
                    System.out.println("☹ El campo digitado no es numérico");
                }

            } else {
                System.out.println("☹ Campo inválido, intente de nuevo");;
            }
        }
        
        sw = false;
        while(sw == false){
            System.out.println("Digite número de campo para obtener moda");
            num = s.nextInt();
            if (num <= n) {
                Moda(mat, num);
                sw = true;
            } else {
                System.out.println("☹ Campo inválido, intente de nuevo");;
            }
        }

    }

    public static boolean Search(String dat, Object[][] mat, int num) {

        boolean sw = false;
        for (int j = 0; j < mat.length; j++) {
            if (dat.equals(String.valueOf(mat[j][num]))) {
                sw = true;
                if (num == 0) {
                    System.out.println("«Llave encontrada»");
                } else {
                    System.out.println("Dato encontrado en el registro: " + mat[j][0]);
                }
                System.out.println("⇝Registro: ");
                ShowRow(mat, j);
                break;
            }
        }
        if (sw == false) {
            System.out.println("☹ Dato no encontrado, intente de nuevo");

        }
        return sw;

    }

    public static void Sort(Object[][] mat, int m, int n) {
        Object aux;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                BigInteger a = new BigInteger(String.valueOf(mat[i][0]));
                BigInteger b = new BigInteger(String.valueOf(mat[j][0]));
                if (a.compareTo(b) > 0) {
                    for (int k = 0; k <= n; k++) {
                        aux = mat[i][k];
                        mat[i][k] = mat[j][k];
                        mat[j][k] = aux;
                    }
                }
            }
        }

    }

    public static void ShowMatrix(Object[][] mat) {
        for (int t = 0; t < mat.length; t++) {
            ShowRow(mat, t);
        }
    }

    public static void ShowRow(Object[][] mat, int i) {
        System.out.print("」");
        for (int j = 0; j < mat[0].length; j++) {
            System.out.print(mat[i][j] + " ┊ ");
        }
        System.out.println("");
    }

    public static String generateRandomText(int len) {
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(len * 6, random).toString(32);
        return text;
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

    public static int Exists(Object[] vec, Object dat) {
        
        int index = -1, i = 0;
        for (Object x : vec) {
            if (String.valueOf(x).equals(String.valueOf(dat))) {
                index = i;
                return index;
            }
            i += 1;
            
        }
        
        return index;
    }

    public static void MaxValue(Object[][] mat, int n) {
        try {

            BigInteger max = new BigInteger(String.valueOf(mat[0][n]));
            for (int i = 1; i < mat.length; i++) {
                BigInteger b = new BigInteger(String.valueOf(mat[i][n]));
                if (b.compareTo(max) > 0) {
                    max = b;
                }
            }
            System.out.println("Valor máximo del campo " + n + ": " + max);
        } catch (NumberFormatException e) {
            List values = new LinkedList<>();
            for (int i = 0; i < mat.length; i++) {
                values.add(mat[i][n]);
            }
            Collections.sort(values);
            System.out.println("Valor máximo del campo " + n + ": " + values.get(mat.length - 1));
        }
    }

    public static void MinValue(Object[][] mat, int n) {
        try {

            BigInteger min = new BigInteger(String.valueOf(mat[0][n]));
            for (int i = 1; i < mat.length; i++) {
                BigInteger b = new BigInteger(String.valueOf(mat[i][n]));
                if (b.compareTo(min) < 0) {
                    min = b;
                }
            }
            System.out.println("Valor mínimo del campo " + n + ": " + min);
        } catch (NumberFormatException e) {
            List values = new LinkedList<>();
            for (int i = 0; i < mat.length; i++) {
                values.add(mat[i][n]);
            }
            Collections.sort(values);
            System.out.println("Valor máximo del campo " + n + ": " + values.get(0));
        }
    }

    public static void Moda(Object[][] mat, int n) {
        int j = 0;
        Object[] table = new Object[mat.length];
        int[] frec = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            if (i == 0) {
                table[j] = mat[i][n];
                frec[j] += 1;
                j+=1;
            } else {
                if (Exists(table, mat[i][n]) != -1) {
                    frec[Exists(table, mat[i][n])] += 1;
                } else{
                    table[j] = mat[i][n];
                    frec[j] += 1;
                    j+=1;
                }
            }
        }
        int max = frec[0];
        int index = 0, i = 0;
        for (int x : frec) {
            if (x > max) {
                max = x;
                i=index;
            }
            index+=1;
        }
        if (max!=1) {
            System.out.println("La moda del campo " +n+ " es el dato: "+table[i]+ ", se repite "+frec[i]+ " veces");
        }else{
            System.out.println("No hay moda");
        }
            
    }

    private static void Average(Object[][] mat, int num) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < mat.length; i++) {
            sum = sum.add(new BigInteger(String.valueOf(mat[i][num])));
        }
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        System.out.println("Promedio: " + numberFormat.format(sum.doubleValue() / new BigInteger(String.valueOf(mat.length)).doubleValue()));

    }

}
