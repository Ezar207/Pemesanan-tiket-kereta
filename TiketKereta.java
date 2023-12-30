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
        return this.jumlahTiket * this.harga;
    }

    public String getKelas() {
        return this.kelas;
    }

    public int getJumlahTiket() {
        return this.jumlahTiket;
    }
}
