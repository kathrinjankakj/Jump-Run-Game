package DegnRun;

import graphics.Color;
import graphics.SpriteGraphicElement;

/**
 * Background elements in the back of this game
 *
 */
public class BackgroundElements extends SpriteGraphicElement {

    /** type of the background element */
    private final String type;
    /** color of the door */
    private final Color doorColor = new Color (95,95,140);
    /** color of the door handle */
    private final Color doorHandleColor = new Color (150,150,170);
    /** color of the floor */
    private final Color floorColor = new Color(121,121,161);
    /** color of the red poster */
    private final Color posterRedColor = new Color (171,84,84);
    /** color of the orange poster */
    private final Color posterOrangeColor = new Color (227,155,105);
    /** color of the wall */
    private final Color wallColor = new Color (71,90,205);
    /** color of the text */
    private final Color textColor = new Color (255,247,85);
    /** color of shadows */
    private final Color shadowColor = new Color (243,200,46);
    /** color of buttons */
    private final Color buttonColor = new Color (172,50,50);
    /** color of text if dead */
    private final Color textDeadColor = new Color(255,112,122);
    /** color of start text if dead */
    private final Color startDeadColor = new Color (200,200,200);
    /** button frame for animation */
    private int buttonFrame=0;

    /**
     * Constructs a background element with the given parameters
     *
     * @param posY X coordinate
     * @param posX Y coordinate
     * @param type type of the element
     */
    public BackgroundElements (int posY, int posX, String type){
        super(posX,posY);
        this.type = type;
            sprite = new Color [24][24];
    }

    /**
     * sets the sprites for all background elements
     */
    public void update(){
        switch (type) {
            case "door" -> {
                door();
                if (x <= -6) {
                    x = (int) (Math.random() * 10 + 28);
                }
                x--;
            }
            case "poster" -> {
                poster();
                if (x <= -10) {
                    x = 30;
                }
                x--;
            }
            case "floor" -> floor();
            case "wall" -> wall();
            case "title" -> title();
            case "start" -> start();
            case "button" -> {
                if(buttonFrame == 0||buttonFrame == 1){
                    button();
                } else if(buttonFrame == 2||buttonFrame == 3){
                    button2();
                }
                buttonFrame= (buttonFrame + 1) % 4;
            }
            case "wallDead" -> wallDead();
            case "textDead" -> textDead();
            case "startDead" -> startDead();
        }
    }

    /**
     * builds the door sprites
     */
    public void door() {
        //add Sprites
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }
        sprite [9][0] = doorColor;
        sprite [9][1] = Color.WHITE;
        sprite [9][2] = Color.WHITE;
        sprite [9][3] = Color.WHITE;
        sprite [9][4] = Color.WHITE;
        sprite [9][5] = doorColor;

        sprite [8][0] = doorColor;
        sprite [8][1] = Color.WHITE;
        sprite [8][2] = Color.WHITE;
        sprite [8][3] = Color.WHITE;
        sprite [8][4] = Color.WHITE;
        sprite [8][5] = doorColor;

        sprite [7][0] = doorColor;
        sprite [7][1] = Color.WHITE;
        sprite [7][2] = Color.WHITE;
        sprite [7][3] = Color.WHITE;
        sprite [7][4] = Color.WHITE;
        sprite [7][5] = doorColor;

        sprite [6][0] = doorColor;
        sprite [6][1] = Color.WHITE;
        sprite [6][2] = Color.WHITE;
        sprite [6][3] = Color.WHITE;
        sprite [6][4] = Color.WHITE;
        sprite [6][5] = doorColor;

        sprite [5][0] = doorColor;
        sprite [5][1] = Color.WHITE;
        sprite [5][2] = Color.WHITE;
        sprite [5][3] = Color.WHITE;
        sprite [5][4] = doorHandleColor;
        sprite [5][5] = doorColor;

