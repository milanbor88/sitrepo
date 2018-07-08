package com.example.service;

import com.example.dto.PutnikIzvestajDTO;
import com.example.dto.VozacNezgodaIzvestajDTO;
import com.example.dto.VozacNezgodaManjaDTO;
import com.example.util.Mail;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void sendSimpleMessage(Mail mail) throws MessagingException, IOException;
    void sendRestartSimpleMessage(Mail mail) throws MessagingException, IOException;
    void sendKontaktFormaSimpleMessage(Mail mail) throws MessagingException, IOException;
    void sendIzvVeca( VozacNezgodaIzvestajDTO nezgodaVecaDTO,String email) throws MessagingException, IOException;
    void sendIzvManja(VozacNezgodaManjaDTO nezgodaManjaDTO, String email) throws MessagingException, IOException;
    void sendIzvPutnik(PutnikIzvestajDTO putnikIzvestajDTO, String email) throws MessagingException, IOException;
    byte[] getPDFReport( VozacNezgodaIzvestajDTO nezgodaIzvestajDTO) throws IOException;

}
