package com.biclapp.config.mail;

import com.biclapp.model.DTO.DTOCreatePedidos;
import com.biclapp.model.DTO.DTODetallePedido;
import com.biclapp.model.entity.Membresias;
import com.biclapp.model.entity.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        StringBuilder detail = new StringBuilder();
        String header = "<!DOCTYPE html><html lang='es'> <head> <title>Biclapp</title> <meta charset='UTF-8' /> <meta name='viewport' content='width=device-width, initial-scale=1.0' /> <style> html { height: 100%; } body { color: #808080; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; width: 100%; padding: 0; margin: 0; height: 100%; } @media (min-width: 0) and (max-width: 420px) { .container { margin: auto; height: 100%; width: 100%; padding-left: .5em; padding-right: .5em; } table { width: 100%; height: 100%; } .imagen { padding-top: 2em; padding-bottom: 2em; text-align: center; } hr { width: 30px; margin-top: 1em; margin-bottom: 1em; height: .05px; background-color: #2cb02c; border-radius: 1em; color: #2cb02c; } .content { padding: 1em; font-size: 1rem; text-align: center; } .titulo { text-align: center; font-weight: lighter; font-size: 1em; color: black; } .texto { padding-top: .5em; padding-bottom: .5em; font-weight: lighter; } .texto-2 { font-weight: bolder; color: rgb(5, 75, 5); font-size: 2em; } .footer { padding-top: 1em; padding-bottom: 1em; text-align: center; font-weight: normal; font-size: 1rem; } } @media (min-width: 421px) { .container { margin: auto; height: 100%; width: 650px; } table { width: 100%; height: 100%; } .imagen { padding-top: 5em; padding-bottom: 2em; text-align: center; } hr { width: 30px; margin-top: 1em; margin-bottom: 1em; height: 1px; background-color: #2cb02c; border-radius: 1em; color: #2cb02c; } .content { font-size: 1rem; text-align: center; padding: 1em 0; } .titulo { text-align: center; font-weight: lighter; font-size: 2em; color: black; } .texto { padding-top: .5em; padding-bottom: .5em; font-weight: lighter; } .texto-2 { font-weight: bolder; color: rgb(5, 75, 5); font-size: 2.6em; } .footer { padding-top: 2em; padding-bottom: 3em; text-align: center; font-weight: normal; font-size: 1.2rem; } } </style> </head>";
        String body = "";
        Membresias membresiaFound;
        Pedidos pedidoFound;
        double total = 0.00;

        switch (tipoOperacion) {
            case "VALIDAR CUENTA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, hemos recibido tu peticiòn de registro a CityBike Lima Delivery.</h3> <h3 class='texto'>Para confirmar tu cuenta, te hemos enviado un código de verificación: </h3> <h2 class='texto-2'>" + model.get("codigo-verificacion").toString() + " </h2> </td> </tr> <td> <hr> </td>";
                break;
            case "CONFIRMA VALIDACIÓN CUENTA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su cuenta ha sido activada correctamente.</h3> <h3 class='texto'>Le damos la bienvenida a Citybike Lima Delivery.</h3> </td> </tr> <td> <hr> </td>";
                break;
            case "CAMBIO CONTRASEÑA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, hemos recibido su solicitud para el cambio de contraseña.</h3> <h3 class='texto'>Para ello, te hemos enviado un código de verificación.</h3> <h2 class='texto-2'>" + model.get("codigo-verificacion").toString() + "</h2> </td> </tr> <td> <hr> </td>";
                break;
            case "CONFIRMA ACTUALIZACIÓN CONTRASEÑA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su contraseña ha sido actualizada correctamente. </h3> </td> </tr> <td> <hr> </td>";
                break;
            case "NUEVO CÓDIGO":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='validar-email' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, te hemos enviado un nuevo código de verificación: </h3> <h2 class='texto-2'>" + model.get("codigo-verificacion").toString() + " </h2> </td> </tr> <td> <hr> </td>";
                break;
            case "REGISTRO PEDIDO":
                pedidoFound = (Pedidos) model.get("pedido");
                DTOCreatePedidos pedidoDet = (DTOCreatePedidos) model.get("detalle-pedido");
                membresiaFound = (Membresias) model.get("membresia");
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='confirma-pedido' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que hemos registrado su pedido de los siguientes artículos: </h3> <tr> <td> <table style='text-align: center'> <tr> <td colspan='2'>PEDIDO</td> </tr><tr></tr><tr></tr><tr></tr> <tr> <td colspan='1'>CÓDIGO</td> <td colspan='1'>" + pedidoFound.getId() + "</td> </tr> <tr> <td colspan='1'>TIPO PEDIDO</td> <td colspan='1'>" + formatTipoPedido(pedidoDet.getTipo_pedido()) + "</td> </tr> <tr> <td colspan='1'>DIRECCIÓN</td> <td colspan='1'>" + pedidoDet.getDireccion() + "</td>   <tr> <td colspan='1'>MEMBRESÍA SELECCIONADA</td> <td colspan='1'>" + membresiaFound.getTipo().toUpperCase() + "</td> </tr>    <tr> <td colspan='1'>FECHA DE PEDIDO</td> <td colspan='1'>" + pedidoDet.getFecha_registro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "</td> </tr>  </tr> <tr></tr> <tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='2'>DETALLES PEDIDO</td></tr>";
                pedidoDet.getDetalles_pedido().forEach(item -> {
                    String costo;
                    if (pedidoDet.getTipo_pedido().equals("A") || pedidoDet.getTipo_pedido().equals("B")) {
                        costo = item.getPrecio().toString();
                    } else {
                        costo = item.getPuntos().toString();
                    }
                    detail
                            .append("<tr> <td colspan='1'>PRODUCTO</td> <td colspan='1'>").append(item.getProducto())
                            .append("</td> </tr> <tr> <td colspan='1'>CANTIDAD</td> <td colspan='1'>").append(item.getCantidad())
                            .append("</td> </tr> <tr> <td colspan='1'>PRECIO</td> <td colspan='1'>").append((!pedidoDet.getTipo_pedido().equals("C")) ? "S/. " : "").append(costo)
                            .append("</td> </tr> <tr></tr><tr></tr><tr></tr><tr></tr>");
                });

                detail
                        .append("<tr> <td colspan='1'>TOTAL</td> <td colspan='1'>").append((!pedidoFound.getTipo_pedido().equals("C")) ? "S/. " : "").append(total)
                        .append("</table></td></tr>");
                body = body.concat(detail.toString().concat((!pedidoDet.getTipo_pedido().equals("C")) ? "<tr style='text-align: center'> <td> <h3 class='texto'>Para poder hacer efectiva su compra, enviar el voucher de pago a uno de los siguientes nro de teléfono a continuación</h3> </td> </tr> <tr style='text-align: center'> <td> <h3 class='texto-normal'>983422657</h3> <h3 class='texto-normal'>900120344</h3> </td> </tr> <tr style='text-align: center'> <td> <h3 class='texto'>Aceptamos pagos vía transferencia bancaria a las siguientes cuentas:</h3> <h3 class='texto-normal'>BCP SOLES - 191-0939560-0-04</h3> <h3 class='texto-normal'>BBVA SOLES - 0011-0686-0100023327</h3> </td> </tr> </td> </tr>" : ""));
                body = body.concat("<td> <hr> </td>");
                break;
            case "PEDIDO EN CURSO":
                pedidoFound = (Pedidos) model.get("pedido");
                String tipoPedido = formatTipoPedido(pedidoFound.getTipo_pedido());
                membresiaFound = (Membresias) model.get("membresia");
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='confirma-pedido' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su pedido ha sido confirmado. </h3> <tr> <td> <table style='text-align: center'> <tr> <td colspan='2'>PEDIDO</td> </tr><tr></tr><tr></tr><tr></tr> <tr> <td colspan='1'>CÓDIGO</td> <td colspan='1'>" + pedidoFound.getId() + "</td> </tr> <tr> <td colspan='1'>TIPO PEDIDO</td> <td colspan='1'>" + tipoPedido + "</td> </tr> <tr> <td colspan='1'>DIRECCIÓN</td> <td colspan='1'>" + pedidoFound.getDireccion() + "</td></tr>   <tr> <td colspan='1'>MEMBRESÍA SELECCIONADA</td> <td colspan='1'>" + membresiaFound.getTipo().toUpperCase() + "</td> </tr>    <tr> <td colspan='1'>FECHA DE PEDIDO</td> <td colspan='1'>" + pedidoFound.getFecha_registro().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "</td> </tr> <tr> <td colspan='1'>FECHA DE ENTREGA</td> <td colspan='1'>" + pedidoFound.getFecha_entrega().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "</td> </tr>   </td> </tr> <tr> <td colspan='1'>HORA ESTIMADA</td> <td colspan='1'>Se realizará la coordinación según la disponibilidad del cliente.</td> </tr>     <tr></tr> <tr></tr><tr></tr><tr></tr><tr></tr><tr><td colspan='2'>DETALLES PEDIDO</td></tr>";
                List<DTODetallePedido> pedidosWithDetails = (List<DTODetallePedido>) model.get("detalle-pedido");
                System.out.println("pedidosWithDetails = " + pedidosWithDetails.size());
                for (DTODetallePedido item : pedidosWithDetails) {
                    total += item.getCantidad() * item.getPrecio();
                    double costo;
                    String producto = "";
                    if (pedidoFound.getTipo_pedido().equals("A") || pedidoFound.getTipo_pedido().equals("B")) {
                        costo = item.getPrecio();
                        if (pedidoFound.getTipo_pedido().equals("A")) {
                            producto = item.getNombre_accesorio();
                        } else {
                            producto = item.getMarca_bicicleta().concat(" - ".concat(item.getModelo_bicicleta()));
                        }
                    } else {
                        costo = item.getPuntos();
                    }
                    detail
                            .append("<tr> <td colspan='1'>PRODUCTO</td> <td colspan='1'>").append(producto)
                            .append("</td> </tr> <tr> <td colspan='1'>CANTIDAD</td> <td colspan='1'>").append(item.getCantidad())
                            .append("</td> </tr> <tr> <td colspan='1'>PRECIO</td> <td colspan='1'>").append((!pedidoFound.getTipo_pedido().equals("C")) ? "S/. " : "").append(costo)
                            .append("</td> </tr> <tr></tr><tr></tr><tr></tr><tr></tr>");
                }

                detail
                        .append("<tr> <td colspan='1'>TOTAL</td> <td colspan='1'>").append((!pedidoFound.getTipo_pedido().equals("C")) ? "S/. " : "").append(total)
                        .append("</table></td></tr>");
                body = body.concat(detail.toString());
                body = body.concat("<td> <hr> </td>");
                break;
            case "PEDIDO ENTREGADO":
                pedidoFound = (Pedidos) model.get("pedido");
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='pedido-entregado' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su pedido Nro. " + pedidoFound.getId() + " ha sido entregado. </h3> <h3 class='texto'>Gracias por confiar en nosotros. </h3> </td> </tr> <td> <hr> </td>";
                break;
            case "PEDIDO DEVUELTO":
                pedidoFound = (Pedidos) model.get("pedido");
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='pedido-entregado' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su pedido Nro. " + pedidoFound.getId() + " ha sido devuelto. </h3> <h3 class='texto'>Gracias por confiar en nosotros. </h3> </td> </tr> <td> <hr> </td>";
                break;
            case "PEDIDO ANULADO":
                pedidoFound = (Pedidos) model.get("pedido");
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='pedido-anulado' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, le informamos que su pedido Nro. " + pedidoFound.getId() + " ha sido anulado. </h3> </td> </tr> <td> <hr> </td>";
                break;
            case "REGISTRO AVERIA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='registro-averia' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, se registró con éxito su solicitud de atención al cliente.</h3> <h3 class='texto'>Responderemos a su solicitud lo más pronto posible.</h3> <h3 class='texto'>Le recordamos además que están disponibles los siguientes números de contacto: </h3> <h3 class='texto-normal'>983422657</h3> <h3 class='texto-normal'>900120344</h3> </td> </tr> <tr> <td> <hr> </td> </tr>";
                break;
            case "ATENCION AVERIA":
                body = "<body> <div class='container'> <table aria-hidden='true'> <tr> <td id class='imagen'> <img src='https://storage.googleapis.com/bucket-citybike-lima-delivery/app-images/logo-citybikelima-delivery.png' alt='registro-averia' width='50%' /> </td> </tr> <tr> <td> <hr> </td> </tr> <tr> <td id class='content'> <h3 class='titulo'>" + model.get("titulo-cabecera").toString() + "</h3> <h3 class='texto'>Saludos, atendió correctamente su incidencia.</h3> <h3 class='texto'>Si tuviese alguna otra duda, le invitamos a comunicarse con nosotros a los siguientes números de contacto: </h3> <h3 class='texto-normal'>983422657</h3> <h3 class='texto-normal'>900120344</h3> </td> </tr> <tr> <td> <hr> </td> </tr>";
                break;
        }

        String footer = "<tr> <td id class='footer'> <p>Biclapp &copy;2021</p> </td> </tr> </table> </div> </body></html>";

        return header.concat(body).concat(footer);
    }

    private String formatTipoPedido(String tipo_pedido) {
        String tipo = "";
        switch (tipo_pedido) {
            case "A":
                tipo = "ACCESORIOS";
                break;
            case "B":
                tipo = "BICICLETA";
                break;
            case "C":
                tipo = "CANJE";
                break;
        }
        return tipo;
    }
}
