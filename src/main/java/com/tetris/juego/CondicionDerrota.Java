public class CondicionDeDerrota {
    private boolean derrota = false;

    public boolean esDerrota() {
        return derrota;
    }

    public void activarDerrota() {
        derrota = true;
    }
}
