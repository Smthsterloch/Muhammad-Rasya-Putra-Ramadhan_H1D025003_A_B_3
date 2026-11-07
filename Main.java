public class Main {
    public static void main(String[] args) {
        System.out.println("=== Konstanta global (req 2): " + Pompa.NAMA_SPBU
                + ", stok kritis < " + Pompa.STOK_KRITIS + " L ===\n");

        // Req 3: overloaded constructor
        PompaPertalite pertalite = new PompaPertalite("P-01", 10000, 150);   // 3 parameter
        PompaPertamax pertamax = new PompaPertamax("P-02", 13500);           // 2 parameter
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
