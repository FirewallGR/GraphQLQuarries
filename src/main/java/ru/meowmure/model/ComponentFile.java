package ru.meowmure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"text", "user"})
public class ComponentFile {
    @JsonProperty
    private String name;
    // private String date;

    @JsonProperty
    private String Architecture;

    @JsonProperty
    private String OS;

    @JsonProperty
    private String remoteS3Key;

    public String getRemoteS3Path() {
        return remoteS3Path;
    }

    @JsonProperty
    private String remoteS3Path;

    @JsonProperty
    private String size;

    @JsonProperty
    private String fileId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArchitecture() {
        return Architecture;
    }

    public void setArchitecture(String architecture) {
        this.Architecture = architecture;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getRemoteS3Key() {
        return remoteS3Key;
    }

    public void setRemoteS3Key(String remoteS3Key) {
        this.remoteS3Key = remoteS3Key;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
