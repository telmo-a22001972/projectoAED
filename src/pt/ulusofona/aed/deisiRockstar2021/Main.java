
package pt.ulusofona.aed.deisiRockstar2021;
import java.io.IOException;
import java.util.*;

import static pt.ulusofona.aed.deisiRockstar2021.Queries.*;



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
    public static HashMap<String, Integer> hashMapComArtistasESuasMusicasInicial = new HashMap<>();
    public static HashMap<String, Integer> hashMapComArtistasESuasMusicasFinal = new HashMap<>();
    public static HashSet<String> hashSetComTodosOsArtistas = new HashSet<>();
    public static HashMap<String, ArrayList<String>> artistasESuasTags = new HashMap<>();
    public static HashMap<String, ArrayList<String>> tagsESeusArtistas = new HashMap<>();
    public static HashMap<String, ArrayList<Song>> usarNoRisingStars = new HashMap<>();
    public static int nLinhas = 0;


    public static void main(String[] args) throws IOException {

        loadFiles();

        System.out.println("\n----------------------TESTE DO MAIN----------------------");

        System.out.println(hashMapComMusicasFinal.get("3vNFsxBbA4dPkymPF5Jm1b"));
        Queries.menu();


    }

    public static void loadFiles() throws IOException {
        loadfiles("songs.txt","song_artists.txt","song_details.txt");

        System.out.println("----------------------LEITURA DO FICHEIRO songs.txt------------");

        long start = System.currentTimeMillis();
        LerFicheiros.lerSongs("songs.txt");
        long end = System.currentTimeMillis();
        System.out.println("(took " + (end - start) + " ms)\n");


        System.out.println("----------------------LEITURA DO FICHEIRO song_artists.txt------------\n");

        long startArtists = System.currentTimeMillis();
        LerFicheiros.lerSongsArtists("song_artists.txt");
        long endArtists = System.currentTimeMillis();
        System.out.println("(took " + (endArtists - startArtists) + " ms)\n");




        System.out.println("----------------------LEITURA DO FICHEIRO song_details.txt------------");

        long startDetails = System.currentTimeMillis();
        LerFicheiros.lerSongDetails("song_details.txt");
        long endDetails = System.currentTimeMillis();
        System.out.println("(took " + (endDetails - startDetails) + " ms)\n");


    }

    public static ArrayList< Song > getSongs() {
        return songsTxtFinal;
    }

    public static ParseInfo getParseInfo(String fileName) {
        if (fileName.equals("songs.txt")) {
            return parseInfoSongsTxTFinal;
        }
        if (fileName.equals("song_artists.txt")) {
            return parseInfoSongsArtistsTxTFinal;
        }
        if (fileName.equals("song_details.txt")) {
            return parseInfoSongsDetailsTxTFinal;
        }
        return null;
    }

    public static String execute(String command) {

        String[] comando = command.split(" ", 2);
        String accao = comando[0];


        switch (accao){
            case "COUNT_SONGS_YEAR" :
                int numero = Integer.parseInt(comando[1]);
                String numeroMusicas = getSongsYear(numero);
                return numeroMusicas;

            case "COUNT_DUPLICATE_SONGS_YEAR" :
                int numero2 = Integer.parseInt(comando[1]);

                String musicasRepetidas = getDuplicateSongsYear(numero2);
                return musicasRepetidas;

            case "GET_ARTISTS_FOR_TAG" :

                return artistsForTag(comando[1]);

            case "ADD_TAGS" :

                String resultado = addTags(comando[1]);
                return resultado;

            case "REMOVE_TAGS" :
                return removerTags(comando[1]);

            case "GET_MOST_DANCEABLE" :
                String[] dados = comando[1].split(" ");
                return getMostDanceable(Integer.parseInt(dados[0]),Integer.parseInt(dados[1]),Integer.parseInt(dados[2]));

            case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN" :
                return getTopArtistsInBetween(comando[1]);

            case "GET_ARTISTS_ONE_SONG" :
                return getArtistsOneSong(comando[1]);

            case "GET_YEAR_HIGHER_DURATION_MUSIC" :
                return getYearHighestDurationMusic(comando[1]);

            case "GET_UNIQUE_TAGS" :
                return getUniqueTags();

            case "CLEANUP" :
                return cleanUp();

            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS" :
                return getUniqueTagsBetweenYear(comando[1]);
            default:
                return "Illegal command. Try again";
        }

    }

    public static String getCreativeQuery(){

        return "GET_YEAR_HIGHER_DURATION_MUSIC 2020 20";
    }

    public static int getTypeOfSecondParameter()
    {
        return 1;
    }

    public static String getVideoUrl()
    {
        return "https://youtu.be/VlUVxBiX7n8";
    }

    public static void loadfiles(String filenameSongs, String filenameArtists, String filenameDetails){
        long start = System.currentTimeMillis();
        LerFicheiros.lerSongs(filenameSongs);

        LerFicheiros.lerSongsArtists(filenameArtists);

        LerFicheiros.lerSongDetails(filenameDetails);
        long end = System.currentTimeMillis();
        System.out.println("(took " + (end - start) + " ms)\n");
    }

}