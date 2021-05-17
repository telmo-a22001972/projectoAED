
package pt.ulusofona.aed.deisiRockstar2021;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static ArrayList < Song > songsArray = new ArrayList < > ();
    public static ArrayList < Song > songsArrayFinal = new ArrayList < > ();
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

    public static void main(String[] args) throws IOException {

        loadFiles();


        System.out.println("\n----------------------TESTE DO MAIN----------------------");

        System.out.println(songArtistsFinal.toString());
        Queries.menu();

    }

    public static void loadFiles() throws IOException {

        System.out.println("----------------------LEITURA DO FICHEIRO songs.txt------------");

        lerFicheiros.lerSongs();


        System.out.println("----------------------LEITURA DO FICHEIRO song_artists.txt------------\n");

        lerFicheiros.lerSongsArtists();



        System.out.println("----------------------LEITURA DO FICHEIRO song_details.txt------------");

        lerFicheiros.lerSongDetails();

    }

    public static ArrayList < Song > getSongs() {
        return songsArrayFinal;
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


}