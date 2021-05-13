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


}
