package seedu.address.commons.events.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_IMAGE;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import seedu.address.logic.commands.PhotoCommand;
import seedu.address.logic.parser.exceptions.ImageException;

/** Reads and stores image file
 */

public class ImageStorage {

    public String execute(String path, int newPath) throws IOException {

        File fileToRead = null;
        BufferedImage image = null;

        File fileToWrite = null;

        String uniquePath = null;

        try {
            System.out.println(path);
            fileToRead = new File(path);
            image = new BufferedImage(963, 640, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(fileToRead);

            uniquePath = Integer.toString(newPath);

            fileToWrite = new File("src/main/resources/images/" + uniquePath + ".jpg");
            ImageIO.write(image, "jpg", fileToWrite);


        } catch (IOException e) {
            throw  new ImageException(String.format(MESSAGE_INVALID_IMAGE,
                    PhotoCommand.MESSAGE_FILE_PATH_NOT_FOUND));
        }

        return uniquePath;

    }

}