package model;

import service.EnvanterServisi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnvanterPaneli extends JPanel {

    private EnvanterServisi envanterServisi;
    private JTextArea envanterMetinAlani;
    private JTextField adField, kategoriField, fiyatField, stokField, malzemeField, renkField, agirlikField, boyutlarField, uretimTarihiField, tedarikciIdField;

    public EnvanterPaneli(EnvanterServisi envanterServisi) {
        this.envanterServisi = envanterServisi;
        setLayout(new BorderLayout());

        // Üst kısım (Başlık ve Geri Dönme Tuşu)
        JPanel ustPanel = new JPanel(new BorderLayout());
        JLabel baslik = new JLabel("Envanter Yönetimi", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 24));
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

        // Orta kısım (Envanter listesi)
        envanterMetinAlani = new JTextArea();
        envanterMetinAlani.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(envanterMetinAlani);
        add(scrollPane, BorderLayout.CENTER);

        // Alt kısım (Envanter ekleme formu)
        JPanel altPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Boşluk ekledik
        JLabel[] labels = {
                new JLabel("Ad:"), new JLabel("Kategori:"), new JLabel("Fiyat:"),
                new JLabel("Stok:"), new JLabel("Malzeme:"), new JLabel("Renk:"),
                new JLabel("Ağırlık:"), new JLabel("Boyutlar:"), new JLabel("Üretim Tarihi (yyyy-MM-dd):"),
                new JLabel("Tedarikçi ID:")
        };
        adField = new JTextField();
        kategoriField = new JTextField();
        fiyatField = new JTextField();
        stokField = new JTextField();
        malzemeField = new JTextField();
        renkField = new JTextField();
        agirlikField = new JTextField();
        boyutlarField = new JTextField();
        uretimTarihiField = new JTextField();
        tedarikciIdField = new JTextField();
        JTextField[] textFields = {
                adField, kategoriField, fiyatField, stokField, malzemeField, renkField,
                agirlikField, boyutlarField, uretimTarihiField, tedarikciIdField
        };
        for (int i = 0; i < labels.length; i++) {
            altPanel.add(labels[i]);
            altPanel.add(textFields[i]);
        }
        JButton ekleButonu = new JButton("Ekle");
        altPanel.add(new JLabel()); // Boşluk
        altPanel.add(ekleButonu);
        add(altPanel, BorderLayout.SOUTH);

        // Ekleme butonu aksiyonu
        ekleButonu.addActionListener(e -> ekleButonuTiklandi());

        // İlk envanterleri yükle
        envanterGuncelle();
    }

    private void ekleButonuTiklandi() {
        try {
            String ad = adField.getText();
            String kategori = kategoriField.getText();
            double fiyat = Double.parseDouble(fiyatField.getText());
            int stok = Integer.parseInt(stokField.getText());
            String malzeme = malzemeField.getText();
            String renk = renkField.getText();
            double agirlik = Double.parseDouble(agirlikField.getText());
            String boyutlar = boyutlarField.getText();
            String uretimTarihiStr = uretimTarihiField.getText();
            int tedarikciId = Integer.parseInt(tedarikciIdField.getText());

            // Tarihi dönüştürme
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date uretimTarihi = dateFormat.parse(uretimTarihiStr);

            Mobilya mobilya = new Mobilya();
            mobilya.setAd(ad);
            mobilya.setKategori(kategori);
            mobilya.setFiyat(fiyat);
            mobilya.setStok(stok);
            mobilya.setMalzeme(malzeme);
            mobilya.setRenk(renk);
            mobilya.setAgirlik(agirlik);
            mobilya.setBoyutlar(boyutlar);
            mobilya.setUretimTarihi(uretimTarihi);
            mobilya.setTedarikciId(tedarikciId);

            envanterServisi.mobilyaEkle(mobilya);
            envanterGuncelle();
            JOptionPane.showMessageDialog(this, "Mobilya başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bilgiler girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void envanterGuncelle() {
        envanterMetinAlani.setText("");
        for (Mobilya mobilya : envanterServisi.tumMobilyalar()) {
            envanterMetinAlani.append("ID: " + mobilya.getId() + ", Ad: " + mobilya.getAd() + ", Kategori: " + mobilya.getKategori() +
                    ", Fiyat: " + mobilya.getFiyat() + ", Stok: " + mobilya.getStok() + ", Malzeme: " + mobilya.getMalzeme() +
                    ", Renk: " + mobilya.getRenk() + ", Ağırlık: " + mobilya.getAgirlik() + ", Boyutlar: " + mobilya.getBoyutlar() +
                    ", Üretim Tarihi: " + mobilya.getUretimTarihi() + ", Tedarikçi ID: " + mobilya.getTedarikciId() + "\n");
        }
    }
}

