import Exceptions.NoteCommentNotFoundException
import Exceptions.NoteNotFoundException

object NoteService {
    private var notes = mutableListOf<Note>()
    private var deletedNotes = mutableListOf<Note>()
    private var noteComments = mutableListOf<NoteComment>()
    private var deletedComments = mutableListOf<NoteComment>()

    fun add(note: Note): ULong {
        notes += note
        return note.id
    }

    @Throws(NoteNotFoundException::class)
    fun createComment(noteComment: NoteComment): ULong {
        for (note in notes) {
            if (note.id == noteComment.noteId) {
                noteComments += noteComment
                return noteComment.id
            }
        }
        throw NoteNotFoundException("The note is not found")
    }

    @Throws(NoteNotFoundException::class)
    fun delete(noteId: ULong): Boolean {
        for (n in notes) {
            if (n.id == noteId) {
                deletedNotes += n
                notes.remove(n)
                for (noteComment in noteComments) {
                    if (noteComment.noteId == noteId) {
                        deletedComments += noteComment
                        noteComments.remove(noteComment)
                    }
                }
                return true
            }
        }
        throw NoteNotFoundException("Cannot delete the note, it's not found")
    }

    @Throws(NoteCommentNotFoundException::class)
    fun deleteComment(commentId: ULong): Boolean {
        for (noteComment in noteComments) {
            if (noteComment.id == commentId) {
                deletedComments += noteComment
                noteComments.remove(noteComment)
                return true
            }
        }
        throw NoteCommentNotFoundException("Cannot delete the comment, it's not found")
    }

    @Throws(NoteNotFoundException::class)
    fun edit(note: Note, title: String, text: String): Boolean {
        for (n in notes) {
            if (n.id == note.id) {
                note.title = title
                note.text = text
                return true
            }
        }
        throw NoteNotFoundException("The note is not found")
    }

    @Throws(NoteCommentNotFoundException::class)
    fun editComment(noteComment: NoteComment, message: String): Boolean {
        for (nc in noteComments) {
            if (nc.id == noteComment.id) {
                noteComment.message = message
                return true
            }
        }
        throw NoteCommentNotFoundException("The comment is not found")
    }

    fun get(noteIds: Array<ULong> = emptyArray()): Array<Note> {
        var resultArray: Array<Note> = emptyArray()
        for (nId in noteIds) {
            for (note in notes) {
                if (note.id == nId) {
                    resultArray += note
                    return resultArray
                }
            }
        }
        return resultArray.also{
            println("Failed to process request")
        }
    }

    @Throws(NoteNotFoundException::class)
    fun getById(noteId: ULong): Note {
        for (note in notes) {
            if (note.id == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("The note is not found")
    }

    fun getComments(note: Note): Array<NoteComment> {
        var commentsArray: Array<NoteComment> = emptyArray<NoteComment>()
        for (comment in noteComments) {
            if (comment.noteId == note.id) {
                commentsArray += comment
            }
        }
        return commentsArray
    }

    fun getFriendsNotes(
        offset: ULong = 0u,
        count: ULong = 0u
    ): Array<Note> {
        // TO DO() ?????????? ?????????????????? ???????????????????? ???????????????????? ???????????? ???????????????? ??????????????
        // !!! ???????????? ?????????? ?????????????? ?? ?????????? ???????? ???????????????? ?????????? ?????????????????? ??????????, ????????????????????, ?????????????????? ?????? ??????????????????????????.
        return emptyArray()
    }

    @Throws(NoteCommentNotFoundException::class)
    fun restoreComment(commentId: ULong): Boolean {
        for (nc in deletedComments) {
            if (nc.id == commentId) {
                noteComments += nc
                deletedComments.remove(nc)
                return true
            }
        }
        throw NoteNotFoundException("The comment is not found")
    }

    enum class PrivacyIndex(index: Int) {
        ALL(0),
        ONLY_FRIENDS(1),
        FRIENDS_OF_FRIENDS(2),
        ONLY_ME(3)
    }

    enum class PrivacyView() {
        ALL,
        FRIENDS,
        FRIENDS_OF_FRIENDS,
        ONLY_ME,
        LIST_USER_ID,
        USER_ID,
        NOT_LIST_USER_ID,
        NOT_USER_ID
    }
}