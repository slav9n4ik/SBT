public interface Store<T> {
    void store(T t);

    T get();
}
