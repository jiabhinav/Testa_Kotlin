package com.radiant.rpl.testa_kotlin.model

data class ModelUser(
    val batch_type: String,
    val exam_status: String,
    val exam_type_pending: List<String>,
    val face_detection: String,
    val image_path: String,
    val msg: String,
    val object_detection: String,
    val proctoring_comparison: String,
    val proctoring_status: String,
    val status: Int,
    val student_details: StudentDetails,
    val student_type: String,
    val suspicious_count: String,
    val user_type: String,
    val user_type_id: String,
    val video_duration: String,
    val video_interval: String,
    val video_proctoring_status: String
) {
    data class StudentDetails(
        val address: String,
        val assessment_status: String,
        val batch_id: Int,
        val batch_name: String,
        val batch_type: String,
        val batchid: String,
        val browser_exit_status: String,
        val candidate_id: String,
        val client_logo: String,
        val face_detection: String,
        val face_recognition: String,
        val jobrole_name: Any,
        val mobile: String,
        val name: String,
        val no_browser_exit: Int,
        val object_detection: String,
        val proctoring_status: String,
        val scheme_id: Any,
        val ssc_id: Int,
        val ssc_logo: String,
        val user_name: String,
        val video_duration: String,
        val video_interval: String,
        val video_streaming_status: String
    )
}