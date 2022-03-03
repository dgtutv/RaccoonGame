package GUI;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GUI {
    RaccoonGame raccoonGame;
    public BufferedImage sfuIcon;
    public BufferedImage scoreIcon;
    public BufferedImage hundredsIcon;
    public BufferedImage tensIcon;
    public BufferedImage onesIcon;
    public int hundreds;
    public int tens;
    public int ones;

    public GUI(RaccoonGame raccoonGame) {
        // Initialize GUI
        this.raccoonGame = raccoonGame;
        ones = 0;
        tens = 1;
        hundreds = 2;
        getImage();
    }

    public GUI(RaccoonGame raccoonGame, int ones, int tens, int hundreds) {
        // Initialize GUI
        this.raccoonGame = raccoonGame;
        this.ones = ones;
        this.tens = tens;
        this.hundreds = hundreds;
        getImage();
    }

    // Get image
    public void getImage() {
        try {
            sfuIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/GUI/sfu2.png")));
            scoreIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/GUI/score.png")));
            String onesPath = "/res/GUI/" + String.valueOf(this.ones) + ".png";
            onesIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(onesPath)));
            String tensPath = "/res/GUI/" + String.valueOf(this.tens) + ".png";
            tensIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tensPath)));
            String hundredsPath = "/res/GUI/" + String.valueOf(this.hundreds) + ".png";
            hundredsIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(hundredsPath)));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGUI(Graphics2D graphics) {
        int sfuWidth = 120;
        int sfuHeight = 50;
        int scoreWidth = 240;
        int scoreHeight = 50;
        int digitSize = 50;

        // Draw GUI
        graphics.drawImage(sfuIcon, raccoonGame.getWidth()/2-sfuWidth/2, 0, sfuWidth, sfuHeight, null);
        graphics.drawImage(scoreIcon, raccoonGame.getWidth()-scoreWidth/2-250, 0, scoreWidth, scoreHeight, null);
        graphics.drawImage(hundredsIcon, raccoonGame.getWidth()-250+scoreWidth/2, 0, digitSize, digitSize, null);
        graphics.drawImage(tensIcon, raccoonGame.getWidth()-250+scoreWidth/2+30, 0, digitSize, digitSize, null);
        graphics.drawImage(onesIcon, raccoonGame.getWidth()-250+scoreWidth/2+60, 0, digitSize, digitSize, null);
    }
}
