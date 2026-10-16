package app.comenzi.service;

public class ComandaServiceSingleton {

    private static class LazyHolder {
        private static final ComandaService INSTANCE = new ComandaServiceImpl();
    }

    private ComandaServiceSingleton() {

    }

    public static ComandaService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
