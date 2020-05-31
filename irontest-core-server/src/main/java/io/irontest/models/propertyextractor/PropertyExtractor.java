package io.irontest.models.propertyextractor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import io.irontest.models.Properties;
import io.irontest.resources.ResourceJsonViews;

public class PropertyExtractor {
    public static final String TYPE_JSONPATH = "JSONPath";

    private long id;
    @JsonView(ResourceJsonViews.TestcaseExport.class)
    private String propertyName;
    @JsonView(ResourceJsonViews.TestcaseExport.class)
    private String type;
    @JsonView(ResourceJsonViews.TestcaseExport.class)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type", visible = true, defaultImpl = Properties.class)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = JSONPathPropertyExtractorProperties.class, name = PropertyExtractor.TYPE_JSONPATH)})
    private Properties otherProperties = new Properties();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getOtherProperties() {
        return otherProperties;
    }

    public void setOtherProperties(Properties otherProperties) {
        this.otherProperties = otherProperties;
    }
}
