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
    public BufferedImage onesIcon, tensIcon, hundredsIcon;
    public BufferedImage zero, one, two, three, four, five, six, seven, eight, nine;
    public BufferedImage titleBackground, endBackground, pauseBackground, pauseText;
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
        //getImage();
    }

    // Get image
    public void getImage() {
        try {
            sfuIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/sfu.png")));
            scoreIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/score.png")));

            zero = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/0.png")));
            one = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/1.png")));
            two = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/2.png")));
            three = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/3.png")));
            four = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/4.png")));
            five = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/5.png")));
            six = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/6.png")));
            seven = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/7.png")));
            eight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/8.png")));
            nine = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/9.png")));

            titleBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/titleBackground.png")));

            pauseText = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/pause.png")));
            pauseBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/pauseBackground.png")));

            endBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GUI/gameOver.png")));
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
        drawPlayScreen(graphics);

        //set background image with title
        graphics.drawImage(pauseBackground, 0, 0, raccoonGame.windowWidth, raccoonGame.windowHeight, null);
        graphics.drawImage(pauseText, raccoonGame.windowWidth/2 - 180, 10*raccoonGame.blockSize, 360, 90, null);

        //instantiate menu
        graphics.setFont(purisa.deriveFont(Font.PLAIN, 48F));
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.white);
        String text = "Resume Game";
        int x = raccoonGame.windowWidth/2 - 6*raccoonGame.blockSize;
        int y = raccoonGame.windowHeight/2 + 4*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        if(cursorNum == 0) {
            graphics.drawString(">", x-raccoonGame.blockSize, y);
        }

        text = "Quit Game";
        x += 1*raccoonGame.blockSize;;
        y += 2*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        if(cursorNum == 1) {
            graphics.drawString(">", x-raccoonGame.blockSize, y);
        }
    }

    private void drawPlayScreen(Graphics2D graphics) {
        int sfuWidth = 120;
        int sfuHeight = 60;
        int scoreWidth = 240;
        int scoreHeight = 50;
        int digitSize = 50;

        // Draw GUI
        graphics.drawImage(sfuIcon, raccoonGame.getWidth()/2-sfuWidth/2, 0, sfuWidth, sfuHeight, null);
        graphics.drawImage(scoreIcon, raccoonGame.getWidth()-scoreWidth/2-250, 0, scoreWidth, scoreHeight, null);

        switch(this.hundreds) {
            case 0 -> hundredsIcon = zero;
            case 1 -> hundredsIcon = one;
            case 2 -> hundredsIcon = two;
            case 3 -> hundredsIcon = three;
            case 4 -> hundredsIcon = four;
            case 5 -> hundredsIcon = five;
            case 6 -> hundredsIcon = six;
            case 7 -> hundredsIcon = seven;
            case 8 -> hundredsIcon = eight;
            case 9 -> hundredsIcon = nine;
        }
        switch(this.tens) {
            case 0 -> tensIcon = zero;
            case 1 -> tensIcon = one;
            case 2 -> tensIcon = two;
            case 3 -> tensIcon = three;
            case 4 -> tensIcon = four;
            case 5 -> tensIcon = five;
            case 6 -> tensIcon = six;
            case 7 -> tensIcon = seven;
            case 8 -> tensIcon = eight;
            case 9 -> tensIcon = nine;
        }
        switch(this.ones) {
            case 0 -> onesIcon = zero;
            case 1 -> onesIcon = one;
            case 2 -> onesIcon = two;
            case 3 -> onesIcon = three;
            case 4 -> onesIcon = four;
            case 5 -> onesIcon = five;
            case 6 -> onesIcon = six;
            case 7 -> onesIcon = seven;
            case 8 -> onesIcon = eight;
            case 9 -> onesIcon = nine;
        }

        graphics.drawImage(hundredsIcon, raccoonGame.getWidth()-250+scoreWidth/2, 0, digitSize, digitSize, null);
        graphics.drawImage(tensIcon, raccoonGame.getWidth()-250+scoreWidth/2+30, 0, digitSize, digitSize, null);
        graphics.drawImage(onesIcon, raccoonGame.getWidth()-250+scoreWidth/2+60, 0, digitSize, digitSize, null);
    }

    private void drawEndScreen(Graphics2D graphics) {

        //set background image with title
        graphics.drawImage(endBackground, 0, 0, raccoonGame.windowWidth, raccoonGame.windowHeight, null);

        //instantiate menu
        graphics.setFont(purisa.deriveFont(Font.PLAIN, 48F));
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(Color.red);

        String text;
        int x, y;

        if(raccoonGame.player.hasEscaped) {
            text = "You Escaped Successfully!";
            x= raccoonGame.windowWidth/2 - 10*raccoonGame.blockSize;
        }
        else {
            text = "You died! :(";
            x = raccoonGame.windowWidth/2 - 5*raccoonGame.blockSize;
        }

        y = raccoonGame.windowHeight/2;
        graphics.drawString(text, x, y);

        text = "Your total score was: " + (raccoonGame.player.score) + "!";
        x = raccoonGame.windowWidth/2 - 11*raccoonGame.blockSize;
        y = raccoonGame.windowHeight/2 + 4*raccoonGame.blockSize;
        graphics.drawString(text, x, y);

        graphics.setColor(Color.white);
        text = "Quit Game";
        x += 7*raccoonGame.blockSize;
        y += 4*raccoonGame.blockSize;
        graphics.drawString(text, x, y);
        graphics.drawString(">", x-raccoonGame.blockSize, y);



    }

}
