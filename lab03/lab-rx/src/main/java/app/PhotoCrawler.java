package app;

import io.reactivex.rxjava3.core.Observable;
import model.Photo;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            Observable<Photo> source = photoDownloader.getPhotoExamples();

            source.subscribe(photoSerializer::savePhoto, Throwable::printStackTrace);

        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query) throws IOException {
        Observable<Photo> source = photoDownloader.searchForPhotos(query);
        source.subscribe(photoSerializer::savePhoto, Throwable::printStackTrace);
    }

    public void downloadPhotosForMultipleQueries(List<String> queries) {
        Observable<Photo> source = Observable.empty();

        for (String query : queries) {
            source = source.mergeWith(photoDownloader.searchForPhotos(query));
        }

        source.subscribe(photoSerializer::savePhoto, Throwable::printStackTrace);
    }
}
