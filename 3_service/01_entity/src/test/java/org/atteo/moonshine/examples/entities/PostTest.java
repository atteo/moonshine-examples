package org.atteo.moonshine.examples.entities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.jta.Transactional;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

import com.google.inject.Inject;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <hibernate>"
		+ "        <initSchema>update</initSchema>"
		+ "    </hibernate>"
		+ "</config>")
public class PostTest extends MoonshineTest {
	@Inject
	private EntityManager entityManager;

	@Test
	@Transactional
	public void shouldReturnEmptySet() {
		// given

		// when
		Query query = entityManager.createQuery("select post from Post post");
		List<?> result = query.getResultList();

		// then
		assertThat(result).isEmpty();
	}
}
