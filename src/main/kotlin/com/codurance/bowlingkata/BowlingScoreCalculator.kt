package com.codurance.bowlingkata

private const val SPARE = '/'
private const val MISS = '-'
private const val STRIKE = 'X'
private const val NORMAL_FRAMES: Int = 18

fun scoreFor(allRolls: String): Int =
    allRolls.foldIndexed(0) { idx, score, roll ->
        score + score(roll) - spareDiff(allRolls, roll, idx) + bonus(allRolls, roll, idx)
    }

private fun bonus(allRolls: String, roll: Char, idx: Int): Int {
    if (isLastFrame(allRolls, idx)) return 0

    val next = score(allRolls.next(idx))
    return when (roll) {
        SPARE -> next
        STRIKE -> {
            val nextButOne = allRolls.next(idx + 1)
            next + score(nextButOne) - spareDiff(allRolls, nextButOne, idx + 2)
        }
        else -> 0
    }
}

private fun isLastFrame(allRolls: String, idx: Int) =
    NORMAL_FRAMES <= allRolls.substring(0, idx)
        .sumBy { roll -> if (roll == STRIKE) 2 else 1 }

private fun String.next(idx: Int) =
    if (idx < length - 1) this[idx + 1] else MISS

private fun spareDiff(allRolls: String, roll: Char, idx: Int) =
    if (roll == SPARE) score(allRolls.previous(idx)) else 0

private fun String.previous(idx: Int) =
    if (idx > 0) this[idx - 1] else MISS

private fun score(roll: Char) =
    when (roll) {
        in '1'..'9' -> String(charArrayOf(roll)).toInt()
        SPARE, STRIKE -> 10
        else -> 0
    }
