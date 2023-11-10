package model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gallery {

    private final List<Photo> photos = new ArrayList<>();

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public ObservableList<Photo> getPhotos() {
        return FXCollections.observableArrayList(photos);
    }

    public void clear() {
        photos.clear();
    }
}
