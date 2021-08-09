package com.example.application.data.service;

import com.example.application.data.entity.VersionsToCompare;

import com.example.application.versioncomparator.VersionComparator;
import com.example.application.versioncomparator.checker.VersionChecker;
import org.springframework.stereotype.Service;

@Service
public class VersionComparatorService {

    public boolean formatCheck(String version) {
        return version.matches("(\\d+\\.\\d+|\\d+\\.\\d+\\.\\d+)");
    }

    public int compareVersions(VersionsToCompare versionsToCompare) {
        VersionComparator comparator = new VersionComparator(new VersionChecker());
        comparator.setFirstVersion(versionsToCompare.getFirstVersion());
        comparator.setSecondVersion(versionsToCompare.getSecondVersion());
        comparator.startComparation();

        return comparator.getResult();
    }

}
