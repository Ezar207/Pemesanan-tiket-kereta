import java.util.ArrayList;

public class Pesanan {
    Kereta kereta;
    Pembeli pembeli;
    ArrayList<String> makananMinuman;
    int totalHarga;

    public Pesanan(Kereta kereta, Pembeli pembeli) {
        this.kereta = kereta;
        this.pembeli = pembeli;
        this.makananMinuman = new ArrayList<>();
        this.totalHarga = kereta.harga * pembeli.jumlahKursi;
    }
}
