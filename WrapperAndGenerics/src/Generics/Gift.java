package Generics;

public class Gift<T> {
    T value;
    void setValue(T value)
    {
        this.value=value;
    }

    T getValue()
    {
        return value;
    }
}
