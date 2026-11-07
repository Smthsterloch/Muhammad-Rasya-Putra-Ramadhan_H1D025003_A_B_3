public class Main {
    public static void main(String[] args) {
        System.out.println("=== Konstanta global (req 2): " + Pompa.NAMA_SPBU
                + ", stok kritis < " + Pompa.STOK_KRITIS + " L ===\n");

        // Req 3: overloaded constructor
        PompaPertalite pertalite = new PompaPertalite("P-01", 10000, 150);   // 3 parameter
        PompaPertamax pertamax = new PompaPertamax("P-02", 15950);           // 2 parameter
        PompaSolar solar = new PompaSolar("P-03", 6800);

        // Req 4, 7: polimorfisme lewat superclass abstract
        Pompa[] daftarPompa = {pertalite, pertamax, solar};
        System.out.println("--- Info semua pompa (override + super.tampilkanInfo) ---");
        for (Pompa p : daftarPompa) {
            p.tampilkanInfo();
        }

        // Req 9: referensi bertipe interface
        System.out.println("\n--- Transaksi lewat interface Transaksi (req 9) ---");
        Transaksi t1 = new PompaPertalite("P-04", 10000, 150);
        Transaksi t2 = pertamax;
        Transaksi t3 = solar;
        t1.cetakStruk(20);
        t2.cetakStruk(30);
        t3.cetakStruk(100);

        // Req 8: logika if/else membaca & mengubah state
        System.out.println("\n--- Pengisian Pertalite (if/else state) ---");
        System.out.println("Isi 20 L  -> Rp" + (long) pertalite.isiBahanBakar(20));
        System.out.println("Isi 80 L  -> Rp" + (long) pertalite.isiBahanBakar(80)); // ditolak (subsidi)
        System.out.println("Isi 60 L  -> Rp" + (long) pertalite.isiBahanBakar(60));
        System.out.println("Isi 60 L  -> Rp" + (long) pertalite.isiBahanBakar(60)); // stok kritis
        System.out.println("Isi 60 L  -> Rp" + (long) pertalite.isiBahanBakar(60)); // stok tidak cukup
        pertalite.tampilkanInfo();

        System.out.println("\n--- Pengisian Pertamax (poin & diskon member) ---");
        for (int i = 1; i <= 4; i++) {
            System.out.println("Isi 30 L (ke-" + i + ") -> Rp" + (long) pertamax.isiBahanBakar(30));
        }
        pertamax.tampilkanInfo();

        // Req 10: multiple interface (Transaksi + Perawatan)
        System.out.println("\n--- Perawatan lewat interface Perawatan (req 10) ---");
        Perawatan r1 = pertamax;
        Perawatan r2 = solar;
        System.out.println("Pertamax perlu servis? " + r1.perluServis());
        System.out.println("Isi 10 L saat nonaktif -> Rp" + (long) pertamax.isiBahanBakar(10));
        r1.servis();
        System.out.println("Isi 10 L setelah servis -> Rp" + (long) pertamax.isiBahanBakar(10));

        System.out.println("\n--- Pengisian Solar ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Isi 200 L (ke-" + i + ") -> Rp" + (long) solar.isiBahanBakar(200));
        }
        System.out.println("Solar perlu servis? " + r2.perluServis());
        r2.servis();

        // Req 3: setter
        System.out.println("\n--- Setter & isi ulang stok ---");
        pertalite.setHargaPerLiter(10500);
        pertalite.isiUlangStok(500);
        pertalite.tampilkanInfo();
        solar.tampilkanInfo();
    }
}

/**
 * Interface untuk semua pompa yang melayani transaksi pembelian BBM.
 */
interface Transaksi {
    double hitungTotal(double liter);

    void cetakStruk(double liter);
}

/**
 * Interface untuk pompa yang memerlukan servis berkala.
 */
interface Perawatan {
    void servis();

    boolean perluServis();
}

/**
 * Superclass abstract untuk seluruh pompa BBM di SPBU.
 */
abstract class Pompa {

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

/**
 * Pompa BBM bersubsidi, dengan batas pembelian per transaksi.
 */
class PompaPertalite extends Pompa implements Transaksi {

    public static final double BATAS_LITER_SUBSIDI = 60.0;

    public PompaPertalite(String idPompa, double hargaPerLiter, double stokLiter) {
        super(idPompa, "Pertalite", hargaPerLiter, stokLiter);
    }

    public PompaPertalite(String idPompa, double hargaPerLiter) {
        super(idPompa, "Pertalite", hargaPerLiter);
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Batas subsidi per transaksi: " + BATAS_LITER_SUBSIDI + " L");
    }

    @Override
    public double isiBahanBakar(double liter) {
        if (!isAktif()) {
            System.out.println("Ditolak: pompa " + getIdPompa() + " nonaktif.");
            return 0;
        } else if (liter <= 0) {
            System.out.println("Ditolak: jumlah liter tidak valid.");
            return 0;
        } else if (liter > BATAS_LITER_SUBSIDI) {
            System.out.println("Ditolak: melebihi batas subsidi " + BATAS_LITER_SUBSIDI + " L.");
            return 0;
        } else if (liter > getStokLiter()) {
            System.out.println("Ditolak: stok tidak cukup (sisa " + getStokLiter() + " L).");
            return 0;
        } else {
            setStokLiter(getStokLiter() - liter);
            setJumlahTransaksi(getJumlahTransaksi() + 1);
            if (stokKritis()) {
                System.out.println("Peringatan: stok " + getJenisBBM() + " kritis!");
            }
            return hitungTotal(liter);
        }
    }

    @Override
    public double hitungTotal(double liter) {
        return liter * getHargaPerLiter();
    }

