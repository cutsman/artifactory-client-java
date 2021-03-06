package org.jfrog.artifactory.client

import org.hamcrest.CoreMatchers
import org.jfrog.artifactory.client.model.*
import org.jfrog.artifactory.client.model.repository.settings.impl.DebianRepositorySettingsImpl
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * test that client correctly sends and receives repository configuration with `debian` package type
 * 
 * @author Ivan Vasylivskyi (ivanvas@jfrog.com)
 */
public class DebianPackageTypeRepositoryTests extends BaseRepositoryTests {

    @BeforeMethod
    protected void setUp() {
        settings = new DebianRepositorySettingsImpl()

        settings.with {
            // local
            debianTrivialLayout = rnd.nextBoolean()

            //remote
            listRemoteFolderItems = rnd.nextBoolean()
        }

        // only local and remote repository supported
        prepareVirtualRepo = false

        super.setUp()
    }

    @Test(groups = "debianPackageTypeRepo")
    public void testDebianLocalRepo() {
        artifactory.repositories().create(0, localRepo)

        def resp = artifactory.repository(localRepo.getKey()).get()
        resp.getRepositorySettings().with {
            assertThat(packageType, CoreMatchers.is(settings.getPackageType()))

            // local
            assertThat(debianTrivialLayout, CoreMatchers.is(settings.getDebianTrivialLayout()))

            // remote
            assertThat(listRemoteFolderItems, CoreMatchers.is(CoreMatchers.nullValue()))
        }
    }

    @Test(groups = "debianPackageTypeRepo")
    public void testDebianRemoteRepo() {
        artifactory.repositories().create(0, remoteRepo)

        def resp = artifactory.repository(remoteRepo.getKey()).get()
        resp.getRepositorySettings().with {
            assertThat(packageType, CoreMatchers.is(settings.getPackageType()))

            // local
            assertThat(debianTrivialLayout, CoreMatchers.is(Boolean.FALSE)) // always in resp payload

            // remote
            assertThat(listRemoteFolderItems, CoreMatchers.is(settings.getListRemoteFolderItems()))
        }
    }

}
