package tests;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import org.junit.Test;
import ratings.Song;
import java.util.ArrayList;
import ratings.datastructures.*;
import ratings.*;


public class TestClasses2 {
    public boolean compareArrayListsIgnoreCase(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1 == null || list2==null) {
            return list1==list2;
        }
        if(list1.size()!=list2.size()){
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equalsIgnoreCase(list2.get(i))) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void test1(){ //Testing compareArrayListsIgnoreCase utility
        ArrayList<String> newlist1 = new ArrayList<>(asList("FEIN", "GOD'S COUNTRY","LOST FOREVER"));
        ArrayList<String> newlist2 = new ArrayList<>(asList("FEIN", "GOD'S COUNTRY","LOST FOREVER"));
        assertEquals(true,compareArrayListsIgnoreCase(newlist1,newlist2));
    }
     @Test
    public void test2(){ //List with empty strings
        ArrayList<String> newList1 = new ArrayList<>(asList(""));
        ArrayList<String> newList2 = new ArrayList<>(asList(""));
        assertEquals(true,compareArrayListsIgnoreCase(newList1,newList2));
    }

    @Test
    public void test4(){ // Testing bayesian average
        Song s1 = new Song("Lil Wayne", "John","TC3");
        s1.addRating(new Rating("Craig",5));
        s1.addRating(new Rating("Jeff",4));
        assertEquals(3.75,s1.bayesianAverageRating(2,3),.001);
    }
    @Test
    public void test100(){ // Testing bayesian average with an invalid rating for the fake ratings
        Movie s1 = new Movie("Lil Wayne", new ArrayList<>(asList("Chris Pratt","Scarlett Johnson")));
        s1.addRating(new Rating("Craig",4));
        s1.addRating(new Rating("Jeff",3));

        assertEquals(0.0,s1.bayesianAverageRating(8,-42),.001);
    }

    @Test
    public void test13(){ // Testing bayesian average with no fake ratings
        Song s1 = new Song("Lil Wayne", "John","TC3");
        s1.addRating(new Rating("Craig",5));
        s1.addRating(new Rating("Jeff",4));
        assertEquals(4.5,s1.bayesianAverageRating(0,3),.001);
    }
    @Test
    public void testotf(){ // Testing bayesian average with no real or fake ratings
        Song s1 = new Song("Lil Wayne", "John","TC3");
        assertEquals(0.0,s1.bayesianAverageRating(0,0),.001);
    }





    @Test
    public void testBayesian(){ //average with no valid ratings
        Movie mov = new Movie("Endgame",new ArrayList<>(asList("Chris Pratt","George", "Paul Pierce")));

        Rating r1 = new Rating("r1",6);
        Rating r2 = new Rating("r2",-2);
        mov.addRating(r1);
        mov.addRating(r2);


        assertEquals(3.0, mov.bayesianAverageRating(2,3),.001);
    }
    @Test
    public void testBayesian2(){ //average with no valid ratings
        Movie mov = new Movie("Endgame",new ArrayList<>(asList("Chris Pratt","George", "Paul Pierce")));

        Rating r1 = new Rating("r1",3);
        Rating r2 = new Rating("r2",5);
        mov.addRating(r1);
        mov.addRating(r2);


        assertEquals(0.0, mov.bayesianAverageRating(1,9),.001);
    }

    @Test
    public void test6(){ //Testing SongTitleComparator when two songs are identical
        ArrayList<Song> songlist = new ArrayList<>();
        Song s1 = new Song("President Carter", "Lil Wayne","TC3");
        Song s2 = new Song("President Carter", "Lil Wayne","TC3");

        songlist.add(s1);
        songlist.add(s2);
        SongTitleComparator comparing = new SongTitleComparator();
        assertEquals(false, comparing.compare(s1,s2));
    }

    @Test
    public void test7(){ //Testing SongTitleComparator when titles are prefixes
        ArrayList<Song> songlist = new ArrayList<>();
        Song s2 = new Song("aa", "Lil Wayne","TC3");
        Song s3 = new Song("aaa", "Lil Wayne","TC3");
        songlist.add(s2);
        songlist.add(s3);
        SongTitleComparator comparing = new SongTitleComparator();
        assertEquals(true, comparing.compare(s2,s3));
    }

