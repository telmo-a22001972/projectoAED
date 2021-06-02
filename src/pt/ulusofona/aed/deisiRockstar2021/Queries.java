package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

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

        String[] comando = command.split(" ");
        String accao = comando[0];
        int numero = Integer.parseInt(comando[1]);



        switch (accao){
            case "COUNT_SONGS_YEAR" :
                String numeroMusicas = getSongsYear(numero);
                return numeroMusicas;

            case "COUNT_DUPLICATE_SONGS_YEAR" :
                //VER O FICHEIRO TESTESTRING! !!!!!
                String musicasRepetidas = getDuplicateSongsYear(numero);
                return musicasRepetidas;

            case "GET_ARTISTS_FOR_TAG" :
                //USAR HAASHMAP OU HASHSET , NO ADD TAGS CRIAR UM ARRAYLIST COM OS ARTISTAS
                //E ATRIBUIR ESSE ARRAYLIST AO NOME DA TAG NUM HASHMAP OU HASHSET
                //NA GET ARTISTS FOR TAG -> FAZER UMA FUNÇÃO EM QUE DÁ RETURN NO ARRAYLIST DESSA TAG



            default:
               return "Illegal command. Try again";
        }

    }

    public static String getSongsYear(int ano){

        Iterator<Song> listaDeMusicas = Main.hashMapSongs.iterator();
        int count = 0;
        while (listaDeMusicas.hasNext()) {
            Song musica = listaDeMusicas.next();
            if (musica.anoLancamento == ano) {
                count++;
            }
        }
        return String.valueOf(count);

    }

    public static String getDuplicateSongsYear(int ano) {

        ArrayList< Song > songsXAno = new ArrayList < > ();

        int count = 0;

        for (int i = 0; i < Main.hashMapSongs.size(); i++) {
            if (Main.hashMapSongs.get(i).anoLancamento == ano) {
                songsXAno.add(Main.hashMapSongs.get(i));

            }
        }
        songsXAno.sort(Comparator.comparing((Song musica) -> musica.titulo));

        for (int primeiroLoop = 0; primeiroLoop < songsXAno.size(); primeiroLoop++) {
            if (primeiroLoop != 0 && songsXAno.get(primeiroLoop).titulo.equals(songsXAno.get(primeiroLoop - 1).titulo)) {
                continue;
            }else if (songsXAno.get(primeiroLoop).titulo.equals(songsXAno.get(primeiroLoop + 1).titulo)) {
                count++;
            }

        }


        return String.valueOf(count);
    }
}
