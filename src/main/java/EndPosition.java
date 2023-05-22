import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;

/**
 * Implementation of the ILineDrawer interface that won't draw a line,
 * but that will allow us to get the Y-position at the end of the file.
 */
public class EndPosition implements ILineDrawer {

    /**
     * A Y-position.
     */
    protected float y;

    /**
     * Gets the Y-position.
     *
     * @return the Y-position
     */
    public float getY() {
        return y;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#draw(com.itextpdf.kernel.pdf.canvas.PdfCanvas, com.itextpdf.kernel.geom.Rectangle)
     */
    @Override
    public void draw(PdfCanvas pdfCanvas, Rectangle rect) {
        this.y = rect.getY();
    }

    /* (non-Javadoc)
     * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getColor()
     */
    @Override
    public Color getColor() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#getLineWidth()
     */
    @Override
    public float getLineWidth() {
        return 0;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setColor(com.itextpdf.kernel.color.Color)
     */
    @Override
    public void setColor(Color color) {
    }

    /* (non-Javadoc)
     * @see com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer#setLineWidth(float)
     */
    @Override
    public void setLineWidth(float lineWidth) {
    }
}
