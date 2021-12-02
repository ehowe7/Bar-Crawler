package src.view;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.model.SpeedPotion;

public class SpeedPotionView extends ItemView {
    protected Image itemImage;
    protected ImageView imageView;
    protected SpeedPotion speedPotion;

    public SpeedPotionView(Image itemImage) {
        this.itemImage = itemImage;
        imageView = new ImageView(this.itemImage);
        speedPotion = new SpeedPotion(20, 5);
    }
    @Override
    public Image getItemImage() {
        return itemImage;
    }

    @Override
    public String getType() {
        return "speed";
    }

    public SpeedPotion getSpeedPotion() {
        return speedPotion;
    }

    public void useItem(PlayerView player) {
        final long[] startTime = {System.currentTimeMillis()};
        final long[] lastAttackTime = {System.currentTimeMillis()};
        player.getModel().setDx(20);
        player.getModel().setDy(20);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                startTime[0] = System.currentTimeMillis();
                if (startTime[0] - lastAttackTime[0] >  speedPotion.getDuration() * 1000) {
                        System.out.println("DONE SPEED!");
                        player.getModel().setDx(player.getModel().getOriginalDx());
                        player.getModel().setDy(player.getModel().getOriginalDy());
                        this.stop();
                    }
            }
        };
        timer.start();
    }
}