package problem1;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object obj) throws IllegalAccessException {
        for(Field field:obj.getClass().getDeclaredFields())
        {
            if(field.isAnnotationPresent(MinAge.class))
            {
                field.setAccessible(true);
                int value=(int)field.get(obj);
                int min=field.getAnnotation(MinAge.class).value();

                if(value<min)
                {
                    throw  new RuntimeException(field.getName()+" must be >= "+min);
                }
            }
        }
    }
}
