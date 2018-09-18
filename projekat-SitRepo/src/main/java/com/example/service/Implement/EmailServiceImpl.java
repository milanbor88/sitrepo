package com.example.service.Implement;

import com.example.dto.PutnikIzvestajDTO;
import com.example.dto.VozacNezgodaIzvestajDTO;
import com.example.dto.VozacNezgodaManjaDTO;
import com.example.service.EmailService;
import com.example.util.Mail;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

@Service("emailService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender emailSender;

//    @Autowired
//    private SpringTemplateEngine templateEngine;

    private Stream<String> korisnikStream = Stream.of("Ime", "Prezime", "Korisnicko ime");
    private Stream<String> korPutnikStream = Stream.of("Ime", "Prezime", "Korisnicko ime");
    private Stream<String> svedokStream = Stream.of("Ime", "Prezime", "Adresa","Telefon","Br. licne karte");
    private Stream<String> drugoStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vaseStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vanStream = Stream.of("Ime", "Prezime", "Adresa","Telefon");
    private Stream<String> vremeNezStream = Stream.of("Datum nezgode", "Mesto nezgode", "Vreme nezgode");
    private Stream<String> ugOsigBStream = Stream.of("Ime", "Prezime", "Adresa", "Postanski broj", "Telefon","Email");
    private Stream<String> ucesnikStream = Stream.of("Email", "Ime", "Prezime", "Adresa", "Postanski broj","Drzava",
            "Telefon","Br. vozacke dozvole","Kategorija dozvole","Vozacka dozvola vazi do","Datum rodjenja");
    private Stream<String> osigBStream = Stream.of("Naziv", "br. ugovora", "br. zelenog kartona", "Polisa vazi od", "Polisa vazi do",
            "Zeleni karton vazi od", "Zeleni karton vazi do","Filijala","Materijalna steta osigurana ugovorom");
    private Stream<String> voziloBStream = Stream.of("Marka", "Tip", "Registarska oznaka vozila","Drzava u kojoj je vozilo registrovano");
    private Stream<String> prikolicaBStream = Stream.of("Registarska oznaka",
            "Drzava u kojoj je registrovana", "Maksimalna dozvoljena tezina");

    private Stream<String> dokaziStream = Stream.of("Dokazi vozila klijenta");

    private Stream<String> dokaziTudjeStream = Stream.of( "Dokazi vozila drugog učesnika");
    final float fntSize = 6.7f;
    final float fntSizeHeade = 7.4f;
    final float lineSpacing = 10f;
    final float intentionRight = 80f;
    public static final String FONT = "font/FreeSans.ttf";


    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        ClassPathResource classPathResource = new ClassPathResource("");

        helper.addAttachment("logo.png", new ClassPathResource("static/images/logo.png"));


        helper.setTo(mail.getTo());
       // helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        helper.setText("<html><body>"
        + "<p>aktivirajte sitrepo nalog</p></br>" +
        //"<a href=\"http://127.0.01:9090/emailActivisation/" + mail.getTo() + "\">Aktivirajte nalog ovde</a></br>" +
       "<a href=\"http://159.89.25.58:9090/sitrepo-0.0.1-SNAPSHOT/emailActivisation/" + mail.getTo() + "\">Aktivirajte nalog ovde</a></br>" +
        "</body></html>", true);
        emailSender.send(message);
    }

    @Override
    public void sendRestartSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        ClassPathResource classPathResource = new ClassPathResource("");

        helper.addAttachment("logo.png", new ClassPathResource("static/images/logo.png"));

        // Context context = new Context();
        //  context.setVariables(mail.getModel());
        // String html = templateEngine.process("email-template", context);

        helper.setTo(mail.getTo());
        // helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        helper.setText("<html><body>"
                + "<p>aktivirajte sitrepo nalog</p></br>" +
                "<a href=\"http://159.89.25.58:8080/sitrepo-0.0.1-SNAPSHOT/emailRestartLozinku/" + mail.getTo() + "\">Resetuj lozinku ovde</a></br>" +
                "</body></html>", true);

