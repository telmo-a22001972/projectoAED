package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static pt.ulusofona.aed.deisiRockstar2021.Main.*;

public class lerFicheiros {
    public static void main(String[] args) {

    }


    public static void lerSongs(){
        int posicao = 0;
        try {
            String linhaSongs = null;
            FileReader fr = new FileReader("songs.txt");
            BufferedReader reader = new BufferedReader(fr);

            while ((linhaSongs = reader.readLine()) != null) {

                String[] dados = linhaSongs.split("@");
                String[] dadosFinais = new String[dados.length];

                if (dados.length != 3) {
                    parseInfoSongsTxT.numLinhasIgnored += 1;
                    continue;
                }

                for (int count = 0; count < dados.length; count++ ) {
                    dadosFinais[count] = dados[count].trim();
                    if (dadosFinais[count].isEmpty()){
                        parseInfoSongsTxT.numLinhasIgnored += 1;

                    }
                }

                String idTemaMusical = dadosFinais[0];
                String nome = dadosFinais[1];
                int anoLancamento = Integer.parseInt(dadosFinais[2]);

                if (hashMapComMusicasInicial.containsKey(idTemaMusical)){
                    parseInfoSongsTxT.numLinhasIgnored += 1;
                    continue;
                }

                parseInfoSongsTxT.numLinhasOk += 1;
                Song musica = new Song(idTemaMusical, nome, anoLancamento);
                musica.posicaoDaMusica = posicao;

                hashMapComMusicasInicial.put(idTemaMusical, musica);
                songsTxt.add(musica);
                posicao++;

            }
            reader.close();
            hashMapComMusicasFinal = (HashMap<String, Song>) hashMapComMusicasInicial.clone();
            hashMapComMusicasInicial.clear();
            songsTxtFinal = (ArrayList<Song>) songsTxt.clone();
            songsTxt.clear();
            parseInfoSongsTxTFinal = new ParseInfo(parseInfoSongsTxT);
            parseInfoSongsTxT.reset();


        }  catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura");
        }

    }


    public static void lerSongsArtists() {
        try {
            String linha = null;
            FileReader fr = new FileReader("song_artists.txt");
            BufferedReader readerSongArtists = new BufferedReader(fr);
            while ((linha = readerSongArtists.readLine()) != null) {

                String[] dados = linha.split("@");
                String[] dadosFinais = new String[dados.length];

                if (dados.length != 2) {
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }
                int count;

                for (count = 0; count < 2; count++ ) {
                    dadosFinais[count] = dados[count].trim();
                }

                String idTemaMusical = dadosFinais[0];
                String artista = dadosFinais[1];

                boolean existeID = existeID(hashMapComMusicasFinal, idTemaMusical);

                if (idTemaMusical.isEmpty() && artista.isEmpty()){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }
                if (idTemaMusical.isEmpty()){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }

                if (!existeID){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }


                separarArtistas(artista,idTemaMusical);


            }
            readerSongArtists.close();

            songArtistsFinal = (ArrayList < Artista>) songArtists.clone();
            songArtists.clear();
            parseInfoSongsArtistsTxTFinal = new ParseInfo(parseInfoSongsArtistsTxT);
            parseInfoSongsArtistsTxT.reset();
            hashMapComArtistasESuasMusicasFinal = (HashMap<String, Integer>) hashMapComArtistasESuasMusicasInicial.clone();
            hashMapComArtistasESuasMusicasInicial.clear();



        }  catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura");
        }

    }

    public static void lerSongDetails() {
        boolean linhaDup = false;
        boolean algoVazio = false;
        try {
            boolean letra = false;
            String linha= null;
            FileReader fr = new FileReader("song_details.txt");
            BufferedReader reader = new BufferedReader(fr);
            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split("@");
                String[] dadosFinais = new String[dados.length];
                if (dados.length != 7) {
                    parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                    continue;
                }
                for (int count = 0; count < dados.length; count++ ) {
                    dadosFinais[count] = dados[count].trim();
                    if (dadosFinais[count].isEmpty()){
                        parseInfoSongsTxT.numLinhasIgnored += 1;
                        algoVazio = true;
                        break;
                    }
                }
                if (algoVazio){
                    algoVazio = false;
                    continue;
                }

                for (Song musica : songDetails){
                    if (musica.id.equals(dadosFinais[0])){
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        linhaDup = true;
                    }
                }
                if (linhaDup){
                    linhaDup = false;
                    continue;
                }

                adicionarDetails(dadosFinais);

            }
            reader.close();
            songDetailsFinal = (ArrayList < Song >) songDetails.clone();
            songDetails.clear();
            //System.out.println(testeSongDetailsFinal.size());
            parseInfoSongsDetailsTxTFinal = new ParseInfo(parseInfoSongsDetailsTxT);
            //System.out.println(parseInfoSongsDetailsTxTFinal.toString());
            parseInfoSongsDetailsTxT.reset();



        }  catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura");
        }
    }

    public static int adicionarDetails(String[] dados){
        String idTemaMusical = null;
        int duracao = 0;
        int letraExplicita = 0;
        int popularidade = 0;
        double dancabilidade = 0;
        double vivacidade = 0;
        double volumeMedio = 0;
        boolean boolLetraExplicita = false;

        for (int i = 0; i < dados.length; i++) {

            switch (i){

                case 0:
                    idTemaMusical = dados[i];
                    if (!existeID(hashMapComMusicasFinal, idTemaMusical)) {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 1:
                    if (numeroInteiro(dados[i])) {
                        duracao = Integer.parseInt(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 2:
                    if (numeroInteiro(dados[i])) {
                        letraExplicita = Integer.parseInt(dados[i]);
                        if (letraExplicita == 1)
                            boolLetraExplicita = true;

                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 3:
                    if (numeroInteiro(dados[i])) {
                        popularidade = Integer.parseInt(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 4:
                    if (numeroDoubble(dados[i])) {
                        dancabilidade = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 5:
                    if (numeroDoubble(dados[i])) {
                        vivacidade = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
                case 6:
                    if (numeroDoubble(dados[i])) {
                        volumeMedio = Double.parseDouble(dados[i]);
                    } else {
                        parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
                        return 0;
                    }
                    break;
            }
        }
        Song musica = hashMapComMusicasFinal.get(idTemaMusical);
        if (musica.detalhesAdicionados){
            parseInfoSongsDetailsTxT.numLinhasIgnored += 1;
            return 0;
        }
        parseInfoSongsDetailsTxT.numLinhasOk += 1;
        musica.duracaoDoTema = duracao;
        musica.letraExplicita = boolLetraExplicita;
        musica.popularidade = popularidade;
        musica.dancabilidade = dancabilidade;
        musica.vivacidade = vivacidade;
        musica.volumeMedio = volumeMedio;
        musica.detalhesAdicionados = true;
        hashMapComMusicasFinal.put(idTemaMusical,musica);
        songsTxt.set(musica.posicaoDaMusica, musica);


        Song.fazerToString(musica);


        return 0;
    }

    public static void clonarEdarReset(){
        songsTxt = new ArrayList<>(hashMapComMusicasFinal.values());
        songsTxtFinal = (ArrayList<Song>) songsTxt.clone();
        songsTxt.clear();
    }

    public static boolean numeroInteiro(String string){
        int numero;

        if (string == null || string.equals(""))
        {
            return false;
        }

        try{
            numero = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean numeroDoubble(String string){
        double numero;

        if (string == null || string.equals(""))
            return false;

        try{
            numero = Double.parseDouble(string);
        }catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static int separarArtistas(String artistas, String idTemaMusical) {
        String[] arrayArtistas = artistas.split(",");

        //Aqui vão ficar os artistas depois do trim
        String[] artistasTrim = new String[arrayArtistas.length];
        Artista[] artistasSong = new Artista[arrayArtistas.length];
        int musicas = 0;

        boolean artistaVazio = false;


        if (hashMapComMusicasFinal.get(idTemaMusical).temArtistas){
            parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
            return 0;
        }
        //Aqui vai ser feito o trim dos artistas 1 a 1
        for (int i = 0; i < artistasTrim.length; i++){
            artistasTrim[i] = arrayArtistas[i].trim();
        }


        //Aqui é feito a remoção dos caracteres desnecessários nos artistas

        for (int u = 0; u < artistasTrim.length; u++) {

            if (artistasTrim[u].length() == 0) {
                //Este artistaVazio vai ser usado para saber se se utiliza a linha ou não
                artistaVazio = true;
                break;
            }
            //Vai remover os caracteres do ínicio que sejam ([, ' , ")
            while (artistasTrim[u].startsWith("[")|| artistasTrim[u].charAt(0) == '"' || artistasTrim[u].charAt(0) == 39 || artistasTrim[u].charAt(0) == ' '){
                //Elimina o caracter
                artistasTrim[u] = artistasTrim[u].substring(1);

                //Vê o tamanho da string para não eliminar o caracter de uma string vazia
                if (artistasTrim[u].isEmpty()) {
                    //Este artistaVazio vai ser usado para saber se se utiliza a linha ou não
                    artistaVazio = true;
                    break;
                }

            }

            //Vai remover caracteres desnecessários no final a string dos artistas
            if (!artistasTrim[u].isEmpty()) {
                //Só entra se a string não for vazia para não dar crash
                while (artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 93 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 39 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 34 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == ' ') {
                    artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 1);
                    if (artistasTrim[u].isEmpty()){
                        artistaVazio = true;
                        break;
                    }
                }
            }

        }


        //Aqui vê-se se foi detetado algum artista vazio
        //E se tal acontecer então não utiliza a linha

        if (!artistaVazio) {

            for (int count = 0; count < artistasTrim.length; count++){
                //Cria array de artistas
                artistasSong[count] = new Artista(idTemaMusical, artistasTrim[count]);

            }
            if (existeID(hashMapComMusicasFinal, idTemaMusical)){
                parseInfoSongsArtistsTxT.numLinhasOk += 1;

                //Aumenta o número de músicas a cada artista
                for (Artista artistaParaMusica : artistasSong){
                    if (hashMapComArtistasESuasMusicasInicial.get(artistaParaMusica.nome)!= null)
                        musicas = hashMapComArtistasESuasMusicasInicial.get(artistaParaMusica.nome);
                    //Mete a música no hashMapDosArtistas, com o nº de músicas atualizado
                    hashMapComArtistasESuasMusicasInicial.put(artistaParaMusica.nome,musicas+1);
                }
                Song musicaNova = hashMapComMusicasFinal.get(idTemaMusical);
                musicaNova.artistas = artistasSong;
                musicaNova.temArtistas = true;
                hashMapComMusicasFinal.put(idTemaMusical,musicaNova);
                return 0;

            }

        } else {
            parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
            return 0;
        }


        return 0;
    }

    public static boolean existeID(HashMap<String, Song> hashMapSongs ,String idTemaMusical) {

        if (hashMapSongs.containsKey(idTemaMusical))
            return true;

        return false;
    }




}
