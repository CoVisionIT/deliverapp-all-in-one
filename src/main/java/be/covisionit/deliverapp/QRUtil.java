package be.covisionit.deliverapp;

import be.covisionit.deliverapp.proto.DespatchAdvice;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class QRUtil {

    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    public static BufferedImage createQrImage(DespatchAdvice despatchAdvice, int size) throws WriterException {

        String qrString = new String(despatchAdvice.toByteArray(), ISO_8859_1);

        QRCodeWriter writer = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, ISO_8859_1.name());
        BitMatrix bitMatrix = writer.encode(qrString, BarcodeFormat.QR_CODE, size, size, hints);

        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_INDEXED);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Color color = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                image.setRGB(x, y, color.getRGB());
            }
        }
        return image;
    }

    public static byte[] createQrPng(DespatchAdvice despatchAdvice, int size) throws WriterException, IOException {
        BufferedImage bufferedImage = createQrImage(despatchAdvice, size);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", output);
        return output.toByteArray();
    }

}

