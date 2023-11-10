package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;

import model.Gallery;
import model.Photo;

public class PhotoSerializer {

    private static final Logger log = Logger.getLogger(PhotoSerializer.class.getName());

    private final String photoLibraryPath;

    public PhotoSerializer(String photoLibraryPath) throws IOException {
        this.photoLibraryPath = photoLibraryPath;
        deleteLibraryContents();
        createLibraryDirectory();
    }

    public void registerGallery(Gallery gallery) {
        gallery.getPhotos().addListener((ListChangeListener<? super Photo>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach((photo -> {
                        this.savePhoto(photo);
                        photo.nameProperty().addListener((observable, oldValue, newValue) -> {
                            renamePhoto(oldValue, newValue);
                        });
                    }));
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removePhoto);
                }
            }
        });
    }

    private void createLibraryDirectory() throws IOException {
        File photoLibraryDir = new File(photoLibraryPath);
        if (!photoLibraryDir.exists()) {
            photoLibraryDir.mkdir();
        }
        if (!photoLibraryDir.isDirectory()) {
            throw new IOException("This is not valid photo library directory path or directory could not be created: " + photoLibraryPath);
        }
    }

    public void savePhoto(Photo photo) {
        log.info("SAVE photo: " + photo.getName());
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(photo.getPhotoData(), null), "png", new File(getPhotoPath(photo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renamePhoto(String oldPhotoName, String newPhotoName) {
        var photoFile = new File(getPhotoPath(oldPhotoName));
        var newPhotoFile = new File(getPhotoPath(newPhotoName));
        photoFile.renameTo(newPhotoFile);
    }

    public void removePhoto(Photo photo) {
        var photoFile = new File(getPhotoPath(photo));
        photoFile.delete();
    }

    public void deleteLibraryContents() {
        File photoLibraryDirectory = new File(photoLibraryPath);
        if(photoLibraryDirectory.exists()) {
            for (String childFile : photoLibraryDirectory.list()) {
                File libraryFile = new File(photoLibraryPath, childFile);
                libraryFile.delete();
            }
        }
    }

    private String getPhotoPath(Photo photo) {
        return getPhotoPath(photo.getName());
    }

    private String getPhotoPath(String photoName) {
        return Paths.get(photoLibraryPath, photoName).toString();
    }
}
