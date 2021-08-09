package com.example.application.views.compareversion;

import com.example.application.data.entity.VersionsToCompare;
import com.example.application.data.service.VersionComparatorService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.dependency.Uses;

@PageTitle("Compare Version")
@Route(value = "versioncomparator", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class CompareVersionView extends Div {

    private TextField firstVersion = new TextField("Prima versione da confrontare");
    private TextField secondVersion = new TextField("Seconda versione da confrontare");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<VersionsToCompare> binder = new Binder(VersionsToCompare.class);

    public CompareVersionView(VersionComparatorService comparatorService) {
        addClassName("compare-version-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        firstVersion.addValueChangeListener(e -> {
            if (!comparatorService.formatCheck(e.getValue())) {
                firstVersion.setInvalid(true);
                firstVersion.setErrorMessage("Formato errato. Inserire un formato valido (Es: 1.0.1 o 1.0)");
            }
        });
        secondVersion.addValueChangeListener(e -> {
            if (!comparatorService.formatCheck(e.getValue())) {
                secondVersion.setInvalid(true);
                secondVersion.setErrorMessage("Formato errato. Inserire un formato valido (Es: 1.0.1 o 1.0)");
            }
        });

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            compareVersions(comparatorService);
        });
        save.addClickShortcut(Key.ENTER);
    }

    private void compareVersions(VersionComparatorService comparatorService) {
        if (firstVersion.isEmpty() && secondVersion.isEmpty()) {
            Notification.show("Inserire le versioni da confrontare");
            firstVersion.setInvalid(true);
            secondVersion.setInvalid(true);
            firstVersion.setErrorMessage("Formato errato. Inserire un formato valido (Es: 1.0.1 o 1.0)");
            secondVersion.setErrorMessage("Formato errato. Inserire un formato valido (Es: 1.0.1 o 1.0)");
        } else if (!firstVersion.isInvalid() && !secondVersion.isInvalid()) {
            int result = comparatorService.compareVersions(binder.getBean());
            Notification.show("Il risultato della comparazione è: " + result);
            clearForm();
        } else {
            Notification.show("Il formato delle versioni inserite non è valido");
            clearForm();
        }
    }

    private void clearForm() {
        binder.setBean(new VersionsToCompare());
    }

    private Component createTitle() {
        return new H3("Inserire le versioni da confrontare");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(firstVersion, secondVersion);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
