/**
 * Pompa solar untuk kendaraan besar, dengan servis berkala.
 */
public class PompaSolar extends Pompa implements Transaksi, Perawatan {

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
