package app;

import model.Musteri;
import service.MusteriServisi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusteriPaneli extends JPanel {
    private MusteriServisi musteriServisi;
    private JTextArea musteriMetinAlani;

    public MusteriPaneli(MusteriServisi musteriServisi) {
        this.musteriServisi = musteriServisi;
        setLayout(new BorderLayout());

        // Üst kısım (Başlık ve Geri Dönme Tuşu)
        JPanel ustPanel = new JPanel(new BorderLayout());
        JLabel baslik = new JLabel("Müşteri Yönetimi", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 18));
        ustPanel.add(baslik, BorderLayout.CENTER);
        JButton geriDonButonu = new JButton("Geri Dön");
        ustPanel.add(geriDonButonu, BorderLayout.WEST);
        add(ustPanel, BorderLayout.NORTH);

        // Geri Dönme Tuşu Aksiyonu
        geriDonButonu.addActionListener(e -> {
            Container parent = this.getParent();
            while (!(parent instanceof JFrame)) {
                parent = parent.getParent();
            }
            JFrame frame = (JFrame) parent;
            frame.dispose(); // Pencereyi kapat
        });

        // Müşteri listeleme alanı
        musteriMetinAlani = new JTextArea();
        musteriMetinAlani.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(musteriMetinAlani);
        add(scrollPane, BorderLayout.CENTER);

        // Müşteri ekleme paneli
        JPanel eklemePaneli = new JPanel();
        eklemePaneli.setLayout(new GridLayout(5, 2));
        eklemePaneli.add(new JLabel("ID:"));
        JTextField idAlani = new JTextField();
        eklemePaneli.add(idAlani);
        eklemePaneli.add(new JLabel("Ad:"));
        JTextField adAlani = new JTextField();
        eklemePaneli.add(adAlani);
        eklemePaneli.add(new JLabel("Telefon:"));
        JTextField telefonAlani = new JTextField();
        eklemePaneli.add(telefonAlani);
        eklemePaneli.add(new JLabel("Email:"));
        JTextField emailAlani = new JTextField();
        eklemePaneli.add(emailAlani);
        JButton ekleButonu = new JButton("Ekle");
        eklemePaneli.add(ekleButonu);
        add(eklemePaneli, BorderLayout.SOUTH);

        // Müşteri ekleme butonu aksiyonu
        ekleButonu.addActionListener(e -> {
            String id = idAlani.getText();
            String ad = adAlani.getText();
            String telefon = telefonAlani.getText();
            String email = emailAlani.getText();
            //veritabanı ekle
            Musteri musteri = new Musteri(id, ad, telefon, email);
            musteriServisi.musteriEkle(musteri);
            musterileriGuncelle();
        });

        musterileriGuncelle();
    }

    public void musterileriGuncelle() {
        musteriMetinAlani.setText("");
        for (Musteri musteri : musteriServisi.tumMusteriler()) {
            musteriMetinAlani.append("ID: " + musteri.getId() + ", Ad: " + musteri.getAd() + ", Telefon: " + musteri.getTelefon() + ", Email: " + musteri.getEmail() + "\n");
        }
    }
}




