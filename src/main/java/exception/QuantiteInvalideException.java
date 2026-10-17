package exception;

public class QuantiteInvalideException extends IllegalArgumentException {
    public QuantiteInvalideException(int quantite) {
        super("La quantité doit être strictement positive : " + quantite);
    }
}
