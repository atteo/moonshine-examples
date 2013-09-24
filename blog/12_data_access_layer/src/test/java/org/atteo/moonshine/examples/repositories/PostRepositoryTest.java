package org.atteo.moonshine.examples.repositories;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.examples.entities.Post;
import org.atteo.moonshine.jta.Transactional;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(fromString = ""
		+ "<config>"
		+ "    <atomikos/>"
		+ "    <h2/>"
		+ "    <transactional/>"
		+ "    <hibernate>"
		+ "        <initSchema>update</initSchema>"
		+ "    </hibernate>"
		+ "    <data-layer/>"
		+ "</config>")
public class PostRepositoryTest extends MoonshineTest {
	@Inject
	private PostRepository blogEntryRepository;

	@Test
	@Transactional
	public void shouldReturnEmptyListOfRepositories() {
		// given

		// when
		Iterable<Post> blogEntries = blogEntryRepository.findAll();

		// then
		assertThat(blogEntries).isEmpty();
	}
}
