package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private static final int DEFAULT_SIZE = 60;
    private final List<ColoredShape> shapes = new ArrayList<>();

    private Point startDrag = null;
    private ShapeType currentShape = ShapeType.CIRCLE;
    private Color currentColor = new Color(30,144,255);

    DrawingPanel() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && startDrag == null) {
                    int size = Math.max(DEFAULT_SIZE, 10);
                    Shape s = createShape(e.getPoint(), size);
                    shapes.add(new ColoredShape(s, currentColor));
                    repaint();
                }
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private Shape createShape(Point p, int size) {
        return switch (currentShape) {
            case CIRCLE -> new Ellipse2D.Double(p.x, p.y, size, size);
            case RECTANGLE -> new Rectangle2D.Double(p.x, p.y, size, size);
        };
    }

    void clear() {
        shapes.clear();
        repaint();
    }

    public void setCurrentShape(ShapeType shape) {
        this.currentShape = shape;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ColoredShape cs : shapes) {
            g2.setColor(cs.color());
            g2.fill(cs.shape());
            g2.setColor(new Color(0,0,0,70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(cs.shape());
        }

        g2.dispose();
    }

    private record ColoredShape(Shape shape, Color color) {}
}
