import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {
    public static void assign(Object to, Object from) {
        JournalProcessor journalProcessor = (JournalProcessor) to;
        Class fromClass = from.getClass();
        Class toClass = to.getClass();
        List<Method> getters = scanGetters(fromClass);

        for (Field field : toClass.getDeclaredFields()) {
            String fieldName = field.getName();
            fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1).toLowerCase();
            String nameOfExpectGetter = "get".concat(fieldName);
            for(Method getter : getters) {
                if(getter.getName().equals(nameOfExpectGetter)) {
                    try {
                        Method setter = toClass.getDeclaredMethod("set".concat(fieldName), getter.getReturnType());
                        setter.invoke(to,getter.invoke(from));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        System.out.println("Didn't find method with name: set" + fieldName);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("Didn't invoke getter: " + getter.getName());
                    }
                }
            }
        }
    }

    private static List<Method> scanGetters(Class clazz) {
        List<Method> getters = new ArrayList<>();
        Method[] methods = clazz.getMethods();

        for(Method method : methods) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }
        return getters;
    }

    private static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        return !void.class.equals(method.getReturnType());
    }
}
