package com.codurance.bowlingkata

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BowlingScoreCalculatorShould {

    @Test fun `calculate score for pins`() {
        assertThat(scoreFor("0"), `is`(0))
        assertThat(scoreFor("1"), `is`(1))
        assertThat(scoreFor("2"), `is`(2))
        assertThat(scoreFor("23"), `is`(5))
    }

    @Test fun `calculate score for missed shot`() {
        assertThat(scoreFor("-"), `is`(0))
        assertThat(scoreFor("1-"), `is`(1))
    }

    @Test fun `calculate score for spares`() {
        assertThat(scoreFor("3/-"), `is`(10))
        assertThat(scoreFor("7/7/7/7/7/7/7/7/7/-"), `is`(146))
    }

    @Test fun `calculate score for strikes`() {
        assertThat(scoreFor("X"), `is`(10))
        assertThat(scoreFor("X52"), `is`(24))
    }

    @Test fun `calculate score for spares and strikes`() {
        assertThat(scoreFor("1/X1/2"), `is`(54))
    }

    @Test fun `calculate score for last frame`() {
        assertThat(scoreFor("1/35XXX458/X3/XX6"), `is`(189))
        assertThat(scoreFor("XXXXXXXXXXXX"), `is`(300))
    }

}
