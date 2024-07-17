package org.acme.javafx;

import com.jpro.boot.JProBoot;
import com.jpro.webapi.WebAPI;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.CDIProvider;
import javafx.application.Application;
import org.acme.javafx.conf.StartupScene;


@QuarkusMain
public class CDIApplication implements QuarkusApplication {


    public static void main(String[] args) {
        Quarkus.run(CDIApplication.class);
    }

    public static CDI<Object> cdi = null;

    public static BeanManager beanManager = null;

    @Override
    public int run(final String... args) {
        var isBrowser = System.getProperty("jprocpfile") != null || System.getProperty("jprocp") != null;
        System.out.println("isBrowser: " + isBrowser);
        if(!isBrowser) {
            Application.launch(FxApplication.class, args);
        } else {

            beanManager = CDI.current().getBeanManager();
            try {
                cdi = CDI.current();
                JProBoot.main();

                while(true) {
                    Thread.sleep(999999);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}