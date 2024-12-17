package aurionpro.erp.ipms.utility.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterTable extends PdfPageEventHelper {

	protected PdfPTable header, footer;

    public HeaderFooterTable(PdfPTable header, PdfPTable footer) {
        this.header = header;
        this.footer = footer;
    }

    public void onStartPage(PdfWriter writer, Document document) {
    	header.writeSelectedRows(0, -1, 20, 830, writer.getDirectContent());
    }

    public void onEndPage(PdfWriter writer, Document document) {
        footer.writeSelectedRows(0, -1, 20, 35, writer.getDirectContent());
    }
}
