data class NoteComment(
    val id: ULong,
    val noteId: ULong = 0u,
    val ownerId: ULong = 0u,
    val replyTo: ULong = 0u,
    var message: String
)