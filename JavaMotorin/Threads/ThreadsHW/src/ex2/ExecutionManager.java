package ex2;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable ... tasks);
}
