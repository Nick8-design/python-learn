import com.android.build.gradle.internal.utils.getDesugarLibConfigFile
import org.gradle.internal.impldep.org.apache.ivy.util.url.IvyAuthenticator
import org.gradle.internal.impldep.org.apache.ivy.util.url.IvyAuthenticator.install

plugins {
    id("com.android.application")
    id ("com.chaquo.python")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.nickdieda.pythonlearn"
    compileSdk = 34

    flavorDimensions += "pyVersion"
    productFlavors {

        create("py38") { dimension = "pyVersion" }



    }

    defaultConfig {
        applicationId = "com.nickdieda.pythonlearn"
        minSdk = 24
        targetSdk = 34
        versionCode = 17
        versionName = "2.6"



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        ndk {
            abiFilters += listOf("arm64-v8a","armeabi-v7a")
        }


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true


    }


    chaquopy {
        productFlavors {
            getByName("py38") {
                version = "3.8"
                pip{
                   install("numpy")

                    /*install("pandas")
                    install("requests")
                    install("matplotlib")
                    install("scipy")

                    install("scikit-learn")  // Machine learning
                    install("tensorflow")    // Deep learning
                    install("keras")         // Deep learning

                    // Modules for data storage and databases
                    install("sqlalchemy")    // SQL toolkit and ORM
                    install("pymysql")       // MySQL adapter

                    // Other useful modules
                    //install("turtle")        // Turtle graphics
                    install("flask")         // Web framework
                    install("django")        // Web framework
                    install("pillow")        // Image processing
                    install("opencv-python") // Computer vision
                    install("sympy")         // Symbolic mathematics
                    install("pytest")        // Testing framework

*/
                }



            }

        }
        defaultConfig {
            version = "3.8"
        }

        sourceSets {
            getByName("main") {
                srcDir("src/main/python")
            }
        }


    }

}


dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



     //code editor
    implementation("io.github.Rosemoe.sora-editor:editor:0.23.4")
    implementation("io.github.Rosemoe.sora-editor:language-textmate:0.23.4")



//Slashscreen
    implementation("androidx.core:core-splashscreen:1.1.0-rc01")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

//am using it to achieve lazycoloumn behaviour
    implementation("androidx.recyclerview:recyclerview:1.3.2")


    //certificate
    implementation ("com.itextpdf:itext7-core:7.1.8")

//desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")




        implementation("com.google.android.gms:play-services-ads:23.5.0")
    
}