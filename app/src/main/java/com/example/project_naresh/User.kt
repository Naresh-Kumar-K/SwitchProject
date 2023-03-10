package com.example.project_naresh

data class User(
    val created: Int,
    val d1: String,
    val d2: String,
    val dir: String,
    val files: List<File>,
    val files_count: Int,
    val item_last_updated: Int,
    val item_size: Int,
    val metadata: Metadata,
    val reviews: List<Review>,
    val server: String,
    val uniq: Int,
    val workable_servers: List<String>
)