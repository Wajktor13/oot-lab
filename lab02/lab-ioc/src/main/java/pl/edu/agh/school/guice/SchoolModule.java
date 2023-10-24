package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;

public class SchoolModule extends AbstractModule {

    @Provides
    public IPersistenceManager providePersistenceManager(SerializablePersistenceManager persistenceManager) {
        return persistenceManager;
    }

    @ProvidesIntoSet
    public IMessageSerializer provideFileMessageSerializer(FileMessageSerializer fileMessageSerializer) {
        return fileMessageSerializer;
    }

    @ProvidesIntoSet
    public IMessageSerializer provideConsoleMessageSerializer(ConsoleMessageSerializer consoleMessageSerializer) {
        return consoleMessageSerializer;
    }

    @Provides
    @Named("classesStorage")
    public String provideClassesStorageName() {
        return "guice-classes.dat";
    }

    @Provides
    @Named("teachersStorage")
    public String provideTeachersStorageName() {
        return "guice-teachers.dat";
    }

    @Provides
    @Named("logFileName")
    public String provideLogFileName() {
        return "persistence.log";
    }
}
