package com.example.application.versioncomparator;

import com.example.application.versioncomparator.checker.VersionChecker;

public class VersionComparator {

    private VersionChecker versionChecker;
    private String firstVersion;
    private String secondVersion;
    private int result;

    public VersionComparator(VersionChecker versionChecker) {
        this.versionChecker = versionChecker;
        result = 0;
    }

    public void startComparation() {
        result = versionChecker.checkVersions(firstVersion, secondVersion);
    }

    public int getResult() {
        return result;
    }

    public String getFirstVersion() {
        return firstVersion;
    }

    public void setFirstVersion(String firstVersion) {
        this.firstVersion = firstVersion;
    }

    public String getSecondVersion() {
        return secondVersion;
    }

    public void setSecondVersion(String secondVersion) {
        this.secondVersion = secondVersion;
    }
}
