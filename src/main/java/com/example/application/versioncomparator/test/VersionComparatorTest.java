package com.example.application.versioncomparator.test;

import com.example.application.data.entity.VersionsToCompare;
import com.example.application.data.service.VersionComparatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VersionComparatorTest {

    @Autowired
    private VersionComparatorService versionComparatorService;

    /**
     * Questo test verifica che la prima versione inserita sia precedente
     * rispetto alla seconda versione inserita.
     */
    @Test
    void previousVersion() {
        VersionsToCompare versionsToCompare = new VersionsToCompare();
        versionsToCompare.setFirstVersion("1.0.0");
        versionsToCompare.setSecondVersion("1.0.1");
        int result = versionComparatorService.compareVersions(versionsToCompare);
        assertEquals(1, result);
    }

    /**
     * Questo test verifica che la prima versione inserita sia successiva
     * rispetto alla seconda versione inserita.
     */
    @Test
    void nextVersion() {
        VersionsToCompare versionsToCompare = new VersionsToCompare();
        versionsToCompare.setFirstVersion("1.1.0");
        versionsToCompare.setSecondVersion("1.0.1");
        int result = versionComparatorService.compareVersions(versionsToCompare);
        assertEquals(-1, result);
    }

    /**
     * Questo test verifica che la prima versione inserita sia la stessa
     * rispetto alla seconda versione inserita.
     */
    @Test
    void sameVersion() {
        VersionsToCompare versionsToCompare = new VersionsToCompare();
        versionsToCompare.setFirstVersion("1.0.0");
        versionsToCompare.setSecondVersion("1.0.0");
        int result = versionComparatorService.compareVersions(versionsToCompare);
        assertEquals(0, result);
    }

    /**
     * Questo test verifica che il formato dell'input inserito sia valido.
     */
    @Test
    void validFormatInput() {
        String firstVersion = "1.0";
        boolean isFirstVersionValid = versionComparatorService.formatCheck(firstVersion);
        assertTrue(isFirstVersionValid);
    }

    /**
     * Questo test verifica che la prima versione inserita sia un input vuoto.
     */
    @Test
    void blankInput() {
        String firstVersion = "";
        boolean isFirstVersionValid = versionComparatorService.formatCheck(firstVersion);
        assertFalse(isFirstVersionValid, "L'input non può essere vuoto");
    }

    /**
     * Questo test verifica che la prima versione inserita sia un input dal
     * formato non valido.
     */
    @Test
    void wrongFormatInput() {
        String firstVersion = "abc123";
        boolean isFirstVersionValid = versionComparatorService.formatCheck(firstVersion);
        assertFalse(isFirstVersionValid, "Il formato dell'input inserito non è valido");
    }

}
