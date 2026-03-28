package constants;

import java.awt.*;

public class DimensionConstants {
    public static final Dimension FRAME_SIZE = new Dimension(540, 760);
    public static final Dimension BUTTON_PANEL_SIZE = new Dimension(
            FRAME_SIZE.width,
            (int) (FRAME_SIZE.height * 0.42));
    public static final Dimension RESULT_PANEL_SIZE = new Dimension(
            FRAME_SIZE.width / 2,
            FRAME_SIZE.height / 5);

    private DimensionConstants() {
        /* This utility class should not be instantiated */
    }
}
