# Appetiser Exam

The goal of the exam is to load the content from the Apple iTunes store and displays the content in
both list and detail format. The URL used to load the content is:

`https://itunes.apple.com/search?term=star&country=au&media=movie`


Room is used to persist the data retrieved from the endpoint, allowing the user to immediately see
the content when opening the app again, even when the endpoint fails to load. A Flow is used to
observe the database and immediately reflect the updated contents to the UI thread whenever
the endpoint is being called.

Hilt is used as the Dependency Injection library for the app as it reduces the boilerplate of
doing Manual Dependency Injection and allows the dependencies' lifecycles to be automatically.
The database and network layers rely on Hilt to conveniently provide dependencies to other classes.

The project utilizes MVVM Architecture. Not only will this allow better separation of concerns,
the `ViewModel`s have independent lifecycles from the `Activity` / `Fragment` and will survive
configuration changes (e.g. device rotation).

## Notable Libraries Used:

* [Coil][coil] to easily load images compared to Glide / Picasso.
* [Hilt][hilt] to load dependencies.
* [Moshi][moshi] to handle JSON response parsing.
* [Retrofit][retrofit] to call the endpoint.
* [Room][room] to persist the content retrieved from the endpoint.

[coil]: https://github.com/coil-kt/coil
[hilt]: https://dagger.dev/hilt/
[moshi]: https://github.com/square/moshi
[retrofit]: https://github.com/square/retrofit
[room]: https://developer.android.com/jetpack/androidx/releases/room
