class Solution {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        // 1. words 를 maxWidth 기준으로 나눠서 Sentence 객체를 생성
        val sentences: List<Sentence> = splitWordsByMaxWidth(words, maxWidth)

        // 2. 1번에서 생성한 Sentence 객체를 활용하여, 정렬작업을 수행
        return justify(sentences)
    }

    private fun splitWordsByMaxWidth(words: Array<String>, maxWidth: Int): List<Sentence> {
        val sentences = mutableListOf<Sentence>()

        var lengthOfCurrentSentence = 0
        var previousIndex = 0

        for ((index, word) in words.withIndex()) {

            // 길이가 이번 sentence 에 포함할 수 있는 경우
            if (lengthOfCurrentSentence + word.length <= maxWidth) {
                lengthOfCurrentSentence += word.length + 1
            } else {
                val currentWords = words.sliceArray(IntRange(previousIndex, index - 1))
                sentences.add(Sentence(words = currentWords, maxWidth = maxWidth))

                previousIndex = index
                lengthOfCurrentSentence = word.length + 1
            }
        }

        val lastWords = words.sliceArray(IntRange(previousIndex, words.lastIndex))
        sentences.add(Sentence(words = lastWords, maxWidth = maxWidth))

        return sentences
    }

    private fun justify(sentences: List<Sentence>): List<String> {
        return sentences.mapIndexed { index, sentence ->

            if (sentence.countOfWords == 1 || index == sentences.lastIndex) {
                sentence.leftJustify()
            } else {
                sentence.fullJustify()
            }
        }
    }

    class Sentence(
        private val words: Array<String>,
        private val maxWidth: Int,
        private val lengthOfSentenceWithoutBlank: Int,
        val countOfWords: Int
    ) {
        constructor(words: Array<String>, maxWidth: Int): this(words, maxWidth, words.map { it.length }.sum(), words.size)

        private val totalLengthOfBlank = maxWidth - lengthOfSentenceWithoutBlank
        private val countOfBlank = (countOfWords - 1).coerceAtLeast(1)

        private val blankSizeBetweenWords = totalLengthOfBlank / countOfBlank
        private val remainBlankSize = totalLengthOfBlank % countOfBlank


        fun leftJustify(): String {
            return words.joinToString(separator = " ").plus(BLANK_ARRAY[totalLengthOfBlank - (countOfWords - 1)])
        }

        fun fullJustify(): String {
            val stringBuilder = StringBuilder()

            for (index in 0 until words.lastIndex) {
                stringBuilder
                    .append(words[index])
                    .append(BLANK_ARRAY[blankSizeBetweenWords + if (remainBlankSize > index) 1 else 0])
            }

            stringBuilder.append(words.last())

            return stringBuilder.toString()
        }
    }

    companion object {
        private const val MAX_LENGTH = 100
        private val BLANK_ARRAY = Array(MAX_LENGTH) { i -> " ".repeat(i) }
    }
}



fun main() {
    println(Solution().fullJustify(arrayOf("This", "is", "an", "example", "of", "text", "justification."), 16))
    println(Solution().fullJustify(arrayOf("What","must","be","acknowledgment","shall","be"), maxWidth = 16))
    println(Solution().fullJustify(arrayOf("Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"), maxWidth = 20))
}