package DegnRun;

import graphics.Color;
import graphics.Movable;
import graphics.SpriteGraphicElement;

/**
 * Prof. Degen, the Hero/ playable character in this game
 */
public class Degen extends SpriteGraphicElement implements Movable {

    /** starting frame is set to 0*/
    private int frame=0;
    /** starting frame for jumping is set to 0 */
    private int jumpFrame=0;
    /**starting counter for jumps is set to 0   */
    private int jumpCount = 0;
    /** starting frame for double jumping is set to 0 */
    private int doubleJumpFrame =0;
    /**starting frame for sliding is set to 0*/
    private int slideFrame=0;
    /** starting value of jumping is set to false */
    private boolean jumping = false;
    /** starting value of double jumping is set to false */
    private boolean DoubleJumping = false;
    /** starting value of sliding is set to false */
    private boolean sliding = false;

    /** color of the pants */
    private final Color pantsColor = new Color (36,36,75);
    /** color of the shirt */
    private final Color shirtColor = new Color (109,125,221);
    /** color of the face */
    private final Color faceColor = new Color (255,225,196);
    /** color of the hair */
    private final Color hairColor = new Color (255,247,85);
    /** color of the arms */
    private final Color armsColor = new Color (71,90,205);

    /**
     * Constructs a character
     */
    public Degen() {
        super(1,16);
        sprite = new Color [7][5];
        frame0();
    }

    /**
     * updates movement of the character
     */
    @Override
    public void update(){
        if (jumping) {
            setToJump();
        } else if(sliding && !DoubleJumping){
            setToSlide();
        } else if (DoubleJumping){
            setDoubleJump();
        } else {
            walk();
        }
    }

    /**
     * movement when character hits an obstacle
     *
     * @param o Obstacle
     * @return true if hit
     */
    public boolean hits (Obstacle o){
        if(this.x <= o.getX()+3 && this.x >=o.getX()-3 && this.y >= o.getY()-6 && this.y <= o.getY()){
            o.setX(24);
            return true;
        }else return false;
    }

    /**
     * initializes values for jumping
     */
    public void setToJump () {
        jumping=true;
        jump();
    }

    /**
     * initializes values for double jumping
     */
    public void setDoubleJump() {
        jumping = false;
        jumpFrame = 0;
        DoubleJumping = true;
        DoubleJump();
        jumpCount=0;
    }

    /**
     * the double jumping of the character for tall obstacles
     */
    public void DoubleJump (){
        if(doubleJumpFrame ==0 || doubleJumpFrame ==1){
            frame4();
            moveTo(1, 9);
        } else if(doubleJumpFrame ==2){
            frame4();
            moveTo(1, 7);
        } else if(doubleJumpFrame ==3){
            frame4();
            moveTo(1, 5);
        } else if(doubleJumpFrame ==4){
            frame4();
            moveTo(1, 4);
        } else if(doubleJumpFrame ==5 || doubleJumpFrame ==6 || doubleJumpFrame ==7){
            frame5();
            moveTo(1, 3);
        } else if(doubleJumpFrame ==8){
            frame4();
            moveTo(1, 4);
        } else if(doubleJumpFrame ==9){
            frame4();
            moveTo(1, 5);
        } else if(doubleJumpFrame ==10){
            frame4();
            moveTo(1, 7);
        } else if(doubleJumpFrame ==11){
            frame4();
            moveTo(1, 10);
        } else if(doubleJumpFrame ==12){
            frame4();
            moveTo(1, 14);
            DoubleJumping = false;
            moveTo(1, 16);
        }
        doubleJumpFrame = (doubleJumpFrame + 1) % 13;
    }

