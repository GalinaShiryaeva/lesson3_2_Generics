data class Report(
    val ownerId: Int = 0,
    val commentId: Int = 0,
    val reason: Int = 0
)

enum class ReportReason(val reason: Int) {
    SPAM(0),
    CHILD_PORNOGRAPHY(1) {
        override fun getReason() = "Детская порнография"
    },
    EXTREMISM(2) {
        override fun getReason() = "Экстремизм"
    },
    VIOLENCE(3) {
        override fun getReason() = "Насилие"
    },
    DRUG_PROPAGANDA(4) {
        override fun getReason() = "Пропаганда наркотиков"
    },
    ADULT_MATERIAL(5) {
        override fun getReason() = "Материал для взрослых"
    },
    INSULT(6) {
        override fun getReason() = "Оскорбление"
    },
    CALLS_FOR_SUICIDE(7) {
        override fun getReason() = "Призывы к суициду"
    };

    open fun getReason() = "Спам"
}