import java.lang.annotation.*;

@Documented                           //Попадет в JavaDoc
@Inherited                            //Наследуется потомками класса
@Target(ElementType.TYPE)             //Область примения над классами и над интерфейсами
@Retention(RetentionPolicy.RUNTIME)   //Будет жить в Runtime
public @interface Service {
    String name();
    boolean lazyLoad() default false;
}
