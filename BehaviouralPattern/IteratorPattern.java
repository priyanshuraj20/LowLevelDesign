package BehaviouralPattern;

import java.util.*;

//======================================================================
// ENTITY
//======================================================================

class Video {

    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}



//======================================================================
// STEP-1
// NORMAL IMPLEMENTATION
//======================================================================

//Suppose we have a YouTube Playlist.
//
//Client directly accesses
//the internal List.
//
//This exposes implementation details.
//
//Client knows:
//
//Playlist internally uses ArrayList.
//
//Tomorrow if ArrayList changes
//to LinkedList,
//Database,
//API,
//or anything else,
//
//Client code will also change.

class BasicYouTubePlaylist {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    public List<Video> getVideos() {
        return videos;
    }
}



//======================================================================
// STEP-2
// ITERATOR INTERFACE
//======================================================================

//Instead of exposing
//the entire collection,
//
//Expose only
//how to traverse it.

interface PlaylistIterator {

    boolean hasNext();

    Video next();
}



//======================================================================
// STEP-3
// CONCRETE ITERATOR
//======================================================================

//Concrete Iterator knows
//how to traverse
//YouTube Playlist.

class YouTubePlaylistIterator implements PlaylistIterator {

    private List<Video> videos;

    private int position;

    public YouTubePlaylistIterator(List<Video> videos) {

        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {

        return position < videos.size();
    }

    @Override
    public Video next() {

        if(hasNext()){

            return videos.get(position++);
        }

        return null;
    }
}



//======================================================================
// STEP-4
// PROBLEM
//======================================================================

//Suppose client writes:
//
//PlaylistIterator iterator =
//new YouTubePlaylistIterator(
//playlist.getVideos());
//
//Although iteration logic
//is now separated,
//
//Client still knows:
//
//1. Concrete Iterator.
//
//2. Internal List.
//
//Abstraction is still broken.


//======================================================================
// STEP-5
// AGGREGATE INTERFACE
//======================================================================

//Every collection
//should know
//how to create
//its iterator.
//
//Client should NOT create
//Concrete Iterator.

interface Playlist {

    PlaylistIterator createIterator();
}



//======================================================================
// STEP-6
// CONCRETE AGGREGATE
//======================================================================

//Concrete Playlist
//creates its own iterator.
//
//Client never uses:
//
//new YouTubePlaylistIterator()

class YouTubePlaylist implements Playlist {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){

        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator(){

        return new YouTubePlaylistIterator(videos);
    }
}



//======================================================================
// CLIENT
//======================================================================

public class IteratorPattern {

    public static void main(String[] args) {

        //==============================================================
        // STEP-1
        // Basic Implementation
        //==============================================================

        BasicYouTubePlaylist basicPlaylist =
                new BasicYouTubePlaylist();

        basicPlaylist.addVideo(new Video("LLD Tutorial"));
        basicPlaylist.addVideo(new Video("System Design Basics"));

        //Client directly accesses List.

        for(Video video : basicPlaylist.getVideos()){

            System.out.println(video.getTitle());
        }


        System.out.println("\n=========================\n");


        //==============================================================
        // FINAL ITERATOR PATTERN
        //==============================================================

        YouTubePlaylist playlist =
                new YouTubePlaylist();

        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));
        playlist.addVideo(new Video("Iterator Pattern"));

        //Client doesn't know
        //which iterator
        //is being created.

        PlaylistIterator iterator =
                playlist.createIterator();

        while(iterator.hasNext()){

            System.out.println(iterator.next().getTitle());
        }

    }
}


//basic implemetation comment :
//import java.util.*;
//
//class Video {
//
//    String title;
//
//    public Video(String title) {
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//}
//
//class YouTubePlaylist {
//
//    private List<Video> videos = new ArrayList<>();
//
//    public void addVideo(Video video) {
//        videos.add(video);
//    }
//
//    public List<Video> getVideos() {
//        return videos;
//    }
//}
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        YouTubePlaylist playlist = new YouTubePlaylist();
//
//        playlist.addVideo(new Video("LLD Tutorial"));
//        playlist.addVideo(new Video("System Design Basics"));
//
//        // Client directly accesses internal collection
//
//        for (Video video : playlist.getVideos()) {
//            System.out.println(video.getTitle());
//        }
//    }
//}Basic Implementation
//
//↓
//
//Client directly accessed ArrayList.
//
//↓
//
//Implementation became exposed.
//
//↓
//
//So we introduced Iterator.
//
//↓
//
//Iterator separated traversal logic.
//
//↓
//
//But client was still creating
//Concrete Iterator.
//
//↓
//
//So we introduced Aggregate
//(Playlist Interface).
//
//↓
//
//Aggregate became responsible
//for creating Iterator.
//
//↓
//
//Now client only asks
//
//createIterator()
//
//and never knows
//
//ArrayList
//
//LinkedList
//
//Concrete Iterator
//
//or traversal logic.
//
//↓
//
//This completely hides
//the internal representation,
//which is the main intent
//of Iterator Pattern.