package gr.javapapo;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import java.io.File;

/**
 * Created by papo on 5/31/14.
 */
@RunWith(Arquillian.class)
public class DemoArquillianTest {

    private static final Logger LOGGER =LoggerFactory.getLogger(DemoArquillianTest.class);

    @EJB UserServices dummyService;

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserServices.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSaysHello() {
        LOGGER.info(">>>>>>>>>>>>> This is a test");
        Assert.assertEquals("hello",dummyService.sayHello());
    }




}
