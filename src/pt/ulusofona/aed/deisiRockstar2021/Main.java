
package pt.ulusofona.aed.deisiRockstar2021;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayList;

import static pt.ulusofona.aed.deisiRockstar2021.Queries.*;

                                            //ESTÁ MUITO LENTO A ADICIONAR OS DETAILS

public class Main {
    public static ArrayList < Song > songsTxt = new ArrayList<>();
    public static ArrayList < Song > songsTxtFinal = new ArrayList<>();
    public static ArrayList < Artista > songArtists = new ArrayList < > ();
    public static ArrayList < Artista > songArtistsFinal = new ArrayList < > ();
    public static ArrayList < Song > songDetails = new ArrayList < Song > ();
    public static ArrayList < Song > songDetailsFinal = new ArrayList < Song > ();
    public static ParseInfo parseInfoSongsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsArtistsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsDetailsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsTxTFinal = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsArtistsTxTFinal = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsDetailsTxTFinal = new ParseInfo(0, 0);
    public static HashMap<String, Song> hashMapComMusicasInicial = new HashMap<>();
    public static HashMap<String, Song> hashMapComMusicasFinal = new HashMap<>();

    public static void main(String[] args) throws IOException {

        loadFiles();

        System.out.println("\n----------------------TESTE DO MAIN----------------------");
        //System.out.println(hashMapComMusicasFinal.toString());
        //System.out.println(Arrays.toString(songArtistsFinal.toArray()));
        //System.out.println(getSongs().toString());

        System.out.println(getSongs().size());
        Queries.menu();

    }

    public static void loadFiles() throws IOException {

        System.out.println("----------------------LEITURA DO FICHEIRO songs.txt------------");

        long start = System.currentTimeMillis();
        lerFicheiros.lerSongs();
        long end = System.currentTimeMillis();
        System.out.println("(took " + (end - start) + " ms)\n");


        System.out.println("----------------------LEITURA DO FICHEIRO song_artists.txt------------\n");

        long startArtists = System.currentTimeMillis();
        lerFicheiros.lerSongsArtists();
        long endArtists = System.currentTimeMillis();
        System.out.println("(took " + (endArtists - startArtists) + " ms)\n");

        //System.out.println(hashMapComMusicasFinal.get("0cS0A1fUEUd1EW3FcF8AEI").toString());



        System.out.println("----------------------LEITURA DO FICHEIRO song_details.txt------------");

        long startDetails = System.currentTimeMillis();
        lerFicheiros.lerSongDetails();
        long endDetails = System.currentTimeMillis();
        System.out.println("(took " + (endDetails - startDetails) + " ms)\n");

        //System.out.println(hashMapComMusicasFinal.get("0cS0A1fUEUd1EW3FcF8AEI").toString());

    }

    public static ArrayList< Song > getSongs() {
        return songsTxtFinal;
    }

    public static ParseInfo getParseInfo(String fileName) {
        if (Objects.equals(fileName, "songs.txt")) {
            return parseInfoSongsTxTFinal;
        }
        if (Objects.equals(fileName, "song_artists.txt")) {
            return parseInfoSongsArtistsTxTFinal;
        }
        if (Objects.equals(fileName, "song_details.txt")) {
            return parseInfoSongsDetailsTxTFinal;
        }
        return null;
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

    public static String getCreativeQuery()
    {
        return "TEMP";
    }

    public static int getTypeOfSecondParameter()
    {
        return 0;
    }

    public static String getVideoUrl()
    {
        return "TEMP";
    }

}