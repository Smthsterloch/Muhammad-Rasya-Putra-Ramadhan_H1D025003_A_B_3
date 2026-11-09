/**
 * Interface untuk semua pompa yang melayani transaksi pembelian BBM.
 */
public interface Transaksi {
    double hitungTotal(double liter);

    void cetakStruk(double liter);
}
