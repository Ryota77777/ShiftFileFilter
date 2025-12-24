plugins {
    id("java")
    id("application")
}

group = "filter"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

application {
    mainClass.set("filter.Main")
}

tasks.jar {
    archiveBaseName.set("shift")
    archiveVersion.set("1.0.0")
    manifest {
        attributes["Main-Class"] = "filter.Main"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}