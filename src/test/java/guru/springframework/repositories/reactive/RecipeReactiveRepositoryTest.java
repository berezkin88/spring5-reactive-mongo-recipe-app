package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Recipe recipe = new Recipe();

        repository.save(recipe).block();

        Long actual = repository.count().block();

        assertThat(actual).isEqualTo(1L);
    }
}
