plugins {
    id("java")
    id("application")
}

group = "parcial1.solid.avengers"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java")
        }
    }
}

application {
    mainClass.set("parcial1.solid.avengers.main.ParcialMain")
}

dependencies {
    // Dependencia para leer archivos .env
    //plementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    // Dependencia para el envío de correos (JavaMail)
    implementation("org.eclipse.angus:angus-mail:2.0.2")

    // Pruebas unitarias
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
    (options as StandardJavadocDocletOptions).apply {
        charSet = "UTF-8"
        docEncoding = "UTF-8"
        addStringOption("Xdoclint:none", "-quiet")
    }
}
