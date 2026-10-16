package app.comenzi.repository;

public class ComandaRepositorySingleton {

    private static class LazyHolder {
        private static final ComandaRepository INSTANCE = new ComandaRepositoryImpl();
    }

    private ComandaRepositorySingleton() {

    }

    public static ComandaRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
