/**
 * Pompa BBM bersubsidi, dengan batas pembelian per transaksi.
 */
public class PompaPertalite extends Pompa implements Transaksi {

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