    /**
     * the jumping of the character for small obstacles
     */
    public void jump() {
        if(jumpFrame ==0 || jumpFrame ==1){
            frame3();
            moveTo(1, 14);
        } else if(jumpFrame ==2){
            frame4();
            moveTo(1, 12);
        } else if(jumpFrame ==3){
            frame4();
            moveTo(1, 10);
            jumpCount=1;
        } else if(jumpFrame ==4 || jumpFrame ==5 || jumpFrame ==6){
            frame5();
            moveTo(1, 10);
        } else if(jumpFrame ==7){
            frame4();
            moveTo(1, 11);
        } else if(jumpFrame ==8){
            frame4();
            moveTo(1, 12);
            jumping = false;
            moveTo(1, 16);
        }
        jumpFrame = (jumpFrame + 1) % 9;
    }

    /**
     *counts the jumps
     */
    public int getJumpCount(){
        return jumpCount;
    }

    /**
     * initializes values for sliding
     */
    public void setToSlide () {
        sliding=true;
        slide();
    }

    /**
     * the sliding of the character for air obstacles
     */
    public void slide() {
        if(slideFrame ==0 || slideFrame ==1){
            frame6();
        } else if(slideFrame ==2 || slideFrame ==3){
            frame7();
        } else if(slideFrame ==4 || slideFrame ==5 || slideFrame ==6 || slideFrame ==7){
            frame8();
        } else if(slideFrame ==8){
            frame8();
            sliding = false;
        }
        slideFrame = (slideFrame + 1) % 9;
    }

    /**
     * the walking of the character when no obstacle is around
     */
    public void walk() {
        if(frame ==0){
            frame0();
        } else if(frame ==1){
            frame1();
        } else if(frame ==2){
            frame2();
        }
        frame = (frame + 1) % 3;
    }

