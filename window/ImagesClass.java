package window;

import javax.swing.*;
import java.awt.*;

public class ImagesClass extends JPanel {
    private Image backgroundImage;

    public ImagesClass(String imagePath) {
        setBackgroundImage(imagePath);

    }

    public void setBackgroundImage(String imagePath) {
        this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        repaint(); // Fuerza que el componente se redibuje después de cambiar la imagen
    }

    public void removeBackgroundImage() {
        this.backgroundImage = null; // Asignar null para "quitar" la imagen
        repaint(); // Forzar el repintado para que se refleje el cambio
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen ocupando todo el espacio del panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
