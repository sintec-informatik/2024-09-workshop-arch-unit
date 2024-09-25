package org.example.archunitdemo.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Repository;

@AnalyzeClasses(
        packages = "org.example.archunitdemo",
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class})
class ArchitectureTest {

    //1. Repositorys sollten mit @Repository annotiert sein und im Namen ..Repository.. enthalten
    @ArchTest
    static ArchRule repositorys_should_be_annoted_and_Named = ArchRuleDefinition.classes()
            .that().resideInAPackage("..repository..").should()
            .beAnnotatedWith(Repository.class)
            .andShould().haveSimpleNameContaining("Repository");

    //2. Keine Klasse in package "persistence" sollte von package "service" abhängen


    //3. Klassen in "service" sollten nur für Klassen in "controller" oder anderen in "service" zugänglich sein


    //4. Keine Klasse in package "controller" sollte Abhängigkeiten auf Klassen im "package" persistence haben


    //5. Services sollten mit @Service annotiert sein und im Namen ..Service.. enthalten


    //6. Controller sollten mit @RestController annotiert sein und „Controller“ im Namen enthalten


    //7. Klassen in package model sollten mit @Entity annotiert sein


    //8. für jede Entity sollte die @ID vom Typ UUID sein


    //9. Keine Klasse sollte aus der Klasse User die Methode setCreationDate(LocalDateTime time) aufrufen


    //10. Keine Klassen sollten Field Injection verwenden


    //11. Keine Klasse sollte zyklischen Abhängigkeiten haben


    //12. Keine Klasse sollte die externe Klasse java.util.Date verwenden


}