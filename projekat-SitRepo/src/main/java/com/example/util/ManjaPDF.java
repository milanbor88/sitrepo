package com.example.util;

import com.example.model.PutnikSvedok;
import com.example.model.VozacNezgodaManja;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Stream;

public class ManjaPDF extends AbstractITextPdfView {

    private Stream<String> svedokStream = Stream.of("Ime", "Prezime", "Adresa","Telefon","Br. lične karte");
    private Stream<String> drugoStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vaseStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vanStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> dokaziStream = Stream.of("Dokazi vozila klijenta");

    private Stream<String> dokaziTudjeStream = Stream.of( "Dokazi vozila drugog učesnika");


    final float fntSize = 6.7f;
    final float fntSizeHeade = 7.4f;
    final float lineSpacing = 10f;
    final float intentionRight = 80f;


    private void addTableHeader(PdfPTable table, Stream<String> headerStream) {
        headerStream
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(lineSpacing,columnTitle,
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    table.addCell(header);
                });
    }

    public static final String FONT = "src/main/resources/fonts/DejaVuSans.ttf";

    BaseFont bf2;

    {
        try {
            bf2 = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Font freeSans = new Font(bf2, fntSize);
    Font f1 = FontFactory.getFont(FONT,  BaseFont.IDENTITY_H, true);

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        VozacNezgodaManja manjaDTO = (VozacNezgodaManja) model.get("manja");


        document.open();
        document.setPageSize(PageSize.A4.rotate());
        Paragraph par111 = new Paragraph();
        Image imagege = Image.getInstance("src/main/resources/static/images/logo.png");
        imagege.scaleAbsolute(70f, 30f);
        imagege.setAbsolutePosition(10f, 550f);
        par111.add(imagege);

        Paragraph naslov = new Paragraph(new Phrase(lineSpacing, "Izveštaj manje nezgode",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize + 10f)));
        naslov.setAlignment(Element.TITLE);

        par111.add(naslov);

        document.add(par111);

        document.add(Chunk.NEWLINE);
/*
        Paragraph header =new Paragraph(new Phrase(lineSpacing,"Aleksandar Bosić",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));*/
        Paragraph header = new Paragraph("Aleksandar Bosić",
                f1);
        header.setAlignment(Element.ALIGN_RIGHT);
        header.setIndentationRight(intentionRight);

        Paragraph headerIzv = new Paragraph(new Phrase(lineSpacing, "Izveštaj za manju saobraćajnu nesreću",
                freeSans));
        headerIzv.setAlignment(Element.ALIGN_RIGHT);
        headerIzv.setIndentationRight(intentionRight);


        Paragraph headerDate = new Paragraph(new Phrase(lineSpacing, LocalDate.now().toString(),
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        headerDate.setAlignment(Element.ALIGN_RIGHT);
        headerDate.setIndentationRight(intentionRight);

        document.add(header);
        document.add(headerIzv);
        document.add(headerDate);


        Paragraph master = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph voziloHeader = new Paragraph(new Phrase(lineSpacing, "Vozilo :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        voziloHeader.setIndentationLeft(intentionRight);
        voziloHeader.setSpacingBefore(10f);


        master.add("Marka vozila : " + manjaDTO.getMarkaVozila());
        master.add("  Tip vozila : " + manjaDTO.getTipVozila());
        master.add("  Registarska oznaka vozila : " + manjaDTO.getRegOznakaVoz());
        master.add("  Država registracije : " + manjaDTO.getDrzavaRegVozila());
        master.setIndentationLeft(intentionRight);
        master.setLeading(20f);

        document.add(voziloHeader);
        document.add(master);


        Paragraph masterPrik = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph prikHeader = new Paragraph(new Phrase(lineSpacing, "Prikolica :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        prikHeader.setIndentationLeft(intentionRight);
        prikHeader.setSpacingBefore(10f);


        masterPrik.add("Registrarska oznaka : " + manjaDTO.getRegOznakaPr());
        masterPrik.add("  Država registracije : " + manjaDTO.getDrRegPrikolica());
        masterPrik.add("  Max dozvoljena težina : " + manjaDTO.getMaxTezinaPr());
        masterPrik.setIndentationLeft(intentionRight);
        masterPrik.setSpacingBefore(10f);


        document.add(prikHeader);
        document.add(masterPrik);

        Paragraph masterOsig = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        Paragraph masterOsigDr = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph osigHeader = new Paragraph(new Phrase(lineSpacing, "Osiguranje :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        osigHeader.setIndentationLeft(intentionRight);
        osigHeader.setSpacingBefore(10f);


        masterOsig.add("Broj ugovora : " + manjaDTO.getBrojUgovoraOsiguranja());
        masterOsig.add("  Filijala : " + manjaDTO.getFilijala());
        masterOsig.add("  Država osiguranja : " + manjaDTO.getDrzava());
        masterOsig.add("  Materijalna šteta osigurana ugovorom : " + manjaDTO.getMaterijalnaStetaOsiguranaUgovorom());
        masterOsig.setIndentationLeft(intentionRight);
        masterOsig.setSpacingBefore(10f);
        masterOsigDr.add("Polisa važi do : " + manjaDTO.getPolisaVaziDo());
        masterOsigDr.add("  Polisa važi do : " + manjaDTO.getPolisaVaziDo());
        masterOsigDr.add("  Zaleni karton važi od : " + manjaDTO.getZeleniKartonVaziOd());
        masterOsigDr.add("  Zaleni karton važi do : " + manjaDTO.getZeleniKartonVaziDo());
        masterOsigDr.setIndentationLeft(intentionRight);
        masterOsigDr.setSpacingBefore(10f);


        document.add(osigHeader);
        document.add(masterOsig);
        document.add(masterOsigDr);

        Paragraph masterUO = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph uoHeader = new Paragraph(new Phrase(lineSpacing, "Ugovorač osiguranja :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        uoHeader.setIndentationLeft(intentionRight);
        uoHeader.setSpacingBefore(10f);


        masterUO.add("Ime prezime ugovorača osiguranja : " + manjaDTO.getImePrezimeUO());
        masterUO.add("  Adresa : " + manjaDTO.getAdresaUO());
        masterUO.add(" Poštanski broj : " + manjaDTO.getPostanskiBrojUO());
        masterUO.add(" Telefon : " + manjaDTO.getTelefonUO());
        masterUO.add(" email : " + manjaDTO.getMailUO());
        masterUO.setIndentationLeft(intentionRight);
        masterUO.setSpacingBefore(10f);

        document.add(uoHeader);
        document.add(masterUO);

        PdfPTable tableSvedok = new PdfPTable(5);

        addTableHeader(tableSvedok, svedokStream);

        manjaDTO.getVozacSvedok().stream()
                .forEach(svedokDTO -> {
                    tableSvedok.addCell(new Phrase(lineSpacing, svedokDTO.getIme(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing, svedokDTO.getPrezime(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing, svedokDTO.getAdresa(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing, svedokDTO.getTelefon(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing, svedokDTO.getBrLicneKarte(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

                });
        PdfPTable tableDrugo = new PdfPTable(4);
        addTableHeader(tableDrugo, drugoStream);

        manjaDTO.getPovredjeniDrugoVozilo()
                .stream().forEach(drugoDTO -> {
            tableDrugo.addCell(new Phrase(lineSpacing, drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing, drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing, drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing, drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });


        PdfPTable tableVase = new PdfPTable(4);
        addTableHeader(tableVase, vaseStream);

        manjaDTO.getPovredjeniVaseVozilo()
                .stream().forEach(drugoDTO -> {
            tableVase.addCell(new Phrase(lineSpacing, drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing, drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing, drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing, drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });


        PdfPTable tableVan = new PdfPTable(4);
        addTableHeader(tableVan, vanStream);

        manjaDTO.getPovredjeniVanVozila()
                .stream().forEach(drugoDTO -> {
            tableVan.addCell(new Phrase(lineSpacing, drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing, drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing, drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing, drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });


        document.add(Chunk.NEWLINE);
        Paragraph p = new Paragraph(new Phrase(lineSpacing, "Izveštaj popunio",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        p.setIndentationLeft(intentionRight);
        //p.setSpacingBefore(10f);

        Paragraph masterKor = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        Paragraph masterKorDr = new Paragraph(new Phrase(lineSpacing, "",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        masterKor.add("Ime : " + manjaDTO.getUser().getName());
        masterKor.add("  Prezime : " + manjaDTO.getUser().getLastName());
        masterKor.add("  Adresa : " + manjaDTO.getUser().getAdresa());
        masterKor.add("  Poštanski broj : " + manjaDTO.getUser().getPostanskiBroj());
        masterKor.add("  Država : " + manjaDTO.getUser().getDrzava());
        masterKor.add("  Telefon : " + manjaDTO.getUser().getTelefon());
        masterKor.setIndentationLeft(intentionRight);
        masterKor.setSpacingBefore(10f);
        masterKorDr.add("Broj vozačke dozvole : " + manjaDTO.getUser().getBrVozackeDozvole());
        masterKorDr.add("  Kategorija vozačke dozvole : " + manjaDTO.getUser().getKategorijaDozvole());
        masterKorDr.add("  Vozaćka dozvole važi do : " + manjaDTO.getUser().getVozackaDozvolaVaziDo());
        masterKorDr.add("  email : " + manjaDTO.getUser().getEmail());

        masterKorDr.setIndentationLeft(intentionRight);
        masterKorDr.setSpacingBefore(10f);

        document.add(p);
        document.add(masterKor);
        document.add(masterKorDr);

        document.add(Chunk.NEWLINE);
        Paragraph sved = new Paragraph(new Phrase(lineSpacing, "Svedok",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        sved.setIndentationLeft(intentionRight);
        sved.setSpacingBefore(10f);
        document.add(sved);
        document.add(Chunk.NEWLINE);
        document.add(tableSvedok);

        document.add(Chunk.NEWLINE);
        Paragraph drug = new Paragraph(new Phrase(lineSpacing, "Povređene osobe za drugo vozilo",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        drug.setIndentationLeft(intentionRight);
        drug.setSpacingBefore(10f);
        document.add(drug);
        document.add(Chunk.NEWLINE);
        document.add(tableDrugo);

        document.add(Chunk.NEWLINE);
        Paragraph vase = new Paragraph(new Phrase(lineSpacing, "Povređene osobe za vaše vozilo",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        vase.setIndentationLeft(intentionRight);
        vase.setSpacingBefore(10f);
        document.add(vase);
        document.add(Chunk.NEWLINE);
        document.add(tableVase);

        document.add(Chunk.NEWLINE);
        Paragraph van = new Paragraph(new Phrase(lineSpacing, "Povređene osobe za van vozila",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        van.setIndentationLeft(intentionRight);
        van.setSpacingBefore(10f);
        document.add(van);
        document.add(Chunk.NEWLINE);
        document.add(tableVan);

        document.newPage();

        PdfPTable tableDokazi = new PdfPTable(1);
        addTableHeader(tableDokazi, dokaziStream);

        manjaDTO.getEvropskiIzvestajIDokazis()
                .stream().filter(drugoDTO -> "1".equals(drugoDTO.getStatus()) && drugoDTO.getSlike() != null).forEach(drugoDTO -> {
            Image ime = null;
            try {

                ime = Image.getInstance(drugoDTO.getSlike());
                PdfPCell cell1 = new PdfPCell(ime);
                cell1.setFixedHeight(70f);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ime.scaleAbsolute(100f, 50f);
                tableDokazi.addCell(cell1);

            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

        });


        PdfPTable tableTudjeDokazi = new PdfPTable(1);
        addTableHeader(tableTudjeDokazi, dokaziTudjeStream);

        manjaDTO.getEvropskiIzvestajIDokazis()
                .stream().filter(drugoDTO -> "2".equals(drugoDTO.getStatus())).forEach(drugoDTO -> {
            Image ime = null;
            if (drugoDTO.getSlike() != null) {
                try {

                    ime = Image.getInstance(drugoDTO.getSlike());
                    PdfPCell cell2 = new PdfPCell(ime);
                    cell2.setFixedHeight(70f);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    ime.scaleAbsolute(100f, 50f);
                    tableTudjeDokazi.addCell(cell2);

                    // }
                } catch (BadElementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

        });

        Paragraph dokazi = new Paragraph(new Phrase(lineSpacing, "Dokazi saobraćajne nesreće",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        dokazi.setIndentationLeft(intentionRight);
        dokazi.setSpacingBefore(10f);
        document.add(dokazi);
        document.add(Chunk.NEWLINE);
        document.add(tableDokazi);

        Paragraph dokaziDrugo = new Paragraph(new Phrase(lineSpacing, "Dokazi saobraćajne nesreće",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        dokaziDrugo.setIndentationLeft(intentionRight);
        dokaziDrugo.setSpacingBefore(10f);
        document.add(dokaziDrugo);
        document.add(Chunk.NEWLINE);
        document.add(tableTudjeDokazi);


        document.add(Chunk.NEWLINE);

       /* Paragraph par = new Paragraph();
        Image ime = Image.getInstance("src/main/resources/static/images/logo.png");
        ime.setAbsolutePosition(10f,10f);

        par.add(ime);
        document.add(par);*/

        document.close();
    }
}
