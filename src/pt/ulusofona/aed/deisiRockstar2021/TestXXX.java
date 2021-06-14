package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static pt.ulusofona.aed.deisiRockstar2021.Main.*;


public class TestXXX {

    public static void main(String[] args) {

    }
    @Test
    public void countSongsYear(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        Assert.assertEquals(0,Integer.parseInt(Main.execute("COUNT_SONGS_YEAR 2000")));
    }

    @Test
    public void countDuplicateSongYear(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        Assert.assertEquals(0,Integer.parseInt(Main.execute("COUNT_DUPLICATE_SONGS_YEAR 2000")));
    }

    @Test
    public void getArtistsForTag(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        Main.execute("ADD_TAGS Thom Yorke;yau");
        String result = Main.execute("GET_ARTISTS_FOR_TAG YAU");
        Assert.assertEquals("Thom Yorke | YAU", result);
    }

    @Test
    public void getMostDanceable(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("GET_MOST_DANCEABLE 2000 2020 1");
        Assert.assertEquals("Black Swan : 2006 : 0.0\n",result);
    }

    @Test
    public void getUniqueTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");

        String result = Main.execute("GET_UNIQUE_TAGS");
        Assert.assertEquals("",result);
    }

    @Test
    public void addTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("ADD_TAGS Thom Yorke;yau");
        Assert.assertEquals("Inexistent artist",result);
    }

    @Test
    public void removeTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("REMOVE_TAGS Thom Yorke;yau");
        Assert.assertEquals("Inexistent artist",result);
    }

}
