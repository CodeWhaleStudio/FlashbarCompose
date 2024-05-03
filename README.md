# FlashbarCompose

This library is inspired by [Flashbar](https://github.com/aritraroy/Flashbar) from legacy android,
while it is completely unusable in Jetpack Compose, so I decided to make one in compose by referencing
[MessageBarCompose](https://github.com/stevdza-san/MessageBarCompose).

Super thanks for these inspiration and implementation.

## Usage

First, you need to wrap all the content into `FlashbarContainer` which allows the Flashbar message to be displayed.

```kotlin
val flashbarState = rememberFlashbarState()
FlashbarContainer(
    state = flashbarState
) {
    // your main content
}
```

Then, you can show a message using the `FlashbarState` like following:

```kotlin
// show info
flashbarState.info("Info Message")
// show error
flashbarState.error("Error message")
```

Available parameters are:

```kotlin
flashbarState.info(
    text: String,
    icon: ImageVector,
    actions: List<FlashbarAction>,
    duration: Long
)
```