//        helper.setText("<html><body>"
//                + "<p>aktivirajte sitrepo nalog</p></br>" +
//                "<a href=\"localhost:9090/emailRestartLozinku/" + mail.getTo() + "\">Resetuj lozinku ovde</a></br>" +
//                "</body></html>", true);
        emailSender.send(message);
    }

    @Override
    public void sendKontaktFormaSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        ClassPathResource classPathResource = new ClassPathResource("");

        helper.addAttachment("logo.png", new ClassPathResource("static/images/logo.png"));

        Map<String, Object> model = mail.getModel();
        helper.setTo(mail.getTo());
        // helper.setText(html, true);
        helper.setSubject("kontakt forma sitrepo");
        helper.setFrom(mail.getFrom());
        helper.setText("<html><body>" + "<p>" + model.get("name") +"</p></br>" +  "<p>" + model.get("username") +"</p></br>"
                + "<p>"+mail.getSubject()+"</p></br>" +
                "</body></html>", true);
        emailSender.send(message);
    }

    @Override
    public void sendIzvVeca(VozacNezgodaIzvestajDTO nezgodaVecaDTO,String email) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        ByteArrayOutputStream outputStream = null;
        outputStream = new ByteArrayOutputStream();
        try {

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("test");
            writePdfVeca(outputStream, nezgodaVecaDTO);

            byte[] bytes = outputStream.toByteArray();
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);


            message.setRecipients(Message.RecipientType.TO,email);
            message.setRecipients(Message.RecipientType.CC, nezgodaVecaDTO.getUser().getEmail());
            message.setSubject("testizvestak");
            message.setFrom("sitrepo@gmail.com");
            message.setContent(mimeMultipart);

            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendIzvManja(VozacNezgodaManjaDTO nezgodaManjaDTO, String email) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        ByteArrayOutputStream outputStream = null;
        outputStream = new ByteArrayOutputStream();
        try {

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("test");
            writePDFManja(outputStream, nezgodaManjaDTO);

            byte[] bytes = outputStream.toByteArray();
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);


            message.setRecipients(Message.RecipientType.TO,email);
            message.setRecipients(Message.RecipientType.CC, nezgodaManjaDTO.getUser().getEmail());
            message.setSubject("Izvestaj za manju nezgodu");
            message.setFrom("sitrepo@gmail.com");
            message.setContent(mimeMultipart);

            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writePdfVeca(OutputStream outputStream, VozacNezgodaIzvestajDTO vecaDTO) throws Exception {


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font f1 = FontFactory.getFont(FONT, "Cp1250",  BaseFont.EMBEDDED);
        f1.setSize(fntSize+10f);
        Paragraph par111 = new Paragraph();
        Image imagege = Image.getInstance("src/main/resources/static/images/logo.png");
        imagege.scaleAbsolute(70f, 30f);
        imagege.setAbsolutePosition(10f,550f);
        par111.add(imagege);

        Paragraph naslov =new Paragraph(new Phrase(lineSpacing,"Izveštaj veće nezgode",
                f1));
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
            //tableDrugo.addCell(drugoDTO.getIme());
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableDrugo.addCell(drugoDTO.getPrezime());
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableDrugo.addCell(drugoDTO.getAdresa());
            tableDrugo.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableDrugo.addCell(drugoDTO.getTelefon());
        });


        PdfPTable tableVase = new PdfPTable(4);
        addTableHeader(tableVase, vaseStream);

        vecaDTO.getPovredjeniVaseVozilo()
                .stream().forEach(drugoDTO -> {
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVase.addCell(drugoDTO.getIme());
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVase.addCell(drugoDTO.getPrezime());
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVase.addCell(drugoDTO.getAdresa());
            tableVase.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVase.addCell(drugoDTO.getTelefon());
        });


        PdfPTable tableVan = new PdfPTable(4);
        addTableHeader(tableVan, vanStream);

        vecaDTO.getPovredjeniVanVozila()
                .stream().forEach(drugoDTO -> {
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getIme(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVan.addCell(drugoDTO.getIme());
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getPrezime(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVan.addCell(drugoDTO.getPrezime());
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getAdresa(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVan.addCell(drugoDTO.getAdresa());
            tableVan.addCell(new Phrase(lineSpacing,drugoDTO.getTelefon(),
                    FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
            //tableVan.addCell(drugoDTO.getTelefon());
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

/*        document.add( Chunk.NEWLINE );

        Paragraph par = new Paragraph();
        Image ime = Image.getInstance("src/main/resources/static/images/logo.png");
        ime.setAbsolutePosition(10f,10f);

        par.add(ime);
        document.add(par);*/

        document.close();

    }

    private void addTableHeader(PdfPTable table, Stream<String> headerStream) {
       /* Stream.of("ime", "prezime", "adresa", "telefon")*/
        headerStream
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    //header.setPhrase(new Phrase(columnTitle));
                    header.setPhrase(new Phrase(lineSpacing,columnTitle,
                            FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
                    table.addCell(header);
                });
    }

    public void writePDFManja(OutputStream outputStream, VozacNezgodaManjaDTO manjaDTO) throws Exception {

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph par111 = new Paragraph();
        Image imagege = Image.getInstance("src/main/resources/static/images/logo.png");
        imagege.scaleAbsolute(70f, 30f);
        imagege.setAbsolutePosition(10f,550f);
        par111.add(imagege);

        Paragraph naslov =new Paragraph(new Phrase(lineSpacing,"Izveštaj manje nezgode",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize+10f)));
        naslov.setAlignment(Element.TITLE);

        par111.add(naslov);

        document.add(par111);

        document.add( Chunk.NEWLINE );

        Paragraph header =new Paragraph(new Phrase(lineSpacing,"Aleksandar Bosić",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));
        header.setAlignment(Element.ALIGN_RIGHT);
        header.setIndentationRight(intentionRight);

        Paragraph headerIzv =new Paragraph(new Phrase(lineSpacing,"Izveštaj za manju saobraćajnu nesreću",
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


        master.add("Marka vozila : " + manjaDTO.getMarkaVozila());
        master.add("  Tip vozila : " + manjaDTO.getTipVozila());
        master.add("  Registarska oznaka vozila : " + manjaDTO.getRegOznakaVoz());
        master.add("  Država registracije : " + manjaDTO.getDrzavaRegVozila());
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


        masterPrik.add("Registrarska oznaka : " + manjaDTO.getRegOznakaPr());
        masterPrik.add("  Država registracije : " + manjaDTO.getDrRegPrikolica());
        masterPrik.add("  Max dozvoljena težina : " + manjaDTO.getMaxTezinaPr());
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

        Paragraph masterUO =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));


        Paragraph uoHeader =new Paragraph(new Phrase(lineSpacing,"Ugovorač osiguranja :",
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

        document.add( Chunk.NEWLINE );
        Paragraph p =new Paragraph(new Phrase(lineSpacing,"Izveštaj popunio",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        p.setIndentationLeft(intentionRight);
        //p.setSpacingBefore(10f);

        Paragraph masterKor =new Paragraph(new Phrase(lineSpacing,"",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSize)));

        Paragraph masterKorDr =new Paragraph(new Phrase(lineSpacing,"",
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

        PdfPTable tableSvedok = new PdfPTable(5);

        addTableHeader(tableSvedok, svedokStream);

        manjaDTO.getVozacSvedok().stream()
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

        manjaDTO.getPovredjeniDrugoVozilo()
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

        manjaDTO.getPovredjeniVaseVozilo()
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

        manjaDTO.getPovredjeniVanVozila()
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

        PdfPTable tableDokazi = new PdfPTable(1);
        addTableHeader(tableDokazi, dokaziStream);

        manjaDTO.getEvropskiIzvestajIDokazis()
                .stream().filter(drugoDTO -> "1".equals(drugoDTO.getStatus()) && drugoDTO.getSlike() != null).forEach(drugoDTO -> {
            Image ime = null;
            try {

                ime = Image.getInstance(drugoDTO.getSlike());
                PdfPCell cell1 = new PdfPCell(ime);
                cell1.setFixedHeight(70f);
                cell1.setHorizontalAlignment( Element.ALIGN_CENTER);
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
                    cell2.setHorizontalAlignment( Element.ALIGN_CENTER);
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

        Paragraph dokazi =new Paragraph(new Phrase(lineSpacing,"Dokazi saobraćajne nesreće",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        dokazi.setSpacingBefore(10f);
        dokazi.setIndentationLeft(intentionRight);
        document.add(dokazi);
        document.add( Chunk.NEWLINE );
        document.add(tableDokazi);

        Paragraph dokaziDrugo =new Paragraph(new Phrase(lineSpacing,"Dokazi saobraćajne nesreće",
                FontFactory.getFont(FontFactory.TIMES_BOLD, fntSizeHeade)));
        dokaziDrugo.setSpacingBefore(10f);
        dokaziDrugo.setIndentationLeft(intentionRight);
        document.add(dokaziDrugo);
        document.add( Chunk.NEWLINE );
        document.add(tableTudjeDokazi);

        document.close();

    }

    @Override
    public void sendIzvPutnik(PutnikIzvestajDTO putnikIzvestajDTO, String email) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        ByteArrayOutputStream outputStream = null;
        outputStream = new ByteArrayOutputStream();
        try {

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("test");
            writePdfPutnik(outputStream, putnikIzvestajDTO);

            byte[] bytes = outputStream.toByteArray();
            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);


            message.setRecipients(Message.RecipientType.TO,email);
            //message.setRecipients(Message.RecipientType.CC, "milanstoka@gmail.com");
            message.setRecipients(Message.RecipientType.CC, putnikIzvestajDTO.getUser().getEmail());
            message.setSubject("testizvestak");
            message.setFrom("sitrepo@gmail.com");
            message.setContent(mimeMultipart);

            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getPDFReport(VozacNezgodaIzvestajDTO nezgodaIzvestajDTO) throws IOException {

        ByteArrayOutputStream outputStream = null;
        outputStream = new ByteArrayOutputStream();
        byte[] bytes=null;
        try {

            writePdfVeca(outputStream, nezgodaIzvestajDTO);

            bytes = outputStream.toByteArray();
        }  catch (Exception e) {
        e.printStackTrace();
        }

        return bytes;
    }


    public void writePdfPutnik(OutputStream outputStream, PutnikIzvestajDTO putnikDTO) throws Exception {


        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, outputStream);
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

        // document.add( Chunk.NEWLINE );

/*        Paragraph par = new Paragraph();
        Image ime = Image.getInstance("src/main/resources/static/images/logo.png");
        ime.setAbsolutePosition(10f,10f);

        par.add(ime);
        document.add(par);*/

        document.close();
    }
}
