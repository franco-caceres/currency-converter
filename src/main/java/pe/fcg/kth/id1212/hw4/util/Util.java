package pe.fcg.kth.id1212.hw4.util;

import org.apache.commons.beanutils.BeanUtils;

public class Util {
    public static <T> T copyBeanProperties(T dest, Object orig) {
        try {
            if (orig != null) {
                BeanUtils.copyProperties(dest, orig);


            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dest;
    }
}
