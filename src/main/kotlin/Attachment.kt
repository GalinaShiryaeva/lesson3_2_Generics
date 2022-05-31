interface Attachment {
    val type: String
}

class VideoAttachment(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int
) : Attachment {
    override val type: String = "video"
}

class PhotoAttachment(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int
) : Attachment {
    override val type: String = "photo"
}

class MapAttachment(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int
) : Attachment {
    override val type: String = "map"
}

class FileAttachment(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int
) : Attachment {
    override val type: String = "file"
}

class AudioAttachment(
    val id: Int,
    val album_id: Int,
    val owner_id: Int,
    val user_id: Int
) : Attachment {
    override val type: String = "audio"
}