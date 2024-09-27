package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.Style;

import java.io.File;
import java.time.LocalDateTime;


     import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.nickdieda.pythonlearn.R;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

        public class generateCertificate {

            public static void generateCertificate(Context context, String fullName, int score) {
                try {



                    File pythonProjectsFolder = new File(Environment.getExternalStorageDirectory(), "Python programs");


                    File scriptFile = new File(pythonProjectsFolder, "/Certificate.pdf");

                    if (scriptFile.exists()) {
                        Toast.makeText(context, "You can only claim your certificate once !!!", Toast.LENGTH_LONG).show();
              // Prompt the user to enter a new name
                    } else {

                        Toast.makeText(context, "Certificate generated for " + fullName, Toast.LENGTH_LONG).show();

                        // Create a writer instance
                        PdfWriter writer = new PdfWriter(scriptFile);

                        // Create a PDF document with landscape orientation
                        PdfDocument pdfDoc = new PdfDocument(writer);
                        pdfDoc.setDefaultPageSize(PageSize.A4.rotate());  // Landscape orientation

                        // Create a Document instance
                        Document document = new Document(pdfDoc);

                        // Define custom styles for the text
                        Style titleStyle = new Style()
                                .setFontSize(36)
                                .setBold()
                                .setFontColor(new DeviceRgb(255, 215, 0))  // Gold-like color for title
                                .setTextAlignment(TextAlignment.CENTER);

                        Style subtitleStyle = new Style()
                                .setFontSize(22)
                                .setItalic()
                                .setFontColor(new DeviceRgb(0, 0, 0))  // Light color for subtitle
                                .setTextAlignment(TextAlignment.CENTER);

                        Style bodyTextStyle = new Style()
                                .setFontSize(18)
                                .setFontColor(new DeviceRgb(0, 0, 0))  // White for regular text
                                .setTextAlignment(TextAlignment.JUSTIFIED);

                        Style thinTextStyle = new Style()
                                .setFontSize(12)
                                .setFontColor(new DeviceRgb(0, 0, 255))  // Thin white text
                                .setItalic()
                                .setTextAlignment(TextAlignment.CENTER);

                        // Set the background color for the certificate
                        document.setBackgroundColor(new DeviceRgb(139, 69, 19));  // Brown background

                        // Add a title for the certificate
                        document.add(new Paragraph("Certificate of Completion")
                                .addStyle(titleStyle)
                                .setBold()
                                .setTextAlignment(TextAlignment.CENTER));

                        // Add a space
                        document.add(new Paragraph("\n"));

                        // Add a subtitle
                        document.add(new Paragraph("This is to certify that")
                                .addStyle(subtitleStyle)
                                .setTextAlignment(TextAlignment.CENTER));

                        // Add the recipient's full name
                        document.add(new Paragraph(fullName)
                                .setFontSize(26)
                                .setBold()
                                .setFontColor(new DeviceRgb(255, 223, 186))  // Light color for emphasis
                                .setTextAlignment(TextAlignment.CENTER));

                        // Add score and certification message
                        document.add(new Paragraph("has successfully completed learning Python.\nScored " + score + " marks out of 254.")
                                .addStyle(bodyTextStyle)
                                .setTextAlignment(TextAlignment.CENTER));

                        // Add the date
                        document.add(new Paragraph("Dated: " + LocalDateTime.now())
                                .addStyle(bodyTextStyle)
                                .setTextAlignment(TextAlignment.CENTER));

                        // Add signature area
                        document.add(new Paragraph("\n"));

                        Bitmap syBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sygnat); // Use your app's logo
                        File sy = new File(context.getFilesDir(), "syg.png");
                        FileOutputStream syOut = new FileOutputStream(sy);
                        syBitmap.compress(Bitmap.CompressFormat.PNG, 100, syOut);
                        syOut.flush();
                        syOut.close();

                        Image syImage = new Image(com.itextpdf.io.image.ImageDataFactory.create(sy.getAbsolutePath()))
                                .setWidth(100)
                                .setHeight(50)
                                .setHorizontalAlignment(HorizontalAlignment.RIGHT);
                        document.add(syImage);

                        document.add(new Paragraph("_________________________")
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setHorizontalAlignment(HorizontalAlignment.RIGHT));
                        document.add(new Paragraph("Signature")
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                                .setFontSize(12));

                        // Add logos and social media icons (replace with actual paths to logos)
                        Bitmap logoBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.les); // Use your app's logo
                        File logoFile = new File(context.getFilesDir(), "logo.png");
                        FileOutputStream logoOut = new FileOutputStream(logoFile);
                        logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, logoOut);
                        logoOut.flush();
                        logoOut.close();

                        Image logoImage = new Image(com.itextpdf.io.image.ImageDataFactory.create(logoFile.getAbsolutePath()))
                                .setWidth(50)
                                .setHeight(50)
                                .setHorizontalAlignment(HorizontalAlignment.LEFT);
                        document.add(logoImage);


                        document.add(new Paragraph("View your name on the completion list: https://python-learn-e7370-default-rtdb.firebaseio.com")
                                .addStyle(thinTextStyle)
                                .setItalic()
                                .setTextAlignment(TextAlignment.CENTER))      ;

                        // Close the document
                        document.close();


                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