    /**
     * initializes values for all colors of frame 0
     * sprite for walking
     */
    private void frame0(){
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = pantsColor;
        sprite [6][2] = Color.TRANSPARENT;
        sprite [6][3] = pantsColor;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = armsColor;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = armsColor;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 1
     * sprite for walking
     */
    private void frame1() {
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = Color.TRANSPARENT;
        sprite [6][2] = pantsColor;
        sprite [6][3] = pantsColor;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = Color.TRANSPARENT;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = armsColor;
        sprite [4][4] = armsColor;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = shirtColor;
        sprite [3][2] = armsColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 2
     * sprite for walking
     */
    private void frame2() {
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = pantsColor;
        sprite [6][2] = pantsColor;
        sprite [6][3] = Color.TRANSPARENT;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = Color.TRANSPARENT;
        sprite [4][1] = shirtColor;
        sprite [4][2] = armsColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = Color.TRANSPARENT;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 3
     * sprite for jumping
     */
    private void frame3(){
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = pantsColor;
        sprite [6][2] = Color.TRANSPARENT;
        sprite [6][3] = pantsColor;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = armsColor;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = armsColor;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 4
     * sprite for jumping
     */
    private void frame4(){
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = Color.TRANSPARENT;
        sprite [6][2] = Color.TRANSPARENT;
        sprite [6][3] = Color.TRANSPARENT;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = Color.TRANSPARENT;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = Color.TRANSPARENT;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = Color.TRANSPARENT;

        sprite [3][0] = armsColor;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = armsColor;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 5
     * sprite for jumping
     */
    private void frame5(){
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = Color.TRANSPARENT;
        sprite [6][2] = Color.TRANSPARENT;
        sprite [6][3] = Color.TRANSPARENT;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = Color.TRANSPARENT;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = Color.TRANSPARENT;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = Color.TRANSPARENT;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = armsColor;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = armsColor;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 6
     * sprite for sliding
     */
    private void frame6(){
        //sets pixels for pants
        sprite [6][0] = Color.TRANSPARENT;
        sprite [6][1] = pantsColor;
        sprite [6][2] = Color.TRANSPARENT;
        sprite [6][3] = pantsColor;
        sprite [6][4] = Color.TRANSPARENT;

        sprite [5][0] = Color.TRANSPARENT;
        sprite [5][1] = pantsColor;
        sprite [5][2] = pantsColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = armsColor;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = shirtColor;
        sprite [4][4] = armsColor;

        sprite [3][0] = Color.TRANSPARENT;
        sprite [3][1] = armsColor;
        sprite [3][2] = shirtColor;
        sprite [3][3] = shirtColor;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = faceColor;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = hairColor;
        sprite [1][2] = faceColor;
        sprite [1][3] = faceColor;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = hairColor;
        sprite [0][2] = hairColor;
        sprite [0][3] = hairColor;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 7
     * sprite for sliding
     */
    private void frame7(){
        //sets pixels for pants
        sprite [6][0] = armsColor;
        sprite [6][1] = pantsColor;
        sprite [6][2] = pantsColor;
        sprite [6][3] = pantsColor;
        sprite [6][4] = pantsColor;

        sprite [5][0] = armsColor;
        sprite [5][1] = shirtColor;
        sprite [5][2] = shirtColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = armsColor;
        sprite [4][1] = shirtColor;
        sprite [4][2] = shirtColor;
        sprite [4][3] = armsColor;
        sprite [4][4] = armsColor;

        sprite [3][0] = faceColor;
        sprite [3][1] = faceColor;
        sprite [3][2] = faceColor;
        sprite [3][3] = Color.TRANSPARENT;
        sprite [3][4] = Color.TRANSPARENT;

        //sets pixels for face and hair
        sprite [2][0] = hairColor;
        sprite [2][1] = faceColor;
        sprite [2][2] = faceColor;
        sprite [2][3] = Color.TRANSPARENT;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = hairColor;
        sprite [1][1] = hairColor;
        sprite [1][2] = hairColor;
        sprite [1][3] = Color.TRANSPARENT;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = Color.TRANSPARENT;
        sprite [0][2] = Color.TRANSPARENT;
        sprite [0][3] = Color.TRANSPARENT;
        sprite [0][4] = Color.TRANSPARENT;
    }

    /**
     * initializes values for all colors of frame 8
     * sprite for sliding
     */
    private void frame8(){
        //sets pixels for pants
        sprite [6][0] = armsColor;
        sprite [6][1] = armsColor;
        sprite [6][2] = shirtColor;
        sprite [6][3] = pantsColor;
        sprite [6][4] = pantsColor;

        sprite [5][0] = faceColor;
        sprite [5][1] = faceColor;
        sprite [5][2] = faceColor;
        sprite [5][3] = pantsColor;
        sprite [5][4] = Color.TRANSPARENT;

        //sets pixels for shirt and arms
        sprite [4][0] = hairColor;
        sprite [4][1] = faceColor;
        sprite [4][2] = faceColor;
        sprite [4][3] = Color.TRANSPARENT;
        sprite [4][4] = Color.TRANSPARENT;

        sprite [3][0] = hairColor;
        sprite [3][1] = hairColor;
        sprite [3][2] = hairColor;
        sprite [3][3] = Color.TRANSPARENT;
        sprite [3][4] = Color.TRANSPARENT;

        sprite [2][0] = Color.TRANSPARENT;
        sprite [2][1] = Color.TRANSPARENT;
        sprite [2][2] = Color.TRANSPARENT;
        sprite [2][3] = Color.TRANSPARENT;
        sprite [2][4] = Color.TRANSPARENT;

        sprite [1][0] = Color.TRANSPARENT;
        sprite [1][1] = Color.TRANSPARENT;
        sprite [1][2] = Color.TRANSPARENT;
        sprite [1][3] = Color.TRANSPARENT;
        sprite [1][4] = Color.TRANSPARENT;

        sprite [0][0] = Color.TRANSPARENT;
        sprite [0][1] = Color.TRANSPARENT;
        sprite [0][2] = Color.TRANSPARENT;
        sprite [0][3] = Color.TRANSPARENT;
        sprite [0][4] = Color.TRANSPARENT;
    }
}
