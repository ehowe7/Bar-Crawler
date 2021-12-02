package src.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.model.HealthPotion;

public class HealthPotionView extends ItemView {
    protected Image itemImage;
    protected ImageView imageView;
    protected HealthPotion healthPotion;

    public HealthPotionView(Image itemImage) {
        this.itemImage = itemImage;
        imageView = new ImageView(this.itemImage);
        healthPotion = new HealthPotion(.15);
    }
    @Override
    public Image getItemImage() {
        return itemImage;
    }

    @Override
    public String getType() {
        return "health";
    }

    public HealthPotion getHealthPotion() {
        return healthPotion;
    }

    public void useItem(PlayerView playerView) {
        playerView.getModel().setPlayerHP(
                Math.min(playerView.getModel().getPlayerHP() + healthPotion.getHeals(),
                        playerView.getModel().getMaxHp()));
        playerView.getProgressBar()
                .setProgress(playerView.getModel().getPlayerHP()
                        / playerView.getModel().getMaxHp());

    }
}
