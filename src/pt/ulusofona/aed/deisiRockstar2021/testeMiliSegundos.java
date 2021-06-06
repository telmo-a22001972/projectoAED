package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.HashMap;

public class testeMiliSegundos {
    public static void main(String[] args) {

        ArrayList<Integer> testeArray = new ArrayList<Integer>();
        HashMap<String, Integer> testeHashMap = new HashMap<String, Integer>();
        Song testeMusica = new Song("teste", "teste",2000);
        testeHashMap.put("teste", 20);
        testeHashMap.put("teste2", 30);
        testeHashMap.put("teste3", 40);
        testeHashMap.put("teste4", 50);

        System.out.println(testeHashMap.toString());
        testeArray = new ArrayList<>(testeHashMap.values());
        System.out.println(testeArray.toString());

    }
}
