package com.example.core.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    val name: String,
    val id: String = UUID.randomUUID().toString(),
    val url: String
)
