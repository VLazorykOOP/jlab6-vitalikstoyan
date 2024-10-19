import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingSegment extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int RADIUS = 100; // Радіус обертання
    private double angle = 0; // Кут обертання
    private Timer timer;

    public RotatingSegment() {
        // Налаштування таймера для анімації
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Визначення центру еліпса
        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        // Розрахунок координат точки, яка рухається по еліпсу
        int pointX = (int) (centerX + RADIUS * Math.cos(angle));
        int pointY = (int) (centerY + RADIUS * Math.sin(angle));

        // Визначення координат відрізка
        int segmentStartX = pointX - 30; // Початкова точка відрізка
        int segmentStartY = pointY;
        int segmentEndX = pointX + 30; // Кінцева точка відрізка
        int segmentEndY = pointY;

        // Малювання відрізка
        g.setColor(Color.BLUE);
        g.drawLine(segmentStartX, segmentStartY, segmentEndX, segmentEndY);

        // Малювання точки, яка рухається
        g.setColor(Color.RED);
        g.fillOval(pointX - 5, pointY - 5, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Оновлення кута обертання
        angle += Math.PI / 30; // Зміна кута на 6 градусів
        repaint(); // Перемалювання компонента
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Segment");
        RotatingSegment rotatingSegment = new RotatingSegment();
        frame.add(rotatingSegment);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
