package cn.com.tcsl.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * <P>
 * Description:反射练习
 * </p>
 * @author libing
 * @version 1.0
 * @Date 2016年5月20日
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class ReflectTest {

    public static Car initByDefaultConst() throws Throwable {
        // 通过类装载器获取 Car 类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("cn.com.tcsl.reflectdemo.Car");

        // 获取类的默认构造器对象并通过它实例化
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        Car car = (Car) cons.newInstance();

        // 通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗CA72");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);

        return car;
    }

    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }

}
