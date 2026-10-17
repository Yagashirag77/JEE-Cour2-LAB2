package metier;

import java.util.List;

public interface IMetier {
    double calcul();

    double commander(int quantite);

    String getDescriptionArticle();

    int getStockDisponible();

    List<String> getHistorique();
}
