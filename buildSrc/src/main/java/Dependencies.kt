object Versions {
    const val kotlin = "1.5.10"
    const val activity = "1.2.3"
    const val compose = "1.0.0-beta08"
    const val activityCompose = "1.3.0-beta01"
    const val composeNavigation = "2.4.0-alpha02"
    const val koin = "3.1.0"
    const val kotlinCoroutines = "1.4.3-native-mt"
    const val ktor = "1.5.3"
    const val kotlinxSerialization = "1.1.0"
    const val sqlDelight = "1.5.0"
    const val kermit = "0.1.9"
}

object Koin {
    val core = "io.insert-koin:koin-core:${Versions.koin}"
    val test = "io.insert-koin:koin-test:${Versions.koin}"
    val android = "io.insert-koin:koin-android:${Versions.koin}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Ktor {
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
}

object Serialization {
    val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
}

object Deps {
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
}

object SqlDelight {
    val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
//    val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosx64:${Versions.sqlDelight}"
//    val jdbcDriver = "org.xerial:sqlite-jdbc:${Versions.sqliteJdbcDriver}"
//    val sqlliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
}

