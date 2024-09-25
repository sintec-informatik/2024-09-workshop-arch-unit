# 2024-09-workshop-arch-unit

In diesem Beispielprojekt wird ein einfaches Schichtenmodell mit einem simplen Datenmodell dargestellt.

Das Projekt dient dazu Architekturrichtlinien mit [ArchUnit](https://www.archunit.org/) zu definieren und zu testen.

Es werden zusätzliche Abhängigkeiten wie [Lombok](https://projectlombok.org/) , [Spring Boot](https://spring.io/projects/spring-boot) und [JUnit](https://junit.org/junit5/) integriert

## Setup

### 1. ArchUnit Dependency einbinden in pom.xml

```pom.xml
        <dependency>
            <groupId>com.tngtech.archunit</groupId>
            <artifactId>archunit-junit5</artifactId>
            <version>1.3.0</version>
            <scope>test</scope>
        </dependency>
```

### 2. Projekt bauen

```shell
mvn clean install
```

stellt sicher, dass das Projekt vollständig gebaut wird.

Wenn Maven nicht in den Umgebungsvariablen konfiguriert ist, könnt ihr auch in der IDE im Maven register ein Install ausführen.


### 3. Prüft dass das "Lombok Plugin" in der IDE installiert ist


# Viel Spaß beim Testen