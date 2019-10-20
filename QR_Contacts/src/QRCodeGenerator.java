import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
/**
 * 
 * @author callicoder
 * 
 * We pulled this code from callicoder.com/generate-qr-code-in-java-usin-zxing/
 * for inspiration and references 
 *
 */
public class QRCodeGenerator {
	
	/*Global variables*/
	
	//Change this string to fit your file system
    private static final String QR_CODE_IMAGE_PATH = "QR_Contacts/qrCode-storage/MyQRCode.png";
    //This string contains the test that will be encoded
    private static final String MESSAGE = "The images are now stored directly inside the project directory :)";

    /*Method definitions*/
    
    private static void generateQRCodeImage(String text, int width, int height, String filePath)
    		throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage(MESSAGE, 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }
}