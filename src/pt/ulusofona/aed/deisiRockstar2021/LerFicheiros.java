package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static pt.ulusofona.aed.deisiRockstar2021.AdicionarDetalhesEArtistas.adicionarDetails;
import static pt.ulusofona.aed.deisiRockstar2021.AdicionarDetalhesEArtistas.separarArtistas;
import static pt.ulusofona.aed.deisiRockstar2021.Main.*;

public class LerFicheiros {
    public static void main(String[] args) {

    }


    public static void lerSongs(String filenameSongs){
        int posicao = 0;
        try {
            String linhaSongs = null;
            FileReader fr = new FileReader(filenameSongs);
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


    public static void lerSongsArtists(String filenameArtists) {
        try {
            String linha = null;
            FileReader fr = new FileReader(filenameArtists);
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


                if (idTemaMusical.isEmpty()){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }

                if (!existeID){
                    parseInfoSongsArtistsTxT.numLinhasIgnored += 1;
                    continue;
                }

                if (hashMapComMusicasFinal.get(idTemaMusical).temArtistas){
                    parseInfoSongsArtistsTxT.numLinhasOk += 1;
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

    public static void lerSongDetails(String filenameDetails) {
        boolean linhaDup = false;
        boolean algoVazio = false;
        try {
            boolean letra = false;
            String linha= null;
            FileReader fr = new FileReader(filenameDetails);
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

        if (string == null || string.equals("")) {
            return false;
        }

        try{
            numero = Double.parseDouble(string);
        }catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean existeID(HashMap<String, Song> hashMapSongs ,String idTemaMusical) {

        if (hashMapSongs.containsKey(idTemaMusical)) {
            return true;
        }
        return false;
    }

    public static String[] veSeHaArtistasRepetidosNaMusica(String[] arrayArtistas){
        int count = 0;
        TreeSet<String> artistas = new TreeSet<>(Arrays.asList(arrayArtistas));

        String[] artistasFinais = new String[artistas.size()];


        for (String artista : artistas){
            artistasFinais[count] = artista;
            count++;
        }

        return artistasFinais;
    }
}
