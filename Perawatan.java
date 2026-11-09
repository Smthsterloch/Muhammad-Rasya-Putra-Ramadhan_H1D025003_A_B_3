/**
 * Interface untuk pompa yang memerlukan servis berkala.
 */
public interface Perawatan {
    void servis();

    boolean perluServis();
}
