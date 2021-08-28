package ir.sharif.math.ap99_2.tinsta_shared.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SharedConfig extends Properties {
    private static final String DEFAULT_ADDRESS = "./resources/ir/sharif/math/ap99_2/tinsta/configurations/main.properties";
    private static final SharedConfig MAIN_CONFIG = new SharedConfig(DEFAULT_ADDRESS);

    public static SharedConfig getConfig(String name) {
        if ("mainConfig".equals(name))
            return MAIN_CONFIG;
        return MAIN_CONFIG.getProperty(SharedConfig.class, name);
    }

    private SharedConfig(String address) {
        super();
        try {
            Reader fileReader = new FileReader(address);
            this.load(fileReader);
        } catch (IOException e) {
            System.err.println(address);
            e.printStackTrace();
        }
    }

    public <E> E getProperty(Class<E> c, String propertyName) {
        return getObject(c, getProperty(propertyName));
    }

    public <E> List<E> getPropertyList(Class<E> c, String propertyName) {
        List<E> list = new ArrayList<>();
        String[] values = getProperty(propertyName).split(",");
        for (String value : values) {
            list.add(getObject(c, value));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public <E> E[] getPropertyArray(Class<E> c, String propertyName) {
        String[] values = getProperty(propertyName).split(",");
        E[] result = (E[]) Array.newInstance(c, values.length);
        for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
            result[i] = getObject(c, values[i]);
        }
        return result;
    }

    private <E> E getObject(Class<E> c, String value) {
        E e = null;
        try {
            Constructor<E> constructor = c.getDeclaredConstructor(String.class);
            e = constructor.newInstance(value);
        } catch (ReflectiveOperationException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
        return e;
    }

}
