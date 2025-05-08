package com.nickdieda.pythonlearn.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.element.Link;


import androidx.documentfile.provider.DocumentFile;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.Style;
import com.nickdieda.pythonlearn.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

public class GenerateCertificate {

    // SharedPreferences key for storing the selected folder URI
    private static final String PREFS_NAME = "AppPrefs";
    private static final String CERTIFICATE_FILE_NAME = "Certificate.pdf";
    private static final String SELECTED_FOLDER_URI_KEY = "selectedFolderUri";
    private static boolean isCertificateGenerated = false;

    public static void generateCertificate(Context context, String fullName, int score) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String folderUriString = sharedPreferences.getString(SELECTED_FOLDER_URI_KEY, null);


            // Ensure the certificate is generated only once
            if (isCertificateGenerated) {
                Toast.makeText(context, "Certificate already generated!", Toast.LENGTH_LONG).show();
                return;
            }

            // Generate certificate file name
//            String fileName = "Certificate_" + fullName.replace(" ", "_") + ".pdf";

            // Check if the file already exists in the chosen folder
            DocumentFile pickedFolder = DocumentFile.fromTreeUri(context, Uri.parse(folderUriString));
            if (pickedFolder != null && pickedFolder.findFile(CERTIFICATE_FILE_NAME) != null) {
                Toast.makeText(context, "Certificate already exists in the selected folder!", Toast.LENGTH_LONG).show();
                return;
            }

            // Create the certificate file
            DocumentFile certificateFile = pickedFolder.createFile("application/pdf", CERTIFICATE_FILE_NAME);

            if (certificateFile == null || certificateFile.getUri() == null) {
                Toast.makeText(context, "Failed to create the certificate file!", Toast.LENGTH_LONG).show();
                return;
            }

            // Open an output stream to write the PDF
            OutputStream outputStream = context.getContentResolver().openOutputStream(certificateFile.getUri());
            if (outputStream == null) {
                Toast.makeText(context, "Error accessing output stream!", Toast.LENGTH_LONG).show();
                return;
            }
//
//            // Create PDF writer and document
//            PdfWriter writer = new PdfWriter(outputStream);
//            PdfDocument pdfDoc = new PdfDocument(writer);
//            pdfDoc.setDefaultPageSize(PageSize.A4.rotate());  // Landscape orientation

            Toast.makeText(context, "Certificate generated for " + fullName, Toast.LENGTH_LONG).show();

            // Create a writer instance
            PdfWriter writer = new PdfWriter(outputStream);

            // Create a PDF document with landscape orientation
            PdfDocument pdfDoc = new PdfDocument(writer);
            pdfDoc.setDefaultPageSize(PageSize.A3.rotate());  // Landscape orientation

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

            // Add logos and social media icons (replace with actual paths to logos)
            Bitmap logoBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.newicon); // Use your app's logo
            File logoFile = new File(context.getFilesDir(), "newicon.png");
            FileOutputStream logoOut = new FileOutputStream(logoFile);
            logoBitmap.compress(Bitmap.CompressFormat.PNG, 100, logoOut);
            logoOut.flush();
            logoOut.close();

            Image logoImage = new Image(com.itextpdf.io.image.ImageDataFactory.create(logoFile.getAbsolutePath()))
                    .setWidth(100)
                    .setHeight(100)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logoImage);




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
            Bitmap logoBitma= BitmapFactory.decodeResource(context.getResources(), R.drawable.dnd); // Use your app's logo
            File logoFil = new File(context.getFilesDir(), "dnd.png");
            FileOutputStream logoOu = new FileOutputStream(logoFil);
            logoBitma.compress(Bitmap.CompressFormat.PNG, 100, logoOu);
            logoOut.flush();
            logoOut.close();

            Image logoImag = new Image(com.itextpdf.io.image.ImageDataFactory.create(logoFil.getAbsolutePath()))
                    .setWidth(50)
                    .setHeight(50)
                    .setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(logoImag);








// Add clickable email link
            String email1 = "comb01-048722022@student.mmust.ak.ke";
            String email2 = "nickeagle888@gmail.com";

// Create a "mailto:" link for the first email
            Link emailLink1 = (Link) new Link(email1,
                    PdfAction.createURI("mailto:" + email1))
                    .setUnderline();

// Create a "mailto:" link for the second email
            Link emailLink2 = (Link) new Link(email2,
                    PdfAction.createURI("mailto:" + email2))
                    .setUnderline();

// Combine the email links into a single paragraph
            document.add(new Paragraph("To verify certificate, contact ")
                    .add(emailLink1)
                    .add(" / ") // Add a separator
                    .add(emailLink2)
                    .addStyle(thinTextStyle)
                    .setItalic()
                    .setTextAlignment(TextAlignment.CENTER));
//
//            document.add(new Paragraph("View your name on the completion list: https://python-learn-e7370-default-rtdb.firebaseio.com")
//                    .addStyle(thinTextStyle)
//                    .setItalic()
//                    .setTextAlignment(TextAlignment.CENTER))      ;


            // Close the document
            document.close();



//
//            Document document = new Document(pdfDoc);
//
//            // Add content to the PDF
//            document.add(new Paragraph("Certificate of Completion")
//                    .setFontSize(36)
//                    .setBold()
//                    .setFontColor(new DeviceRgb(255, 215, 0)) // Gold-like color
//                    .setTextAlignment(TextAlignment.CENTER));
//
//            document.add(new Paragraph("\nThis is to certify that")
//                    .setFontSize(22)
//                    .setItalic()
//                    .setFontColor(new DeviceRgb(0, 0, 0)) // Black text
//                    .setTextAlignment(TextAlignment.CENTER));
//
//            document.add(new Paragraph(fullName)
//                    .setFontSize(26)
//                    .setBold()
//                    .setFontColor(new DeviceRgb(255, 223, 186)) // Light color for emphasis
//                    .setTextAlignment(TextAlignment.CENTER));
//
//            document.add(new Paragraph("has successfully completed learning Python.\nScored " + score + " marks out of 254.")
//                    .setFontSize(18)
//                    .setFontColor(new DeviceRgb(0, 0, 0))
//                    .setTextAlignment(TextAlignment.CENTER));
//
//            document.add(new Paragraph("Dated: " + LocalDateTime.now())
//                    .setFontSize(18)
//                    .setFontColor(new DeviceRgb(0, 0, 0))
//                    .setTextAlignment(TextAlignment.CENTER));
//
//
//
//
//
//
//
//// Add clickable email link
//            String email1 = "comb01-048722022@student.mmust.ak.ke";
//            String email2 = "nickeagle888@gmail.com";
//
//// Create a "mailto:" link for the first email
//            Link emailLink1 = (Link) new Link(email1,
//                    PdfAction.createURI("mailto:" + email1))
//                    .setUnderline();
//
//// Create a "mailto:" link for the second email
//            Link emailLink2 = (Link) new Link(email2,
//                    PdfAction.createURI("mailto:" + email2))
//                    .setUnderline();
//
//// Combine the email links into a single paragraph
//            document.add(new Paragraph("To verify certificate, contact ")
//                    .add(emailLink1)
//                    .add(" / ") // Add a separator
//                    .add(emailLink2)
////                    .addStyle(thinTextStyle)
//                    .setItalic()
//                    .setTextAlignment(TextAlignment.CENTER));
//
//
//
//
//            // Close the document
//            document.close();

            // Mark the certificate as generated
            isCertificateGenerated = true;
            Toast.makeText(context, "Certificate generated successfully!", Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error generating certificate: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

