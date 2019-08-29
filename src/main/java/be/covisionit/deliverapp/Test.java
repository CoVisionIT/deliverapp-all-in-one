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

        ImageIO.write(qrImage, "PNG", new File("out.png"));
    }
}
