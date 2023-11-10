package controller;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;
import util.PhotoDownloader;


public class GalleryController {

    private Gallery galleryModel;

    @FXML
    private TextField imageNameField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize() {
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });

        imagesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                this.imageNameField.textProperty().unbindBidirectional(oldValue.nameProperty());
            }

            this.bindSelectedPhoto(newValue);
        });
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;

        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        if (selectedPhoto != null) {
            imageView.imageProperty().bind(selectedPhoto.imageProperty());
            imageNameField.textProperty().bindBidirectional(selectedPhoto.nameProperty());
        }
    }

    public void searchButtonClicked(ActionEvent event) {
        PhotoDownloader photoDownloader = new PhotoDownloader();

        this.galleryModel.clear();

        photoDownloader.searchForPhotos("Agent Cooper").subscribe(photo -> {
            Platform.runLater(() -> galleryModel.addPhoto(photo));
        });
    }
}

