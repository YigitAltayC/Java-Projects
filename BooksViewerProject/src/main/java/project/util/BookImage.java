package project.util;

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

    public BufferedImage getImage() {
        return image;
    }
}