    @Test
    public void test8(){ //Testing SongBayesianRatingComparator when both songs have equivalent ratings
        Song s2 = new Song("Window Pain", "J Cole","TC3");
        Song s3 = new Song("Power Trip", "J Cole","TC3");

        s2.addRating(new Rating("Jermaine",4));
        s2.addRating(new Rating("Jake",5));
        s2.addRating(new Rating("Steve",3));
        s2.addRating(new Rating("Fracis",2));

        s3.addRating(new Rating("Jermaine",4));
        s3.addRating(new Rating("Jake",5));
        s3.addRating(new Rating("Steve",3));
        s3.addRating(new Rating("Fracis",2));

        SongBayesianRatingComparator SBRC = new SongBayesianRatingComparator();

        assertEquals(false, SBRC.compare(s2,s3));
    }
    @Test
    public void test9(){ //Testing SongBayesianRatingComparator when songs have different ratings
        Song s1 = new Song("Window Pain", "J Cole","TC3");
        Song s2 = new Song("Power Trip", "J Cole","TC3");

        s1.addRating(new Rating("Jermaine",1));
        s1.addRating(new Rating("Jake",1));
        s1.addRating(new Rating("Steve",1));
        s1.addRating(new Rating("Fracis",1));

        s2.addRating(new Rating("Jermaine",5));
        s2.addRating(new Rating("Jake",5));
        s2.addRating(new Rating("Steve",5));
        s2.addRating(new Rating("Fracis",5));


        SongBayesianRatingComparator SBRC = new SongBayesianRatingComparator();

        assertEquals(true, SBRC.compare(s2,s1));
    }
    @Test
    public void test10(){ //Testing SongBayesianRatingComparator with unequal amounts of ratings
        Song s1 = new Song("Window Pain", "J Cole","TC3");
        Song s2 = new Song("Power Trip", "J Cole","TC3");

        s1.addRating(new Rating("Jermaine",3));
        s1.addRating(new Rating("Jake",3));


        s2.addRating(new Rating("Jermaine",5));
        s2.addRating(new Rating("Jake",5));
        s2.addRating(new Rating("Steve",5));
        s2.addRating(new Rating("Fracis",5));


        SongBayesianRatingComparator SBRC = new SongBayesianRatingComparator();

        assertEquals(true, SBRC.compare(s2,s1));
    }
    @Test
    public void test11(){ //Testing SongBayesianRatingComparator with invalid ratings
        Song s1 = new Song("Window Pain", "J Cole","TC3");
        Song s2 = new Song("Power Trip", "J Cole","TC3");

        s1.addRating(new Rating("Jermaine",6));
        s1.addRating(new Rating("Jake",0));
        s1.addRating(new Rating("Jake",6));
        s1.addRating(new Rating("Jake",6));


        s2.addRating(new Rating("Jermaine",6));
        s2.addRating(new Rating("Jake",100));
        s2.addRating(new Rating("Steve",100));
        s2.addRating(new Rating("Fracis",-1));


        SongBayesianRatingComparator SBRC = new SongBayesianRatingComparator();

        assertEquals(false, SBRC.compare(s2,s1));
    }
    @Test
    public void testMovieCastOrder(){
        ArrayList<String> cast = new ArrayList<>(asList("Chris Hemsworth, Tyler Perry","Terry Crews"));
        Movie mov1 = new Movie("Tenet",cast);
        mov1.addRating(new Rating("Jake",5));
        mov1.addRating(new Rating("Paul",3));
        mov1.addRating(new Rating("George",2));


        assertEquals(true,compareArrayListsIgnoreCase(cast,mov1.getCast()));

        }
    @Test
    public void testBayesian1(){
        Movie mov = new Movie("Endgame",new ArrayList<>(asList("Chris Pratt","George", "Paul Pierce")));



        assertEquals(3.0, mov.bayesianAverageRating(5,3),.001);

    }
    @Test
    public void testBayesian3(){
        Song s1 = new Song("Endgame","Wayne","Diddy");
        Rating r1 = new Rating("paul ",5);
        Rating r2 = new Rating("carl ",2);
        Rating r3 = new Rating("jesse ",3);



        assertEquals(3.0, s1.bayesianAverageRating(7,3),.001);

    }




}




