package metier;

import dao.IDao;
import exception.QuantiteInvalideException;
import exception.StockInsuffisantException;
import org.junit.Assert;
import org.junit.Test;

public class MetierImplTest {
    @Test
    public void testCalcul() {
        MetierImpl metier = creerMetier(10.0, 5);

        Assert.assertEquals(20.0, metier.calcul(), 0.001);
    }

    @Test
    public void testCommandeAvecRemiseEtMiseAJourDuStock() {
        MetierImpl metier = creerMetier(100.0, 5);

        double total = metier.commander(3);

        Assert.assertEquals(270.0, total, 0.001);
        Assert.assertEquals(2, metier.getStockDisponible());
        Assert.assertEquals(1, metier.getHistorique().size());
    }

    @Test(expected = QuantiteInvalideException.class)
    public void testCommandeAvecQuantiteNulle() {
        creerMetier(100.0, 5).commander(0);
    }

    @Test
    public void testCommandeRefuseeSiStockInsuffisant() {
        MetierImpl metier = creerMetier(100.0, 2);

        try {
            metier.commander(3);
            Assert.fail("Une exception était attendue");
        } catch (StockInsuffisantException exception) {
            Assert.assertTrue(exception.getMessage().contains("2 disponible"));
            Assert.assertEquals(2, metier.getStockDisponible());
            Assert.assertTrue(metier.getHistorique().isEmpty());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInjectionDaoNullRefusee() {
        new MetierImpl().setDao(null);
    }

    private MetierImpl creerMetier(double prix, int stock) {
        MetierImpl metier = new MetierImpl();
        metier.setDao(new DaoFictif(prix, stock));
        return metier;
    }

    private static class DaoFictif implements IDao {
        private final double prix;
        private int stock;

        private DaoFictif(double prix, int stock) {
            this.prix = prix;
            this.stock = stock;
        }

        @Override
        public double getValue() {
            return prix;
        }

        @Override
        public String getArticle() {
            return "Miniature F1";
        }

        @Override
        public String getEcurie() {
            return "Écurie de test";
        }

        @Override
        public int getStock() {
            return stock;
        }

        @Override
        public void retirerDuStock(int quantite) {
            stock -= quantite;
        }
    }
}
