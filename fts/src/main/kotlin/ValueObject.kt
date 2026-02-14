package ru.it_arch.kddd.fts

public sealed interface ValueObject : Fts {

    /** For `data class` */
    public interface Data : ValueObject, Validatable

    /** For `value class` */
    public interface Value<BOXED : Any> : ValueObject, Validatable {
        public val boxed: BOXED

        /**
         * Копирование объекта с новым значением.
         *
         * Имеет тот же смысл, что и метод `copy()` у `data class`. Обусловлен необходимостью создовать объект на
         * уровне абстракции, чтобы иметь возможность писать логику, еще до генерации имплементации.
         * */
        public fun <T : Value<BOXED>> copy(boxed: BOXED): T
    }

    /** For `enum class`, `sealed interface` */
    public interface Adt : ValueObject
}
