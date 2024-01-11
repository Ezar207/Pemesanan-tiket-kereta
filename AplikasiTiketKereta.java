import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AplikasiTiketKereta {
    static ArrayList<Kereta> daftarKereta = new ArrayList<>();
    static ArrayList<Pesanan> riwayatPembelian = new ArrayList<>();
    static Map<String, Integer> daftarMakanan = new HashMap<>();
    static Map<String, Integer> daftarMinuman = new HashMap<>();

    public static void main(String[] args) {
        inisialisasiDataKereta();
        inisialisasiDaftarMakananMinuman();

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("=== Aplikasi Pembelian Tiket Kereta ===");
            System.out.println("1. Melihat ketersediaan kereta");
            System.out.println("2. Pembelian tiket");
            System.out.println("3. Pesan makanan dan minuman");
            System.out.println("4. Kritik dan saran");
            System.out.println("5. Tampilkan riwayat pembelian");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    melihatKetersediaanKereta();
                    break;
                case 2:
                    pembelianTiket();
                    break;
                case 3:
                    pesanMakananMinuman();
                    break;
                case 4:
                    kritikSaran();
                    break;
                case 5:
                    tampilkanRiwayatPembelian();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 6);
    }

    private static void inisialisasiDataKereta() {
        daftarKereta.add(new Kereta("Bisnis", "Jakarta - Surabaya", "08.00", 150000));
        daftarKereta.add(new Kereta("Ekonomi", "Jakarta - Surabaya", "10.00", 100000));
        daftarKereta.add(new Kereta("Bisnis", "Surabaya - Jakarta", "12.00", 150000));
        daftarKereta.add(new Kereta("Ekonomi", "Surabaya - Jakarta", "14.00", 100000));
    }

    private static void inisialisasiDaftarMakananMinuman() {
        daftarMakanan.put("Nasi Goreng", 20000);
        daftarMakanan.put("Mie Goreng", 18000);
        daftarMakanan.put("Ayam Bakar", 25000);

        daftarMinuman.put("Es Teh", 5000);
        daftarMinuman.put("Es Jeruk", 6000);
        daftarMinuman.put("Air Mineral", 3000);
    }

    private static void melihatKetersediaanKereta() {
        System.out.println("=== Ketersediaan Kereta ===");
        for (Kereta kereta : daftarKereta) {
            System.out.println("Jenis: " + kereta.jenis);
            System.out.println("Tujuan: " + kereta.tujuan);
            System.out.println("Jam: " + kereta.jam);
            System.out.println("Harga: " + kereta.harga);
            System.out.println("============================");
        }
    }

    private static void pembelianTiket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Pembelian Tiket ===");
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.next();
        System.out.print("Pilih jenis kereta (Bisnis/Ekonomi): ");
        String jenisKereta = scanner.next();
        System.out.print("Masukkan jumlah kursi yang ingin dibeli: ");
        int jumlahKursi = scanner.nextInt();

        Kereta keretaTerpilih = null;
        for (Kereta kereta : daftarKereta) {
            if (kereta.jenis.equalsIgnoreCase(jenisKereta)) {
                keretaTerpilih = kereta;
                break;
            }
        }

        if (keretaTerpilih != null) {
            Pembeli pembeli = new Pembeli(nama, jumlahKursi);
            Pesanan pesanan = new Pesanan(keretaTerpilih, pembeli);
            riwayatPembelian.add(pesanan);
            System.out.println("Pembelian tiket berhasil!");
        } else {
            System.out.println("Jenis kereta tidak valid. Pembelian tiket gagal.");
        }
    }

    private static void pesanMakananMinuman() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Pesan Makanan dan Minuman ===");
        System.out.print("Masukkan nomor pesanan: ");
        int nomorPesanan = scanner.nextInt();

        if (nomorPesanan > 0 && nomorPesanan <= riwayatPembelian.size()) {
            Pesanan pesanan = riwayatPembelian.get(nomorPesanan - 1);

            // Tampilkan daftar makanan dan minuman
            System.out.println("Daftar Makanan:");
            int makananIndex = 1;
            for (Map.Entry<String, Integer> entry : daftarMakanan.entrySet()) {
                System.out.println(makananIndex + ". " + entry.getKey() + " - Harga: Rp" + entry.getValue());
                makananIndex++;
            }

            System.out.println("Daftar Minuman:");
            int minumanIndex = 4;
            for (Map.Entry<String, Integer> entry : daftarMinuman.entrySet()) {
                System.out.println(minumanIndex + ". " + entry.getKey() + " - Harga: Rp" + entry.getValue());
                minumanIndex++;
            }

            int totalHargaMakananMinuman = 0;

            System.out.println("Pilih makanan/minuman (ketik 'selesai' untuk menyelesaikan pesanan): ");
            String item;
            while (!(item = scanner.next()).equalsIgnoreCase("selesai")) {
                try {
                    int selectedItem = Integer.parseInt(item);

                    if (selectedItem >= 1 && selectedItem <= makananIndex - 1) {
                        String makanan = (String) daftarMakanan.keySet().toArray()[selectedItem - 1];
                        pesanan.makananMinuman.add(makanan);
                        totalHargaMakananMinuman += daftarMakanan.get(makanan);
                    } else if (selectedItem >= makananIndex && selectedItem <= makananIndex + minumanIndex - 2) {
                        String minuman = (String) daftarMinuman.keySet().toArray()[selectedItem - makananIndex];
                        pesanan.makananMinuman.add(minuman);
                        totalHargaMakananMinuman += daftarMinuman.get(minuman);
                    } else {
                        System.out.println("Nomor pilihan tidak valid. Silakan pilih lagi.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Silakan pilih lagi.");
                }
            }

            pesanan.totalHarga += totalHargaMakananMinuman;

            System.out.println("Pembelian makanan dan minuman berhasil!");
            System.out.println("Total Harga Makanan dan Minuman: Rp" + totalHargaMakananMinuman);
        } else {
            System.out.println("Nomor pesanan tidak valid. Pesanan makanan dan minuman gagal.");
        }
    }

    private static void kritikSaran() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Kritik dan Saran ===");
        System.out.print("Masukkan kritik dan saran Anda: ");
        String input = scanner.nextLine();
        System.out.println("Terima kasih atas kritik dan saran Anda!");
    }

    private static void tampilkanRiwayatPembelian() {
        System.out.println("=== Riwayat Pembelian ===");
        int totalPembelian = 0;

        for (int i = 0; i < riwayatPembelian.size(); i++) {
            Pesanan pesanan = riwayatPembelian.get(i);

            System.out.println("Nomor Pesanan: " + (i + 1));
            System.out.println("Jenis Kereta: " + pesanan.kereta.jenis);
            System.out.println("Tujuan: " + pesanan.kereta.tujuan);
            System.out.println("Jam: " + pesanan.kereta.jam);
            System.out.println("Harga Tiket: Rp" + pesanan.kereta.harga);
            System.out.println("Nama Pembeli: " + pesanan.pembeli.nama);
            System.out.println("Jumlah Kursi: " + pesanan.pembeli.jumlahKursi);

            if (!pesanan.makananMinuman.isEmpty()) {
                System.out.println("Makanan dan Minuman:");
                for (String item : pesanan.makananMinuman) {
                    int hargaItem = daftarMakanan.containsKey(item) ? daftarMakanan.get(item) : daftarMinuman.get(item);
                    System.out.println(item + " - Harga: Rp" + hargaItem);
                }
            }

            System.out.println("Total Harga: Rp" + pesanan.totalHarga);
            System.out.println("============================");

            totalPembelian += pesanan.totalHarga;
        }

        System.out.println("Total Pembelian Tiket dan Makanan/Minuman: Rp" + totalPembelian);
    }
}
