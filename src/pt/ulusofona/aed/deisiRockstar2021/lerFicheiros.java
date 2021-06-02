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
                parseInfoSongsTxT.numLinhasOk += 1;

                Song song = new Song(idTemaMusical, nome, anoLancamento);
                songsArray.add(song);
            }
            reader.close();
            Main.songsArrayFinal = (ArrayList< Song >) songsArray.clone();
            songsArray.clear();
            parseInfoSongsTxTFinal = new ParseInfo(parseInfoSongsTxT);
            parseInfoSongsTxT.reset();
            //System.out.println(parseInfoSongsTxTFinal.toString());

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

                boolean existeID = existeID(songsArrayFinal, idTemaMusical);
                if (idTemaMusical.isEmpty() && artista.isEmpty()){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }
                if (idTemaMusical.isEmpty()){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }
                parseInfoSongsArtistsTxT.numLinhasOk += 1;
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


        }  catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura");
        }

    }

    public static void lerSongDetails() {

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
                    }
                }

                parseInfoSongsDetailsTxT.numLinhasOk += 1;
                String idTemaMusical = dadosFinais[0];
                //System.out.println(idTemaMusical);
                int duracao = Integer.parseInt(dadosFinais[1]);
                //System.out.println(duracao);
                int letraExplicita = Integer.parseInt(dadosFinais[2]);
                //System.out.println(letraExplicita);
                if (letraExplicita == 0) {
                    letra = false;
                } else {
                    letra = true;
                }
                //System.out.println(letra);
                int populariedade = Integer.parseInt(dadosFinais[3]);
                //System.out.println(populariedade);
                double dancabilidade = Double.parseDouble(dadosFinais[4]);
                //System.out.println(dancabilidade);
                double vivacidade = Double.parseDouble(dadosFinais[5]);
                //System.out.println(vivacidade);
                double volumeMedio = Double.parseDouble(dadosFinais[6]);
                //System.out.println(volumeMedio);
                Song song = new Song(idTemaMusical, null, null, 0, duracao, letra, populariedade, dancabilidade, vivacidade, volumeMedio);
                songDetails.add(song);
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


    public static void separarArtistas(String artistas, String idTemaMusical) {
        String[] arrayArtistas = artistas.split(",");
        String teste = arrayArtistas[0];

        //Aqui vão ficar os artistas depois do trim
        String[] artistasTrim = new String[arrayArtistas.length];
        Artista[] artistasSong = new Artista[arrayArtistas.length];


        boolean artistaVazio = false;

        //Aqui vai ser feito o trim dos artistas 1 a 1

        for (int i = 0; i < artistasTrim.length; i++){
            artistasTrim[i] = arrayArtistas[i].trim();
        }


        //Aqui é feito a remoção dos caracteres desnecessários nos artistas

        for (int u = 0; u < artistasTrim.length; u++) {

            //Vai remover os caracteres do ínicio que sejam ([, ' , ")
            while (artistasTrim[u].startsWith("[")|| artistasTrim[u].charAt(0) == '"' || artistasTrim[u].charAt(0) == 39){
                //Elimina o caracter
                artistasTrim[u] = artistasTrim[u].substring(1);

                //Vê o tamanho da string para não eliminar o caracter de uma string vazia
                if (artistasTrim[u].length() == 0) {
                    //Este artistaVazio vai ser usado para saber se se utiliza a linha ou não
                    artistaVazio = true;
                    break;
                }

            }

            //Vai remover caracteres desnecessários no final a string dos artistas
            if (artistasTrim[u].length() != 0) {
            //Só entra se a string não for vazia para não dar crash
                while (artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 93 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 39 || artistasTrim[u].charAt(artistasTrim[u].length() - 1) == 34) {
                    artistasTrim[u] = artistasTrim[u].substring(0, artistasTrim[u].length() - 1);
                }
            }

        }


        //Aqui vê-se se foi detetado algum artista vazio
        //E se tal acontecer então não utiliza a linha

        if (!artistaVazio) {
            for (int count = 0; count < artistasTrim.length; count++){
                artistasSong[count] = new Artista(idTemaMusical, artistasTrim[count]);
            }
            for (int k = 0; k < songsArrayFinal.size(); k++){
                    if (songsArrayFinal.get(k).id.equals(idTemaMusical)) {
                        songsArrayFinal.get(k).artistas = artistasSong;
                    }

            }
        }

    }

    public static boolean existeID(ArrayList<Song> songsArrayFinal ,String idTemaMusical) {

        for (Song musica : songsArrayFinal){
            if (songsArrayFinal.get(0).id.equals(musica.id))
                return true;
        }
        return false;
    }

}
