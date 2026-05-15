plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    compileOnly("com.github.decentsoftware-eu.decentholograms:plugin:2.10.0") { isTransitive = false }
}