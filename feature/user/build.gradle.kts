import groovy.lang.ExpandoMetaClassCreationHandle.disable
import groovyjarjarantlr4.v4.runtime.misc.RuleDependencyChecker.checkDependencies
import java.lang.module.ModuleFinder.compose

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.feature.user"
    compileSdk {
        version = release(37)
    }
    lint {
        abortOnError = true          // 有 error 时构建失败
        warningsAsErrors = true     // 先不把 warning 当 error，避免太严
        checkDependencies = true     // 同时检查依赖库
        baseline = file("lint-baseline.xml")  // 基线文件
        // 按需禁用或启用特定检查
        disable += listOf("MissingTranslation")
        enable += listOf("RtlHardcoded", "RtlCompat")
    }
    defaultConfig {
        minSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":data:user"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
