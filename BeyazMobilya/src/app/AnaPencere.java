package app;

import service.EnvanterServisi;
import service.MusteriServisi;
import service.SatisServisi;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EnvanterPaneli; // Bu satır eklenmeli

public class AnaPencere extends JFrame {

    private EnvanterServisi envanterServisi;
    private MusteriServisi musteriServisi;
    private SatisServisi satisServisi;
    private JPanel anaPanel;
    private CardLayout cardLayout;

    public AnaPencere() throws SQLException {
        envanterServisi = new EnvanterServisi();
        musteriServisi = new MusteriServisi();
        satisServisi = new SatisServisi();

        setTitle("Mobilya Mağazası Otomasyonu");
        setSize(1200, 800); // Boyut artırıldı
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        anaPanel = new JPanel(cardLayout);

        // Panelleri oluştur
        JPanel anaMenuPaneli = yeniAnaMenuPaneli();
        EnvanterServisi envanterServisi = new EnvanterServisi(); // Geçerli bir nesne oluşturulduğundan emin olun
        EnvanterPaneli envanterPaneli = new EnvanterPaneli(envanterServisi);

        MusteriPaneli musteriPaneli = new MusteriPaneli(musteriServisi);
        SatisPaneli satisPaneli = new SatisPaneli(satisServisi, musteriServisi, envanterServisi);

        // Panelleri ekle
        anaPanel.add(anaMenuPaneli, "Ana Menü");
        anaPanel.add(envanterPaneli, "Envanter");
        anaPanel.add(musteriPaneli, "Müşteriler");
        anaPanel.add(satisPaneli, "Satışlar");

        // Ana panele ekle
        add(anaPanel);
    }

    private JPanel yeniAnaMenuPaneli() {
        JPanel menuPaneli = new JPanel();
        menuPaneli.setLayout(new BorderLayout());

        // Başlık etiketi
        JLabel baslik = new JLabel("<html>Be<span style='color:red;'>y</span>az Mobilya</html>", JLabel.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 48)); // Font büyüklüğü artırıldı
        baslik.setForeground(Color.WHITE); // Metin rengi beyaz yapıldı
        baslik.setOpaque(true);
        baslik.setBackground(Color.BLACK); // Arka plan rengi siyah yapıldı
        menuPaneli.add(baslik, BorderLayout.NORTH);

        // Menü butonları
        JPanel butonPaneli = new JPanel(new GridLayout(1, 3));
        JButton envanterButonu = new JButton("Envanter");
        JButton musteriButonu = new JButton("Müşteriler");
        JButton satisButonu = new JButton("Satışlar");
        butonPaneli.add(envanterButonu);
        butonPaneli.add(musteriButonu);
        butonPaneli.add(satisButonu);
        menuPaneli.add(butonPaneli, BorderLayout.CENTER);

        // Buton aksiyonları
        envanterButonu.addActionListener(e -> cardLayout.show(anaPanel, "Envanter"));
        musteriButonu.addActionListener(e -> cardLayout.show(anaPanel, "Müşteriler"));
        satisButonu.addActionListener(e -> cardLayout.show(anaPanel, "Satışlar"));

        // Butonların arka plan rengi ve yazı rengi ayarlandı
        Color butonRenk = new Color(68, 114, 196); // Mavi tonunda renk
        envanterButonu.setBackground(butonRenk);
        envanterButonu.setForeground(Color.WHITE);
        musteriButonu.setBackground(butonRenk);
        musteriButonu.setForeground(Color.WHITE);
        satisButonu.setBackground(butonRenk);
        satisButonu.setForeground(Color.WHITE);

        // Butonların kenarlık rengi ve boyutu ayarlandı
        envanterButonu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        musteriButonu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        satisButonu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        return menuPaneli;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnaPencere pencere = null;
            try {
                pencere = new AnaPencere();
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
            pencere.setVisible(true);
        });
    }
}
