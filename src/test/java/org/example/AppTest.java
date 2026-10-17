package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class AppTest {
    @Test
    public void testDemonstrationF1() {
        PrintStream sortieInitiale = System.out;
        ByteArrayOutputStream sortieCapturee = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(sortieCapturee, true, StandardCharsets.UTF_8));
            App.main(new String[0]);
        } finally {
            System.setOut(sortieInitiale);
        }

        String affichage = sortieCapturee.toString(StandardCharsets.UTF_8);
        Assert.assertTrue(affichage.contains("Lando Norris"));
        Assert.assertTrue(affichage.contains("McLaren"));
        Assert.assertTrue(affichage.contains("195.75 EUR"));
    }
}
