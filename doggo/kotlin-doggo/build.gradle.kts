import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
	id("au.com.dius.pact") version "4.1.2"
	id("org.unbroken-dome.test-sets") version "2.2.1"
}

group = "com.doggo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

testSets {
	"componentTest"()
	"contractTest"()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	"componentTestImplementation"(sourceSets["test"].output)
	"contractTestImplementation"(sourceSets["test"].output)
	"contractTestImplementation"("au.com.dius.pact.consumer:junit5:4.1.0")
}

pact {
	publish {
		pactBrokerUrl = "https://pact-broker.dev.doggo"
	}
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

pact {
	publish {
		pactBrokerUrl = "https://pact-broker.doggo"
	}
}