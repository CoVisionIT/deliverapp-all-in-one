package be.covisionit.deliverapp;

import be.covisionit.deliverapp.proto.DespatchAdvice;
import com.google.zxing.WriterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws WriterException, IOException {
        DespatchAdvice despatchAdvice = TestData.buildDespatchAdvice();

        BufferedImage qrImage = QRUtil.createQrImage(despatchAdvice, 300);

        String filename = "out.bmp";
        ImageIO.write(qrImage, "BMP", new File(filename));
        System.out.println("QR Image written to '" + filename + "'");

        // Convert bytes to HEX string:
        byte[] bytes = despatchAdvice.toByteArray();
        System.out.println("HEX: ");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (byte aByte : bytes) {
            sb.append(String.format("%02X", aByte)).append(" ");
            count++;
            if (count % 8 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
