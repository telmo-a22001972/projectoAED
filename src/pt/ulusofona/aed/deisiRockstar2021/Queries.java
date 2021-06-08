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
            System.out.println(tagsESeusArtistas.toString());
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

        //Mudei para songsXAno.size()-1 pois no else if vai comparar a última música com nada e da crash
        for (int loop = 0; loop < songsXAno.size()-1; loop++) {
            //if (loop != 0 && songsXAno.get(loop).titulo.equals(songsXAno.get(loop - 1).titulo)) {

            //}else
            if (songsXAno.get(loop).titulo.equals(songsXAno.get(loop + 1).titulo)) {
                count++;
            }

        }


        return String.valueOf(count);
    }

    public static String addTags(String tagsEArtista) {
        //Posição 0 é o artista
        //O resto são as tags
        String[] dados = tagsEArtista.split(";");
        String artista = dados[0];
        int nTagsDadas = dados.length-1, nTagsTotais;
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> artistaParaTagPrimeiraVez = new ArrayList<>();
        artistaParaTagPrimeiraVez.add(artista);

        //Primeira vez que se adiciona tags a este artista
        if (!artistasESuasTags.containsKey(artista)) {
            //Vê se existe artista
            if (hashSetComTodosOsArtistas.contains(artista)) {
                //Adiciona tags dadas no arrayList que se criou novo
                for (int count = 1; count <= nTagsDadas; count++) {
                    tags.add(dados[count].toUpperCase());

                    //                  A PARTIR DAQUI É O GET_ARTISTS_FOR_TAG - POR ISSO PODE BAZAR DAQUI
                    //Primeira vez que se adiciona artistas a esta tag
                    if (!tagsESeusArtistas.containsKey(dados[count])){
                        //Adiciona à BD as tags com o artista
                        tagsESeusArtistas.put(dados[count], artistaParaTagPrimeiraVez);

                    //Não é a primeira vez que se mete artistas a esta tag
                    }else {
                        ArrayList<String> artistasDaTag = new ArrayList<>();
                        artistasDaTag = tagsESeusArtistas.get(dados[count]);
                        if (!artistasDaTag.contains(artista)){
                            artistasDaTag.add(artista);
                        }
                        tagsESeusArtistas.put(dados[count],artistasDaTag);
                        artistasDaTag.clear();
                    }
                }
                //Mete na BD
                artistasESuasTags.put(artista, tags);

                //Vai fazer a string <ARTISTA> | TAG | TAG
                return outputAddTags(artista);
            }
        //Não é a primeira vez que se adiciona tags ao artista
        //Ou seja já existe na BD então já tem outras tags
        }else {
            //Vai buscar as tags que o artista já tem
            ArrayList<String> tagsExistentes = artistasESuasTags.get(artista);
            ArrayList<String> artistaParaTagSegundaVez = new ArrayList<>();
            artistaParaTagSegundaVez.add(artista);
            for (int count = 1; count <= nTagsDadas; count++){
                //Como não pode ter tags repetidas
                //Vai verificar se ela já foram adicionadas
                if (!tagsExistentes.contains(dados[count])){
                    //Senão adiciona aos arraylist
                    tagsExistentes.add(dados[count].toUpperCase());
                }

                if (!tagsESeusArtistas.containsKey(dados[count])){
                    tagsESeusArtistas.put(dados[count], artistaParaTagSegundaVez);
                }else {
                    ArrayList<String> artistasDaTag = tagsESeusArtistas.get(dados[count]);
                    if (!artistasDaTag.contains(artista)){
                        artistasDaTag.add(artista);
                    }
                    tagsESeusArtistas.put(dados[count],artistasDaTag);
                    artistasDaTag.clear();
                }
            }
            //Atualiza a BD agora com as tags adicionadas
            artistasESuasTags.put(artista, tagsExistentes);

            //Faz a string <ARTISTA> | TAG | TAG
            return outputAddTags(artista);
        }
        return "Inexistent artist";
    }

    public static String outputAddTags(String artista){
        String output = artista;
        ArrayList<String> tags = artistasESuasTags.get(artista);

        if (tags.size() == 0){
            return artista +" | No tags";
        }

        for (int i = 0; i < tags.size(); i++){
            output += " | "+tags.get(i);
        }
        return output;
    }

    public static String removerTags(String tagsEArtista) {
        String[] dados = tagsEArtista.split(";");
        String artista = dados[0];
        int nTagsDadas = dados.length-1;
        ArrayList<String> tagsDoArtista;

        //Caso não exista o artista
        if (!artistasESuasTags.containsKey(artista)){
            return "Inexistent artist";
        }

        //Copia as tags do artista
        tagsDoArtista = artistasESuasTags.get(artista);
        //Remove as tags pedidas
        for (int i = 1; i<=nTagsDadas; i++){
            tagsDoArtista.remove(dados[i].toUpperCase());
        }
        //Atualiza o artista
        artistasESuasTags.put(artista,tagsDoArtista);

        //Output
        return outputAddTags(artista);
    }

    public static String artistsForTag(String tag){

        if (tagsESeusArtistas.containsKey(tag)) {
            if (tagsESeusArtistas.get(tag) != null) {
                ArrayList<String> artistas = tagsESeusArtistas.get(tag);
                String output = artistas.get(0);

                if (artistas.size() == 1) {
                    return output;
                }

                for (int i = 0; i <= artistas.size(); i++){
                    output += ";"+ artistas.get(i);
                }
                return output;
            }else {
                return "No results";
            }
        }

        return "No results";

    }
}
