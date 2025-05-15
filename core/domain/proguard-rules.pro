# Keep no-args constructor of deserialized class
-keepclassmembers class com.kotlinpl.core.domain.util.Result { <init>() }
-keep class com.kotlinpl.core.domain.util.Result$Success
-keep class com.kotlinpl.core.domain.util.Result$Error
-keepclassmembers class com.kotlinpl.core.domain.util.Result$Success { *; }
-keepclassmembers class com.kotlinpl.core.domain.util.Result$Error { *; }