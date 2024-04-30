package eu.radvan.papirfly.article.controller

import eu.radvan.papirfly.article.service.CreateArticleService
import eu.radvan.papirfly.article.service.GetArticleBySequenceIdService
import eu.radvan.papirfly.article.service.SearchArticlesService
import eu.radvan.papirfly.article.service.command.toCommand
import eu.radvan.papirfly.article.service.query.GetArticleBySequenceIdQuery
import eu.radvan.papirfly.article.service.query.SearchArticlesQuery
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/articles")
class ArticleController(
    private val createArticleService: CreateArticleService,
    private val getArticleBySequenceIdService: GetArticleBySequenceIdService,
    private val searchArticlesService: SearchArticlesService,
) {

    @PostMapping(consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK) // HttpStatus.CREATED could be used
    fun createArticle(
        @Valid
        @RequestBody
        request: CreateArticleRequest,
    ): ArticleResponse = createArticleService(request.toCommand())

    @GetMapping("/{articleId}", produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun getArticle(
        @PathVariable articleId: Long
    ): ArticleResponse = getArticleBySequenceIdService(
        GetArticleBySequenceIdQuery(
            articleSequenceId = articleId
        )
    )

    @GetMapping(produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    fun searchArticles(
        @RequestParam("name", required = false) name: String?,
        @RequestParam("description", required = false) description: String?,
    ): List<ArticleResponse> = searchArticlesService(
        SearchArticlesQuery(
            name = name,
            description = description
        )
    )

}
