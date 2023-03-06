/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastyApp.services;

import FastyApp.entities.Facture;
import FastyApp.utils.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author ISSAM
 */
public class ServiceFacture implements InterfaceFacture<Facture> {

    Connection connect = DataSource.getInstance().getCnx();

    @Override
    public void ajouterFacture(Facture p) {
        try {
            String req = "INSERT INTO `facture` (`id_facture`, `montant`, `id_prop`, `id_client`, `id_produit` ) VALUES ('" + p.getId_facture() + "' , '" + p.getMontant() + "' , '" + p.getId_prop() + "' , '" + p.getId_client() + "' , '" + p.getId_produit() + "')";

            Statement s = connect.createStatement();
            s.executeUpdate(req);
            System.out.println("Facture Created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerFacture(int id) {
        try {
            String req = "DELETE FROM `facture` WHERE  id_facture = " + id;
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Facture Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierFacture(Facture p) {
        try {
            String req = "UPDATE `facture` SET `montant`= '" + p.getMontant() + "', `id_prop`= '" + p.getId_prop() + "', `id_client`= '" + p.getId_client() + "' ,`id_produit`= '" + p.getId_produit() + "' WHERE id_facture = '" + p.getId_facture() + "'";
            Statement st = connect.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Facture> getAllFacture() {
        List<Facture> list = new ArrayList<>();
        try {
            String req = "Select * from facture";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Facture f = new Facture(rs.getInt(1), rs.getString("montant"), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                list.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    @Override
    public Facture getOneByIdF(int id) {
        Facture f = null;
        try {
            String req = "Select * from facture where id_facture=" + id;
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    f = new Facture(rs.getInt(1), rs.getString("montant"), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return f;

    }

    public void exporterPDF(Facture facture) {
        try {
            // this lines to get desktop path
            FileSystemView view = FileSystemView.getFileSystemView();
            File homeDirectory = view.getHomeDirectory();
            String directoryPath = homeDirectory.getPath() + "/FastyFacture";

// create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // this line to prepare the generated file path
            String file_name = directoryPath + "/facture" + facture.getId_facture() + ".pdf";

            // creating the file 
            Document document = new Document();
            // generating the file
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            // start editing file
            document.open();

            PdfPTable titleTable = new PdfPTable(new float[]{2, 4});

// create a cell for the title
            PdfPCell titleCell = new PdfPCell(new Phrase("Facture N° " + facture.getId_facture(), new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD)));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setPadding(0); // set padding to 0

            titleTable.addCell(titleCell);

// create a cell for the image
            Image m = Image.getInstance("C:/Users/ISSAM/Desktop/FastyProject/Fasty/Fasty/src/FastyApp/ImageUsed/logo.png");
            m.scaleAbsolute(100f, 50f);
            PdfPCell imageCell = new PdfPCell(m);
            imageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            imageCell.setBorder(Rectangle.NO_BORDER);
            titleTable.addCell(imageCell);

            document.add(titleTable);

            // create space between table and title
            Paragraph para2 = new Paragraph("\n");
            document.add(para2);

            Paragraph para3 = new Paragraph("Entreprise :  FastyApp");
            document.add(para3);
            Paragraph para4 = new Paragraph("Adresse postale :  1, 2 rue André Ampère - 2083 - Pôle Technologique - El Ghazala");
            document.add(para4);

            Paragraph para5 = new Paragraph("Adresse mail :  fastyappesprit@gmail.com");
            document.add(para5);

            Paragraph para6 = new Paragraph("Téléphone :  +216 00 000 000");
            document.add(para6);
            Paragraph paraa = new Paragraph("\n\n\n");
            document.add(paraa);

            // get the list of elements to display
            //   ObservableList<Reclamation> reclamationsList = getAll();
            // create table of 4 columns
            PdfPTable table = new PdfPTable(4);
            // column 1
            PdfPCell c0 = new PdfPCell(new Phrase("Expediteur"));
            table.addCell(c0);
            PdfPCell cl = new PdfPCell(new Phrase("Recepteur"));
            table.addCell(cl);
            // column 2
            PdfPCell cl1 = new PdfPCell(new Phrase("Produit"));
            table.addCell(cl1);
            // column 3
            PdfPCell cl11 = new PdfPCell(new Phrase("Montant"));
            table.addCell(cl11);

            // set titles of the columns
            table.setHeaderRows(1);

            /*    // filling the table 
            for (int i = 0; i < reclamationsList.size(); i++) {
                

            }*/
            table.addCell(new ServiceUser().getUserFullNameById(facture.getId_prop()));
            table.addCell(new ServiceUser().getUserFullNameById(facture.getId_client()));
            table.addCell(new ServiceProduit().getNameById(facture.getId_produit()));
            table.addCell(facture.getMontant() + " DT");
            // add the table to the file
            document.add(table);
            Paragraph para9 = new Paragraph("Total : " + facture.getMontant() + " DT");
            para9.setAlignment(Element.ALIGN_RIGHT);
            document.add(para9);
            Paragraph para2z = new Paragraph("\n");
            document.add(para2z);
            Paragraph para10 = new Paragraph("Signature");
            para10.setAlignment(Element.ALIGN_RIGHT);

            document.add(para10);
            // save the file
            document.close();
            // open the file
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendEmail(String sourceEmail, String sourcePwd, String desEmail, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a mail session
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sourceEmail, sourcePwd);
            }
        });

        // Create a new email message
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sourceEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(desEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
