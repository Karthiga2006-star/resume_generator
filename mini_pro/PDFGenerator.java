import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PDFGenerator {
    public static void generatePDF(String fileName, String name, String email, String phone, String summary,
                                   String skills, String experience, String education, String template) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        // Add a logo at the top
        Image logo = Image.getInstance("logo.png");  // Ensure the file "logo.png" is in the same folder
        logo.scaleToFit(100, 100);  // Resize the logo to fit
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);

        // Add decorative heading
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLUE);
        document.add(new Paragraph("Resume", headingFont));
        document.add(new Paragraph("\n"));

        // Add section headings
        Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);

        // Name and Contact Info
        document.add(new Paragraph("Personal Information", sectionFont));
        document.add(new Paragraph("Name: " + name));
        document.add(new Paragraph("Email: " + email));
        document.add(new Paragraph("Phone: " + phone));
        document.add(new Paragraph("\n"));

        // Summary
        document.add(new Paragraph("Summary", sectionFont));
        document.add(new Paragraph(summary));
        document.add(new Paragraph("\n"));

        // Skills
        document.add(new Paragraph("Skills", sectionFont));
        document.add(new Paragraph(skills));
        document.add(new Paragraph("\n"));

        // Experience
        document.add(new Paragraph("Experience", sectionFont));
        document.add(new Paragraph(experience));
        document.add(new Paragraph("\n"));

        // Education
        document.add(new Paragraph("Education", sectionFont));
        document.add(new Paragraph(education));
        document.add(new Paragraph("\n"));

        // Footer
        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 14, BaseColor.GRAY);
        document.add(new Paragraph("\nGenerated using Resume Builder", footerFont));

        document.close();
    }
}


