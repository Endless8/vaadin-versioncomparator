package com.example.application.versioncomparator.checker;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VersionChecker {

    public int checkVersions(String firstVersion, String secondVersion) {
        int result = 0;
        List<Integer> s1Numbers = Arrays.stream(firstVersion.split("\\."))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> s2Numbers = Arrays.stream(secondVersion.split("\\."))
                .map(Integer::parseInt).collect(Collectors.toList());

        int s1NumbersSize = s1Numbers.size();
        int s2NumbersSize = s2Numbers.size();
        int iterations = Math.max(s1NumbersSize, s2NumbersSize);

        for (int i = 0; i < iterations; i++) {
            int s1CurrentNumber = i < s1NumbersSize ? s1Numbers.get(i) : 0;
            int s2CurrentNumber = i < s2NumbersSize ? s2Numbers.get(i) : 0;

            if (s1CurrentNumber < s2CurrentNumber) {
                return 1;
            } else if (s1CurrentNumber > s2CurrentNumber) {
                return -1;
            }
        }

        return result;
    }
}
