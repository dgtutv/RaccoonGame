package GUI;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GUI {
    RaccoonGame raccoonGame;
    public BufferedImage sfuIcon;
    public BufferedImage scoreIcon;
    public BufferedImage hundredsIcon;
    public BufferedImage tensIcon;
    public BufferedImage onesIcon;
    public BufferedImage titleBackground, endBackground;
    Font customFont, purisa;
    public int hundreds;
    public int tens;
    public int ones;
    public int cursorNum = 0;

    public GUI(RaccoonGame raccoonGame) {
        // Initialize GUI
        this.raccoonGame = raccoonGame;
        ones = 0;
        tens = 0;
        hundreds = 0;


        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/customFont.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/fonts/Purisa Bold.ttf");
            purisa = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        }
        catch(FontFormatException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

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

    public void update(int ones, int tens, int hundreds) {
        // update numbers
        this.ones = ones;
        this.tens = tens;
        this.hundreds = hundreds;
        getImage();
    }

    // Get image
    public void getImage() {
        try {
            sfuIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/sfu2.png")));
            scoreIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/score.png")));
            String onesPath = "/GUI/" + String.valueOf(this.ones) + ".png";
            onesIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(onesPath)));
            String tensPath = "/GUI/" + String.valueOf(this.tens) + ".png";
            tensIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tensPath)));
            String hundredsPath = "/GUI/" + String.valueOf(this.hundreds) + ".png";
            hundredsIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(hundredsPath)));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGUI(Graphics2D graphics) {
        //if playState, draw GUI for gameplay
        if(raccoonGame.gameState == raccoonGame.playState) {
            drawPlayScreen(graphics);
        }
        //if titleState, draw GUI for title screen
        if(raccoonGame.gameState == raccoonGame.titleState) {
            drawTitleScreen(graphics);
        }

        //if endState, draw GUI for end game screen
        if(raccoonGame.gameState == raccoonGame.endState) {
            drawEndScreen(graphics);
        }

        //if pauseState, draw GUI for end pause screen
        if(raccoonGame.gameState == raccoonGame.pauseState) {
            drawPauseScreen(graphics);
        }

    }

    private void drawTitleScreen(Graphics2D graphics) {
        try {
            titleBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/titleBackground.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        //set background image with title
        graphics.drawImage(titleBackground, 0, 0, raccoonGame.windowWidth, raccoonGame.windowHeight, null);

        //instantiate menu
        graphics.setFont(purisa.deriveFont(Font.PLAIN, 48F));
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.white);
        String text = "Start Game";
        int x = raccoonGame.windowWidth/2 - 4*raccoonGame.blockSize;
        int y = raccoonGame.windowHeight/2 + 4*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        if(cursorNum == 0) {
            graphics.drawString(">", x-raccoonGame.blockSize, y);
        }

        text = "Quit Game";
        x += 0.5*raccoonGame.blockSize;;
        y += 2*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        if(cursorNum == 1) {
            graphics.drawString(">", x-raccoonGame.blockSize, y);
        }


    }

    private void drawPauseScreen(Graphics2D graphics) {

    }

    private void drawPlayScreen(Graphics2D graphics) {
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

    private void drawEndScreen(Graphics2D graphics) {
        try {
            endBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/gameOver.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        //set background image with title
        graphics.drawImage(endBackground, 0, 0, raccoonGame.windowWidth, raccoonGame.windowHeight, null);

        //instantiate menu
        graphics.setFont(purisa.deriveFont(Font.PLAIN, 48F));
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.white);
        String text = "Quit Game";
        int x = raccoonGame.windowWidth/2 - 4*raccoonGame.blockSize;
        int y = raccoonGame.windowHeight/2 + 4*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        graphics.drawString(">", x-raccoonGame.blockSize, y);



    }

}
