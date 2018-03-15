package util;

import org.springframework.data.domain.Page;

import com.crossover.techtrial.dto.ArticlesDTO;
import com.crossover.techtrial.dto.CommentsDTO;
import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.model.Comment;

public final class MappingUtil {
	
	public static ArticlesDTO mapToArticlesDto(Page<Article> page)
	{
		ArticlesDTO articlesDto = new ArticlesDTO();
		articlesDto.setArticles(page.getContent());
		articlesDto.setPageCount(page.getTotalPages());
		articlesDto.setPageNumber(page.getNumber());
		articlesDto.setPageSize(page.getSize());
		return articlesDto;
	}
	
	public static CommentsDTO maptoCommentsDto(Page<Comment> page)
	{
		CommentsDTO commentsDto = new CommentsDTO();
		commentsDto.setComments(page.getContent());
		commentsDto.setPageCount(page.getTotalPages());
		commentsDto.setPageNumber(page.getNumber());
		commentsDto.setPageSize(page.getSize());
		return commentsDto;
	}

}
