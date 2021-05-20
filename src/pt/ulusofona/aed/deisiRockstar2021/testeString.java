package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class testeString {
    public static void main(String[] args) {
        ArrayList<String> testeSort = new ArrayList<>();
        testeSort.add(new String("Andora"));
        testeSort.add(new String("Portugal"));
        testeSort.add(new String("Basdasd"));
        testeSort.add(new String("Casdasd"));
        testeSort.add(new String("Hadsdasd"));
        testeSort.add(new String("AAdasdsd"));
        testeSort.add(new String("AAdasdsd"));
        testeSort.add(new String("Zasdasdsad"));
        System.out.println(testeSort+"\n");

        Collections.sort(testeSort);
        System.out.println(testeSort);

    }

}
