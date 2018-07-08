package com.example.util;

import com.example.model.PutnikIzvestaj;
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

public class PutnikPDF extends AbstractITextPdfView {

    private Stream<String> korisnikStream = Stream.of("Ime", "Prezime", "Korisnicko ime");
    private Stream<String> svedokStream = Stream.of("Ime", "Prezime", "Adresa","Telefon","Br. licne karte");
    private Stream<String> vremeNezStream = Stream.of("Datum nezgode", "Mesto nezgode", "Vreme nezgode");


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

        PutnikIzvestaj putnikDTO = (PutnikIzvestaj) model.get("putnik");

        document.open();
        Paragraph par111 = new Paragraph();
        Image imagege = Image.getInstance("src/main/resources/static/images/logo.png");
        imagege.scaleAbsolute(70f, 30f);
        imagege.setAbsolutePosition(10f,550f);
        par111.add(imagege);

        Paragraph naslov =new Paragraph(new Phrase(lineSpacing,"Izveštaj nezgode u slučaju putnika",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize+10f)));
        naslov.setAlignment(Element.TITLE);

        par111.add(naslov);

        document.add(par111);

        document.add( Chunk.NEWLINE );

        Paragraph header =new Paragraph(new Phrase(lineSpacing,"Aleksandar Bosić",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        header.setAlignment(Element.ALIGN_RIGHT);
        header.setIndentationRight(intentionRight);

        Paragraph headerIzv =new Paragraph(new Phrase(lineSpacing,"Izveštaj nezgode u slučaju putnika",
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


        master.add("Marka vozila : " + putnikDTO.getMarkaVozila());
        master.add("  Tip vozila : " + putnikDTO.getTipVozila());
        master.add("  Registarska oznaka vozila : " + putnikDTO.getRegOznakaVoz());
        master.add("  Država registracije : " + putnikDTO.getDrzavaRegVozila());
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


        masterPrik.add("Registrarska oznaka : " + putnikDTO.getRegOznakaPr());
        masterPrik.add("  Država registracije : " + putnikDTO.getDrRegPrikolica());
        masterPrik.add("  Max dozvoljena težina : " + putnikDTO.getMaxTezinaPr());
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


        masterOsig.add("Broj ugovora : " + putnikDTO.getBrojUgovoraOsiguranja());
        masterOsig.add("  Filijala : " + putnikDTO.getFilijala());
        masterOsig.add("  Država osiguranja : " + putnikDTO.getDrzava());
        masterOsig.add("  Materijalna šteta osigurana ugovorom : " + putnikDTO.getMaterijalnaStetaOsiguranaUgovorom());
        masterOsig.setIndentationLeft(intentionRight);
        masterOsig.setSpacingBefore(10f);
        masterOsigDr.add("Polisa važi do : " + putnikDTO.getPolisaVaziDo());
        masterOsigDr.add("  Polisa važi do : " + putnikDTO.getPolisaVaziDo());
        masterOsigDr.add("  Zaleni karton važi od : " + putnikDTO.getZeleniKartonVaziOd());
        masterOsigDr.add("  Zaleni karton važi do : " + putnikDTO.getZeleniKartonVaziDo());
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


        masterUO.add("Ime prezime ugovorača osiguranja : " + putnikDTO.getImePrezimeUO());
        masterUO.add("  Adresa : " + putnikDTO.getAdresaUO());
        masterUO.add(" Poštanski broj : " + putnikDTO.getPostanskiBrojUO());
        masterUO.add(" Telefon : " + putnikDTO.getTelefonUO());
        masterUO.add(" email : " + putnikDTO.getMailUO());
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

        masterKor.add("Ime : " + putnikDTO.getUser().getName());
        masterKor.add("  Prezime : " + putnikDTO.getUser().getLastName());
        masterKor.add("  Adresa : " + putnikDTO.getUser().getAdresa());
        masterKor.add("  Poštanski broj : " + putnikDTO.getUser().getPostanskiBroj());
        masterKor.add("  Država : " + putnikDTO.getUser().getDrzava());
        masterKor.add("  Telefon : " + putnikDTO.getUser().getTelefon());
        masterKor.setIndentationLeft(intentionRight);
        masterKor.setSpacingBefore(10f);
        masterKorDr.add("Broj vozačke dozvole : " + putnikDTO.getUser().getBrVozackeDozvole());
        masterKorDr.add("  Kategorija vozačke dozvole : " + putnikDTO.getUser().getKategorijaDozvole());
        masterKorDr.add("  Vozaćka dozvole važi do : " + putnikDTO.getUser().getVozackaDozvolaVaziDo());
        masterKorDr.add("  email : " + putnikDTO.getUser().getEmail());

        masterKorDr.setIndentationLeft(intentionRight);
        masterKorDr.setSpacingBefore(10f);

        document.add(p);
        document.add(masterKor);
        document.add(masterKorDr);

        PdfPTable tableSvedok = new PdfPTable(5);

        addTableHeader(tableSvedok, svedokStream);

        putnikDTO.getPutnikSvedok().stream()
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


        PdfPTable tableVremeNez = new PdfPTable(3);
        addTableHeader(tableVremeNez, vremeNezStream);

        tableVremeNez.addCell(new Phrase(lineSpacing,putnikDTO.getPutnikNezgoda().getDatumNezgodePU(),
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        tableVremeNez.addCell(new Phrase(lineSpacing,putnikDTO.getPutnikNezgoda().getMestoNezgodePU(),
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        tableVremeNez.addCell(new Phrase(lineSpacing,putnikDTO.getPutnikNezgoda().getVremeNezgodePU(),
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        document.add( Chunk.NEWLINE );
        Paragraph sved =new Paragraph(new Phrase(lineSpacing,"Svedok",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        sved.setSpacingBefore(10f);
        sved.setIndentationLeft(intentionRight);
        document.add(sved);
        document.add( Chunk.NEWLINE );
        document.add(tableSvedok);

        document.add( Chunk.NEWLINE );
        Paragraph vremeNez =new Paragraph(new Phrase(lineSpacing,"Vreme nezgode",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        vremeNez.setSpacingBefore(10f);
        vremeNez.setIndentationLeft(intentionRight);
        document.add(vremeNez);
        document.add( Chunk.NEWLINE );
        document.add(tableVremeNez);

        document.add( Chunk.NEWLINE );
/*
        Paragraph par = new Paragraph();
        Image ime = Image.getInstance("src/main/resources/static/images/logo.png");
        ime.setAbsolutePosition(10f,10f);

        par.add(ime);
        document.add(par);*/

        document.close();

    }
}

