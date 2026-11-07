# Sistem SPBU - Responsi Akhir Praktikum PBO

## Identitas
- **Nama**: Muhammad Rasya Putra Ramadhan
- **NIM**: H1D025003
- **Tema**: SPBU
- **Jurusan**: Informatika, Fakultas Teknik, Universitas Jenderal Soedirman

## Deskripsi
Simulasi pompa BBM di SPBU. Superclass abstract `Pompa` punya tiga subclass
(`PompaPertalite`, `PompaPertamax`, `PompaSolar`). Dua interface dipakai:
`Transaksi` dan `Perawatan`.

## Struktur Class
| File | Peran |
|---|---|
| `Pompa.java` | Abstract superclass, atribut private, konstanta, 2 konstruktor |
| `PompaPertalite.java` | Subclass, implements `Transaksi` |
| `PompaPertamax.java` | Subclass, implements `Transaksi, Perawatan` |
| `PompaSolar.java` | Subclass, implements `Transaksi, Perawatan` |
| `Transaksi.java` | Interface transaksi |
| `Perawatan.java` | Interface servis |
| `Main.java` | Alur pengujian seluruh persyaratan |

## Pemetaan 10 Persyaratan
1. **State & Behavior**: atribut `stokLiter`, `hargaPerLiter`, dll; `void tampilkanInfo()`; `double isiBahanBakar()`, `boolean stokKritis()`.
2. **Immutability**: `public static final STOK_KRITIS`, `NAMA_SPBU`; `private final idPompa`.
3. **Enkapsulasi & Overloading**: semua atribut private + getter/setter, `this`, dua konstruktor di setiap class.
4. **Inheritance**: tiga subclass `extends Pompa`; `Main` terpisah.
5. **Super chaining**: `super(...)` di konstruktor subclass, `super.tampilkanInfo()`.
6. **Overriding**: `@Override` pada `tampilkanInfo()` dan method interface.
7. **Abstract class**: `Pompa` abstract dengan `abstract double isiBahanBakar(double)`.
8. **Logika state**: `isiBahanBakar()` memakai if/else yang membaca & mengubah stok, status aktif, poin, dan hitungan servis.
9. **Polimorfisme interface**: `Transaksi t1 = new PompaPertalite(...)`.
10. **Multiple interface**: `PompaPertamax` dan `PompaSolar` mengimplementasikan `Transaksi, Perawatan`.

## Compile & Run (Terminal)
```bash
javac *.java
java Main
```

## Screenshot Luaran
![Luaran program](screenshots/output.png)

> Jalankan program, ambil screenshot terminal, simpan sebagai `screenshots/output.png`.
