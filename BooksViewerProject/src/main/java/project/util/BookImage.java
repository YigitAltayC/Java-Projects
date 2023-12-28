package project.util;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BookImage {

    private BufferedImage image;

    public BookImage(String URL)
    {
        try {
            image = ImageIO.read(new URL(URL));
        } catch (IOException e) {
            throw new BookException("Invalid URL for book image: PARAMETER:" + URL);
        }
    }

    public BufferedImage getImageVariable() {
        return image;
    }

    public WritableImage getImage() {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return wr;
    }
}
