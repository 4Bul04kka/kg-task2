import java.awt.*;

public class Ellipse {

    private int x;
    private int y;
    private int majorSemiAxis;
    private int minorSemiAxis;
    private Color color;



    public Ellipse(final int x, final int y, final int majorSemiAxis, final int minorSemiAxis, final Color color) {
        this.x = x;
        this.y = y;
        this.majorSemiAxis = majorSemiAxis;
        this.minorSemiAxis = minorSemiAxis;
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMajorSemiAxis(int majorSemiAxis) {
        this.majorSemiAxis = majorSemiAxis;
    }

    public void setMinorSemiAxis(int minorSemiAxis) {
        this.minorSemiAxis = minorSemiAxis;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void putPixel(int x, int y, Color color, final Graphics gr) {
        gr.setColor(color);
        gr.drawLine(x,y,x,y);
    }
    void pixels4Dir(int x1, int y1, int x2, int y2, Color color, final Graphics gr) // Рисование пикселя для первого квадранта, и, симметрично, для остальных
    {
        putPixel(x1 + x2, y1 + y2, color,gr);
        putPixel(x1 + x2, y1 - y2, color,gr);
        putPixel(x1 - x2, y1 - y2, color,gr);
        putPixel(x1 - x2, y1 + y2, color,gr);
    }

    void drawEllipse(final Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        int x = 0; // Компонента x
        int y = minorSemiAxis; // Компонента y
        int powW = majorSemiAxis * majorSemiAxis; // a^2, a - большая полуось
        int powH = minorSemiAxis * minorSemiAxis; // b^2, b - малая полуось
        int delta = 4 * powH * ((x + 1) * (x + 1)) + powW * ((2 * y - 1) * (2 * y - 1)) - 4 * powW * powH; // Функция координат точки (x+1, y-1/2)
        while (powW * (2 * y - 1) > 2 * powH * (x + 1)) // Первая часть дуги
        {
            pixels4Dir(this.x, this.y, x, y,color,gr);
            if (delta < 0) // Переход по горизонтали
            {
                x++;
                delta += 4 * powH * (2 * x + 3);
            }
            else // Переход по диагонали
            {
                x++;
                delta = delta - 8 * powW * (y - 1) + 4 * powH * (2 * x + 3);
                y--;
            }
        }
        delta = powH * ((2 * x + 1) * (2 * x + 1)) + 4 * powW * ((y + 1) * (y + 1)) - 4 * powW * powH; // Функция координат точки (x+1/2, y-1)
        while (y + 1 != 0) // Вторая часть дуги, если не выполняется условие первого цикла, значит выполняется a^2(2y - 1) <= 2b^2(x + 1)
        {
            pixels4Dir(this.x, this.y, x, y,color,gr);
            if (delta < 0) // Переход по вертикали
            {
                y--;
                delta += 4 * powW * (2 * y + 3);
            }
            else // Переход по диагонали
            {
                y--;
                delta = delta - 8 * powH * (x + 1) + 4 * powW * (2 * y + 3);
                x++;
            }
        }
    }
}