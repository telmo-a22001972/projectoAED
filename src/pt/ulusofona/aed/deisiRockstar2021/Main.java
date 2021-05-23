
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

        System.out.println(songDetailsFinal.toString());
        //Perguntar se é suposto dar merge no que é lido dos ficheiro por exemplo:
        //no ficheiro songs é lido a musica com o ID - 0xJ6Xr620TwVr6kqDSk5tn
        //Eu quando leio o song_details se houver uma musica com o ID - 0xJ6Xr620TwVr6kqDSk5tn
        //Devo juntar os objetos Song do songs.txt com  o id, titulo e ano com o Song do song_details.txt
        //Que tem as o id, e as métricas das musicas?
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