package com.lauracercas.moviecards.endtoendtestplaywright;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lauracercas.moviecards.util.Messages.NEW_ACTOR_TITLE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Autor: Manuel Chamorro Serrano
 * Proyecto: TFM Comparativa Selenium y Playwright
 * Fecha: 10/08/2024
 */

public class ActorE2ETest {

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
    public void testPageLoad() {
        page.navigate("http://localhost:8089/actors/new");
        assertEquals("FichasPeliculasApp | Aplicación de gestión de fichas de películas", page.title());

        assertTrue(page.isVisible("#name"));
        assertTrue(page.isVisible("#birthDate"));
        assertTrue(page.isVisible("#country"));
    }

    @Test
    public void testNewActorTitle() {
        page.navigate("http://localhost:8089/actors/new");
        String title = page.locator(".title").textContent();
        assertEquals(NEW_ACTOR_TITLE, title);
    }

    @Test
    public void testListActors() {
        page.navigate("http://localhost:8089/actors");
        String title = page.locator(".card-header").textContent();
        assertEquals("Listado Actores", title);

        Locator table = page.locator(".table-hover");

        Locator thead = table.locator("thead");
        assertTrue(thead.isVisible());

        Locator headerRow = thead.locator("tr");
        assertEquals("Identificador", headerRow.locator("th").nth(0).textContent());
        assertEquals("Nombre", headerRow.locator("th").nth(1).textContent());
        assertEquals("Fecha Nacimiento", headerRow.locator("th").nth(2).textContent());
        assertEquals("Pais", headerRow.locator("th").nth(3).textContent());
        assertEquals("Editar", headerRow.locator("th").nth(4).textContent());
    }
}
