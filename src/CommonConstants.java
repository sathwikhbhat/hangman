import java.awt.*;

public class CommonConstants {

    public static final String DATA_PATH = "resources/data.txt";
    public static final String IMAGE_PATH = "resources/1.png";
    public static final String FONT_PATH = "resources/Cartoonero.ttf";

    public static final Color PRIMARY_COLOUR = Color.decode("#142120");
    public static final Color SECONDARY_COLOUR = Color.decode("#FCA311");
    public static final Color BACKGROUND_COLOUR = Color.decode("#101828");

    public static final Dimension FRAME_SIZE = new Dimension(540, 760);
    public static final Dimension Button_PANEL_SIZE = new Dimension(FRAME_SIZE.width, (int) (FRAME_SIZE.height * 0.42));
    public static final Dimension RESULT_PANEL_SIZE = new Dimension((int)FRAME_SIZE.width/2, (int)FRAME_SIZE.height/5);

}