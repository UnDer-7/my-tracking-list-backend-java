package com.cade.core.utils;

import com.cade.core.exception.CoreException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class Assert {

    public <T extends CoreException> void thatIsTrue(final Boolean value, final T exception) {
        thatIsNotNull(value, exception);
        if (Boolean.FALSE.equals(value)) throw exception;
    }

    public <T extends CoreException> void thatIsFalse(final Boolean value, final T exception) {
        thatIsNotNull(value, exception);
        if (Boolean.TRUE.equals(value)) throw exception;
    }

    public <T extends CoreException> void thatIsNull(final Object value, final T exception) {
        if (Objects.nonNull(value)) throw exception;
    }

    public <T extends CoreException> void thatIsNotNull(final Object value, final T exception) {
        if (Objects.isNull(value)) throw exception;
    }

}
