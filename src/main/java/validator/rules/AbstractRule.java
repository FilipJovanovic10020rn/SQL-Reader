package validator.rules;

public abstract class AbstractRule {
    private String poruka;

    public abstract boolean proveraPravila(String upit);
}
