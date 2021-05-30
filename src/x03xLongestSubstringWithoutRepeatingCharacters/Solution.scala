package x03xLongestSubstringWithoutRepeatingCharacters

// Link: [LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

object Solution {
  def main(args: Array[String]): Unit = {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("pwwkew"))
    println(lengthOfLongestSubstring(""))
  }
  def lengthOfLongestSubstring(s: String): Int = {
    var existed_chars: Map[String, Int] = Map()
    var non_repeated: List[String] = List()
    var longest_non_repeated = 0
    // 使用 s.sliding(1) 将string转化为iterator，再转换成list
    // <String>.sliding(<int>) 每次迭代取多少个元素
    // println(s.sliding(2).toList)
    val splitted_chars: List[String] = s.sliding(1).toList

    for ((char, char_pos) <- splitted_chars.zipWithIndex){
      // 遍历每个字符

      if (existed_chars.getOrElse(char, -1) == -1) {
        // 未碰撞到已存在字符
        non_repeated = non_repeated :+ char
        existed_chars += (char -> char_pos)
        if (longest_non_repeated < non_repeated.length) {
          longest_non_repeated = non_repeated.length
        }
      } else {
        // 碰撞到存在字符
        val hit_char_pos: Int = existed_chars.getOrElse(char, -1)
        // 不记录之前字符的位置
        for ((k, v) <- existed_chars) {
          if (v < hit_char_pos) {
            existed_chars += (k -> -1)
          }
        }
        // 去掉这个字符以及前面的内容
        // 要重复字符的后面一个(hit_char_pos + 1)到当前字符(char_pos + 1)
        non_repeated = splitted_chars.slice(hit_char_pos + 1, char_pos + 1)
        existed_chars += (char -> char_pos)

        if (longest_non_repeated < non_repeated.length) {
          longest_non_repeated = non_repeated.length
        }
      }
    }

    longest_non_repeated
  }
}
