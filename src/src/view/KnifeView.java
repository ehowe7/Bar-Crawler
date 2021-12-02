package src.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KnifeView extends ItemView {
    protected Image itemImage;
    protected ImageView imageView;

    public KnifeView(Image itemImage) {

        this.itemImage = itemImage;
        imageView = new ImageView(this.itemImage);
    }
    @Override
    public Image getItemImage() {
        return itemImage;
    }

    @Override
    public String getType() {
        return "knife";
    }

}
