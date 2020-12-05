import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("idea")
	id("eclipse")
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.google.cloud.tools.jib") version "2.7.0"
	id("org.openapi.generator") version "4.2.1"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

group = "de.mcella.openapi.v3"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val alpineJreImage = "adoptopenjdk/openjdk11:alpine-jre"
val openApiDocumentation = "${project.rootDir}/documents/user.yaml"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.create<org.openapitools.generator.gradle.plugin.tasks.ValidateTask>("validateUserOpenApiDocumentation") {
	inputSpec.set(openApiDocumentation)
}

task("validateOpenApi") {
	dependsOn.add(listOf("validateUserOpenApiDocumentation"))
}

jib {
	from {
		image = "$alpineJreImage"
	}
	to {
		image = "freeformapp"
	}
	//noinspection GroovyAssignabilityCheck
	container {
		jvmFlags = listOf("-Xmx1g", "-XX:+ExitOnOutOfMemoryError")
	}
}
