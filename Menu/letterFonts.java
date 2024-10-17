package Menu;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class letterFonts {

    public Font titleFont, subMenusFont, font3, font4, font5, font6;

    public letterFonts(){
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/AceRecords.ttf")).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(titleFont);

            subMenusFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/8-bit.ttf")).deriveFont(50f);
            ge.registerFont(subMenusFont);

            font3 = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/8-bit.ttf")).deriveFont(25f);
            ge.registerFont(font3);

            font4 = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/8-bit.ttf")).deriveFont(15f);
            ge.registerFont(font4);

            font5 = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/8-bit.ttf")).deriveFont(13f);
            ge.registerFont(font5);

            font6 = Font.createFont(Font.TRUETYPE_FONT, new File("src/letterFonts/8-bit.ttf")).deriveFont(11f);
            ge.registerFont(font6);
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
