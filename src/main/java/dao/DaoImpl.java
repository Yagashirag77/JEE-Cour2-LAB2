package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("dao")
@Profile("prod")
public class DaoImpl implements IDao {
    private final String article = "Polo George Russell";
    private final String ecurie = "Mercedes";
    private int stock = 15;

    @Override
    public double getValue() {
        return 95.0;
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
