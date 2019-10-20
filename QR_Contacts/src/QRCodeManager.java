import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author Kallen Marcavage
 * Credit due to callicoder.com/generate-qr-code-in-java-usin-zxing/ for instruction on integrating the libraries required
 * to encode and decode QR codes as well as heavy influence on the effective implementation of essential code.
 */

/* This Java class merges the responsibilities of the QRCodeGenerator and QRCodeReader classes to limit the amount of memory
 * consumed when importing the libraries. Accessor and Mutator methods are also included to allow for integration with SQL
 * code managing the storage of data while securing the contents of this class.
 */
public class QRCodeManager {

//	*** Global Variables ***
	
	private static String QR_CODE_IMAGE_PATH	= "QR_Contacts/qrCode-storage/MyQRCode.png";
	private static String QR_CODE_MESSAGE		= "Error: mutator method did not properly insert message";
	
//	*** Method Definitions ***
	
	public String getMessage() {
		return QR_CODE_MESSAGE;
	}
	
	public void setMessage(String message) {
		QR_CODE_MESSAGE = message;
	}
	
	public String getPath() {
		return QR_CODE_IMAGE_PATH;
	}
	
	public void setPath(String path) {
		QR_CODE_IMAGE_PATH = path;
	}
	
	/**
	 * This method writes a PNG format image of the QR code generated based on a given string.
	 * @param text 				- the string to be encoded
	 * @param width				- the width of the QR code
	 * @param height			- the height of the QR code
	 * @param filePath			- the path for the PNG image
	 * @throws WriterException	- in the case of an internal failure with the writer
	 * @throws IOException		- in the case that the desired file path is invalid
	 */
	private static void encodeQRCode(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath("QR_Contacts/qrCode-storage/" + filePath); // Automatically routes codes to dedicated storage
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
	
	/**
	 * This method takes an image format file containing a QR code and decodes the message it contains.
	 * @param qrCodeimage	- the file path for the image to be decoded
	 * @return				- the message formatted as a String object
	 * @throws IOException	- in the case that the desired file path is invalid
	 */
	private static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
	
//	*** Main Method ***
	/**
	 * The main method is used exclusively for local development testing
	 * @param args
	 */
//	public static void main(String[] args) {
//		
//		//Generate a QR code with the given message and save it in the given path
//		
//        try {
//            encodeQRCode(QR_CODE_MESSAGE, 350, 350, QR_CODE_IMAGE_PATH);
//        } catch (WriterException e) {
//            System.out.println("EXCEPTION: Could not generate QR Code, WriterException :: " + e.getMessage());
//        } catch (IOException e) {
//            System.out.println("EXCEPTION: Could not generate QR Code, IOException :: " + e.getMessage());
//        }
//        
//        //Attempt to read said QR code and decode the message and return to stdout for dev verification.
//        
//        try {
//            File file = new File(QR_CODE_IMAGE_PATH);
//            String decodedText = decodeQRCode(file);
//            if(decodedText == null) {
//                System.out.println("No QR Code found in the image");
//            } else {
//                System.out.println("Found text: " + decodedText);
//            }
//        } catch (IOException e) {
//            System.out.println("EXCEPTION: Could not decode QR Code, IOException :: " + e.getMessage());
//        }
//    }
}
