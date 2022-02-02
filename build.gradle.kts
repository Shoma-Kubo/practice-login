import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.6.1"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  kotlin("jvm") version "1.6.0"
  kotlin("plugin.spring") version "1.6.0"
  kotlin("kapt") version "1.6.0"
}

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {

  /* Kotlin */
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  /* Spring */
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  /* Spring web */
  implementation("org.springframework.boot:spring-boot-starter-web")

  /* Spring security */
  implementation("org.springframework.boot:spring-boot-starter-security")
  testImplementation("org.springframework.security:spring-security-test")

  /* Validation */
  implementation("org.springframework.boot:spring-boot-starter-validation")

  /* Thymeleaf */
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

  /* Lombok */
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  /* Tika */
  implementation("org.apache.tika:tika-parsers:1.26")

  /* MySQL */
  runtimeOnly("mysql:mysql-connector-java")

  /* Doma */
  val domaVersion = "2.50.0"
  implementation("org.seasar.doma:doma-kotlin:${domaVersion}")
  //implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.6.0")
  kapt("org.seasar.doma:doma-processor:${domaVersion}")
  //implementation("org.seasar.doma:doma:${domaVersion}")
  implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.6.0")
  //kapt("org.seasar.doma:doma:${domaVersion}")
}

val compileKotlin: KotlinCompile by tasks

kapt {
  arguments {
    arg("doma.resources.dir", compileKotlin.destinationDir)
  }
}

tasks.register("copyDomaResources", Sync::class) {
  from("src/main/resources")
  into(compileKotlin.destinationDir)
  include("doma.compile.config")
  include("META-INF/**/*.sql")
  include("META-INF/**/*.script")
}

tasks.withType<KotlinCompile> {
  dependsOn(tasks.getByName("copyDomaResources"))
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}