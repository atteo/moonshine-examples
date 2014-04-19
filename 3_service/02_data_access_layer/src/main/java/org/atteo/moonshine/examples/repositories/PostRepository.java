package org.atteo.moonshine.examples.repositories;

import org.atteo.moonshine.examples.entities.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
	Iterable<Post> findByTitle(String title);
}
