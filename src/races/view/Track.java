package races.view;

import races.Commons;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Track implements Commons {
    private int numberOfTracks;

    Track(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    void draw(Graphics g) {
        Font font = new Font("Arial Black", Font.BOLD, 19);
        g.setFont(font);
        for (int i = 0; i < numberOfTracks; i++) {
            g.setColor(Color.BLUE);
            g.drawString(String.valueOf(i + 1), 0, TRACK_WIDTH * (i + 1));
            g.drawLine(START, 0, START, TRACK_WIDTH * (i + 1));
            g.setColor(Color.RED);
            g.drawLine(FINISH, 0, FINISH, TRACK_WIDTH * (i + 1));

            g.setColor(Color.BLACK);
            g.drawLine(0, TRACK_WIDTH * (i + 1), TRACK_LENGTH, TRACK_WIDTH * (i + 1));
        }

        Font rotatedFont = rotateFont(font, 90);
        g.setFont(rotatedFont);
        g.setColor(Color.BLUE);
        g.drawString("START", START, 2);
        g.setColor(Color.RED);
        g.drawString("FINISH", FINISH, 2);
    }

    private Font rotateFont(Font f, int radians) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(radians), 0, 0);

        return f.deriveFont(affineTransform);
    }
}
