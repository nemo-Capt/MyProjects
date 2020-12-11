public class Singleton {
    private static Singleton instance;
    String str;

    private Singleton(String str) {
        this.str = str;
    }

    static Singleton getInstance(String str) {
        if (instance == null) {
            instance = new Singleton(str);

        }
        return instance;
    }
}
