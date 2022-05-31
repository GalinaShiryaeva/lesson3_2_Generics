package Exceptions

import java.lang.RuntimeException

class NoteCommentNotFoundException(message: String): RuntimeException(message) {
}