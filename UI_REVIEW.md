# Knight Compass local UI demo

Open the repository folder in Android Studio, sync Gradle, and run app. Java activities use Android Views and XML, with no external runtime dependencies or network permissions.

## UI XML inventory

- app/src/main/res/layout/activity_home.xml
- app/src/main/res/layout/item_event.xml
- app/src/main/res/layout/activity_login.xml
- app/src/main/res/layout/activity_event_detail.xml
- Supporting appearance: app/src/main/res/drawable/*.xml and app/src/main/res/values/{colors,styles}.xml

This is an inventory for later review, not a final submission package.

## Demo flow

Home opens first. Today and Weekend select different local lists. Search filters the selected list. Login opens a demo form; Login and Back to Home return to Home. Any event card opens its own details; Events or system Back returns to the preserved list. Detail actions display local informational dialogs.

## Design decisions

- Colors, cards, header, and information table follow the supplied mockups.
- A custom compass emblem replaces the official UCF seal, which was not supplied as a separate licensed asset.
- Login is designed to match the other screens; Login is a demo action and does not authenticate or retain credentials.
- Mock dates from April 2025 are intentionally preserved. Today and Weekend are sample collections, not live date filters.
- The third Today card has a distinct first-generation event title instead of repeating the career fair title.
- Detail content scrolls on smaller screens. No backend, map integration, real registration, or review storage is included.
- Build uses the installed Android Gradle Plugin 9.5.0, Gradle 9.6.0, SDK 37, Java 11 source compatibility, and Android Studio's bundled JDK. Target SDK 35 keeps this checkpoint independent of API 37 behavior changes.

- app/src/main/java/edu/ucf/knightcompass/MapActivity.java (Dhyan)
- app/src/main/java/edu/ucf/knightcompass/Review.java (Dhyan)
- app/src/main/java/edu/ucf/knightcompass/ReviewsActivity.java (Dhyan)
- app/src/main/res/drawable-nodpi/ucf_seal.png (Dhyan)
- app/src/main/res/drawable/ic_car.xml (Dhyan)
- app/src/main/res/drawable/ic_open.xml (Dhyan)
- app/src/main/res/drawable/ic_pin.xml (Dhyan)
- app/src/main/res/drawable/icon_cream.xml (Dhyan)
- app/src/main/res/drawable/icon_dark.xml (Dhyan)
- app/src/main/res/drawable/reply_rule.xml (Dhyan)
- app/src/main/res/layout/activity_map.xml (Dhyan)
- app/src/main/res/layout/activity_reviews.xml (Dhyan)
- app/src/main/res/layout/dialog_review.xml (Dhyan)
- app/src/main/res/layout/item_review.xml (Dhyan)
