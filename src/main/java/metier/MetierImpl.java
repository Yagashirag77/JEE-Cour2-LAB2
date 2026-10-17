package metier;

import dao.IDao;
import exception.QuantiteInvalideException;
import exception.StockInsuffisantException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("metier")
public class MetierImpl implements IMetier {
    @Autowired
    @Qualifier("dao2")
    private IDao dao;
    private final List<String> historique = new ArrayList<>();

    @Override
    public double calcul() {
        return dao.getValue() * 2;
    }

    @Override
    public double commander(int quantite) {
        verifierQuantite(quantite);

        if (quantite > dao.getStock()) {
            throw new StockInsuffisantException(quantite, dao.getStock());
        }

        double sousTotal = dao.getValue() * quantite;
        double remise = quantite >= 3 ? 0.10 : 0.0;
        double total = arrondir(sousTotal * (1 - remise));

        dao.retirerDuStock(quantite);
        historique.add("Commande de " + quantite + " x " + dao.getArticle()
                + " - total : " + total + " EUR");
        return total;
    }

    @Override
    public String getDescriptionArticle() {
        return dao.getArticle() + " - " + dao.getEcurie();
    }

    @Override
    public int getStockDisponible() {
        return dao.getStock();
    }

    @Override
    public List<String> getHistorique() {
        return List.copyOf(historique);
    }

    private void verifierQuantite(int quantite) {
        if (quantite <= 0) {
            throw new QuantiteInvalideException(quantite);
        }
    }

    private double arrondir(double montant) {
        return Math.round(montant * 100.0) / 100.0;
    }

    public void setDao(IDao dao) {
        if (dao == null) {
            throw new IllegalArgumentException("Le DAO ne peut pas être nul");
        }
        this.dao = dao;
    }
}
