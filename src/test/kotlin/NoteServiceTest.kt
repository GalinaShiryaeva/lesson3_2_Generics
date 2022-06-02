import Exceptions.NoteCommentNotFoundException
import Exceptions.NoteNotFoundException
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun testAdd() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")

        // act
        NoteService.add(note)

        // assert
        assertTrue("Note id must be grater then 0", note.id > 0u)
    }

    @Test(expected = NoteNotFoundException::class)
    fun testCreateComment_throwsException() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        val noteComment = NoteComment(3u, 2u, message = "Test comment message")

        // act
        NoteService.createComment(noteComment)
    }

    @Test
    fun testCreateComment() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val noteComment = NoteComment(1u, note.id, message = "Test comment message")

        // act
        val createComment = NoteService.createComment(noteComment)

        // assert
        assertTrue("Failed to create the comment", createComment > 0u)
    }

    @Test(expected = NoteNotFoundException::class)
    fun testDelete_throwsException() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        // The note has not been added to list 'notes', thus the exception must appear

        // act
        NoteService.delete(note.id)
    }

    @Test
    fun testDelete() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)

        // act
        val isDeleted = NoteService.delete(note.id)

        // assert
        assertTrue("Failed to delete the note", isDeleted)
    }

    @Test(expected = NoteCommentNotFoundException::class)
    fun testDeleteComment_throwsException() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val invalidCommentId: ULong = 2u

        // act
        val isDeleted = NoteService.deleteComment(invalidCommentId)
    }

    @Test
    fun testDeleteComment() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val commentId = NoteService.createComment(NoteComment(11u, note.id, message = "Test message"))

        // act
        val isDeleted = NoteService.deleteComment(commentId)

        // assert
        assertTrue("Failed to delete the comment", isDeleted)
    }

    @Test(expected = NoteNotFoundException::class)
    fun testEdit_throwsException() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        // The note has not been added to list 'notes', thus the exception must appear

        // act
        NoteService.edit(note, "New title", "New text")
    }

    @Test
    fun testEdit() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)

        // act
        val isEdited = NoteService.edit(note, "New title", "New text")

        // assert
        assertTrue("Failed to edit the note", isEdited)
    }

    @Test(expected = NoteCommentNotFoundException::class)
    fun testEditComment_throwsException() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val comment = NoteComment(11u, note.id, message = "Test message")
        // The comment has not been added to list 'noteComments', thus the exception must appear

        // act
        val isCommentEdited = NoteService.editComment(comment, "Edited message")
    }

    @Test
    fun testEditComment() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val comment = NoteComment(11u, note.id, message = "Test message")
        NoteService.createComment(comment)

        // act
        val isCommentEdited = NoteService.editComment(comment, "Edited message")

        // assert
        assertTrue("Failed to edit the comment", isCommentEdited)
    }

    @Test
    fun testGet() {
        // arrange
        val note1 = Note(1u, "Test note1 title", "Test note1 text")
        NoteService.add(note1)
        val note2 = Note(2u, "Test note2 title", "Test note2 text")
        NoteService.add(note2)
        val note3 = Note(3u, "Test note3 title", "Test note3 text")
        NoteService.add(note3)

        val noteIds: Array<ULong> = arrayOf(note1.id, note2.id, note3.id)

        // act
        val result = NoteService.get(noteIds)

        // assert
        assertTrue("Failed to perform request", result.isNotEmpty())
    }

    @Test
    fun testGetById() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)

        // act
        val result = NoteService.getById(note.id)

        // assert
        assertTrue("Failed to perform request", result.id == note.id)
    }

    @Test
    fun testGetComments() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val comment1 = NoteComment(11u, note.id, message = "Test message")
        NoteService.createComment(comment1)
        val comment2 = NoteComment(12u, note.id, message = "Test message")
        NoteService.createComment(comment2)

        // act
        val result = NoteService.getComments(note)

        // assert
        assertTrue("Failed to perform request", result.isNotEmpty())
    }

    @Test
    fun testRestoreComment() {
        // arrange
        val note = Note(1u, "Test note title", "Test note text")
        NoteService.add(note)
        val comment1 = NoteComment(11u, note.id, message = "Test message")
        NoteService.createComment(comment1)

        // act
        NoteService.deleteComment(comment1.id)
        val isRestored = NoteService.restoreComment(comment1.id)

        // assert
        assertTrue("Failed to perform request", isRestored)
    }
}