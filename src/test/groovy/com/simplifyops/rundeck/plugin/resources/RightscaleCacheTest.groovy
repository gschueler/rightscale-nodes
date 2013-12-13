package com.simplifyops.rundeck.plugin.resources

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class RightscaleCacheTest {

    @Test
    public void constructor() {
        def RightscaleCache cache = new RightscaleCache()
        Assert.assertTrue(cache.getClouds().size()==0)
        Assert.assertTrue(cache.getDatacenters().size()==0)
        Assert.assertTrue(cache.getDeployments().size()==0)
        Assert.assertTrue(cache.getImages().size()==0)
        Assert.assertTrue(cache.getInputs().size()==0)
        Assert.assertTrue(cache.getInstances().size()==0)
        Assert.assertTrue(cache.getInstanceTypes().size()==0)
        Assert.assertTrue(cache.getServerArrays().size()==0)
        Assert.assertTrue(cache.getServerTemplates().size()==0)
        Assert.assertTrue(cache.getServers().size()==0)
        Assert.assertTrue(cache.getTags().size()==0)
    }

    @Test
    public void getCollection() {
        def RightscaleCache cache = new RightscaleCache()
        Assert.assertNotNull(cache.getResources('clouds'))
    }

    @Test
    public void load() {
        def RightscaleCache cache = new RightscaleCache()
        cache.load {
            def c = new CloudResource()
            c.attributes['name'] = "cloud1"
            cache.updateClouds(["/api/clouds/1": c])
        }
        Assert.assertEquals(1,cache.getClouds().size())
        Assert.assertEquals("cloud1",cache.getClouds().get("/api/clouds/1")['attributes']['name'])
    }

    @Test
    public void updateClouds() {
        def RightscaleCache cache = new RightscaleCache()
         cache.updateClouds(
                CloudResource.burst(new XmlParser().parseText(XmlData.CLOUDS),
                        'cloud', CloudResource.&create))
        def resources = cache.getClouds()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateDatacenters() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateDatacenters(
                DatacenterResource.burst(new XmlParser().parseText(XmlData.DATACENTERS),
                        'datacenter', DatacenterResource.&create))
        def resources = cache.getDatacenters()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateDeployments() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateDeployments(
                DeploymentResource.burst(new XmlParser().parseText(XmlData.DEPLOYMENTS),
                        'deployment', DatacenterResource.&create))
        def resources = cache.getDeployments()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateImages() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateImages(
                ImageResource.burst(new XmlParser().parseText(XmlData.IMAGES),
                        'image', ImageResource.&create))
        def resources = cache.getImages()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateInputs() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateInputs(
                InputsResource.burst(new XmlParser().parseText(XmlData.INPUTS),
                        'input', InputsResource.&create))
        def resources = cache.getInputs()
        Assert.assertEquals(1,resources.size())
    }

    @Test
    public void updateInstances() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateInstances(
                InstanceResource.burst(new XmlParser().parseText(XmlData.INSTANCES),
                        'instance', InstanceResource.&create))
        def resources = cache.getInstances()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateInstanceTypes() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateInstanceTypes(
                InstanceTypeResource.burst(new XmlParser().parseText(XmlData.INSTANCE_TYPES),
                        'instance_type', InstanceTypeResource.&create))
        def resources = cache.getInstanceTypes()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateServerArrays() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateServerArrays(
                ServerArrayResource.burst(new XmlParser().parseText(XmlData.SERVER_ARRAYS),
                        'server_array', ServerArrayResource.&create))
        def resources = cache.getServerArrays()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateServerTemplates() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateServerTemplates(
                ServerTemplateResource.burst(new XmlParser().parseText(XmlData.SERVER_TEMPLATES),
                        'server_template', ServerTemplateResource.&create))
        def resources = cache.getServerTemplates()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void updateServers() {
        def RightscaleCache cache = new RightscaleCache()
        cache.updateServers(
                ServerResource.burst(new XmlParser().parseText(XmlData.SERVERS),
                        'server', ServerResource.&create))
        def resources = cache.getServers()
        Assert.assertEquals(2,resources.size())
    }

    @Test
    public void cacheNeedsRefresh() {
        def RightscaleCache cache = new RightscaleCache()
        Assert.assertFalse(cache.needsRefresh())
    }
}