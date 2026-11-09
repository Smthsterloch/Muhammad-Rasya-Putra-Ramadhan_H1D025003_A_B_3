/**
 * Superclass abstract untuk seluruh pompa BBM di SPBU.
 */
public abstract class Pompa {

    // Konstanta global (immutability)
    public static final double STOK_KRITIS = 100.0;
    public static final String NAMA_SPBU = "SPBU Unsoed 44.531.03";

    // Atribut instans kekal (immutability)
    private final String idPompa;

    // Atribut state (seluruhnya private)
    private String jenisBBM;
    private double hargaPerLiter;
    private double stokLiter;
    private int jumlahTransaksi;
    private boolean aktif;

    // Konstruktor 1: lengkap
    public Pompa(String idPompa, String jenisBBM, double hargaPerLiter, double stokLiter) {
        this.idPompa = idPompa;
        this.jenisBBM = jenisBBM;
        this.hargaPerLiter = hargaPerLiter;
        this.stokLiter = stokLiter;
        this.jumlahTransaksi = 0;
        this.aktif = true;
    }

    // Konstruktor 2 (overloading): stok awal default 1000 liter
    public Pompa(String idPompa, String jenisBBM, double hargaPerLiter) {
        this(idPompa, jenisBBM, hargaPerLiter, 1000.0);
    }

    // ---------- Getter & Setter ----------
    public String getIdPompa() {
        return idPompa;
    }

    public String getJenisBBM() {
        return jenisBBM;
    }

    public void setJenisBBM(String jenisBBM) {
        this.jenisBBM = jenisBBM;
    }

    public double getHargaPerLiter() {
        return hargaPerLiter;
    }

    public void setHargaPerLiter(double hargaPerLiter) {
        if (hargaPerLiter > 0) {
            this.hargaPerLiter = hargaPerLiter;
        }
    }

    public double getStokLiter() {
        return stokLiter;
    }

    public void setStokLiter(double stokLiter) {
        this.stokLiter = Math.max(0, stokLiter);
    }

    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    // ---------- Method prosedur (void) ----------
    public void tampilkanInfo() {
        System.out.println("[" + NAMA_SPBU + "] Pompa " + idPompa + " | " + jenisBBM
                + " | Rp" + (long) hargaPerLiter + "/L | Stok: " + stokLiter
                + " L | Transaksi: " + jumlahTransaksi
                + " | Status: " + (aktif ? "AKTIF" : "NONAKTIF"));
    }

    public void isiUlangStok(double liter) {
        if (liter > 0) {
            this.stokLiter += liter;
        }
    }

    // ---------- Method fungsi (return value) ----------
    public boolean stokKritis() {
        return stokLiter < STOK_KRITIS;
    }

    // ---------- Abstract method ----------
    /**
     * Mengisi BBM sebanyak liter tertentu.
     * @return total biaya, atau 0 jika pengisian ditolak
     */
    public abstract double isiBahanBakar(double liter);
}
