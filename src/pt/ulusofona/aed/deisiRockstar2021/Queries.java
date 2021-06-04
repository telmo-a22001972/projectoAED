package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;
import static pt.ulusofona.aed.deisiRockstar2021.Main.*;

public class Queries {

    public static void menu(){
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis();
            String result = execute(line);
            long end = System.currentTimeMillis();
            System.out.println(result);
            System.out.println("(took " + (end - start) + " ms)\n");
            line = in.nextLine();

        }
        System.out.println("Adeus.");
    }


    public static String getSongsYear(int ano){

        Iterator<Song> listaDeMusicas = songsTxtFinal.iterator();
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

        for (int i = 0; i < songsTxtFinal.size(); i++) {
            if (songsTxtFinal.get(i).anoLancamento == ano) {
                songsXAno.add(songsTxtFinal.get(i));

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
