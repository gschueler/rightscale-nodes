package com.simplifyops.rundeck.plugin.resources;


/**
 *
 */
public interface RightscaleCache extends RightscaleAPI {
    void load(Closure c);

    int size();
    boolean needsRefresh();
    void setRefreshInterval(int millis);
    boolean hasResource(String key, String href)
    void updateClouds(Map<String, RightscaleResource> clouds);
    void updateDatacenters(Map<String, RightscaleResource> datacenters);
    void updateDeployments(Map<String, RightscaleResource> deployments);
    void updateImages(Map<String, RightscaleResource> images);
    void updateInputs(Map<String, RightscaleResource> inputs);
    void updateInstances(Map<String, RightscaleResource> instances);
    void updateInstanceTypes(Map<String, RightscaleResource> instanceTypes);
    void updateServerArrayInstances(Map<String, RightscaleResource> instances);
    void updateServerArrays(Map<String, RightscaleResource> serverArrays);
    void updateServers(Map<String, RightscaleResource> servers);
    void updateServerTemplates(Map<String, RightscaleResource> serverTemplates);
    void updateSshKeys(Map<String, RightscaleResource> ssh_keys);
    void updateSubnets(Map<String, RightscaleResource> subnets);
    void updateTags(Map<String, RightscaleResource> tags);

}
