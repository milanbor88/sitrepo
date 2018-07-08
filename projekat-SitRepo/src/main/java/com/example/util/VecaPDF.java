package com.example.util;

import com.example.model.VozacNezgodaVeca;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

public class VecaPDF extends AbstractITextPdfView {

    private Stream<String> korisnikStream = Stream.of("Ime", "Prezime", "Korisnicko ime");
    private Stream<String> svedokStream = Stream.of("Ime", "Prezime", "Adresa","Telefon","Br. licne karte");
    private Stream<String> drugoStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vaseStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vanStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");


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

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        VozacNezgodaVeca vecaDTO = (VozacNezgodaVeca) model.get("veca");

        document.open();
        Paragraph par111 = new Paragraph();
        Image imagege = Image.getInstance("src/main/resources/static/images/logo.png");
        imagege.scaleAbsolute(70f, 30f);
        imagege.setAbsolutePosition(10f,550f);
        par111.add(imagege);

        Paragraph naslov =new Paragraph(new Phrase(lineSpacing,"Izveštaj veće nezgode",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize+10f)));
        naslov.setAlignment(Element.TITLE);

        par111.add(naslov);

        document.add(par111);

        document.add( Chunk.NEWLINE );

        Paragraph header =new Paragraph(new Phrase(lineSpacing,"Aleksandar Bosić",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        header.setAlignment(Element.ALIGN_RIGHT);
        header.setIndentationRight(intentionRight);

        Paragraph headerIzv =new Paragraph(new Phrase(lineSpacing,"Izveštaj za veću saobraćajnu nesreću",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        headerIzv.setAlignment(Element.ALIGN_RIGHT);
        headerIzv.setIndentationRight(intentionRight);



        Paragraph headerDate =new Paragraph(new Phrase(lineSpacing, LocalDate.now().toString(),
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        headerDate.setAlignment(Element.ALIGN_RIGHT);
        headerDate.setIndentationRight(intentionRight);

        document.add(header);
        document.add(headerIzv);
        document.add(headerDate);


        Paragraph master =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph voziloHeader =new Paragraph(new Phrase(lineSpacing,"Vozilo :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        voziloHeader.setIndentationLeft(intentionRight);
        voziloHeader.setSpacingBefore(10f);


        master.add("Marka vozila : " + vecaDTO.getMarkaVozila());
        master.add("  Tip vozila : " + vecaDTO.getTipVozila());
        master.add("  Registarska oznaka vozila : " + vecaDTO.getRegOznakaVoz());
        master.add("  Država registracije : " + vecaDTO.getDrzavaRegVozila());
        master.setIndentationLeft(intentionRight);
        master.setLeading(20f);

        document.add(voziloHeader);
        document.add(master);


        Paragraph masterPrik =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph prikHeader =new Paragraph(new Phrase(lineSpacing,"Prikolica :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        prikHeader.setIndentationLeft(intentionRight);
        prikHeader.setSpacingBefore(10f);


        masterPrik.add("Registrarska oznaka : " + vecaDTO.getRegOznakaPr());
        masterPrik.add("  Država registracije : " + vecaDTO.getDrRegPrikolica());
        masterPrik.add("  Max dozvoljena težina : " + vecaDTO.getMaxTezinaPr());
        masterPrik.setIndentationLeft(intentionRight);
        masterPrik.setSpacingBefore(10f);


        document.add(prikHeader);
        document.add(masterPrik);

        Paragraph masterOsig =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        Paragraph masterOsigDr =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph osigHeader =new Paragraph(new Phrase(lineSpacing,"Osiguranje :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        osigHeader.setIndentationLeft(intentionRight);
        osigHeader.setSpacingBefore(10f);


        masterOsig.add("Broj ugovora : " + vecaDTO.getBrojUgovoraOsiguranja());
        masterOsig.add("  Filijala : " + vecaDTO.getFilijala());
        masterOsig.add("  Država osiguranja : " + vecaDTO.getDrzava());
        masterOsig.add("  Materijalna šteta osigurana ugovorom : " + vecaDTO.getMaterijalnaStetaOsiguranaUgovorom());
        masterOsig.setIndentationLeft(intentionRight);
        masterOsig.setSpacingBefore(10f);
        masterOsigDr.add("Polisa važi do : " + vecaDTO.getPolisaVaziDo());
        masterOsigDr.add("  Polisa važi do : " + vecaDTO.getPolisaVaziDo());
        masterOsigDr.add("  Zaleni karton važi od : " + vecaDTO.getZeleniKartonVaziOd());
        masterOsigDr.add("  Zaleni karton važi do : " + vecaDTO.getZeleniKartonVaziDo());
        masterOsigDr.setIndentationLeft(intentionRight);
        masterOsigDr.setSpacingBefore(10f);


        document.add(osigHeader);
        document.add(masterOsig);
        document.add(masterOsigDr);

        Paragraph masterUO =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph uoHeader =new Paragraph(new Phrase(lineSpacing,"Ugovorač osiguranja :",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));

        uoHeader.setIndentationLeft(intentionRight);
        uoHeader.setSpacingBefore(10f);


        masterUO.add("Ime prezime ugovorača osiguranja : " + vecaDTO.getImePrezimeUO());
        masterUO.add("  Adresa : " + vecaDTO.getAdresaUO());
        masterUO.add(" Poštanski broj : " + vecaDTO.getPostanskiBrojUO());
        masterUO.add(" Telefon : " + vecaDTO.getTelefonUO());
        masterUO.add(" email : " + vecaDTO.getMailUO());
        masterUO.setIndentationLeft(intentionRight);
        masterUO.setSpacingBefore(10f);

        document.add(uoHeader);
        document.add(masterUO);


        document.add( Chunk.NEWLINE );
        Paragraph p =new Paragraph(new Phrase(lineSpacing,"Izveštaj popunio",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        p.setIndentationLeft(intentionRight);
        //p.setSpacingBefore(10f);

        Paragraph masterKor =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        Paragraph masterKorDr =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        masterKor.add("Ime : " + vecaDTO.getUser().getName());
        masterKor.add("  Prezime : " + vecaDTO.getUser().getLastName());
        masterKor.add("  Adresa : " + vecaDTO.getUser().getAdresa());
        masterKor.add("  Poštanski broj : " + vecaDTO.getUser().getPostanskiBroj());
        masterKor.add("  Država : " + vecaDTO.getUser().getDrzava());
        masterKor.add("  Telefon : " + vecaDTO.getUser().getTelefon());
        masterKor.setIndentationLeft(intentionRight);
        masterKor.setSpacingBefore(10f);
        masterKorDr.add("Broj vozačke dozvole : " + vecaDTO.getUser().getBrVozackeDozvole());
        masterKorDr.add("  Kategorija vozačke dozvole : " + vecaDTO.getUser().getKategorijaDozvole());
        masterKorDr.add("  Vozaćka dozvole važi do : " + vecaDTO.getUser().getVozackaDozvolaVaziDo());
        masterKorDr.add("  email : " + vecaDTO.getUser().getEmail());

        masterKorDr.setIndentationLeft(intentionRight);
        masterKorDr.setSpacingBefore(10f);

        document.add(p);
        document.add(masterKor);
        document.add(masterKorDr);

        PdfPTable tableSvedok = new PdfPTable(5);

        addTableHeader(tableSvedok, svedokStream);

        vecaDTO.getVozacSvedok().stream()
                .forEach(svedokDTO -> {
                    tableSvedok.addCell(new Phrase(lineSpacing,svedokDTO.getIme(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing,svedokDTO.getPrezime(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing,svedokDTO.getAdresa(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing,svedokDTO.getTelefon(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    tableSvedok.addCell(new Phrase(lineSpacing,svedokDTO.getBrLicneKarte(),
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

                });
        PdfPTable tableDrugo = new PdfPTable(4);
        addTableHeader(tableDrugo, drugoStream);

        vecaDTO.getPovredjeniDrugoVozilo()
                .stream().forEach(drugoDTO -> {
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });


        PdfPTable tableVase = new PdfPTable(4);
        addTableHeader(tableVase, vaseStream);

        vecaDTO.getPovredjeniVaseVozilo()
                .stream().forEach(drugoDTO -> {
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });


        PdfPTable tableVan = new PdfPTable(4);
        addTableHeader(tableVan, vanStream);

        vecaDTO.getPovredjeniVanVozila()
                .stream().forEach(drugoDTO -> {
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        });

        document.add( Chunk.NEWLINE );
        Paragraph sved =new Paragraph(new Phrase(lineSpacing,"Svedok",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        sved.setSpacingBefore(10f);
        sved.setIndentationLeft(intentionRight);
        document.add(sved);
        document.add( Chunk.NEWLINE );
        document.add(tableSvedok);

        document.add( Chunk.NEWLINE );
        Paragraph drug =new Paragraph(new Phrase(lineSpacing,"Povredjene osobe za drugo vozilo",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        drug.setSpacingBefore(10f);
        drug.setIndentationLeft(intentionRight);
        document.add(drug);
        document.add( Chunk.NEWLINE );
        document.add(tableDrugo);

        document.add( Chunk.NEWLINE );
        Paragraph vase =new Paragraph(new Phrase(lineSpacing,"Povredjene osobe za vase vozilo",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        vase.setSpacingBefore(10f);
        vase.setIndentationLeft(intentionRight);
        document.add(vase);
        document.add( Chunk.NEWLINE );
        document.add(tableVase);

        document.add( Chunk.NEWLINE );
        Paragraph van =new Paragraph(new Phrase(lineSpacing,"Povredjene osobe za van vozila",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        van.setSpacingBefore(10f);
        van.setIndentationLeft(intentionRight);
        document.add(van);
        document.add( Chunk.NEWLINE );
        document.add(tableVan);

        document.add( Chunk.NEWLINE );

/*        Paragraph par = new Paragraph();
        Image ime = Image.getInstance("src/main/resources/static/images/logo.png");
        ime.setAbsolutePosition(10f,10f);

        par.add(ime);
        document.add(par);*/

        document.close();



    }
}
