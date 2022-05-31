data class Note(
    val id: ULong,
    var title: String,
    var text: String,
    var privacy: Int = NoteService.PrivacyIndex.ALL.ordinal,
    var comment_privacy: Int = NoteService.PrivacyIndex.ALL.ordinal,
    var privacy_view: MutableList<NoteService.PrivacyView> = mutableListOf(NoteService.PrivacyView.ALL),
    var privacy_comment: MutableList<NoteService.PrivacyView> = mutableListOf(NoteService.PrivacyView.ALL)
)

