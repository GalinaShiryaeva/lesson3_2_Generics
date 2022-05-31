import Exceptions.CommentNotFoundException
import Exceptions.PostNotFoundException
import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        // arrange
        WallService.add(Post(76))

        // act
        WallService.createComment(Comment(0, 3, 222))
    }

    @Test
    fun shouldNotThrow() {
        // arrange
        WallService.add(Post(76))

        // act
        val comment1 = WallService.createComment(Comment(0, 3, 76))

        // assert
        assertTrue("Несуществующий id поста!", comment1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_Exception_WrongOwnerId() {
        // arrange
        WallService.add(Post(26))
        WallService.createComment(Comment(23, 21, 26))

        // act
        WallService.reportComment(20, 23, 3)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_Exception_WrongCommentId() {
        // arrange
        WallService.add(Post(26))
        WallService.createComment(Comment(23, 21, 26))

        // act
        WallService.reportComment(21, 20, 3)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportComment_Exception_InvalidReason() {
        // arrange
        WallService.add(Post(26))
        WallService.createComment(Comment(23, 21, 26))

        // act
        WallService.reportComment(21, 23, 9)
    }
}