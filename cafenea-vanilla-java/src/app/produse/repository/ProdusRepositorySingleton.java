package app.produse.repository;

public class ProdusRepositorySingleton {

    private static class LazyHolder {
        private static final ProdusRepository INSTANCE = new ProdusRepositoryImpl();
    }

    private ProdusRepositorySingleton() {

    }

    public static ProdusRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
