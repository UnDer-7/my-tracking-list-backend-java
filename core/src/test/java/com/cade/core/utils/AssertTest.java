package com.cade.core.utils;

import com.cade.core.domain.ErrorMessages;
import com.cade.core.exception.InternalServerErrorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class AssertTest {

    @Nested
    @DisplayName("Tests for method thatIsTrue")
    class ThatIsTrue {

        @Test
        void thatIsTrue_should_not_fail() {
            assertThatCode(() -> Assert.thatIsTrue(true, new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR)))
                .doesNotThrowAnyException();
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(booleans = false)
        void thatIsTrue_should_fail(final Boolean value) {
            final var expectedException = new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR);

            assertThatExceptionOfType(expectedException.getClass())
                .isThrownBy(() -> Assert.thatIsTrue(value, expectedException))
                .withMessage(ErrorMessages.UNEXPECTED_ERROR.getExceptionMessage());
        }

    }

    @Nested
    @DisplayName("Tests for method thatIsFalse")
    class ThatIsFalse {

        @Test
        void thatIsFalse_should_not_fail() {
            assertThatCode(() -> Assert.thatIsFalse(false, new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR)))
                .doesNotThrowAnyException();
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(booleans = true)
        void thatIsFalse_should_fail(final Boolean value) {
            final var expectedException = new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR);

            assertThatExceptionOfType(expectedException.getClass())
                .isThrownBy(() -> Assert.thatIsFalse(value, expectedException))
                .withMessage(ErrorMessages.UNEXPECTED_ERROR.getExceptionMessage());
        }

    }

    @Nested
    @DisplayName("Tests for method thatIsNull")
    class ThatIsNull {

        @Test
        void thatIsNull_should_not_fail() {
            assertThatCode(() -> Assert.thatIsNull(null, new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR)))
                .doesNotThrowAnyException();
        }

        @Test
        void thatIsNull_should_fail() {
            final var expectedException = new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR);

            assertThatExceptionOfType(expectedException.getClass())
                .isThrownBy(() -> Assert.thatIsNull("mock", expectedException))
                .withMessage(ErrorMessages.UNEXPECTED_ERROR.getExceptionMessage());
        }

    }

    @Nested
    @DisplayName("Tests for method thatIsNotNull")
    class ThatIsNotNull {

        @Test
        void thatIsNotNull_should_not_fail() {
            assertThatCode(() -> Assert.thatIsNotNull("abc", new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR)))
                .doesNotThrowAnyException();
        }

        @Test
        void thatIsNotNull_should_fail() {
            final var expectedException = new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR);

            assertThatExceptionOfType(expectedException.getClass())
                .isThrownBy(() -> Assert.thatIsNotNull(null, expectedException))
                .withMessage(ErrorMessages.UNEXPECTED_ERROR.getExceptionMessage());
        }

    }

    @Nested
    @DisplayName("Tests for method thatStatusCodeIsSuccess")
    class ThatStatusCodeIsSuccess {

        @ParameterizedTest
        @ValueSource(ints = { 200, 201, 203, 204, 205, 206, 299 })
        void thatStatusCodeIsSuccess_should_not_fail(final int statusCodes) {
            assertThatCode(() -> Assert.thatStatusCodeIsSuccess(statusCodes, new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR)))
                .doesNotThrowAnyException();
        }

        @NullSource
        @ParameterizedTest
        @ValueSource(ints = { 100, 101, 103, 199, 300, 301, 302, 400, 401, 405, 422, 500, 501, 503, 504 })
        void thatIsNotNull_should_fail(final Integer statusCode) {
            final var expectedException = new InternalServerErrorException(ErrorMessages.UNEXPECTED_ERROR);

            assertThatExceptionOfType(expectedException.getClass())
                .isThrownBy(() -> Assert.thatStatusCodeIsSuccess(statusCode, expectedException))
                .withMessage(ErrorMessages.UNEXPECTED_ERROR.getExceptionMessage());
        }

    }

}
