package com.lauracercas.moviecards.endtoendtestplaywright;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lauracercas.moviecards.util.Messages.NEW_MOVIE_TITLE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Autor: Manuel Chamorro Serrano
 * Proyecto: TFM Comparativa Selenium y Playwright
 * Fecha: 10/08/2024
 */

public class MovieE2ETest {

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
    public void testPageLoadAndForm() {
        page.navigate("http://localhost:8089/movies/new");
        assertEquals("FichasPeliculasApp | Aplicación de gestión de fichas de películas", page.title());

        assertTrue(page.isVisible("#title"));
        assertTrue(page.isVisible("#year"));
        assertTrue(page.isVisible("#duration"));
        assertTrue(page.isVisible("#country"));
        assertTrue(page.isVisible("#director"));
        assertTrue(page.isVisible("#genre"));
        assertTrue(page.isVisible("#sinopsis"));
    }

    @Test
    public void testNewMovieTitle() {
        page.navigate("http://localhost:8089/movies/new");
        String title = page.locator(".title").textContent();
        assertEquals(NEW_MOVIE_TITLE, title);
    }

    @Test
    public void testListMovies() {
        page.navigate("http://localhost:8089/movies");
        String title = page.locator(".card-header").textContent();
        assertEquals("Listado Peliculas", title);

        Locator table = page.locator(".table-hover");

        Locator thead = table.locator("thead");
        assertTrue(thead.isVisible());

        Locator headerRow = thead.locator("tr");
        assertEquals("Identificador", headerRow.locator("th").nth(0).textContent());
        assertEquals("Titulo", headerRow.locator("th").nth(1).textContent());
        assertEquals("Año", headerRow.locator("th").nth(2).textContent());
        assertEquals("Duración", headerRow.locator("th").nth(3).textContent());
        assertEquals("País", headerRow.locator("th").nth(4).textContent());
        assertEquals("Dirección", headerRow.locator("th").nth(5).textContent());
        assertEquals("Género", headerRow.locator("th").nth(6).textContent());
        assertEquals("Sinopsis", headerRow.locator("th").nth(7).textContent());
        assertEquals("Editar", headerRow.locator("th").nth(8).textContent());
    }
}