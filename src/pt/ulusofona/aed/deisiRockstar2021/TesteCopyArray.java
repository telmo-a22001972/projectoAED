package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class TesteCopyArray {
    public static void main(String[] args) {

        String teste = "     0nxvFG50rGXkiGQqOO2MHr @ Be Alright @ 2012";


        String testSplit[] = teste.split("@");
        System.out.println(Arrays.toString(testSplit));
        String testeArray2[] = new String[3];
        for (int count = 0; count < testSplit.length; count++) {
            testeArray2[count] = testSplit[count].trim();
        }
        System.out.println(Arrays.toString(testeArray2));






    }
}
