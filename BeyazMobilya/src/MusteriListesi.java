import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import model.Musteri;

public class MusteriListesi extends JFrame {
    private JTable musteriTablosu;
    private DefaultTableModel model;

    public MusteriListesi() {
        setTitle("Müşteri Listesi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Müşteri bilgilerini tutacak ArrayList oluştur
        ArrayList<Musteri> musteriler = new ArrayList<>();
        musteriler.add(new Musteri("1", "Ahmet", "1234567890", "ahmet@example.com"));
        musteriler.add(new Musteri("2", "Ayşe", "0987654321", "ayse@example.com"));
        // İstediğiniz kadar müşteri ekleyebilirsiniz...

        // JTable için model oluştur
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Ad");
        model.addColumn("Telefon");
        model.addColumn("Email");

        // Müşteri bilgilerini model üzerine ekle
        for (Musteri musteri : musteriler) {
            model.addRow(new Object[]{musteri.getId(), musteri.getAd(), musteri.getTelefon(), musteri.getEmail()});
        }

        // JTable oluştur ve modeli ata
        musteriTablosu = new JTable(model);

        // JTable görünümünü scroll edilebilir hale getir
        JScrollPane scrollPane = new JScrollPane(musteriTablosu);

        // JTable'i ekranın ortasına yerleştir
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MusteriListesi musteriListesi = new MusteriListesi();
            musteriListesi.setVisible(true);
        });
    }
}