    @Override
    public void cetakStruk(double liter) {
        System.out.println("STRUK | " + getJenisBBM() + " " + liter + " L | Total: Rp"
                + (long) hitungTotal(liter));
    }
}

/**
 * Pompa BBM non-subsidi dengan poin member dan servis berkala.
 */
class PompaPertamax extends Pompa implements Transaksi, Perawatan {

    public static final int BATAS_SERVIS = 3;
    public static final int POIN_DISKON = 5;
    public static final double DISKON = 0.05;

    private int poinMember;
    private int isiSejakServis;

    public PompaPertamax(String idPompa, double hargaPerLiter, double stokLiter, int poinMember) {
        super(idPompa, "Pertamax", hargaPerLiter, stokLiter);
        this.poinMember = poinMember;
        this.isiSejakServis = 0;
    }

    public PompaPertamax(String idPompa, double hargaPerLiter) {
        this(idPompa, hargaPerLiter, 1000.0, 0);
    }

    public int getPoinMember() {
        return poinMember;
    }

    public void setPoinMember(int poinMember) {
        this.poinMember = poinMember;
    }

    public int getIsiSejakServis() {
        return isiSejakServis;
    }

    public void setIsiSejakServis(int isiSejakServis) {
        this.isiSejakServis = isiSejakServis;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Poin member: " + poinMember + " | Pengisian sejak servis: " + isiSejakServis);
    }

    @Override
    public double isiBahanBakar(double liter) {
        if (!isAktif()) {
            System.out.println("Ditolak: pompa " + getIdPompa() + " nonaktif, perlu servis.");
            return 0;
        } else if (liter <= 0 || liter > getStokLiter()) {
            System.out.println("Ditolak: liter tidak valid / stok tidak cukup.");
            return 0;
        } else {
            double total = hitungTotal(liter);
            setStokLiter(getStokLiter() - liter);
            setJumlahTransaksi(getJumlahTransaksi() + 1);
            poinMember += (int) (liter / 10) + 1;
            isiSejakServis++;
            if (perluServis()) {
                setAktif(false);
                System.out.println("Peringatan: pompa dinonaktifkan, menunggu servis.");
            }
            return total;
        }
    }

    @Override
    public double hitungTotal(double liter) {
        double total = liter * getHargaPerLiter();
        if (poinMember >= POIN_DISKON) {
            total -= total * DISKON;
        }
        return total;
    }

    @Override
    public void cetakStruk(double liter) {
        System.out.println("STRUK | " + getJenisBBM() + " " + liter + " L | Total: Rp"
                + (long) hitungTotal(liter) + " | Poin: " + poinMember);
    }

    @Override
    public void servis() {
        isiSejakServis = 0;
        setAktif(true);
        System.out.println("Servis pompa " + getIdPompa() + " selesai, pompa aktif kembali.");
    }

    @Override
    public boolean perluServis() {
        return isiSejakServis >= BATAS_SERVIS;
    }
}

/**
 * Pompa solar untuk kendaraan besar, dengan servis berkala.
 */
class PompaSolar extends Pompa implements Transaksi, Perawatan {

    public static final int BATAS_SERVIS = 4;

    private double literTerjualHariIni;
    private int isiSejakServis;

    public PompaSolar(String idPompa, double hargaPerLiter, double stokLiter) {
        super(idPompa, "Solar", hargaPerLiter, stokLiter);
        this.literTerjualHariIni = 0;
        this.isiSejakServis = 0;
    }

    public PompaSolar(String idPompa, double hargaPerLiter) {
        super(idPompa, "Solar", hargaPerLiter);
        this.literTerjualHariIni = 0;
        this.isiSejakServis = 0;
    }

    public double getLiterTerjualHariIni() {
        return literTerjualHariIni;
    }

    public void setLiterTerjualHariIni(double literTerjualHariIni) {
        this.literTerjualHariIni = literTerjualHariIni;
    }

    public int getIsiSejakServis() {
        return isiSejakServis;
    }

    public void setIsiSejakServis(int isiSejakServis) {
        this.isiSejakServis = isiSejakServis;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("   -> Terjual hari ini: " + literTerjualHariIni + " L");
    }

    @Override
    public double isiBahanBakar(double liter) {
        if (!isAktif()) {
            System.out.println("Ditolak: pompa " + getIdPompa() + " nonaktif, perlu servis.");
            return 0;
        } else if (liter <= 0) {
            System.out.println("Ditolak: jumlah liter tidak valid.");
            return 0;
        } else if (liter > getStokLiter()) {
            System.out.println("Ditolak: stok solar tidak cukup (sisa " + getStokLiter() + " L).");
            return 0;
        } else {
            setStokLiter(getStokLiter() - liter);
            setJumlahTransaksi(getJumlahTransaksi() + 1);
            literTerjualHariIni += liter;
            isiSejakServis++;
            if (perluServis()) {
                setAktif(false);
                System.out.println("Peringatan: pompa dinonaktifkan, menunggu servis.");
            }
            return hitungTotal(liter);
        }
    }

    @Override
    public double hitungTotal(double liter) {
        return liter * getHargaPerLiter();
    }

    @Override
    public void cetakStruk(double liter) {
        System.out.println("STRUK | " + getJenisBBM() + " " + liter + " L | Total: Rp"
                + (long) hitungTotal(liter));
    }

    @Override
    public void servis() {
        isiSejakServis = 0;
        setAktif(true);
        System.out.println("Servis pompa " + getIdPompa() + " selesai, pompa aktif kembali.");
    }

    @Override
    public boolean perluServis() {
        return isiSejakServis >= BATAS_SERVIS;
    }
}
