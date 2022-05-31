import Exceptions.CommentNotFoundException
import Exceptions.PostNotFoundException

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reportComments = emptyArray<Report>()

    @Throws(PostNotFoundException::class)
    fun createComment(comment: Comment): Boolean {
        //var isPresentPost = false
        for (post in posts) {
            if (post.id == comment.postId) {
                comments += comment
                //isPresentPost = true
                return true
            }
        }
        //if (!isPresentPost) {
        throw PostNotFoundException("Пост не найден")
        //}
        //return isPresentPost
    }

    fun reportComment(ownerId: Int, commentId: Int, reason: Int): Boolean {
        var isPresentReason = false
        var reasonText = ""
        for (rsn in ReportReason.values()) {
            if (rsn.reason == reason) {
                isPresentReason = true
                reasonText = rsn.getReason()
            }
        }
        if (!isPresentReason) {
            throw CommentNotFoundException("Несуществующая причина жалобы")
        }
        for (comment in comments) {
            if (comment.commentId == commentId) {
                if (comment.ownerId == ownerId) {
                    reportComments += Report(ownerId, commentId, reason)
                    println("Комментарий [id = $commentId, ownerId = $ownerId] получил жалобу по причине '$reasonText'")
                    return true
                } else throw CommentNotFoundException("Комментарий с данным 'ownerId' не найден")
            } else throw CommentNotFoundException("Комментарий с данным 'commentId' не найден")
        }
        return false
    }

    fun findPostById(id: Int): Post? {
        if (posts.isNotEmpty()) {
            for (post in posts) {
                if (post.id == id) {
                    return post
                }
            }
        }
        return null.also {
            println("Пост не найден")
        }
    }

    fun isFoundPostById(id: Int): Boolean {
        if (posts.isNotEmpty()) {
            for (post in posts) {
                if (post.id == id) {
                    return true
                }
            }
        }
        return false
    }

    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (p in posts) {
            if (post.id == p.id) {
                p.owner_id = 3
                p.from_id = 5
                // и т.д. по свойствам
                return true
            }
        }
        return false
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }
    }


}