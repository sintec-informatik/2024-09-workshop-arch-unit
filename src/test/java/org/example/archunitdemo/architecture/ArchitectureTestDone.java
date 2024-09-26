package org.example.archunitdemo.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.example.archunitdemo.persistence.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AnalyzeClasses(
        packages = "org.example.archunitdemo",
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class})
class ArchitectureTestDone {

    //1. Repositorys sollten mit @Repository annotiert sein und im Namen ..Repository.. enthalten
    @ArchTest
    static ArchRule repositorys_should_be_annoted_and_Named = ArchRuleDefinition.classes()
            .that().resideInAPackage("..repository..").should()
            .beAnnotatedWith(Repository.class)
            .andShould().haveSimpleNameContaining("Repository");


    //2. Keine Klasse in package "persistence" sollte von package "service" abhängen
    @ArchTest
    static ArchRule persistence_should_not_depend_on_service =
            ArchRuleDefinition.noClasses()
                    .that().resideInAPackage("..persistence..")
                    .should().dependOnClassesThat().resideInAnyPackage("..service..");

    //3. Klassen in "service" sollten nur für Klassen in "controller" oder anderen in "service" zugänglich sein
    @ArchTest
    static final ArchRule services_should_only_be_accessed_by_controllers_or_other_services =
            ArchRuleDefinition.classes().that().resideInAPackage("..service..")
                    .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    //4. Keine Klasse in package "controller" sollte Abhängigkeiten auf Klassen im "package" persistence haben
    @ArchTest
    static ArchRule persistence_should_not_depend_on_controller =
            FreezingArchRule.freeze(
                    ArchRuleDefinition.noClasses()
                    .that().resideInAPackage("..controller..")
                    .should().dependOnClassesThat().resideInAnyPackage("..persistence..")
                    .because("controllers must use business services instead of the persistence layer")
        );

    //5. Services sollten mit @Service annotiert sein und im Namen ..Service.. enthalten
    @ArchTest
    static ArchRule services_should_be_annotated_with_Service = ArchRuleDefinition.classes()
            .that().resideInAPackage("..service..").should()
            .beAnnotatedWith(Service.class)
            .andShould().haveSimpleNameContaining("Service");

    //6. Controller sollten mit @RestController annotiert sein und „Controller“ im Namen enthalten
    @ArchTest
    static ArchRule controllers_should_be_annotated_with_RestController = ArchRuleDefinition.classes()
            .that().resideInAPackage("..controller..").should()
            .beAnnotatedWith(RestController.class)
            .andShould().haveSimpleNameContaining("Controller");

    //7. Klassen in package model sollten mit @Entity annotiert sein​
    @ArchTest
    static ArchRule models_Should_Be_annoted_with_Entity = ArchRuleDefinition.classes()
                                                                             .that().resideInAPackage("..model..").should()
                                                                             .beAnnotatedWith(Entity.class);

    //8. für jede Entity sollte die @ID vom Typ UUID sein
    @ArchTest
    static ArchRule id_Should_Be_UUID = ArchRuleDefinition.fields()
                                                          .that().areDeclaredInClassesThat().resideInAPackage("..model..").and()
                                                          .areAnnotatedWith(Id.class).should()
                                                          .haveRawType("java.util.UUID");


    //9. Keine Klasse sollte aus der Klasse User die Methode setCreationDate(LocalDateTime time) aufrufen
    @ArchTest
    static ArchRule no_class_should_use_setCreationDate = ArchRuleDefinition.noClasses()
                                                                    .should().callMethod(User.class,"setCreationDate", LocalDateTime.class)
                                                                    .because("CreationDate is already Set by User Object");

    //10. Keine Klassen sollten Field Injection verwenden
    @ArchTest
    static ArchRule no_class_should_use_FieldInjection = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION
                                                                            .because("It's not a good style and we want constructor injection");

    //11. Keine Klasse sollte zyklischen Abhängigkeiten haben​
    @ArchTest
    static ArchRule free_Of_Cycles = SlicesRuleDefinition.slices().matching("org.example.archunitdemo.(*)..").should().beFreeOfCycles();

    //12. Keine Klasse sollte die externe Klasse java.util.Date verwenden
    @ArchTest
    private static ArchRule no_java_util_date =
            ArchRuleDefinition.noClasses()
                    .should().dependOnClassesThat()
                    .haveFullyQualifiedName(java.util.Date.class.getName());

}