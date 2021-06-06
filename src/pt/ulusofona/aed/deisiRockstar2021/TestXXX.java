package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static pt.ulusofona.aed.deisiRockstar2021.Main.loadFiles;


public class TestXXX {

    public static void main(String[] args) {

    }
    @org.junit.Test
    public void testGetSongToString3Musicas() throws IOException {
        Song song2 = new Song("1oYYd2gnWZYrt89EBXdFiO","Message In A Bottles",null,1979,0,false,0,0,0,0);

        Assert.assertEquals("1oYYd2gnWZYrt89EBXdFiO | Message In A Bottles | 1979",song2.toString());

    }

    @org.junit.Test
    public void testGetSongToString2Musicas() throws IOException {
        Song song1 = new Song("1oYYd2gnWZYrt89EBXdFiO","Message In A Bottle",null,1979,0,false,0,0,0,0);

        Assert.assertEquals("1oYYd2gnWZYrt89EBXdFiO | Message In A Bottle | 1979",song1.toString());
    }

    @Test
    public void testeGetSongsYear() throws IOException {
        Main.loadFiles();
        Assert.assertEquals("2016",Main.execute("GET_SONGS_YEAR 2000"));
    }

    @Test
    public void contarNumeroMusicasDoArtista() throws IOException {
        Main.loadFiles();
        Assert.assertEquals(10,Artista.numeroMusicasDoArtista("White Lion"));
    }

    @Test
    public void getSongsFicheiroPequeno() throws  IOException {
        Main.loadFiles();
        Assert.assertEquals(1, Main.songsTxtFinal.size());
    }

    @Test
    public void toStringSimpleFile() throws  IOException {
        Main.loadFiles();

        Assert.assertEquals("3vNFsxBbA4dPkymPF5Jm1b | This Mess We're In | 2000 | 3:55 | 62.0 | PJ Harvey / Thom Yorke | (1 / 4)", Main.hashMapComMusicasFinal.get("3vNFsxBbA4dPkymPF5Jm1b").toString());
    }


}
