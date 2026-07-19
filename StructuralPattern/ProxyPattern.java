package StructuralPattern;

import java.util.*;
// WITHOUT PROXY
// ==========================================================
// STEP 1 : Common Interface
//
// Client sirf is interface ko use karega.
// Isse future me RealDownloader ya ProxyDownloader
// easily replace ho sakte hain.
// ==========================================================
interface VideoDownloader {

    String downloadVideo(String videoUrl);
}



// ==========================================================
// STEP 2 : Real Object
//
// Ye actual heavy object hai.
//
// Ye internet pe jaake video download karta hai.
//
// Assume download karna expensive operation hai.
// ==========================================================
class RealVideoDownloader implements VideoDownloader {

    @Override
    public String downloadVideo(String videoUrl) {

        System.out.println("Downloading video from URL : " + videoUrl);

        // Imagine...
        // Internet Call
        // Database Call
        // Cloud Storage
        // Takes 5 seconds

        return "Video Content : " + videoUrl;
    }
}



// ==========================================================
// Driver
// ==========================================================
//public class ProxyPattern {
//
//    public static void main(String[] args) {
//
//        VideoDownloader downloader =
//                new RealVideoDownloader();
//
//        downloader.downloadVideo("java-course");
//
//        System.out.println();
//
//        // Same video again
//
//        downloader.downloadVideo("java-course");
//    }
//}

//User same video dobara dekh raha hai.
//
//Fir bhi
//
//Internet
//
//↓
//
//Download
//
//↓
//
//Internet
//
//↓
//
//Download
//
//Har baar server hit ho raha hai.
//
//Ye expensive hai.
//
//Server load increase.
//
//Bandwidth waste.
//
//Time waste.




//STEP 2 : Proxy Pattern
//
//Ab beech me ek intelligent object bithate hain.
//
//Client
//
//↓
//
//Proxy
//
//↓
//
//Real Downloader
//
//Proxy bolega
//
//"Ek minute...
//
//Pehle check karta hu cache me hai ya nahi."



import java.util.*;

// ==========================================================
// Common Interface
// ==========================================================
interface VideoDownloader {

    String downloadVideo(String videoUrl);
}



// ==========================================================
// Real Object
//
// Actual downloading happens here.
//
// This object knows nothing about cache.
// ==========================================================
class RealVideoDownloader implements VideoDownloader {

    @Override
    public String downloadVideo(String videoUrl) {

        System.out.println("Downloading video from URL : " + videoUrl);

        return "Video Content : " + videoUrl;
    }
}



// ==========================================================
// PROXY
//
// This class sits between Client and Real Downloader.
//
// Responsibilities:
//
// 1. Check cache
// 2. If present -> return cached result
// 3. Else -> download and cache it
// ==========================================================
class CachedVideoDownloader implements VideoDownloader {

    // Real Object
    private RealVideoDownloader realDownloader;

    // Cache Memory
    private Map<String, String> cache;


    public CachedVideoDownloader() {

        realDownloader = new RealVideoDownloader();

        cache = new HashMap<>();
    }


    @Override
    public String downloadVideo(String videoUrl) {

        // -----------------------------
        // Cache Hit
        // -----------------------------
        if (cache.containsKey(videoUrl)) {

            System.out.println(
                    "Returning Cached Video : " + videoUrl);

            return cache.get(videoUrl);
        }

        // -----------------------------
        // Cache Miss
        // -----------------------------
        System.out.println(
                "Cache Miss...Downloading...");

        String video =
                realDownloader.downloadVideo(videoUrl);

        cache.put(videoUrl, video);

        return video;
    }
}



// ==========================================================
// Driver
// ==========================================================
public class ProxyPattern {

    public static void main(String[] args) {

        VideoDownloader downloader =
                new CachedVideoDownloader();

        System.out.println();

        downloader.downloadVideo("java-course");

        System.out.println();

        downloader.downloadVideo("java-course");

        System.out.println();

        downloader.downloadVideo("lld-course");

        System.out.println();

        downloader.downloadVideo("lld-course");
    }
}

///Cache Miss...Downloading...
/// Downloading video from URL : java-course
///
/// Returning Cached Video : java-course
///
/// Cache Miss...Downloading...
/// Downloading video from URL : lld-course
///
/// Returning Cached Video : lld-course

///Client
///    │
///    ▼
/// CachedVideoDownloader
///    │
///    ├── Cache me hai?
///    │       │
///    │       ├── YES → Return
///    │       │
///    │       └── NO
///    │
///    ▼
/// RealVideoDownloader
///    │
/// Internet