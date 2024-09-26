package org.example.archunitdemo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

class ExtraArchitectureTest {

    // Service-Klassen sollten mit "Service" enden und mit @Service annotiert sein
    @Test
    void servicesShouldBeAnnotatedWithService() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.example.archunitdemo");
        ArchRule rule = ArchRuleDefinition.classes()
                                          .that().resideInAPackage("..service..").should()
                                          .beAnnotatedWith(Service.class)
                                          .andShould().haveSimpleNameContaining("Service")
                                          .because("Service must have Annotation and should be Named like that");
        rule.check(importedClasses);
    }

    // Testmethoden sollten mit "should" beginnen
    @Test
    public void testMethodsShouldStartWithShould() {
        // Importiere Klassen aus dem Testpaket oder Testverzeichnis
        var importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)  // Keine JAR-Dateien
                .importPackages("org.example.archunitdemo.extendTests");  // Welches Package soll geprüft und importiert werden

        // Definiert die Bedingung, dass Methoden mit "should" beginnen müssen
        ArchCondition<JavaMethod> shouldStartWithShould = new ArchCondition<>("beginnen mit 'should'") {
            @Override
            public void check(JavaMethod method, ConditionEvents events) {
                if (!method.getName().startsWith("should")) {
                    String message = String.format("Method %s does not start with 'should'", method.getFullName());
                    events.add(SimpleConditionEvent.violated(method, message));
                }
            }
        };

        // Regel: Alle Testmethoden müssen mit "should" beginnen
        ArchRule rule = methods()
                .that().areAnnotatedWith(Test.class)
                .should(shouldStartWithShould);

        // Prüfe die Regel für alle importierten Testklassen
        rule.check(importedClasses);
    }

    //Schichtenarchitektur
    @Test
    void layer_dependency_rule() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.example.archunitdemo");

        ArchRule rule = Architectures.layeredArchitecture()
                                     .consideringAllDependencies()
                                     .layer("Controller").definedBy("..controller..")
                                     .layer("Service").definedBy("..service..")
                                     .layer("Persistence").definedBy("..persistence..")

                                     .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                                     .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                                     .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")

                                     .because("Layers must respect a defined dependency hierarchy");

        FreezingArchRule freeze = FreezingArchRule.freeze(rule);
        freeze.check(importedClasses);
    }
}
