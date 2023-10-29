package app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CrawlerApp {

    public static final String SCRAPER_API_KEY = "d74dddad0fcfe947e46e9eb82f991482";

    private static final List<String> TOPICS = List.of("Agent Cooper", "Sherlock", "Poirot", "Detective Monk");


    public static void main(String[] args) throws IOException {
        PhotoCrawler photoCrawler = new PhotoCrawler();
        photoCrawler.resetLibrary();
        photoCrawler.downloadPhotoExamples();
        photoCrawler.downloadPhotosForQuery(TOPICS.get(0));
        photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
    }
}