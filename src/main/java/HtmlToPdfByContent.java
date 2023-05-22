import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;

/**
 * Converts an HTML page to a PDF that consists of a single page.
 */
public class HtmlToPdfByContent {
    public static void main(String[] args) throws IOException, TransformerException {
        String htmlFilePath = "E:\\infosis\\htmlToPdf\\src\\main\\java\\htmls\\chico.html";
        String DEST = "E:\\infosis\\htmlToPdf\\src\\main\\java\\pdfs\\chico.pdf";

        File file = new File(DEST);

        String html = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
        HtmlToPdfByContent app = new HtmlToPdfByContent();
        app.createPdf(html, DEST);
    }

    public void createPdf(String html, String dest) throws IOException {
        float pageWidth = 200F;
        float maxPageHeight = 14400F;
        float bottomMargin = 14;

        ConverterProperties properties = new ConverterProperties();
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        PageSize customPageSize =  new PageSize(pageWidth, maxPageHeight);
        pdf.setDefaultPageSize(customPageSize);

        Document document = HtmlConverter.convertToDocument(html, pdf, properties);

        EndPosition endPosition = new EndPosition();
        LineSeparator separator = new LineSeparator(endPosition);
        document.add(separator);
        document.getRenderer().close();

        PdfPage page = pdf.getPage(pdf.getNumberOfPages());
        float y = endPosition.getY() - bottomMargin;
        page.setMediaBox(new Rectangle(0, y, pageWidth, maxPageHeight -y));

        document.close();
        pdf.close();
        writer.flush();
        writer.close();
    }

}
