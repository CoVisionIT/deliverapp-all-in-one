package be.covisionit.deliverapp;

import be.covisionit.deliverapp.proto.DespatchAdvice;
import com.google.zxing.WriterException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws WriterException, IOException {
        DespatchAdvice despatchAdvice = TestData.buildDespatchAdvice();

        BufferedImage qrImage = QRUtil.createQrImage(despatchAdvice, 300);

        ImageIO.write(qrImage, "PNG", new File("out.png"));
        System.out.println("QR Image written to 'out.png'");

        // BASE 64 encode / decode example:
        byte[] bytes = despatchAdvice.toByteArray();
        System.out.print("original: ");
        for (byte aByte : bytes) {
            System.out.print(aByte + " ");
        }
        System.out.println();

        String qrBase64String = new BASE64Encoder().encode(bytes);
        System.out.println("base 64 string: " + qrBase64String);

        System.out.print("after decode: ");
        byte[] decodedBytes = new BASE64Decoder().decodeBuffer(qrBase64String);
        for (byte aByte : decodedBytes) {
            System.out.print(aByte + " ");
        }
        System.out.println();
    }
}
