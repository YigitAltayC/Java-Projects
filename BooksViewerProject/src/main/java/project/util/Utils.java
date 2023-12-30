package project.util;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    private static String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public static Calendar getCalendar(int day, int month, int year) {
        Calendar date = Calendar.getInstance();

        date.set(Calendar.YEAR, year);

        date.set(Calendar.MONTH, month+1);

        date.set(Calendar.DAY_OF_MONTH, day);

        return date;
    }

    public static String calendarToString(Calendar cal)
    {
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(cal.getTime());

        String formatted = format.format(cal.getTime());
        String[] calendarSplit = formatted.split("-");
        return months[Integer.parseInt(calendarSplit[1]) - 1] + " " + calendarSplit[2] + ", " + calendarSplit[0];


    }

    public static WritableImage getWritableImage(String imageUrl)
    {
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
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
        } catch (IOException e) {
            return null;
        }
    }
}
