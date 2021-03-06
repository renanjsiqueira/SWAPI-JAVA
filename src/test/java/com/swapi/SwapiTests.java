package com.swapi;

import com.swapi.models.Film;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author Zygimantus
 */
public class SwapiTests {

    private static final String FILMS = "Title:A New Hope\n"
            + "Title:Attack of the Clones\n"
            + "Title:The Phantom Menace\n"
            + "Title:Revenge of the Sith\n"
            + "Title:Return of the Jedi\n"
            + "Title:The Empire Strikes Back\n"
            + "Title:The Force Awakens";

    @Test
    public void filmsTest() throws IOException {

        StarWarsApi.init();
        StarWars api = StarWarsApi.getApi();

        Call<SWModelList<Film>> films = api.getAllFilms(1);

        Response<SWModelList<Film>> response = films.execute();

        StringJoiner joiner = new StringJoiner("\n", "", "");

        SWModelList<Film> body = response.body();

        if (body != null) {
            ArrayList<Film> results = body.getResults();

            for (Film f : results) {
                joiner.add("Title:" + f.getTitle());
            }
        }

        Assert.assertEquals(FILMS, joiner.toString());
    }
}
