package org.atteo.moonshine.examples.repositories;

import javax.inject.Singleton;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.TopLevelService;
import org.atteo.moonshine.springdata.RepositoryFactoryProvider;
import org.atteo.moonshine.springdata.RepositoryProvider;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.google.inject.Module;
import com.google.inject.PrivateModule;

@XmlRootElement(name = "data-layer")
public class DataAccessLayerService extends TopLevelService {

	@Override
	public Module configure() {
		return new PrivateModule() {
			@Override
			protected void configure() {
				bind(RepositoryFactorySupport.class).toProvider(RepositoryFactoryProvider.class)
						.in(Singleton.class);
				bind(PostRepository.class).toProvider(
						new RepositoryProvider<PostRepository>(PostRepository.class))
						.in(Singleton.class);
				expose(PostRepository.class);
			}
		};
	}
}
