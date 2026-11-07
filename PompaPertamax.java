/**
 * Pompa BBM non-subsidi dengan poin member dan servis berkala.
 */
public class PompaPertamax extends Pompa implements Transaksi, Perawatan {

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
