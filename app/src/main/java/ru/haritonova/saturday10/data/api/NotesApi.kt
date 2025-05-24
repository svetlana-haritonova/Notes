package ru.haritonova.saturday10.data.api

import retrofit2.http.*

interface NotesApi {
    @POST("/api/user/signin")
    suspend fun login(@Body body: SignInRequest): AuthResponse

    @GET("/api/note")
    suspend fun getNotes(@Header("Authorization") token: String): List<NoteDTO>

    @POST("/api/note")
    suspend fun createNote(@Header("Authorization") token: String, @Body body: NoteDTO): CreatedNoteResponse

    @PATCH("/api/note/{id}")
    suspend fun updateNote(@Header("Authorization") token: String, @Path("id") id: String, @Body body: NoteDTO): NoteIdResponse

    @DELETE("/api/note/{id}")
    suspend fun deleteNote(@Header("Authorization") token: String, @Path("id") id: String)
}

data class SignInRequest(val login: String, val password: String)
data class AuthResponse(val token: String, val user_id: String)
data class NoteDTO(val id: String = "", val title: String, val text: String)
data class CreatedNoteResponse(val note_id: String)
data class NoteIdResponse(val note_id: String)
