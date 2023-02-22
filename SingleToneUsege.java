//Una clase la cual solo pueda crear objetos de ella misma en su interior
public class SingleToneUsege {
    private static SingleToneUsege instance;
    private SingleToneUsege(){}
    public static SingleToneUsege getInstance() {
        if (instance == null) {
            instance = new SingleToneUsege();
        }
        return instance;
    }
}
