package com.tygr.inspectors.modules.XAST.VSG;

public enum ReportTypeVSG {
    CSV("--csv-export", ".csv"), TXT("--results", ".txt"), XML("--export", ".xml");

    private String key;
    private String fileExtension;

    ReportTypeVSG(String key, String fileExtension) {
        this.key = key;
        this.fileExtension = fileExtension;
    }

    public String getKey() {
        return key;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }
}
