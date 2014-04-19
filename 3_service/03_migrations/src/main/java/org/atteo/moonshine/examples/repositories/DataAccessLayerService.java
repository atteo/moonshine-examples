package org.atteo.moonshine.examples.repositories;

import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.TopLevelService;
import org.atteo.moonshine.database.DatabaseMigration;
import org.atteo.moonshine.database.DatabaseService;
import org.atteo.moonshine.jpa.JpaService;
import org.atteo.moonshine.liquibase.LiquibaseFacade;
import org.atteo.moonshine.services.ImportService;
import org.atteo.moonshine.springdata.RepositoryFactoryProvider;
import org.atteo.moonshine.springdata.RepositoryProvider;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.google.inject.Module;
import com.google.inject.PrivateModule;

@XmlRootElement(name = "data-access-layer")
public class DataAccessLayerService extends TopLevelService {
	@ImportService
	@XmlIDREF
	@XmlAttribute
	private JpaService jpa;

	@ImportService
	private DatabaseService database;

	@Override
	public Module configure() {
		database = jpa.getDatabaseService();

		database.registerMigration(new DatabaseMigration() {
			public void execute(DataSource dataSource) {
				new LiquibaseFacade(dataSource).migrate("/migration01.xml");
			}
		});

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
