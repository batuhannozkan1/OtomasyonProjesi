package app;

import model.Satis;
import service.SatisServisi;
import service.MusteriServisi;
import service.EnvanterServisi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SatisPaneli extends JPanel {

    private SatisServisi satisServisi;
    private MusteriServisi musteriServisi;
    private EnvanterServisi envanterServisi;
    private JTextArea satisMetinAlani;

    public SatisPaneli(SatisServisi satisServisi, MusteriServisi musteriServisi, EnvanterServisi envanterServisi) {
        this.satisServisi = satisServisi;
        this.musteriServisi = musteriServisi;
        this.envanterServisi = envanterServisi;
        setLayout(new BorderLayout());

        // Başlık etiketi
        JLabel baslik = new JLabel("Satış Yönetimi", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 18));
        add(baslik, BorderLayout.NORTH);

        // Satış listeleme alanı
        satisMetinAlani = new JTextArea();
        satisMetinAlani.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(satisMetinAlani);
        add(scrollPane, BorderLayout.CENTER);

        // Satış ekleme paneli
        JPanel eklemePaneli = new JPanel();
        eklemePaneli.setLayout(new GridLayout(6, 2));
        eklemePaneli.add(new JLabel("Satış ID:"));
        JTextField satisIdAlani = new JTextField();
        eklemePaneli.add(satisIdAlani);
        eklemePaneli.add(new JLabel("Müşteri ID:"));
        JTextField musteriIdAlani = new JTextField();
        eklemePaneli.add(musteriIdAlani);
        eklemePaneli.add(new JLabel("Mobilya ID:"));
        JTextField mobilyaIdAlani = new JTextField();
        eklemePaneli.add(mobilyaIdAlani);
        eklemePaneli.add(new JLabel("Satılan Miktar:"));
        JTextField miktarAlani = new JTextField();
        eklemePaneli.add(miktarAlani);
        eklemePaneli.add(new JLabel("Satış Tarihi (yyyy-MM-dd):"));
        JTextField tarihAlani = new JTextField();
        eklemePaneli.add(tarihAlani);
        JButton ekleButonu = new JButton("Satış Ekle");
        eklemePaneli.add(ekleButonu);
        add(eklemePaneli, BorderLayout.SOUTH);

        // Satış ekleme butonu aksiyonu
        ekleButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String satisId = satisIdAlani.getText();
                String musteriId = musteriIdAlani.getText();
                String mobilyaId = mobilyaIdAlani.getText();
                int satilanMiktar = Integer.parseInt(miktarAlani.getText());
                LocalDate satisTarihi = LocalDate.parse(tarihAlani.getText());

                Satis satis = new Satis(satisId, musteriId, mobilyaId, satilanMiktar, satisTarihi);
                satisServisi.satisEkle(satis);
                satislariGuncelle();
            }
        });

        satislariGuncelle();
    }

     public  void hataMesajiGoster(String hataMesaji) {
        JOptionPane.showMessageDialog(this, hataMesaji, "Hata", JOptionPane.ERROR_MESSAGE);
    }

    public void satislariGuncelle() {
        satisMetinAlani.setText("");
        for (Satis satis : satisServisi.tumSatislar()) {
            satisMetinAlani.append("Satış ID: " + satis.getSatisId() + ", Müşteri ID: " + satis.getMusteriId() + ", Mobilya ID: " + satis.getMobilyaId() + ", Satılan Miktar: " + satis.getSatilanMiktar() + ", Satış Tarihi: " + satis.getSatisTarihi() + "\n");
        }
    }
}
