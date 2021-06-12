object Versions {
    const val kotlin = "1.5.10"
    const val activity = "1.2.3"
    const val compose = "1.0.0-beta08"
    const val activityCompose = "1.3.0-beta01"
    const val composeNavigation = "2.4.0-alpha02"
    const val koin = "3.1.0"
}

object Koin {
    val core = "io.insert-koin:koin-core:${Versions.koin}"
    val test = "io.insert-koin:koin-test:${Versions.koin}"
    val android = "io.insert-koin:koin-android:${Versions.koin}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}