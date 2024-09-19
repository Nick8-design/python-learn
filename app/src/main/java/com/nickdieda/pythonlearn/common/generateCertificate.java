package com.nickdieda.pythonlearn.common;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

public class generateCertificate {

    public static void generateCertificate(Context context,String fullName, int score) {
        try {
            // Create a path to save the certificate
            //String filePath = Environment.getExternalStorageDirectory().getPath() + "/Certificate.pdf";
            File pythonProjectsFolder = new File(Environment.getExternalStorageDirectory(), "Python programs");
            // Create a writer instance
            File scriptFile = new File(pythonProjectsFolder,   "/Certificate.pdf");
            Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show();

            PdfWriter writer = new PdfWriter(scriptFile);

            // Create a PDF document
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Create a Document
            Document document = new Document(pdfDoc);

            // Add content to the document (customize it for a certificate layout)
            document.add(new Paragraph("\t\t\t\tCertificate of Completion"));
            document.add(new Paragraph("\n\n\t\tThis is to certify that"));
            document.add(new Paragraph(fullName));
            document.add(new Paragraph("has scored " + score + " marks"));
            document.add(new Paragraph("Date "+ LocalDateTime.now()));

            // Close the document
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}