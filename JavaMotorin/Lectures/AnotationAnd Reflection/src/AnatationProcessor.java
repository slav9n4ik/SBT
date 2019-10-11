import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnatationProcessor {

    static Map<String, Object> servicesMap = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

//        innspectService(SimpleService.class);
//        innspectService(LazyService.class);
//        innspectService(String.class);
        loadServices("SimpleService");
        loadServices("LazyService");
        loadServices("java.lang.String");

        System.out.println(servicesMap.get("AnySimpleSer"));
        System.out.println(servicesMap.get("AnyLazySer"));


    }

    static void loadServices(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        if (clazz.isAnnotationPresent(Service.class)){
            Object serviceObj = clazz.getDeclaredConstructor().newInstance();
            servicesMap.put(clazz.getAnnotation(Service.class).name(),serviceObj);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    method.invoke(serviceObj);
                } else {
                    System.out.println(method.getName() + " hasn't got \"Init\" annotation");
                }
            }
        }
    }

    private static void innspectService(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)) {
            Service ann = service.getAnnotation(Service.class);
            System.out.println("Annotation name:" + ann.name());
            Method[] methods = service.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getAnnotation(Init.class) != null) {
                    System.out.println(method.getName() + " has \"Init\" annotation");
                } else {
                    System.out.println(method.getName() + " hasn't got \"Init\" annotation");
                }
            }
        } else {
            System.out.println(service.getName() + " Didn't find Annotation \"Service\" Anotation");
        }
    }
}
