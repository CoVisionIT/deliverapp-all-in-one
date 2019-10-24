# DeliverApp-all-in-one

This repo provides a java 1.6 jar to generate DeliverApp QR code images or just the binary content for it. 
All required dependencies are packed within the all-in-one jar.

To be used as an external jar when writing a SAP PI JAVA Mapping ("Imported Archive").

The jar can be found here: 
[all-in-one-0.0.1-jar-with-dependencies](artifacts/be/covisionit/deliverapp-all-in-one/0.0.1/deliverapp-all-in-one-0.0.1-jar-with-dependencies.jar).


#### Example usage

Create a JAVA Mapping in SAP PI to build a DespatchAdvice object and convert it to a HEX string...

    import be.covisionit.deliverapp.TestData;
    import be.covisionit.deliverapp.proto.DespatchAdvice;

    ...

    DespatchAdvice despatchAdvice = TestData.buildDespatchAdvice();
    byte[] bytes = despatchAdvice.toByteArray();

    StringBuilder sb = new StringBuilder();
    for (byte aByte : bytes) {
        sb.append(String.format("%02X", aByte));
    }
    System.out.println(sb.toString());

Then, in SAP Smart Forms, use the invocation code \BIN:xxyyzz\ where xxyyzz is the output of the code above (1A1E0A1C42657374656C626F6E20313231...)

Unfortunately, the length of a symbol in a Smartform is restricted to 255 characters.
So you'll have to split the string above in multiple pieces before passing them to the QR tag:

    <QR>\BIN:&qr_string_part1(c)&&qr_string_part2(c)...&&qr_string_partN(c)&\</>

#### Alternative usage

If that didn't work for you, try the QRUtil.createQrPng() method that returns the bytes of a PNG QR image.

    import be.covisionit.deliverapp.TestData;
    import be.covisionit.deliverapp.proto.DespatchAdvice;

    ... 

    DespatchAdvice despatchAdvice = TestData.buildDespatchAdvice();
    byte[] qrImage = QRUtil.createQrPng(despatchAdvice, 300);
