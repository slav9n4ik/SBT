@Service(name = "AnyLazySer", lazyLoad = true)
public class LazyService {

    @Init
    public void initLazyService() throws Exception{
        System.out.println("method initLazyService()");
    }
}
