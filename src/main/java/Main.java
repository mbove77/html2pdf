import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.renderer.IRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            String htmlFilePath = "E:\\infosis\\htmlToPdf\\src\\main\\java\\grande.html";
            String pdfFilePath = "E:\\infosis\\htmlToPdf\\src\\main\\java\\grande.pdf";

            String html = new String(Files.readAllBytes(Paths.get(htmlFilePath)));

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFilePath));
            Document doc = new Document(pdfDoc, new PageSize(PageSize.A4.getWidth(), 10000));

            List<IElement> tempElements = HtmlConverter.convertToElements(html);
            for (IElement element : tempElements) {
                IBlockElement blockElement = (IBlockElement) element;
                doc.add(blockElement);
            }

            doc.close();

            PdfPage lastPage = pdfDoc.getLastPage();

            // Obtenemos el área de contenido de la última página
            Rectangle contentArea = lastPage.getPageSize();


            // Establecemos la nueva altura
            pdfDoc.close();

/*

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            float pageWidth = 200F;
            float pageHeight = PageSize.A4.getHeight();
            float topAndBottonMargin = 15F;
            float leftAndRightMargin = 5F;

            ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream();
            PdfDocument tempPdf = new PdfDocument(new PdfWriter(tempOutputStream));
            Document tempDocument = new Document(tempPdf, new PageSize(200.0F, pageHeight), false);
            tempDocument.setMargins(topAndBottonMargin, leftAndRightMargin, topAndBottonMargin, leftAndRightMargin);


            float tempDocSize = 0f;
            List<IElement> tempElements = HtmlConverter.convertToElements(html);
            for (IElement element : tempElements) {
                IBlockElement blockElement = (IBlockElement) element;
                tempDocument.add(blockElement);
            }

            float totalHeight = 0;
            int numberOfPages = tempPdf.getNumberOfPages();
            for(int i = 1; i <= numberOfPages; i++) {
                PdfPage page = tempPdf.getPage(i);
                float contentHeight = page.getPageSize().getHeight() - tempDocument.getTopMargin() - tempDocument.getBottomMargin();
                totalHeight += contentHeight;
            }

            System.out.println("La altura total del contenido es: " + totalHeight);


            tempDocument.flush();
            tempDocument.close();
            tempPdf.close();


            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdf = new PdfDocument(writer);
            // hay que hacer que el doc size sea x paginas + las pagesize
            Document document = new Document(pdf, new PageSize(pageWidth, totalHeight));
            document.setMargins(topAndBottonMargin, leftAndRightMargin, topAndBottonMargin, leftAndRightMargin);

            List<IElement> elements = HtmlConverter.convertToElements(html);
            for (IElement element : elements) {
                document.add((IBlockElement) element);
            }

            document.close();
            pdf.close();
*/

            System.out.println("¡Archivo HTML convertido a PDF exitosamente!");
        } catch (IOException e) {
            System.out.println("Error al convertir el archivo HTML a PDF: " + e.getMessage());
        }
    }
}
