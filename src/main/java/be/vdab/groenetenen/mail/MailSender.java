package be.vdab.groenetenen.mail;

import be.vdab.groenetenen.domain.Offerte;

public interface MailSender {
    void nieuweOfferte(Offerte offerte, String offertesURL);
}
