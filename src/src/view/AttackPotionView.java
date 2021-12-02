package src.view;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.model.AttackPotion;

public class AttackPotionView extends ItemView {
    protected Image itemImage;
    protected ImageView imageView;
    protected AttackPotion attackPotion;

    public AttackPotionView(Image itemImage) {
        this.itemImage = itemImage;
        imageView = new ImageView(this.itemImage);
        attackPotion = new AttackPotion(2, 5);
    }
    @Override
    public Image getItemImage() {
        return itemImage;
    }

    @Override
    public String getType() {
        return "attack";
    }

    public AttackPotion getAttackPotion() {
        return attackPotion;
    }

    public void useItem(PlayerView player) {
        final long[] startTime = {System.currentTimeMillis()};
        final long[] lastAttackTime = {System.currentTimeMillis()};
        player.getModel().setDamageMultiplier(2);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                startTime[0] = System.currentTimeMillis();
                if (startTime[0] - lastAttackTime[0] >  attackPotion.getDuration() * 1000) {
                    System.out.println("DONE ATTACK!");
                    player.getModel().setDamageMultiplier(1);
                    this.stop();
                }
            }
        };
        timer.start();
    }
}