fun version(artifact: String): String {
	return project.ext["version.${artifact.toLowerCase()}"]?.toString()
		?: throw IllegalStateException("No version found for artifact '$artifact'")
}

plugins {
	id("io.gitlab.arturbosch.detekt") version "1.0.0.RC6-2"
	id("org.sonarqube") version "2.6.2"
	id("org.jetbrains.kotlin.jvm") version "1.3.21" apply true
	id("maven")
	id("idea")
}

group = "com.mantono"
version = "0.1.0"
description = "Kotlin graph library"

defaultTasks("test")

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version("kotlin")}")
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${version("coroutines")}")
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:${version("coroutines")}")

	// Jackson
	implementation("com.fasterxml.jackson.core:jackson-core:${version("jackson")}")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${version("jackson")}")
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:${version("jackson")}")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${version("jackson")}")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${version("jackson")}")

	// Junit
	testCompile("org.junit.jupiter:junit-jupiter-api:${version("junit")}")
	testRuntime("org.junit.jupiter:junit-jupiter-engine:${version("junit")}")
}

tasks {
	test {
		useJUnitPlatform()

		// Show test results.
		testLogging {
			events("passed", "skipped", "failed")
		}
		reports {
			junitXml.isEnabled = false
			html.isEnabled = true
		}
	}

	compileKotlin {
		sourceCompatibility = version("jvm")
		kotlinOptions {
			jvmTarget = version("jvm")
		}
	}

	wrapper {
		description = "Generates gradlew[.bat] scripts for faster execution"
		gradleVersion = version("gradle")
	}
}