package com.example.util;

import com.example.model.Osiguranje;
import com.example.model.PovredjeniVaseVozilo;
import com.example.model.PutnikSvedok;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class PDFView extends AbstractITextPdfView {


    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        ArrayList<PutnikSvedok> ps = (ArrayList<PutnikSvedok>) model.get("promenljiva");

        //Image image = Image.getInstance(imageLocation);
        //document.add(image);
        document.add(new Paragraph("Izvestaj"));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.ORANGE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Naziv", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Broj ugovora", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Broj zalenog kartona", font));
        table.addCell(cell);

        for (PutnikSvedok o : ps) {
            table.addCell(o.getIme());
            table.addCell(o.getAdresa());
            table.addCell(o.getTelefon());
        }

        document.add(table);
    }
}

