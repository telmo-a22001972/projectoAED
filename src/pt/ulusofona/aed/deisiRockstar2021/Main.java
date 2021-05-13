
package pt.ulusofona.aed.deisiRockstar2021;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {
    public static ArrayList < Song > teste6 = new ArrayList < > ();
    public static ArrayList < Song > getSongsArray = new ArrayList < > ();
    public static ArrayList < Artista > testeSongArtists = new ArrayList < > ();
    public static ArrayList < Artista > testeSongArtistsFinal = new ArrayList < > ();
    public static ArrayList < Song > testeSongDetails = new ArrayList < Song > ();
    public static ArrayList < Song > testeSongDetailsFinal = new ArrayList < Song > ();
    public static ParseInfo parseInfoSongsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsArtistsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsDetailsTxT = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsTxTFinal = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsArtistsTxTFinal = new ParseInfo(0, 0);
    public static ParseInfo parseInfoSongsDetailsTxTFinal = new ParseInfo(0, 0);

    public static void main(String[] args) throws IOException {
        ArrayList < Song > teste7 = new ArrayList < Song > ();
        loadFiles();

        teste7 = getSongs();
        ParseInfo teste8 = getParseInfo("songs.txt");
        System.out.println("\n----------------------TESTE DO MAIN----------------------");
        //System.out.println(teste7.toString());
        //System.out.println(teste8.toString());
        //System.out.println(getSongsArray.size());
        //System.out.println(parseInfoSongsTxTFinal.toString());

    }

    public static void loadFiles() throws IOException {
        //Aqui lê-se o ficheiro songs.txt
        //System.out.println("----------------------LEITURA DO FICHEIRO songs.txt------------");

        lerFicheiros.lerSongs();
        //System.out.println(getSongsArray.toString());
        //System.out.println("Ok: " + parseInfoSongsTxT.numLinhasOk + ", Ignored: " + parseInfoSongsTxT.numLinhasIgnored + "\n");


        //System.out.println("----------------------LEITURA DO FICHEIRO song_artists.txt------------\n");

        lerFicheiros.lerSongsArtists();

        //System.out.println(testeSongArtistsFinal.toString());
        //System.out.println("Ok: " + parseInfoSongsArtistsTxTFinal.numLinhasOk + ", Ignored: " + parseInfoSongsArtistsTxTFinal.numLinhasIgnored + "\n");


        System.out.println("----------------------LEITURA DO FICHEIRO song_details.txt------------");
        //Aqui lê-se o ficheiro song_details.txt

        lerFicheiros.lerSongDetails();

        //System.out.println("Ok: " + parseInfoSongsDetailsTxTFinal.numLinhasOk + ", Ignored: " + parseInfoSongsDetailsTxTFinal.numLinhasIgnored);
    }

    public static ArrayList < Song > getSongs() {
        return getSongsArray;
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