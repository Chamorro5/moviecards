package com.lauracercas.moviecards.endtoendtestplaywright;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Autor: Manuel Chamorro Serrano
 * Proyecto: TFM Comparativa Selenium y Playwright
 * Fecha: 10/08/2024
 */

public class IndexE2ETest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    public void testLinks() {
        page.navigate("http://localhost:8089");

        assertEquals("FichasPeliculasApp | Aplicación de gestión de fichas de peliculas", page.title());

        assertTrue(page.isVisible("a[href*='/registerActorMovie']"));
        assertTrue(page.isVisible("a[href*='/actors']"));
        assertTrue(page.isVisible("a[href*='/actors/new']"));
        assertTrue(page.isVisible("a[href*='/movies']"));
        assertTrue(page.isVisible("a[href*='/movies/new']"));
    }

    @Test
    public void testTitles() {
        page.navigate("http://localhost:8089");

        assertEquals("Inscripción Actor en Pelicula", page.locator(".registerActorMovie").textContent());
        assertEquals("Listado actores", page.locator(".actorList").textContent());
        assertEquals("Nuevo Actor", page.locator(".newActor").textContent());
        assertEquals("Listado peliculas", page.locator(".moviesList").textContent());
        assertEquals("Nueva pelicula", page.locator(".newMovie").textContent());
    }
}