package ru.vsu.cs.volobueva;

import java.util.Locale;

import ru.vsu.cs.util.SwingUtils;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrameMain().setVisible(true);
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
}
