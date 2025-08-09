// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.11.1" apply false
    id("com.chaquo.python") version "15.0.1" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
