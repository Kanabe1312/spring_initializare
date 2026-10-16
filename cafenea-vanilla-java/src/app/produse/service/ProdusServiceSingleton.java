package app.produse.service;

public class ProdusServiceSingleton {

    private static class LazyHolder {
        private static final ProdusService INSTANCE = new ProdusServiceImpl();
    }

    private ProdusServiceSingleton() {

    }

    public static ProdusService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
