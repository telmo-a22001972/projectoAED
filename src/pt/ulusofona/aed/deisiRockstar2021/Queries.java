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
                }
                //Mete na BD
                artistasESuasTags.put(artista, tags);

                //                  A PARTIR DAQUI É O GET_ARTISTS_FOR_TAG - POR ISSO PODE BAZAR DAQUI
                //Primeira vez que se adiciona artistas a esta tag
                for (int contagem = 1; contagem <= nTagsDadas; contagem++) {
                    //Vou mer upperCase
                    if (!tagsESeusArtistas.containsKey(dados[contagem].toUpperCase())) {
                        //Adiciona à BD as tags com o artista
                        tagsESeusArtistas.put(dados[contagem].toUpperCase(), artistaParaTagPrimeiraVez);

                        //Não é a primeira vez que se mete artistas a esta tag
                    } else {
                        ArrayList<String> artistasDaTag = tagsESeusArtistas.get(dados[contagem].toUpperCase());
                        if (!artistasDaTag.contains(artista)) {
                            artistasDaTag.add(artista);
                        }
                        tagsESeusArtistas.put(dados[contagem].toUpperCase(), artistasDaTag);

                    }
                }
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
                if (!tagsExistentes.contains(dados[count].toUpperCase())){
                    //Senão adiciona aos arraylist
                    tagsExistentes.add(dados[count].toUpperCase());
                }

            }
            //Atualiza a BD agora com as tags adicionadas
            artistasESuasTags.put(artista, tagsExistentes);

            for (int contagem = 1; contagem <= nTagsDadas; contagem++) {
                if (!tagsESeusArtistas.containsKey(dados[contagem].toUpperCase())) {
                    //Adiciona à BD as tags com o artista
                    tagsESeusArtistas.put(dados[contagem].toUpperCase(), artistaParaTagPrimeiraVez);

                    //Não é a primeira vez que se mete artistas a esta tag
                } else {
                    ArrayList<String> artistasDaTag = tagsESeusArtistas.get(dados[contagem].toUpperCase());
                    if (!artistasDaTag.contains(artista)) {
                        artistasDaTag.add(artista);
                    }
                    tagsESeusArtistas.put(dados[contagem].toUpperCase(), artistasDaTag);

                }
            }
            //Faz a string <ARTISTA> | TAG | TAG
            return outputAddTags(artista);
        }
        return "Inexistent artist";
    }

    public static String outputAddTags(String artista){
        StringBuilder output = new StringBuilder(artista);
        ArrayList<String> tags = artistasESuasTags.get(artista);

        if (tags.size() == 0){
            return artista +" | No tags";
        }

        for (int i = 0; i < tags.size(); i++){
            if (i == 0) {
                output.append(" | ").append(tags.get(i));
            }else {
                output.append(",").append(tags.get(i));
            }
        }
        return output.toString();
    }

    public static String removerTags(String tagsEArtista) {
        String[] dados = tagsEArtista.split(";");
        String artista = dados[0];
        int nTagsDadas = dados.length-1;
        ArrayList<String> tagsDoArtista;
        ArrayList<String> artistasDaTag;

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

        for (int k = 1; k<=nTagsDadas; k++){
            if (tagsESeusArtistas.containsKey(dados[k].toUpperCase())) {
                artistasDaTag = tagsESeusArtistas.get(dados[k].toUpperCase());
                artistasDaTag.remove(artista);
                tagsESeusArtistas.put(dados[k].toUpperCase(), artistasDaTag);
            }
        }

        //Output

        return outputAddTags(artista);
    }


    public static String artistsForTag(String tag){

        if (tagsESeusArtistas.containsKey(tag.toUpperCase())) {
            if (tagsESeusArtistas.get(tag.toUpperCase()) != null) {
                ArrayList<String> artistas = tagsESeusArtistas.get(tag.toUpperCase());
                String output;
                if (!artistas.isEmpty()){
                    output = artistas.get(0);
                }else {
                    return "No results";
                }

                if (artistas.size() == 1) {
                    return output;
                }

                for (int i = 1; i < artistas.size(); i++){
                    output += ";"+ artistas.get(i);
                }
                return output;
            }else {
                return "No results";
            }
        }

        return "No results";

    }


    public static String getMostDanceable(int anoX, int anoY, int N) {
        StringBuilder dancabilidade = new StringBuilder();
        ArrayList<Song> listaDeMusicas = new ArrayList<>();
        int count;

        for (count = 0; count < songsTxtFinal.size(); count++) {
            if (songsTxtFinal.get(count).anoLancamento >= anoX && songsTxtFinal.get(count).anoLancamento <= anoY) {
                listaDeMusicas.add(songsTxtFinal.get(count));
            }
        }
        listaDeMusicas.sort(Comparator.comparing((Song musica) -> musica.dancabilidade).reversed());
        for (count = 0; count < N; count++) {
            dancabilidade.append(listaDeMusicas.get(count).toStringDancabilidade()).append("\n");
        }
        return dancabilidade.toString();
    }

    public static String getTopArtistsInBetween(String input) {
        String[] dados = input.split(" ");
        int numeroArtistas = Integer.parseInt(dados[0]);
        int A = Integer.parseInt(dados[1]);
        int B = Integer.parseInt(dados[2]);
        int count;


        ArrayList<Artista> artistas = new ArrayList<>();
        ArrayList<String> nomesArtistas = new ArrayList<>();

        for (count = 0;count < songsTxtFinal.size(); count++) {
            Song musica = songsTxtFinal.get(count);
            if (musica.temArtistas) {
                for (int i = 0; i < musica.artistas.length; i++) {
                    if (musica.artistas[i] != null) {
                        if (musica.artistas[i].numeroDeMusicas >= A && musica.artistas[i].numeroDeMusicas <= B) {
                            if (!nomesArtistas.contains(musica.artistas[i].nome)) {
                                nomesArtistas.add(musica.artistas[i].nome);
                                artistas.add(musica.artistas[i]);
                            }
                        }
                    }
                }
            }
        }
        if (artistas.size() == 0){
            return "No results";
        }
        artistas.sort(Comparator.comparing((Artista artista) -> artista.numeroDeMusicas).reversed());

        if (artistas.size()-1 < numeroArtistas){
            StringBuilder output = new StringBuilder();
            for (int k = 0; k < artistas.size(); k++){
                output.append(artistas.get(k).nome).append(" ").append(artistas.get(k).numeroDeMusicas).append("\n");
            }
            return output.toString();
        }
        StringBuilder output2 = new StringBuilder();
        for (int k = 0; k < numeroArtistas; k++){

            output2.append(artistas.get(k).nome).append(" ").append(artistas.get(k).numeroDeMusicas).append("\n");
        }
        return output2.toString();

    }


    public static String getArtistsOneSong(String input){
        String[] dados = input.split(" ");
        int anoIni = Integer.parseInt(dados[0]);
        int anoFim = Integer.parseInt(dados[1]);
        StringBuilder output = new StringBuilder();

        ArrayList<String> nomesArtistas = new ArrayList<>();
        HashMap<String, Song> artistaEMusica = new HashMap<>();
        HashMap<String, Song> artistsEVariasMusicas = new HashMap<>();

        if (anoIni>=anoFim){
            return "Invalid period";
        }

        for (Song musica : songsTxtFinal) {

            if (musica.anoLancamento >= anoIni && musica.anoLancamento <= anoFim) {

                if (musica.temArtistas) {

                    for (int i = 0; i < musica.artistas.length; i++) {
                        if (musica.artistas[i] != null) {
                            if (!artistsEVariasMusicas.containsKey(musica.artistas[i].nome)) {
                                artistsEVariasMusicas.put(musica.artistas[i].nome, musica);
                                artistaEMusica.put(musica.artistas[i].nome, musica);
                                nomesArtistas.add(musica.artistas[i].nome);
                            } else {
                                artistaEMusica.remove(musica.artistas[i].nome);
                                nomesArtistas.remove(musica.artistas[i].nome);
                            }
                        }


                    }
                }
            }
        }

        if (nomesArtistas.size() != 0){
            Collections.sort(nomesArtistas);
            for (int i = 0; i < nomesArtistas.size(); i++){
                output.append(nomesArtistas.get(i)).append(" | ").append(artistaEMusica.get(nomesArtistas.get(i)).titulo).append(" | ").append(artistaEMusica.get(nomesArtistas.get(i)).anoLancamento).append("\n");
            }
        }
        return output.toString();
    }

    public static String getYearHighDurationMusic(String input){
        String[] dados = input.split(" ");
        int ano = Integer.parseInt(dados[0]);
        int duracao = Integer.parseInt(dados[1]);
        ArrayList<Song> musicasPedidas = new ArrayList<>();

        for (Song musica : songsTxtFinal){
            if (musica.anoLancamento == ano && (musica.duracaoDoTema/1000) / 60 >= duracao)
                musicasPedidas.add(musica);
        }

        if (musicasPedidas.size() == 0){
            return "No results";
        }
        musicasPedidas.sort(Comparator.comparing((Song musica) -> musica.duracaoDoTema).reversed());

        StringBuilder output = new StringBuilder();
        for (int k = 0; k < musicasPedidas.size(); k++){
            String duracaoOuPut = String.valueOf((musicasPedidas.get(k).duracaoDoTema /1000)/60);
            duracaoOuPut += ":"+ ((musicasPedidas.get(k).duracaoDoTema /1000)%60);
            output.append(musicasPedidas.get(k).titulo).append(" | ").append(duracaoOuPut).append("\n");
        }
        return output.toString();


    }

    public static String getUniqueTags(){
        StringBuilder output = new StringBuilder();
        for (String tag : tagsESeusArtistas.keySet()){

        }
        return "";
    }

    public static String cleanUp(){
        int countMusicas = 0;
        int countArtistas = 0;
        for(int count = 0;count < songsTxtFinal.size(); count++){
            Song musica = songsTxtFinal.get(count);
            Artista artista2 = songArtistsFinal.get(count);
            if (!musica.temArtistas){
                for (int i = 0; i < musica.artistas.length; i++){
                    musica.artistas[i].numeroDeMusicas--;
                }
                songsTxtFinal.remove(musica);
                countMusicas++;
            }
            if (!musica.detalhesAdicionados){
                for (int i = 0; i < musica.artistas.length; i++){
                    musica.artistas[i].numeroDeMusicas--;
                }
                songsTxtFinal.remove(musica);
                countMusicas++;
            }
        }
        for(int count = 0;count < songArtistsFinal.size(); count++){
            Artista artista = songArtistsFinal.get(count);
            if (artista.numeroDeMusicas == 0){
                songArtistsFinal.remove(artista);
                hashSetComTodosOsArtistas.remove(artista.nome);
                countArtistas++;
            }
        }
        return "Musicas removidas: " + String.valueOf(countMusicas) + "\n" + "Artistas removidos: " + String.valueOf(countArtistas) + "\n";
    }
}
