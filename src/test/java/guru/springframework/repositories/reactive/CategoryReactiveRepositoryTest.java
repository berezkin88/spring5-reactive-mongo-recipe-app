package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll().block();
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setDescription("foo");

        repository.save(category).block();

        Category actual = repository.findByDescription("foo").block();

        assertThat(actual).isNotNull();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setDescription("foo");

        repository.save(category).block();

        Long actual = repository.count().block();

        assertThat(actual).isEqualTo(1L);
    }
}
