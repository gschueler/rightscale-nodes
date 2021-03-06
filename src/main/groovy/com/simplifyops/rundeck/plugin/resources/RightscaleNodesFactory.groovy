package com.simplifyops.rundeck.plugin.resources

import com.codahale.metrics.ConsoleReporter
import com.codahale.metrics.MetricRegistry;
import com.dtolabs.rundeck.core.common.Framework;
import com.dtolabs.rundeck.core.plugins.Plugin;
import com.dtolabs.rundeck.core.plugins.configuration.*;
import com.dtolabs.rundeck.core.resources.ResourceModelSource;
import com.dtolabs.rundeck.core.resources.ResourceModelSourceFactory;
import com.dtolabs.rundeck.plugins.util.DescriptionBuilder;

import java.util.*;


@Plugin(name = "rightscale-nodes", service = "ResourceModelSource")
public class RightscaleNodesFactory implements ResourceModelSourceFactory, Describable {
    public static final String PROVIDER_NAME = "rightscale-nodes";
    public static MetricRegistry metrics = new MetricRegistry();

    private Framework framework;

    public static final String ENDPOINT = "endpoint";

    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ACCOUNT = "account";
    public static final String REFRESH_INTERVAL = "refreshInterval";
    public static final String USERNAME = "username";
    public static final String INPUT_PATT = "inputs";
    public static final String TAG_PATT = "tags";
    public static final String TAG_ATTR = "generateTagAttributes";
    public static final String HTTP_TIMEOUT = "httpTimeout";
    public static final String HTTP_LOG = "httpLog";
    public static final String METRICS_INTVERVAL = "metricsInterval";

    /**
     * Default constructor.
     * @param framework
     * @return new instance.
     */
    public ResourceModelSourceFactory(final Framework framework) {
        this.framework = framework;
    }

    public ResourceModelSource createResourceModelSource(final Properties properties) throws ConfigurationException {
        final RightscaleNodes modelSource = new RightscaleNodes(properties);
        modelSource.validate();
        return modelSource;
    }

    /**
     * Plugin configuration properties.
     */
    static Description DESC = DescriptionBuilder.builder()
            .name(PROVIDER_NAME)
            .title("RightScale Nodes")
            .description("Generates nodes from instances in your RightScale account.")
            .property(PropertyUtil.string(EMAIL, "Email", "Email address for RightScale User.", true, null))
            .property(PropertyUtil.string(PASSWORD, "Password", "RightScale Password.", true, null))
            .property(PropertyUtil.string(ACCOUNT, "Account", "RightScale Account", true, null))
            .property(PropertyUtil.string(ENDPOINT, "Endpoint", "RightScale  API Endpoint URL. Must support API v1.5 (default: https://us-3.rightscale.com).", false, "https://us-3.rightscale.com"))
            .property(PropertyUtil.integer(REFRESH_INTERVAL, "Refresh Interval",
            "Minimum time in seconds between API requests to RightScale (default: 60).", false, "60"))
            .property(PropertyUtil.string(USERNAME, "Default Node Username", "Username for remote command execution.", true, null))
            .property(PropertyUtil.string(INPUT_PATT, "Input pattern",
            "Regular expression used to match resource inputs (default: .*).", false, ".*"))
            .property(PropertyUtil.string(TAG_PATT, "Tag pattern",
            "Regular expression used to match resource tags (default: .*).", false, ".*"))
            .property(PropertyUtil.bool(TAG_ATTR, "Generate attributes from tags",
            "For tags that contain an equal sign (foo=bar), generate a like node attribute.",
            false, "false"))
            .property(PropertyUtil.integer(HTTP_TIMEOUT, "HTTP timeout",
            "Timeout for HTTP connection and read requests (time in milliseconds).",
            false, "30000"))
            .property(PropertyUtil.bool(HTTP_LOG, "HTTP request logging",
            "Print debug HTTP request info and the content of the response to service.log ",
            false, "false"))
            .property(PropertyUtil.integer(METRICS_INTVERVAL, "Metrics logging interval",
            "Log the codahale metrics to the service.log file at the specified minute interval (no logging if unset).",
            false, "15"))
            .build();

    public Description getDescription() {
        return DESC;
    }
}
