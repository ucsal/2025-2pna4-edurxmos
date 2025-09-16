package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Clique para inserir figuras");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

            JPanel controls = new JPanel();
            JButton colorBtn = new JButton("Cor...");
            JButton clearBtn = new JButton("Limpar");

            JComboBox<ShapeType> shapeSelector = new JComboBox<>(ShapeType.values());

            colorBtn.addActionListener(e -> {
                Color chosen = JColorChooser.showDialog(frame, "Escolha uma cor", panel.getCurrentColor());
                if (chosen != null) {
                    panel.setCurrentColor(chosen);
                }
            });

            clearBtn.addActionListener(e -> panel.clear());
            shapeSelector.addActionListener(e -> panel.setCurrentShape((ShapeType) shapeSelector.getSelectedItem()));

            controls.add(new JLabel("Forma:"));
            controls.add(shapeSelector);
            controls.add(colorBtn);
            controls.add(clearBtn);

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.NORTH);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
