import java.util.Scanner;

class MainPemesananTiket {
    public static void main(String[] args) {
        // Membuat objek tiket untuk kelas bisnis dan ekonomi
        TiketKereta tiketBisnis = new Bisnis();
        TiketKereta tiketEkonomi = new Ekonomi();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di layanan pemesanan tiket kereta!");

        // Memilih kelas tiket
        System.out.println("Silahkan pilih tiket anda ");
        System.out.println("1. Bisnis  (150000) ");
        System.out.println("2. Ekonomi (100000) ");
        System.out.print("Pilih kelas tiket (Bisnis/Ekonomi): ");
        String kelas = scanner.nextLine().trim().toLowerCase();

        // Memilih jumlah tiket
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlahTiket = scanner.nextInt();

        // Memproses pemesanan tiket
        if (kelas.equals("bisnis")) {
            tiketBisnis.pesanTiket(jumlahTiket);
            tampilkanRingkasanPemesanan(tiketBisnis);
        } else if (kelas.equals("ekonomi")) {
            tiketEkonomi.pesanTiket(jumlahTiket);
            tampilkanRingkasanPemesanan(tiketEkonomi);
        } else {
            System.out.println("Kelas tiket tidak valid. Silakan pilih Bisnis atau Ekonomi.");
        }
    }

    private static void tampilkanRingkasanPemesanan(TiketKereta tiket) {
        // Menampilkan ringkasan pemesanan
        System.out.println("\nRingkasan Pemesanan:");
        System.out.println("Kelas: " + tiket.getKelas());
        System.out.println("Jumlah Tiket: " + tiket.getJumlahTiket());
        System.out.println("Total Harga: Rp " + tiket.totalHarga());
    }
}
