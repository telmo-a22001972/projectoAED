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

        for (int loop = 0; loop < songsXAno.size()-1; loop++) {
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
            dancabilidade.append(listaDeMusicas.get(count).toStringDancabilidade());
            if (count != N-1){
                dancabilidade.append("\n");
            }
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

        if (artistas.size() < numeroArtistas){
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
                output.append(nomesArtistas.get(i)).append(" | ").append(artistaEMusica.get(nomesArtistas.get(i)).titulo).append(" | ").append(artistaEMusica.get(nomesArtistas.get(i)).anoLancamento);
                if (i != nomesArtistas.size()-1){
                    output.append("\n");
                }
                nLinhas++;
            }
        }
        return output.toString();
    }

    public static String getYearHighestDurationMusic(String input){

        String[] dados = input.split(" ");
        int ano = Integer.parseInt(dados[0]);
        int duracao = Integer.parseInt(dados[1]);
        ArrayList<Song> musicasPedidas = new ArrayList<>();

        for (Song musica : songsTxtFinal){
            if (musica.anoLancamento == ano && (musica.duracaoDoTema/1000) / 60 >= duracao) {
                musicasPedidas.add(musica);
            }
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
        ArrayList<Tag> tags = new ArrayList<>();
        for (String tag : tagsESeusArtistas.keySet()){
            int tamanhoArray = tagsESeusArtistas.get(tag).size();
            tags.add(new Tag(tag,tamanhoArray));
        }
        tags.sort(Comparator.comparing((Tag tag) -> tag.numeroVezesUsada));
        for (int count = 0; count < tags.size(); count++){

            output.append(tags.get(count).tag).append(" ").append(tags.get(count).numeroVezesUsada).append("\n");

        }
        return output.toString();
    }

    public static String getUniqueTagsBetweenYear(String input){
        String[] dados = input.split(" ");
        int anoInit = Integer.parseInt(dados[0]);
        int anoFim = Integer.parseInt(dados[1]);

        StringBuilder output = new StringBuilder();
        ArrayList<Tag> tags = new ArrayList<>();
        boolean encontrou = true;
        /*
               Vamos ver fazer de tag a tag
         */
        for (String tag : tagsESeusArtistas.keySet()){
            /*
                Vamos buscar o ArrayList dessa tag
             */
            ArrayList<String> artistas = tagsESeusArtistas.get(tag);
            encontrou = true;
            /*
                 Vamos de musica em musica ver se algum dos seus artista é igual a algum artista da tag
             */
            for (int posicao = 0; posicao < songsTxtFinal.size() && encontrou; posicao++){
                /*
                    Primeiro vemos se o ano da musica está entre os anos pedidos
                 */
                if (songsTxtFinal.get(posicao).anoLancamento >= anoInit && songsTxtFinal.get(posicao).anoLancamento <= anoFim){
                    /*
                        Vemos se a música tem artistas
                     */
                    if (songsTxtFinal.get(posicao).artistas != null) {
                        /*
                            Vamos ver a cada artista da musica se é igual a algum dos artistas da tag
                         */
                        for (int k = 0; k < songsTxtFinal.get(posicao).artistas.length && encontrou; k++) {
                            /*
                                Vai comparar o artista da musica com todos os artistas da tag
                             */
                            for (int u = 0; u < artistas.size() && encontrou; u++) {
                                if (artistas.get(u).equals(songsTxtFinal.get(posicao).artistas[k].nome)) {
                                    if (!tags.contains(new Tag(tag, artistas.size()))) {
                                        tags.add(new Tag(tag, artistas.size()));
                                        encontrou = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        if (tags.size() == 0){
            return "No results";
        }
        tags.sort(Comparator.comparing((Tag tag) -> tag.numeroVezesUsada).reversed());
        for (int count = 0; count < tags.size(); count++){
            output.append(tags.get(count).tag).append(" ").append(tags.get(count).numeroVezesUsada);
            if (count != tags.size()-1){
                output.append("\n");
            }
        }
        return output.toString();
    }

    public static String cleanUp(){
        int countMusicas = 0;
        int countArtistas = 0;
        Set<String> artistas = hashMapComArtistasESuasMusicasFinal.keySet();
        ArrayList<String> artistasParaRemover = new ArrayList<>();


        for(int count = 0;count < songsTxtFinal.size(); count++){
            Song musica = songsTxtFinal.get(count);

            if (!musica.temArtistas && !musica.detalhesAdicionados){
                songsTxtFinal.remove(musica);
                hashMapComArtistasESuasMusicasFinal.remove(musica.id);
                countMusicas++;
                count--;
            }

            if (!musica.detalhesAdicionados && musica.temArtistas){
               if (musica.artistas != null) {
                    for (int i = 0; i < musica.artistas.length; i++) {
                        int numMusicasArtistas = hashMapComArtistasESuasMusicasFinal.get(musica.artistas[i].nome) - 1;
                        hashMapComArtistasESuasMusicasFinal.put(musica.artistas[i].nome, numMusicasArtistas);
                    }
               }
                songsTxtFinal.remove(musica);
                hashMapComArtistasESuasMusicasFinal.remove(musica.id);
                countMusicas++;
                count--;
            }

            if (!musica.temArtistas && musica.detalhesAdicionados){
                songsTxtFinal.remove(musica);
                hashMapComArtistasESuasMusicasFinal.remove(musica.id);
                countMusicas++;
                count--;
            }

        }


        for(String artista : artistas){
            int nMusicas = hashMapComArtistasESuasMusicasFinal.get(artista);
            if (nMusicas == 0){
                artistasParaRemover.add(artista);
                countArtistas++;
            }
        }


        for (String artista : artistasParaRemover){
            hashSetComTodosOsArtistas.remove(artista);
            hashMapComArtistasESuasMusicasFinal.remove(artista);
        }

        return "Musicas removidas: " + (countMusicas) + "; " + "Artistas removidos: " + (artistasParaRemover.size());
    }

}
