import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MultibindingsScanner;
import com.google.inject.multibindings.ProvidesIntoSet;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.Set;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(PersistenceService.class).toInstance(new InMemoryPersistence());
        bind(PersistenceService.class).to(InMemoryPersistence.class);
//        bind(InMemoryPersistence.class).asEagerSingleton();

        install(MultibindingsScanner.asModule());
    }

//    @ProvidesIntoSet
//    @HomepageResourceAnnotation
//    Class<?> providesJackson() {
//        // Features.
//        return JacksonFeature.class;
//    }

    @ProvidesIntoSet
    @HomepageResourceAnnotation
    Class<?> providesHomepageResource() {
        return HomepageResource.class;
    }

    @Provides
    @Singleton
    @Inject
    ResourceConfig providesJerseyResources(@HomepageResourceAnnotation Set<Class<?>> resources, Injector injector) {
        ResourceConfig resourceConfig = new ResourceConfig();
        resources.stream().map(injector::getInstance).forEach(resourceConfig::register);
        return resourceConfig;
    }

}
