package src.model;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class EnemySpriteModel {
    private ArrayList<Image> forwardNeutralSprites;
    private ArrayList<Image> forwardHitSprites;

    public EnemySpriteModel() {
        forwardNeutralSprites = generateArrayList("assets/enemies/standing");
        forwardHitSprites = generateArrayList("assets/enemies/hit");
    }


    public ArrayList<Image> generateArrayList(String dir) {
        ArrayList<Image> images = new ArrayList<>();
        File directory = new File(dir);
        for (File f : directory.listFiles()) {
            images.add(new Image("file:" + dir + "/" +  f.getName()));
            System.out.println("file:" + dir + "/" +  f.getName());
        }
        //allow for randomization of map
        //Collections.shuffle(images);
        return images;
    }

    public ArrayList<Image> getForwardNeutralSprites() {
        return forwardNeutralSprites;
    }

    public ArrayList<Image> getForwardHitSprites() {
        return forwardHitSprites;
    }
}
