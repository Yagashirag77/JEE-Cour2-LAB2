package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresentationXML {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {
            IMetier metier = context.getBean("metier", IMetier.class);
            System.out.println("=== Boutique F1 - configuration XML ===");
            System.out.println("Article : " + metier.getDescriptionArticle());
            System.out.println("Montant calculé : " + metier.calcul() + " EUR");
        }
    }
}
