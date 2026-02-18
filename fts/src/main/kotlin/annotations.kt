package ru.it_arch.kddd.fts

/**
 * Определяет параметры генерируемой имплементации для [ValueObject] и [Entity]
 *
 * @property implementationName имя генерируемой имплементации. Переопределяет опцию KSP.
 * @property dsl вкл/выкл генерацию DSL. По умолчанию — `true`
 * @property json вкл/выкл генерацию JSON. По умолчанию — `false`
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class Generatable(
    val implementationName: String = "",
    val dsl: Boolean = true,
    val json: Boolean = false,
)

/**
 * Определяет методы сериализации и параметры генерируемой имплементации для [ValueObject.Value] общих типов ([java.io.File], [java.net.URI], [java.util.UUID], и т.п.)
 *
 * @property serialization метод сериализации имплементации. По умолчанию — `toString()`
 * @property deserialization способ создания имплементации. Имя статического метода. По умолчанию — через конструктор класса
 * @property useStringInDsl `true` — в DSL-билдере реализация будет создаваться из строки. `false` — будет использоваться непосредственно
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class Parsable(
    val serialization: String = "toString()",
    val deserialization: String = "",
    val useStringInDsl: Boolean = false,
)

/**
 * Определяет явное имя поля для сериализации.
 * Аналог kotlinx.serialization.SerialName.
 *
 * @property value имя поля.
 * */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class SerialName(val value: String)

/**
 * Определяет имя поля в билдере.
 *
 * @property value имя поля.
 * */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class BuilderName(val value: String)

/**
 * Игнорировать этот тип
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class Ignore

/**
 * Учитывать, но не создавать имплементацию
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class ImplementLater


/**
 * Определяет нейтральный элемент типа. Генерируется значение по умолчанию `DEFAULT`.
 * Для [ValueObject.Value] задаются параметры [name], [value] из которых будет сгенерировано
 * нейтральное свойство.
 * Для [ValueObject.Data] задается параметр [name]. Нейтральное свойство генерируется, если все
 * свойства [ValueObject.Data] имеют нейтральные свойства.
 * Для `data object` внутри `sealed` параметры не используются и не указываются.
 *
 * @property name имя свойства для нейтрального значения
 * @property value строковое представление нейтрального значения
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class Neutral(
    val name: String = "DEFAULT",
    val value: String = ""
)

/**
 * Грязный хак для генерации аннотации `@OptIn(ExperimentalSerializationApi::class)`.
 * Проблема в том, что штатным путем с помощью _KotlinPoet_ ее не вставишь из-за ограничения использования. Написать:
 * ```
 * AnnotationSpec.builder(OptIn::class)
 * ```
 * не получится.
 *
 * После кодогенерации в результирующем файле
 * `import ru.it_arch.kddd.OptIn` подменяется на `import kotlinx.serialization.ExperimentalSerializationApi`
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
public annotation class OptIn(val markerClass: String)
