package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dao", "metier"})
public class Presentation2 {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {
            context.getEnvironment().setActiveProfiles("dev");
            context.register(Presentation2.class);
            context.refresh();

            IMetier metier = context.getBean(IMetier.class);
            System.out.println("=== Boutique F1 ===");
            System.out.println("Article : " + metier.getDescriptionArticle());
            System.out.println("Stock avant commande : " + metier.getStockDisponible());
            System.out.println("Prix de deux articles : " + metier.calcul() + " EUR");

            double total = metier.commander(3);
            System.out.println("Total pour trois articles avec remise : " + total + " EUR");
            System.out.println("Stock après commande : " + metier.getStockDisponible());
            System.out.println("Historique : " + metier.getHistorique());
        }
    }
}
