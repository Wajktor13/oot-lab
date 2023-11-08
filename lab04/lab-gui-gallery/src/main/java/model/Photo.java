package model;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import javafx.beans.property.*;
import javafx.scene.image.Image;


public class Photo {

    private final Property<String> name = new SimpleStringProperty();

    private final ObjectProperty<Image> photoData = new SimpleObjectProperty<>();

    public Photo(String extension, byte[] photoData) {
        this.photoData.setValue(new Image(new ByteArrayInputStream(photoData)));
        this.name.setValue(UUID.randomUUID().toString() + "." + extension);
    }

    public String getName() {
        return this.name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public Image getPhotoData() {return this.photoData.getValue();}

    public Property<String> nameProperty() {return this.name;}

    public ObjectProperty<Image> imageProperty() {return this.photoData;}
}
