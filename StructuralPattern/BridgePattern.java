package StructuralPattern;
interface PlayQuality {
    void play(String title);
}

class WebHDPlayer implements PlayQuality {

    @Override
    public void play(String title) {
        System.out.println("Web Player : Playing " + title + " in HD");
    }
}

class MobileHDPlayer implements PlayQuality {

    @Override
    public void play(String title) {
        System.out.println("Mobile Player : Playing " + title + " in HD");
    }
}

class SmartTVUltraHDPlayer implements PlayQuality {

    @Override
    public void play(String title) {
        System.out.println("Smart TV : Playing " + title + " in Ultra HD");
    }
}

class Web4KPlayer implements PlayQuality {

    @Override
    public void play(String title) {
        System.out.println("Web Player : Playing " + title + " in 4K");
    }
}
//Without Bridge tumhe classes banani padengi  3 Devices × 3 Qualities = 9 classes

//Solution
//
//Device aur Quality ko separate kar do.
//
//VideoPlayer
//      |
//      | HAS-A
//      ▼
//VideoQuality


//public class BridgePattern {
//}



//Bridge:


// ======================================================
// ABSTRACTION
//
// VideoPlayer doesn't know HOW the video is streamed.
//
// It only knows it has a VideoQuality object.
//
// This "HAS-A" relationship is the BRIDGE.
//
// VideoPlayer  -------->  VideoQuality
// ======================================================
abstract class VideoPlayer {

    // Bridge
    protected VideoQuality quality;

    public VideoPlayer(VideoQuality quality) {
        this.quality = quality;
    }

    // Every platform (Web, Mobile, TV)
    // will implement its own play()
    public abstract void play(String title);
}
// ======================================================
// REFINED ABSTRACTION
//
// WebPlayer is one type of VideoPlayer.
//
// It doesn't know whether quality is HD or 4K.
//
// It simply delegates that work to VideoQuality.
// ======================================================
class WebPlayer extends VideoPlayer {

    public WebPlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {

        System.out.println("Web Platform :");

        // Delegate to implementation
        quality.load(title);
    }
}
class SmartTVPlayer extends VideoPlayer {

    public SmartTVPlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {

        System.out.println("Smart TV Platform :");

        quality.load(title);
    }
}
public class BridgePattern  {

    public static void main(String[] args) {

        // Web + HD
        VideoPlayer player1 =
                new WebPlayer(new HDQuality());

        player1.play("Avengers");

        System.out.println();


        // Mobile + SD
        VideoPlayer player2 =
                new MobilePlayer(new SDQuality());

        player2.play("Interstellar");

        System.out.println();


        // Smart TV + 4K
        VideoPlayer player3 =
                new SmartTVPlayer(new UltraHDQuality());

        player3.play("Avatar");
    }
}