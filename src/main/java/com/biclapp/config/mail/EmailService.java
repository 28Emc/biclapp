package com.biclapp.config.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    @Async
    public void enviarEmail(Map<String, Object> model, String tipoOperacion) throws MessagingException {
        String remitente = model.get("from").toString();
        String destinatario = model.get("to").toString();
        String tituloEmail = model.get("subject").toString();
        String plantilla = armarPlantilla(model, tipoOperacion);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setText(plantilla, true);
        helper.setFrom(remitente);
        helper.setTo(destinatario);
        helper.setSubject(tituloEmail);

        sender.send(message);
    }

    private String armarPlantilla(Map<String, Object> model, String tipoOperacion) {
        String header = "<!DOCTYPE html><html lang='es'> <head> <title>Biclapp</title> <meta charset='UTF-8' /> <meta name='viewport' content='width=device-width, initial-scale=1.0' /> <style> html { height: 100%; } body { color: #808080; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; width: 100%; padding: 0; margin: 0; height: 100%; } @media (min-width: 0) and (max-width: 420px) { .container { margin: auto; height: 100%; width: 100%; padding-left: .5em; padding-right: .5em; } table { width: 100%; height: 100%; } .imagen { padding-top: 2em; padding-bottom: 2em; text-align: center; } hr { width: 30px; margin-top: 1em; margin-bottom: 1em; height: .05px; background-color: #2cb02c; border-radius: 1em; color: #2cb02c; } .content { padding: 1em; font-size: 1rem; text-align: center; } .titulo { text-align: center; font-weight: lighter; font-size: 1em; color: black; } .texto { padding-top: .5em; padding-bottom: .5em; font-weight: lighter; } .texto-2 { font-weight: bolder; color: rgb(5, 75, 5); font-size: 2em; } .footer { padding-top: 1em; padding-bottom: 1em; text-align: center; font-weight: normal; font-size: 1rem; } } @media (min-width: 421px) { .container { margin: auto; height: 100%; width: 650px; } table { width: 100%; height: 100%; } .imagen { padding-top: 5em; padding-bottom: 2em; text-align: center; } hr { width: 30px; margin-top: 1em; margin-bottom: 1em; height: 1px; background-color: #2cb02c; border-radius: 1em; color: #2cb02c; } .content { font-size: 1rem; text-align: center; padding: 1em 0; } .titulo { text-align: center; font-weight: lighter; font-size: 2em; color: black; } .texto { padding-top: .5em; padding-bottom: .5em; font-weight: lighter; } .texto-2 { font-weight: bolder; color: rgb(5, 75, 5); font-size: 2.6em; } .footer { padding-top: 2em; padding-bottom: 3em; text-align: center; font-weight: normal; font-size: 1.2rem; } } </style> </head>";
        String body = "";

        if (tipoOperacion.equals("VALIDAR CUENTA")) {
            body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/spring-bucket-biclapp-dev/app-images/logo-citybike.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, hemos recibido tu peticiòn de registro a CityBike Lima Delivery.</h3> <h3 class='texto'>Para confirmar tu cuenta, te hemos enviado un código de verificación: </h3> <h2 class='texto-2'>" + model.get("codigo-verificacion").toString() + " </h2> </td> </tr> <td> <hr> </td>";
        } else if (tipoOperacion.equals("CONFIRMA VALIDACIÓN CUENTA")) {
            body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/spring-bucket-biclapp-dev/app-images/logo-citybike.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su cuenta ha sido activada correctamente.</h3> <h3 class='texto'>Le damos la bienvenida a Citybike Lima Delivery.</h3> </td> </tr> <td> <hr> </td>";
        } else if (tipoOperacion.equals("CAMBIO CONTRASEÑA")) {
            body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/spring-bucket-biclapp-dev/app-images/logo-citybike.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, hemos recibido su solicitud para el cambio de contraseña.</h3> <h3 class='texto'>Para ello, te hemos enviado un código de verificación.</h3> <h2 class='texto-2'>" + model.get("codigo-verificacion").toString() + "</h2> </td> </tr> <td> <hr> </td>";
        } else if (tipoOperacion.equals("CONFIRMA ACTUALIZACIÓN CONTRASEÑA")) {
            body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/spring-bucket-biclapp-dev/app-images/logo-citybike.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su contraseña ha sido actualizada correctamente. </h3> </td> </tr> <td> <hr> </td>";
        }

        String footer = "<tr> <td id class='footer'> <p>Biclapp &copy;2021</p> </td> </tr> </table> </div> </body></html>";

        return header.concat(body).concat(footer);
    }
}
