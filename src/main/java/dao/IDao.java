package dao;

public interface IDao {
    double getValue();

    String getArticle();

    String getEcurie();

    int getStock();

    void retirerDuStock(int quantite);
}
