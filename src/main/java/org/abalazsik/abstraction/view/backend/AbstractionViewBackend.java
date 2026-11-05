package org.abalazsik.abstraction.view.backend;

import org.abalazsik.abstraction.view.backend.controller.AbstractionDiagramController;
import org.abalazsik.abstraction.view.backend.controller.WebFluxConfig;
import org.abalazsik.abstraction.view.backend.data.AbstractionDiagramRepositoryImpl;
import org.abalazsik.abstraction.view.backend.data.DBConfig;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramRepository;
import org.abalazsik.abstraction.view.backend.domain.AbstractionDiagramService;
import org.abalazsik.abstraction.view.backend.inmemory.AbstractionDiagramInMemoryRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author ador
 */
@SpringBootApplication(scanBasePackageClasses = {DBConfig.class, WebFluxConfig.class, AbstractionDiagramController.class})
public class AbstractionViewBackend {

    public static void main(String[] args) {
        SpringApplication.run(AbstractionViewBackend.class, args);
    }

    @Bean
    public AbstractionDiagramService abstractionDiagramService(AbstractionDiagramRepositoryImpl repositoryImpl) {
        return new AbstractionDiagramService(repositoryImpl);
    }
    
    /*
    @Bean
    public AbstractionDiagramRepository repositoryImpl() {
        return new AbstractionDiagramInMemoryRepositoryImpl();
    }
    */
}
