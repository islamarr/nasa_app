
-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}

-keepnames @kotlin.Metadata class com.adyen.android.assignment.features.main_screen.domain.entities.**
-keepnames @kotlin.Metadata class com.adyen.android.assignment.features.general_errors.**
-keep class com.adyen.android.assignment.features.main_screen.domain.entities.** { *; }
-keep class com.adyen.android.assignment.features.general_errors.** { *; }
-keepclassmembers class com.adyen.android.assignment.features.main_screen.domain.entities.** { *; }
-keepclassmembers class com.adyen.android.assignment.features.general_errors.** { *; }