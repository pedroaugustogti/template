package gr.javapapo.jpatests;

import gr.javapapo.SimpleUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  Arquillian test that is creating a test.war with a h2 datasource activated, eventually the ExampleDS DS bundled
 *  with Widlfy 8.2 Server.

 * Created by papo on 6/15/14.
 */
@Transactional(TransactionMode.ROLLBACK)
@RunWith(Arquillian.class)
public class UserEntityTest {


    @PersistenceContext(name = "test")
    EntityManager em;

    SimpleUser theSimpleUser = null;

    @Before
    public  void init(){
        theSimpleUser = new SimpleUser();
        theSimpleUser.setUsername("javapapo");
        em.persist(theSimpleUser);

    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"test.war").addAsWebInfResource("web.xml")
                .addClass(SimpleUser.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    @Test
    public void testGetUser() {
        SimpleUser testSimpleUser = em.find(SimpleUser.class, theSimpleUser.getId());
        Assert.assertEquals("javapapo", testSimpleUser.getUsername());
    }

}
