class TiketKereta {
    private String kelas;
    private int harga;
    private int jumlahTiket;

    public TiketKereta(String kelas, int harga) {
        this.kelas = kelas;
        this.harga = harga;
        this.jumlahTiket = 0;
    }

    public void pesanTiket(int jumlah) {
        this.jumlahTiket += jumlah;
    }

    public int totalHarga() {
        int total = this.jumlahTiket * this.harga;

        // Diskon 10% jika jumlah tiket lebih dari 5
        if (this.jumlahTiket > 5) {
            double diskon = 0.1 * total;
            total -= diskon;
            System.out.println("Selamat, Anda telah mendapatkan diskon 10% karena telah memesan lebih dari lima tiket.");
        }

        return total;
    }

    public String getKelas() {
        return this.kelas;
    }

    public int getJumlahTiket() {
        return this.jumlahTiket;
    }
}
