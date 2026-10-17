package exception;

public class StockInsuffisantException extends IllegalStateException {
    public StockInsuffisantException(int quantiteDemandee, int stockDisponible) {
        super("Stock insuffisant : " + quantiteDemandee
                + " demandé(s), " + stockDisponible + " disponible(s)");
    }
}
