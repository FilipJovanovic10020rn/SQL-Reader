package validator.rules;

public abstract class AbstractRule {
    private String poruka;

    public abstract String proveraPravila(String upit);

    public abstract boolean provera(String entitet, String atribut);
}