        sprite [4][0] = doorColor;
        sprite [4][1] = Color.WHITE;
        sprite [4][2] = Color.WHITE;
        sprite [4][3] = Color.WHITE;
        sprite [4][4] = Color.WHITE;
        sprite [4][5] = doorColor;

        sprite [3][0] = doorColor;
        sprite [3][1] = Color.WHITE;
        sprite [3][2] = Color.WHITE;
        sprite [3][3] = Color.WHITE;
        sprite [3][4] = Color.WHITE;
        sprite [3][5] = doorColor;

        sprite [2][0] = doorColor;
        sprite [2][1] = Color.WHITE;
        sprite [2][2] = Color.WHITE;
        sprite [2][3] = Color.WHITE;
        sprite [2][4] = Color.WHITE;
        sprite [2][5] = doorColor;

        sprite [1][0] = doorColor;
        sprite [1][1] = Color.WHITE;
        sprite [1][2] = Color.WHITE;
        sprite [1][3] = Color.WHITE;
        sprite [1][4] = Color.WHITE;
        sprite [1][5] = doorColor;

        sprite [0][0] = doorColor;
        sprite [0][1] = doorColor;
        sprite [0][2] = doorColor;
        sprite [0][3] = doorColor;
        sprite [0][4] = doorColor;
        sprite [0][5] = doorColor;
    }

    /**
     * builds the poster sprites
     */
    public void poster() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }
        for (int a= 0; a<=4;a++){
            for (int b= 0; b<=4;b++){
                sprite[a][b] = posterRedColor;
            }
        }

        for (int a= 0; a<=4;a++){
            for (int b= 6; b<=10;b++){
                sprite[a][b] = posterOrangeColor;
            }
        }
    }

    /**
     * builds the floor sprites
     */
    public void floor() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }
        for (int i= 0; i<=23;i++){
                sprite [20][i] = doorColor;
                sprite [21][i] = floorColor;
                sprite [22][i] = floorColor;
                sprite [23][i] = floorColor;
        }
    }

    /**
     * builds the wall sprites
     */
    public void wall() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=12;a++){
                sprite [a][i] = wallColor;
            }
        }
    }

    /**
     * builds the title sprites
     */
    public void title() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite [0][15] = textColor;
        sprite [0][16] = shadowColor;

        sprite [1][1] = textColor;
        sprite [1][2] = textColor;
        sprite [1][3] = shadowColor;
        sprite [1][6] = textColor;
        sprite [1][7] = textColor;
        sprite [1][8] = shadowColor;
        sprite [1][10] = textColor;
        sprite [1][11] = textColor;
        sprite [1][12] = textColor;
        sprite [1][13] = textColor;
        sprite [1][14] = shadowColor;
        sprite [1][15] = textColor;
        sprite [1][16] = shadowColor;
        sprite [1][18] = textColor;
        sprite [1][19] = shadowColor;
        sprite [1][21] = textColor;
        sprite [1][22] = shadowColor;

        sprite [2][1] = textColor;
        sprite [2][2] = shadowColor;
        sprite [2][3] = textColor;
        sprite [2][4] = shadowColor;
        sprite [2][6] = textColor;
        sprite [2][7] = shadowColor;
        sprite [2][10] = textColor;
        sprite [2][11] = shadowColor;
        sprite [2][18] = textColor;
        sprite [2][19] = textColor;
        sprite [2][20] = shadowColor;
        sprite [2][21] = textColor;
        sprite [2][22] = shadowColor;

        sprite [3][1] = textColor;
        sprite [3][2] = shadowColor;
        sprite [3][3] = textColor;
        sprite [3][4] = shadowColor;
        sprite [3][6] = textColor;
        sprite [3][7] = textColor;
        sprite [3][8] = shadowColor;
        sprite [3][10] = textColor;
        sprite [3][11] = shadowColor;
        sprite [3][12] = textColor;
        sprite [3][13] = textColor;
        sprite [3][14] = shadowColor;
        sprite [3][18] = textColor;
        sprite [3][19] = shadowColor;
        sprite [3][20] = textColor;
        sprite [3][21] = textColor;
        sprite [3][22] = shadowColor;

        sprite [4][1] = textColor;
        sprite [4][2] = shadowColor;
        sprite [4][3] = textColor;
        sprite [4][4] = shadowColor;
        sprite [4][6] = textColor;
        sprite [4][7] = shadowColor;
        sprite [4][10] = textColor;
        sprite [4][11] = shadowColor;
        sprite [4][13] = textColor;
        sprite [4][14] = shadowColor;
        sprite [4][18] = textColor;
        sprite [4][19] = shadowColor;
        sprite [4][21] = textColor;
        sprite [4][22] = shadowColor;

        sprite [5][1] = textColor;
        sprite [5][2] = textColor;
        sprite [5][3] = shadowColor;
        sprite [5][6] = textColor;
        sprite [5][7] = textColor;
        sprite [5][8] = shadowColor;
        sprite [5][10] = textColor;
        sprite [5][11] = textColor;
        sprite [5][12] = textColor;
        sprite [5][13] = textColor;
        sprite [5][14] = shadowColor;
        sprite [5][18] = textColor;
        sprite [5][19] = shadowColor;
        sprite [5][21] = textColor;
        sprite [5][22] = shadowColor;

        sprite [7][1] = textColor;
        sprite [7][2] = textColor;
        sprite [7][3] = textColor;
        sprite [7][4] = shadowColor;
        sprite [7][6] = textColor;
        sprite [7][7] = shadowColor;
        sprite [7][8] = textColor;
        sprite [7][9] = shadowColor;
        sprite [7][11] = textColor;
        sprite [7][12] = shadowColor;
        sprite [7][14] = textColor;
        sprite [7][15] = shadowColor;

        sprite [8][1] = textColor;
        sprite [8][2] = shadowColor;
        sprite [8][3] = textColor;
        sprite [8][4] = shadowColor;
        sprite [8][6] = textColor;
        sprite [8][7] = shadowColor;
        sprite [8][8] = textColor;
        sprite [8][9] = shadowColor;
        sprite [8][11] = textColor;
        sprite [8][12] = textColor;
        sprite [8][13] = shadowColor;
        sprite [8][14] = textColor;
        sprite [8][15] = shadowColor;

        sprite [9][1] = textColor;
        sprite [9][2] = textColor;
        sprite [9][3] = textColor;
        sprite [9][4] = shadowColor;
        sprite [9][6] = textColor;
        sprite [9][7] = shadowColor;
        sprite [9][8] = textColor;
        sprite [9][9] = shadowColor;
        sprite [9][11] = textColor;
        sprite [9][12] = shadowColor;
        sprite [9][13] = textColor;
        sprite [9][14] = textColor;
        sprite [9][15] = shadowColor;

        sprite [10][1] = textColor;
        sprite [10][2] = textColor;
        sprite [10][3] = shadowColor;
        sprite [10][6] = textColor;
        sprite [10][7] = shadowColor;
        sprite [10][8] = textColor;
        sprite [10][9] = shadowColor;
        sprite [10][11] = textColor;
        sprite [10][12] = shadowColor;
        sprite [10][14] = textColor;
        sprite [10][15] = shadowColor;

        sprite [11][1] = textColor;
        sprite [11][2] = shadowColor;
        sprite [11][3] = textColor;
        sprite [11][4] = shadowColor;
        sprite [11][6] = textColor;
        sprite [11][7] = textColor;
        sprite [11][8] = textColor;
        sprite [11][9] = shadowColor;
        sprite [11][11] = textColor;
        sprite [11][12] = shadowColor;
        sprite [11][14] = textColor;
        sprite [11][15] = shadowColor;
    }

    /**
     * builds the first part of the button sprite animation
     */
    public void button() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite[0][1] = buttonColor;
        sprite[0][2] = buttonColor;
        sprite[0][3] = buttonColor;
        sprite[0][4] = buttonColor;
        sprite[0][5] = buttonColor;

        sprite[1][0] = buttonColor;
        sprite[1][1] = buttonColor;
        sprite[1][2] = buttonColor;
        sprite[1][3] = buttonColor;
        sprite[1][4] = buttonColor;
        sprite[1][5] = buttonColor;
        sprite[1][6] = buttonColor;

        sprite[2][0] = Color.BLACK;
        sprite[2][1] = Color.BLACK;
        sprite[2][2] = Color.BLACK;
        sprite[2][3] = Color.BLACK;
        sprite[2][4] = Color.BLACK;
        sprite[2][5] = Color.BLACK;
        sprite[2][6] = Color.BLACK;
    }

    /**
     * builds the second part of the button sprite animation
     */
    public void button2() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite[1][1] = buttonColor;
        sprite[1][2] = buttonColor;
        sprite[1][3] = buttonColor;
        sprite[1][4] = buttonColor;
        sprite[1][5] = buttonColor;

        sprite[2][0] = Color.BLACK;
        sprite[2][1] = Color.BLACK;
        sprite[2][2] = Color.BLACK;
        sprite[2][3] = Color.BLACK;
        sprite[2][4] = Color.BLACK;
        sprite[2][5] = Color.BLACK;
        sprite[2][6] = Color.BLACK;
    }

    /**
     * builds the start text sprites
     */
    public void start() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite[0][0] = Color.BLACK;
        sprite[0][1] = Color.BLACK;
        sprite[0][2] = buttonColor;
        sprite[0][3] = buttonColor;
        sprite[0][4] = buttonColor;
        sprite[0][5] = Color.BLACK;
        sprite[0][6] = Color.BLACK;
        sprite[0][7] = Color.BLACK;
        sprite[0][8] = buttonColor;
        sprite[0][9] = buttonColor;
        sprite[0][10] = buttonColor;
        sprite[0][11] = Color.BLACK;
        sprite[0][12] = Color.BLACK;
        sprite[0][13] = Color.BLACK;

        sprite[1][0] = Color.BLACK;
        sprite[1][3] = buttonColor;
        sprite[1][5] = Color.BLACK;
        sprite[1][7] = Color.BLACK;
        sprite[1][8] = buttonColor;
        sprite[1][10] = buttonColor;
        sprite[1][12] = Color.BLACK;

        sprite[2][0] = Color.BLACK;
        sprite[2][1] = Color.BLACK;
        sprite[2][3] = buttonColor;
        sprite[2][5] = Color.BLACK;
        sprite[2][6] = Color.BLACK;
        sprite[2][7] = Color.BLACK;
        sprite[2][8] = buttonColor;
        sprite[2][9] = buttonColor;
        sprite[2][10] = buttonColor;
        sprite[2][12] = Color.BLACK;

        sprite[3][1] = Color.BLACK;
        sprite[3][3] = buttonColor;
        sprite[3][5] = Color.BLACK;
        sprite[3][7] = Color.BLACK;
        sprite[3][8] = buttonColor;
        sprite[3][9] = buttonColor;
        sprite[3][12] = Color.BLACK;

        sprite[4][0] = Color.BLACK;
        sprite[4][1] = Color.BLACK;
        sprite[4][3] = buttonColor;
        sprite[4][5] = Color.BLACK;
        sprite[4][7] = Color.BLACK;
        sprite[4][8] = buttonColor;
        sprite[4][10] = buttonColor;
        sprite[4][12] = Color.BLACK;
    }

    /**
     * builds the text sprites if dead
     */
    public void textDead(){
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite[1][5] = textDeadColor;
        sprite[1][6] = buttonColor;
        sprite[1][7] = textDeadColor;
        sprite[1][8] = buttonColor;
        sprite[1][10] = textDeadColor;
        sprite[1][11] = textDeadColor;
        sprite[1][12] = textDeadColor;
        sprite[1][13] = buttonColor;
        sprite[1][15] = textDeadColor;
        sprite[1][16] = buttonColor;
        sprite[1][17] = textDeadColor;
        sprite[1][18] = buttonColor;

        sprite[2][5] = textDeadColor;
        sprite[2][6] = buttonColor;
        sprite[2][7] = textDeadColor;
        sprite[2][8] = buttonColor;
        sprite[2][10] = textDeadColor;
        sprite[2][11] = buttonColor;
        sprite[2][12] = textDeadColor;
        sprite[2][13] = buttonColor;
        sprite[2][15] = textDeadColor;
        sprite[2][16] = buttonColor;
        sprite[2][17] = textDeadColor;
        sprite[2][18] = buttonColor;

        sprite[3][6] = textDeadColor;
        sprite[3][7] = buttonColor;
        sprite[3][10] = textDeadColor;
        sprite[3][11] = buttonColor;
        sprite[3][12] = textDeadColor;
        sprite[3][13] = buttonColor;
        sprite[3][15] = textDeadColor;
        sprite[3][16] = buttonColor;
        sprite[3][17] = textDeadColor;
        sprite[3][18] = buttonColor;

        sprite[4][6] = textDeadColor;
        sprite[4][7] = buttonColor;
        sprite[4][10] = textDeadColor;
        sprite[4][11] = buttonColor;
        sprite[4][12] = textDeadColor;
        sprite[4][13] = buttonColor;
        sprite[4][15] = textDeadColor;
        sprite[4][16] = buttonColor;
        sprite[4][17] = textDeadColor;
        sprite[4][18] = buttonColor;

        sprite[5][6] = textDeadColor;
        sprite[5][7] = buttonColor;
        sprite[5][10] = textDeadColor;
        sprite[5][11] = textDeadColor;
        sprite[5][12] = textDeadColor;
        sprite[5][13] = buttonColor;
        sprite[5][15] = textDeadColor;
        sprite[5][16] = textDeadColor;
        sprite[5][17] = textDeadColor;
        sprite[5][18] = buttonColor;

        sprite[7][1] = textDeadColor;
        sprite[7][2] = textDeadColor;
        sprite[7][3] = buttonColor;
        sprite[7][6] = textDeadColor;
        sprite[7][7] = textDeadColor;
        sprite[7][8] = textDeadColor;
        sprite[7][9] = buttonColor;
        sprite[7][11] = textDeadColor;
        sprite[7][12] = textDeadColor;
        sprite[7][13] = textDeadColor;
        sprite[7][14] = buttonColor;
        sprite[7][16] = textDeadColor;
        sprite[7][17] = textDeadColor;
        sprite[7][18] = buttonColor;
        sprite[7][21] = textDeadColor;
        sprite[7][22] = buttonColor;

        sprite[8][1] = textDeadColor;
        sprite[8][2] = buttonColor;
        sprite[8][3] = textDeadColor;
        sprite[8][4] = buttonColor;
        sprite[8][7] = textDeadColor;
        sprite[8][8] = buttonColor;
        sprite[8][11] = textDeadColor;
        sprite[8][12] = buttonColor;
        sprite[8][16] = textDeadColor;
        sprite[8][17] = buttonColor;
        sprite[8][18] = textDeadColor;
        sprite[8][19] = buttonColor;
        sprite[8][21] = textDeadColor;
        sprite[8][22] = buttonColor;

        sprite[9][1] = textDeadColor;
        sprite[9][2] = buttonColor;
        sprite[9][3] = textDeadColor;
        sprite[9][4] = buttonColor;
        sprite[9][7] = textDeadColor;
        sprite[9][8] = buttonColor;
        sprite[9][11] = textDeadColor;
        sprite[9][12] = textDeadColor;
        sprite[9][13] = textDeadColor;
        sprite[9][14] = buttonColor;
        sprite[9][16] = textDeadColor;
        sprite[9][17] = buttonColor;
        sprite[9][18] = textDeadColor;
        sprite[9][19] = buttonColor;
        sprite[9][21] = textDeadColor;
        sprite[9][22] = buttonColor;

        sprite[10][1] = textDeadColor;
        sprite[10][2] = buttonColor;
        sprite[10][3] = textDeadColor;
        sprite[10][4] = buttonColor;
        sprite[10][7] = textDeadColor;
        sprite[10][8] = buttonColor;
        sprite[10][11] = textDeadColor;
        sprite[10][12] = buttonColor;
        sprite[10][16] = textDeadColor;
        sprite[10][17] = buttonColor;
        sprite[10][18] = textDeadColor;
        sprite[10][19] = buttonColor;
        sprite[10][21] = textDeadColor;
        sprite[10][22] = buttonColor;

        sprite[11][1] = textDeadColor;
        sprite[11][2] = textDeadColor;
        sprite[11][3] = buttonColor;
        sprite[11][6] = textDeadColor;
        sprite[11][7] = textDeadColor;
        sprite[11][8] = textDeadColor;
        sprite[11][9] = buttonColor;
        sprite[11][11] = textDeadColor;
        sprite[11][12] = textDeadColor;
        sprite[11][13] = textDeadColor;
        sprite[11][14] = buttonColor;
        sprite[11][16] = textDeadColor;
        sprite[11][17] = textDeadColor;
        sprite[11][18] = buttonColor;
        sprite[11][21] = textDeadColor;
        sprite[11][22] = buttonColor;
    }

    /**
     * builds the start text sprites if dead
     */
    public void startDead() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        sprite[0][0] = Color.WHITE;
        sprite[0][1] = Color.WHITE;
        sprite[0][2] = startDeadColor;
        sprite[0][3] = startDeadColor;
        sprite[0][4] = startDeadColor;
        sprite[0][5] = Color.WHITE;
        sprite[0][6] = Color.WHITE;
        sprite[0][7] = Color.WHITE;
        sprite[0][8] = startDeadColor;
        sprite[0][9] = startDeadColor;
        sprite[0][10] = startDeadColor;
        sprite[0][11] = Color.WHITE;
        sprite[0][12] = Color.WHITE;
        sprite[0][13] = Color.WHITE;

        sprite[1][0] = Color.WHITE;
        sprite[1][3] = startDeadColor;
        sprite[1][5] = Color.WHITE;
        sprite[1][7] = Color.WHITE;
        sprite[1][8] = startDeadColor;
        sprite[1][10] = startDeadColor;
        sprite[1][12] = Color.WHITE;

        sprite[2][0] = Color.WHITE;
        sprite[2][1] = Color.WHITE;
        sprite[2][3] = startDeadColor;
        sprite[2][5] = Color.WHITE;
        sprite[2][6] = Color.WHITE;
        sprite[2][7] = Color.WHITE;
        sprite[2][8] = startDeadColor;
        sprite[2][9] = startDeadColor;
        sprite[2][10] = startDeadColor;
        sprite[2][12] = Color.WHITE;

        sprite[3][1] = Color.WHITE;
        sprite[3][3] = startDeadColor;
        sprite[3][5] = Color.WHITE;
        sprite[3][7] = Color.WHITE;
        sprite[3][8] = startDeadColor;
        sprite[3][9] = startDeadColor;
        sprite[3][12] = Color.WHITE;

        sprite[4][0] = Color.WHITE;
        sprite[4][1] = Color.WHITE;
        sprite[4][3] = startDeadColor;
        sprite[4][5] = Color.WHITE;
        sprite[4][7] = Color.WHITE;
        sprite[4][8] = startDeadColor;
        sprite[4][10] = startDeadColor;
        sprite[4][12] = Color.WHITE;
    }

    /**
     * builds the wall sprites if dead
     */
    public void wallDead() {
        for (int i= 0; i<=23;i++){
            for (int a= 0; a<=23;a++){
                sprite [a][i] = Color.TRANSPARENT;
            }
        }

        for(int i= 0; i<=19;i++){
            for (int a= 0; a<=23;a++){
                sprite [i][a] = Color.BLACK;
            }
        }
    }
}
