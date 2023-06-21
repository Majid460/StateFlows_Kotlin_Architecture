package com.example.testapp.base

import java.io.Serializable

data class BaseModel(
    var data: Any? = null,
    var status: Boolean = false
) : Serializable
