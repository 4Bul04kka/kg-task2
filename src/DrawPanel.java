import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel{

    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;

    private Ellipse ellipse;

    public DrawPanel(final int width, final int height) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.ellipse = new Ellipse( 600, 300, 200, 1000,  Color.RED);

    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        ellipse.drawEllipse(gr);

    }

    }
