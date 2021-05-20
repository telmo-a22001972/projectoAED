package pt.ulusofona.aed.deisiRockstar2021;

import java.util.Iterator;
import java.util.Scanner;

public class Queries {

    public static void menu(){
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis();
            String result = Queries.execute(line);
            long end = System.currentTimeMillis();
            System.out.println(result);
            System.out.println("(took " + (end - start) + " ms)\n");
            line = in.nextLine();

        }
        System.out.println("Adeus.");
    }

    public static String execute(String command) {

        Scanner separador = new Scanner(command);
        String comando = separador.next();


        switch (comando){
            case "COUNT_SONGS_YEAR" :
                String numeroMusicas = getSongsYear(command);
                return numeroMusicas;

            case "COUNT_DUPLICATE_SONGS_YEAR" :
                //VER O FICHEIRO TESTESTRING! !!!!!
                String musicasRepetidas = getDuplicateSongsYear(command);
                return musicasRepetidas;

            case "GET_ARTISTS_FOR_TAG" :
                //USAR HAASHMAP OU HASHSET , NO ADD TAGS CRIAR UM ARRAYLIST COM OS ARTISTAS
                //E ATRIBUIR ESSE ARRAYLIST AO NOME DA TAG NUM HASHMAP OU HASHSET
                //NA GET ARTISTS FOR TAG -> FAZER UMA FUNÇÃO EM QUE DÁ RETURN NO ARRAYLIST DESSA TAG



            default:
               return "Illegal command. Try again";
        }

    }

    public static String getSongsYear(String comando){

        Scanner separador = new Scanner(comando);
        String lixo = separador.next();
        int ano = separador.nextInt();

        Iterator<Song> listaDeMusicas = Main.songsArrayFinal.iterator();
        int count = 0;
        while (listaDeMusicas.hasNext()) {
            Song musica = listaDeMusicas.next();
            if (musica.anoLancamento == ano) {
                count++;
            }
        }
        return String.valueOf(count);

    }

    public static String getDuplicateSongsYear(String comando) {
        Scanner separador = new Scanner(comando);
        String lixo = separador.next();
        int ano = separador.nextInt(), count = 0;


        return String.valueOf(count);
    }
}
