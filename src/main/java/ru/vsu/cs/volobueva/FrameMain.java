package ru.vsu.cs.volobueva;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JSpinner spinnerSize;
    private JSpinner spinnerLevelCount;
    private JButton buttonDraw;
    private JLabel labelImg;
    private JCheckBox checkBoxCheckUpperOrBelow;
    private JButton saveButton;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("Task12");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();

        spinnerSize.setValue(500);
        spinnerLevelCount.setValue(4);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Drawing files", "png");
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Draw squares");

        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        buttonDraw.addActionListener(e -> {
            int squareSize = (int) spinnerSize.getValue();
            int recursionDepth = (int) spinnerLevelCount.getValue();
            boolean flagAtUpper = checkBoxCheckUpperOrBelow.isSelected();

            BufferedImage image = new BufferedImage(squareSize, squareSize, BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = image.createGraphics();

            PaintSquares.paintSquares(g2d, image.getWidth(), image.getHeight(), recursionDepth, flagAtUpper);

            labelImg.setIcon(new ImageIcon(image));
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Icon drawingIcon = labelImg.getIcon();
                        BufferedImage image = new BufferedImage(drawingIcon.getIconWidth(), drawingIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = image.createGraphics();

                        drawingIcon.paintIcon(null, g2d, 0, 0);

                        g2d.dispose();

                        String file = fileChooserSave.getSelectedFile().getPath();

                        if (!file.toLowerCase().endsWith(".png")) {
                            file += ".png";
                        }

                        PaintSquares.saveDrawing(file, image);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
