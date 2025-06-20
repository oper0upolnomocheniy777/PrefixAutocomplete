plugins {
    application
    java
}

application {
    mainClass.set("service.App")
    applicationDefaultJvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
        "-Dconsole.encoding=UTF-8"
    )
}

tasks.withType<JavaExec> {
    systemProperty("file.encoding", "UTF-8")
    systemProperty("sun.jnu.encoding", "UTF-8")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}