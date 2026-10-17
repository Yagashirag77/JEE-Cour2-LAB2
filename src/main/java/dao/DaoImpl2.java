package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("dao2")
@Profile("dev")
public class DaoImpl2 implements IDao {
    private final String article = "Casquette Lando Norris";
    private final String ecurie = "McLaren";
    private int stock = 8;

    @Override
    public double getValue() {
        return 72.50;
    }

    @Override
    public String getArticle() {
        return article;
    }

    @Override
    public String getEcurie() {
        return ecurie;
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
