plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.avast.gradle.docker-compose") version "0.14.2"
}

group = "br.com.atlasmundi"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val springCloudVersion = "Hoxton.SR10"
val javaMoneyVersion = "1.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.auth0:java-jwt:3.16.0")
    implementation("javax.money:money-api:$javaMoneyVersion")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")

    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}


dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks {
    test {
        useJUnitPlatform()
        systemProperty("spring.profiles.active", "local")
    }

    bootJar {
        archiveFileName.set("app.jar")
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    jacocoTestCoverageVerification {
        dependsOn(jacocoTestReport)

        violationRules {
            rule { limit { minimum = 0.0.toBigDecimal() } }
        }
    }

    check {
        dependsOn(jacocoTestCoverageVerification)
    }

    composeUp {
        doLast {
//            configureVault()
        }
    }
}

dockerCompose {
    val test by tasks
    isRequiredBy(test)
}