plugins {
    java
    idea
}

group = "com.auqkwa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = uri("https://papermc.io/repo/repository/maven-public/"))
}

dependencies {
    module(":shared")
    compileOnly("io.github.waterfallmc:waterfall-api:1.16-R0.4-SNAPSHOT")
    implementation("org.mongodb:mongodb-driver-legacy:4.2.3")
}
