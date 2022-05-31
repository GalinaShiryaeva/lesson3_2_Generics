import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun testAdd() {
        // arrange
        var post = Post(1)

        // act
        WallService.add(post)

        // assert
        assertTrue("id не должен быть равен 0", post.id > 0)
    }

    @Test
    fun testUpdate_ExistingId() {
        // arrange
        var post = Post(1)
        var isUpdated = false

        // act
        WallService.add(post)
        isUpdated = WallService.update(Post(1))

        // assert
        assertTrue("Результат функции update должен быть = false", isUpdated)
    }

    @Test
    fun testUpdate_NotExistingId() {
        // arrange
        var post = Post(1)
        var isUpdated = true

        // act
        WallService.add(post)
        isUpdated = WallService.update(Post(10))

        // assert
        assertFalse("Результат функции update должен быть = true", isUpdated)
    }
}