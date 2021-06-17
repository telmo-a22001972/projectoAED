package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static pt.ulusofona.aed.deisiRockstar2021.Main.*;


public class TestQueries {

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
        Assert.assertEquals("Thom Yorke", result);
    }

    @Test
    public void getMostDanceable(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("GET_MOST_DANCEABLE 2000 2020 1");
        Assert.assertEquals("Hearing Damage : 2009 : 0.627",result);
    }

    @Test
    public void getUniqueTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        tagsESeusArtistas.clear();
        Main.execute("ADD_TAGS Thom Yorke;yau");
        String result = Main.execute("GET_UNIQUE_TAGS");
        Assert.assertEquals("YAU 1",result);
    }

    @Test
    public void addTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("ADD_TAGS Thom Yorke;yau");
        Assert.assertEquals("Thom Yorke | YAU",result);
    }

    @Test
    public void removeTags(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        Main.execute("ADD_TAGS Thom Yorke;yau");
        String result = Main.execute("REMOVE_TAGS Thom Yorke;yau");
        Assert.assertEquals("Thom Yorke | No tags",result);
    }

    @Test
    public void testCreativeQuery(){
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("GET_YEAR_HIGHER_DURATION_MUSIC 2000 3");
        Assert.assertEquals("This Mess We're In | 3:55",result);

        result = Main.execute("GET_YEAR_HIGHER_DURATION_MUSIC 2009 3");
        Assert.assertEquals("Hearing Damage | 5:3", result);
    }

    @Test
    public void getArtistsWithSongsInBetween(){
        tagsESeusArtistas.clear();
        songArtistsFinal.clear();
        Main.loadfiles("C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\songs.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_details.txt","C:\\Users\\TESTES\\IdeaProjects\\projeto2\\test-files\\song_artists.txt");
        String result = Main.execute("GET_TOP_ARTISTS_WITH_SONGS_BETWEEN 3 0 5");
        Assert.assertEquals("Thom Yorke 4\n" +
                                    "[UNKLE] 1\n" +
                                    "[PJ Harvey] 1",result);

    }
}
