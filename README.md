# SpringBoot Kotlin App - A Demo Kotlin App with Spring Boot

![](https://raw.githubusercontent.com/NEDONION/my-pics-space/main/20230516193018.png)

## Requisites
- Kotlin
- Spring Boot
- Maven
- H2 Database
- JPA
- Mustache (for UI)
- Junit 5 

## Get Started

- Clone the repository, and install Maven Dependencies
- http://localhost:8090


## Documentation
### 1. Creating a New Project

Go to File | New | Project, and select Spring Initializr.

Follow the steps of the wizard to use the following parameters:

- Artifact: "blog"
- Type: "Gradle - Kotlin" or "Maven"
- Language: Kotlin
- Name: "Blog"
- Dependencies: "Spring Web Starter", "Mustache", "Spring Data JPA", "H2 Database" and "Spring Boot DevTools"

### 2. Creating your own extensions

Instead of using util classes with abstract methods like in Java, it is usual in Kotlin to provide such functionalities via Kotlin extensions. Here we are going to add a format() function to the existing LocalDateTime type in order to generate text with the English date format.

`src/main/kotlin/com/jiacheng/demo/utils/Extensions.kt`

```kotlin
fun LocalDateTime.format(): String = this.format(englishDateFormatter)

private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }

private val englishDateFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd")
    .appendLiteral(" ")
    .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
    .appendLiteral(" ")
    .appendPattern("yyyy")
    .toFormatter(Locale.ENGLISH)

private fun getOrdinal(n: Int) = when {
  n in 11..13 -> "${n}th"
  n % 10 == 1 -> "${n}st"
  n % 10 == 2 -> "${n}nd"
  n % 10 == 3 -> "${n}rd"
  else -> "${n}th"
}

fun String.toSlug() = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
```

### 3. Implementing the blog engine

We update the "blog" Mustache templates. 使用 Mustache 构建 UI 页面。
- `src/main/resources/templates/blog.mustache`
- `src/main/resources/templates/article.mustache`

We update the `HtmlController` in order to render blog and article pages with the formatted date. `ArticleRepository` and `MarkdownConverter` constructor parameters will be automatically autowired since `HtmlController` has a single constructor (implicit `@Autowired`).

Then, we add data initialization to a new `DemoConfiguration` class. 构造数据初始化的配置文件。
`src/main/kotlin/com/jiacheng/demo/config/DemoConfiguration.kt`


### 4. Testing with JUnit 5

For the sake of this example, let’s create an integration test in order to demonstrate various features:

- We use real sentences between backticks instead of camel-case to provide expressive test function names.
- JUnit 5 allows to inject constructor and method parameters, which is a good fit with Kotlin read-only and non-nullable properties.
- This code leverages `getForObject` and `getForEntity` Kotlin extensions (you need to import them).

`src/test/kotlin/com/jiacheng/demo/IntegrationTests.kt`

## References
- https://github.com/spring-guides/tut-spring-boot-kotlin
