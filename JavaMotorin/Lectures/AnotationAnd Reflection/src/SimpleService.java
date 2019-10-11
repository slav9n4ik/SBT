@Service(name = "AnySimpleSer")
public class SimpleService {

    @Init
    public void initService() {
        System.out.println("method initSimpleService() with anatation");
    }

    public void initServiceNoAnatation() {
        System.out.println("initSimpleService()");
    }
}
