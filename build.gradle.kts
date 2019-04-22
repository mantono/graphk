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
	compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin")
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutines")
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

	// Jackson
	implementation("com.fasterxml.jackson.core:jackson-core:$jackson")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")
	implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:$jackson")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jackson")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson")

	// Junit
	testCompile("org.junit.jupiter:junit-jupiter-api:$junit")
	testRuntime("org.junit.jupiter:junit-jupiter-engine:$junit")
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
		sourceCompatibility = jvm
		kotlinOptions {
			jvmTarget = jvm
		}
	}

	wrapper {
		description = "Generates gradlew[.bat] scripts for faster execution"
		gradleVersion = gradle
	}
}