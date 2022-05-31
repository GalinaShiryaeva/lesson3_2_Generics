fun main() {
    var id: ULong = 0u
    var idComment: ULong = 0u

    val note1 = Note(++id, "The first note", "Text of the first note")
    val note2 = Note(++id, "The second note", "Text of the second note")
    val note3 = Note(++id, "The third note", "Text of the third note")

    var noteComment11 = NoteComment(++idComment, note1.id, message = "Comment to note1")
    var noteComment12 = NoteComment(++idComment, note1.id, message = "Another comment to note1")

    var noteComment21 = NoteComment(++idComment, note2.id, message = "Comment to note2")
    var noteComment22 = NoteComment(++idComment, note2.id, message = "Another comment to note2")

    NoteService.add(note1)
    NoteService.add(note2)
    NoteService.add(note3)

    println("ID of the new comment for note1: " + NoteService.createComment(noteComment11))
    NoteService.createComment(noteComment12)
    NoteService.createComment(noteComment21)
    NoteService.createComment(noteComment22)

    println(NoteService.delete(note3.id))
    println(NoteService.deleteComment(note3.id))

    println("Edit note2: " + NoteService.edit(note2, "New title for note2", "New text for note2"))
    //println("Edit note3 (doesn't exist): " + NoteService.edit(note3, "New title for note3", "New text for note3"))

    println("Edit comment12 for note1: " + NoteService.editComment(noteComment12, "Changed comment12"))
    println("Edit comment22 for note2: " + NoteService.editComment(noteComment22, "Changed comment22"))

    val getNoteArray: Array<ULong> = arrayOf(0u, 1u, 2u)
    val getNote = NoteService.get(getNoteArray)
    println("\nGot an array of notes:")
    for (note in getNote) {
        println("ID = ${note.id} \n title = '${note.title}' \n text = '${note.text}")
    }

    println("Result of getById(): " + NoteService.getById(note2.id))

    val getComments: Array<NoteComment> = NoteService.getComments(note2)
    println("\nGot an array of comments for note2:")
    for (comment in getComments) {
        println(NoteService.getComments(note2))
    }

    println(NoteService.restoreComment(note3.id))

}