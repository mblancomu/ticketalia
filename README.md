# Manuel Blanco Ticketalia Challenge
## Creating an offline app

## Challenge

In the development of this app, the Ticketmaster API (https://developer.ticketmaster.com/products-and-docs/apis/getting-started/) has been used to download the Events ordered by name (and for now a default country: Spain), which are stored locally with Room, which allows information about them to always be available even if there is no internet connection, being Offline first.

The app has two sections, Events and Venues, available in the BottomBarNavigation, which are two lists, using different technologies to page and download information locally. In the case of Events, manual pagination is used, with the logic in the ViewModel, and the data is stored in the local database for each page that is called remotely. In Venues, however, the Paging3 library is used to perform paging and local storage, using a Mediator that brings together all this logic.

In addition to the main screen with the lists of both sections, there is a detail screen for each list, which is executed by clicking on any element in the list, showing more detailed information, and using the Jetpack Compose Navigation.

## References

For the creation of this project I have based myself on a mixed solution of modularization by layers and features, as recommended by Android in its latest articles(https://www.youtube.com/watch?v=16SwTvzDO0A).

The project follows as a reference the app that Android uses in an educational way about Architectures, and especially about the modularization of the app, in order to respect as far as possible the Clean Architecture and the Separation of Concerns.

https://developer.android.com/topic/modularization

The following image shows the basic structure of the app:

![](img/architecture.jpg)

## Used technology

- MVI Pattern
- ROOM as database
- Coil for images
- Hilt as Dependency Injector
- Paging3 as pagination & caching enginee
- Compose for the views
- Flow/Coroutines
- Kotlin DSL
- Testing with: Robolectric, Turbine, Roborazzi, Paparazzi & JUnit4

## Technical decisions

##### Modularization by layers and features:

As I have commented before, I have made a modularization of the project by layers and features, thus following the android recommendations on architecture, focusing on the separation of concerns and making the app expandable and easily maintainable by the development team (where it is perfectly can be worked in different modules without conflicts when working).

The dependencies between modules can be seen in the following scheme:

![](img/relations.jpg)

Below is an informative table about the content of the different modules:

| Module       | Content                                                                                                                                                | Parent Module |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|
| common       | Utilities for the rest of the modules (extensions, converters, etc.)                                                                                   | core          |
| data         | Repositories, mediator for Pagign3, data models and utils                                                                                              | core          |
| database     | All the information regarding the ddbb (Daos, models, useful)                                                                                          | core          |
| datastore    | Contains the share preferences used in the app                                                                                                         | core          |
| designsystem | Everything related to the app's design system (theme, colors, typography, etc.), with custom components (buttons, background, etc.).                   | core          |
| domain       | Interface of the app use cases (business logic)                                                                                                        | core          |
| model        | Business logic models that will be used in the presentation layer of the app                                                                           | core          |
| network      | Models associated with network data from the API (DTOs), implementation of the network API(endpoints) and the Retrofit singleton.                      | core          |
| testing      | Utilities for testing and fake data to carry out the testing (data, repositories, etc.).                                                               | core          |
| ui           | Composables that are used in different parts of the app (such as list items, loading, error view, etc.).                                               | core          |
| events       | Information regarding the presentation layer of this feature, which includes the implementation of the use case, view model, screens, navigation, etc. | features      |
| venues       | Information regarding the presentation layer of this feature, which includes the implementation of the use case, view model, screens, navigation, etc. | features      |
| favorites    | TO-DO                                                                                                                                                  | features      |

##### Paging3 as paging and caching engine in Venues section:

One of the most important decisions I have made in this project has been to use the Jectpack Paging library, to manage paging and caching..

Despite being a very complete solution, since it is in charge of all the management of cached information and paging, it is true that it breaks the data flow that I had in mind for this app, and that I have exposed in the detail screen. I know that the detail screen was not in this statement, but I wanted to create it to apply the data flow that I usually like, with MVI. Meanwhile, with Paging the flow varies slightly, as can be seen in the following image:

![](img/paging_flow.png)

Using Paging I have been able to understand how this library works, which has been pleasant, since Android is quite committed to it and its implementation has always been somewhat complex for developers, by using their own objects throughout almost the entire data flow. Also, with Paging we don't need the typical Events/State to communicate the ViewModel with the view, since the PagingData manages all that, already having in the Composable the different states of the data through Paging.

The usual flow of data with which I usually work is what you can see in the following image (although with Paging it has been completely broken).

![](img/data_flow.jpg)

##### Manual paging and caching engine in Events section:

In the Events section, pagination has been carried out manually, with its own logic, as well as data caching to be able to have the information without an internet connection. The aim is to present alternatives to the use of Paging3.

##### Testing: unit test & screenshot test

In this project, both unit tests have been carried out for the use cases and for the ViewModels of each screen, as well as screenshots about the state of the different screens, using Paparazzi. In the testing module there is a series of utilities and data for the tests. It remains to carry out some instrumentation tests, using Robolectric and espresso mainly, and if necessary Mockk to generate more fake data with which to test.

##### Compose: declarative view system

As for the view system, they are made with Compose, on which a theme for the app has been designed, made up of different components that are available in the designsystem module. This allows us to reuse the different Composables, decoupling and reusing code in an efficient way.

Thanks to Compose, the testing of the different screens, with the different states, has been carried out by testing screenshots with Paparazzi (and some small tests with Roborazzi).

##### Design System:

Given the importance of design systems in an app with Compose, I have generated a module that contains all the information corresponding to it, with colors, buttons, backgrounds, etc... customized for this app.

##### Datastore:

I had generated the datastore module with the idea of putting a button to change the Theme (dark/light) on hot and store its variable there, but again I couldn't finish it due to lack of time. It only remains to put the button and call the method to save that boolean on the dark theme.

## Improvements to be made in this project

Among the improvements that can be made to this (unfinished) project are:

- Creation of the favorites feature, following the flow of the other features..
- Improve the logic of pagination and saving information locally in the events section.
- Improve animations.
- New features like Search, Filter...on the lists.

## Reflections

Although the architecture followed in the project is based on the recommendations by Android, and especially in its example project `Now in Android`, there are still certain things that can be improved, because even in its project we see some deficiencies, such as the dependency between their features, or having the implementation of the use cases in the domain module, when in reality they contain information regarding the presentation layer. In this project I have tried to solve these deficiencies, first with the global navigation of the app (to avoid dependencies between features) and then by abstracting the use cases with their interfaces in the domain module, with their implementations in the domain module. corresponding feature.

Perhaps this architecture can be improved even further to make it more scalable, and perhaps one option would be to move everything that is needed per feature to those modules, in order to be able to work as independently as possible by each team in a specific feature, without the need to modify other modules.

Finally, in this app I have mixed a slightly different MVI in some features, because with the use of Paging3, it is the library itself that manages the states of that list, so it did not make much sense to apply a State for the list. screen, since it all boils down to Paging's management of it.