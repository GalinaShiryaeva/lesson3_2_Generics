data class Post(
    var id: Int,
    var owner_id: Int = 0,
    var from_id: Int = 0,
    var created_by: Int = 0,
    var date: Int = 0,
    var text: String = "",
    var reply_owner_id: Int = 0,
    var reply_post_id: Int = 0,
    var friends_only: Boolean = true,
    var comments: Comments? = null,
    var copyright: Copyright = Copyright(),
    var likes: Likes = Likes(),
    var reposts: Reposts = Reposts(),
    var views: Views = Views(),
    var post_type: String = "",
    var post_source: PostSource? = null,
    var attachments: Array<Attachment>? = null,
    var geo: Geo? = null,
    var signer_id: Int = 1,
    var copy_history: Array<Post>? = null,
    var can_pin: Boolean = true,
    var can_delete: Boolean = true,
    var can_edit: Boolean = true,
    var is_pinned: Boolean = true,
    var marked_as_ads: Boolean = true,
    var is_favorite: Boolean = true,
    var donut: Donut = Donut(),
    var postponed_id: Int = 0
) {
    constructor(id: Int, attachments: Array<Attachment>?) : this(id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (owner_id != other.owner_id) return false
        if (from_id != other.from_id) return false
        if (created_by != other.created_by) return false
        if (date != other.date) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + owner_id
        result = 31 * result + from_id
        result = 31 * result + created_by
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        return result
    }
}

data class Geo(
    var type: String,
    var coordinates: String,
    var place: Place
)

data class Place(
    var id: Int,
    var title: String,
    var latitude: Int,
    var longitude: Int,
    var created: Int,
    var icon: String,
    var checkins: Int,
    var updated: Int,
    var type: Int,
    var country: Int,
    var city: Int,
    var address: String
)

data class PostSource(
    var type: String,
    var platform: String,
    var data: String,
    var url: String
)

data class Donut(
    var is_donut: Boolean = false,
    var paid_duration: Int = 0,
    var placeholder: Boolean = false,
    var can_publish_free_copy: Boolean = false,
    var edit_mode: String = arrayOf("all", "duration").toString()
)

data class Views(
    var count: Int = 1
)

data class Reposts(
    var count: Int = 0,
    var user_reposted: Boolean = true
)

data class Likes(
    var count: Int = 0,
    var user_likes: Boolean = true,
    var can_like: Boolean = true,
    var can_publish: Boolean = true,
)

data class Copyright(
    var id: Int = 1,
    var link: String = "",
    var name: String = "",
    var type: String = ""
)

data class Comments(
    var count: Int = 1,
    var can_post: Boolean = true,
    var groups_can_post: Boolean = true,
    var can_close: Boolean = false,
    var can_open: Boolean = false,
)