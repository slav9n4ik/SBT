public class InMemoryStore<T> implements Store<T> {

    private T t;

    @Override
    public void store(T t) {
        this.t = t;
    }

    @Override
    public T get() {
        return t;
    }
}
