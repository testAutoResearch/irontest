package io.irontest.models.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.irontest.models.Properties;
import io.irontest.resources.ResourceJsonViews;

public class IIBEndpointProperties extends Properties {
    @JsonView({ResourceJsonViews.TeststepEdit.class, ResourceJsonViews.TestcaseExport.class})
    private String host;
    @JsonView({ResourceJsonViews.TeststepEdit.class, ResourceJsonViews.TestcaseExport.class})
    private Integer port;
    @JsonView({ResourceJsonViews.TeststepEdit.class, ResourceJsonViews.TestcaseExport.class})
    private boolean useSSL = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    /**
     * Used to unify integration node address display on test step action tab and test case run report.
     * @return
     */
    @JsonProperty
    @JsonView(ResourceJsonViews.TeststepEdit.class)
    public String getIntegrationNodeAddress() {
        return (isUseSSL() ? "https" : "http") + "://" + host + ":" + port;
    }

    @JsonIgnore
    public void setIntegrationNodeAddress(String integrationNodeAddress) {
        //  do nothing
    }
}